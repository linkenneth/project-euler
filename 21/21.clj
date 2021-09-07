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

(defn proper-divisors [n]
  (disj (divisors n) n))
(def proper-divisors (memoize proper-divisors))

(let [divisor-sums (->> (range 2 10000)
                        (map (juxt identity #(apply + (proper-divisors %))))
                        (into {}))]
  (->> (range 2 10000)
       (filter (fn [x]
                 (and (not= x (divisor-sums x))
                      (= x ((comp divisor-sums divisor-sums) x)))))
       (apply +)))
