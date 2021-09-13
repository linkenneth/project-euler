;; We can just brute-force this? 1000 * 1000 * up to 80 (but mostly fewer)

(defn prime?
  "Probabilistically determine primality with the Miller-Rabin test."
  ([n] (prime? n 10))
  ([n certainty] (.isProbablePrime (biginteger n) certainty)))
(def prime? (memoize prime?))

(defn quadratic [n a b]
  (+ (* n n) (* a n) b))

;; non-parallel version
;; "Elapsed time: 2707.730968 msecs"
(time (->> (map (fn [[a b]] [a b (count (take-while #(prime? (quadratic % a b)) (range)))])
                (for [a (range -999 1000) b (range -1000 1001)] [a b]))
           (apply max-key last)))

;; parallel version but naive, slower
;; "Elapsed time: 12583.987893 msecs"
(time (->> (pmap (fn [[a b]] [a b (count (take-while #(prime? (quadratic % a b)) (range)))])
                 (for [a (range -999 1000) b (range -1000 1001)] [a b]))
           (apply max-key last)))

;; much better
;;"Elapsed time: 751.135795 msecs"
(time (->> (pmap (fn [b]
                   (->> (map (fn [a] [a b (count (take-while #(prime? (quadratic % a b)) (range)))]) (range -999 1000))
                        (apply max-key last)))
                 (range -1000 1001))
           (apply max-key last)))
