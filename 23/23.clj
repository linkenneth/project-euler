(require '[clojure.set :as set])

(defn divisible? [n d] (zero? (mod n d)))

(defn prime?
  "Probabilistically determine primality with the Miller-Rabin test."
  ([n] (prime? n 10))
  ([n certainty] (.isProbablePrime (biginteger n) certainty)))
(def prime? (memoize prime?))

(def primes
  (concat [2 3 5 7] (filter prime? (iterate inc 11))))

(defn divisors
  "Primarily builds on the fact that
  divisors(n) = divisors(n / p) UNION divisors(p)."
  [n]
  (if (prime? n)
    #{1 n}
    (let [p (first (filter (partial divisible? n) primes))]
      (set/union #{n p}
                 (divisors (/ n p))
                 (set (map (partial * p) (divisors (/ n p))))))))

(defn proper-divisors [n] (disj (divisors n) n))
(def proper-divisors (memoize proper-divisors))

(defn abundant? [n] (< n (apply + (proper-divisors n))))
(def abundant? (memoize abundant?))

(let [abundant-nums (filter abundant? (range 12 28124))
      expressible-nums (set (mapcat (fn [x]
                                      (take-while (partial > 28124)
                                                  (map (partial + x) abundant-nums)))
                                    abundant-nums))]
  (apply + (remove expressible-nums (range 1 28124))))

;; this is actually slower. probably because later on it becomes too many small
;; tasks, not enough big tasks

; (let [abundant-nums (filter abundant? (range 12 28124))
;       expressible-nums (->> abundant-nums
;                             (pmap #(take-while (partial > 28124)
;                                                (map (partial + %) abundant-nums)))
;                             flatten
;                             set
;                             time)]
;   (apply + (remove expressible-nums (range 1 28124))))
