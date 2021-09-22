(defn prime?
  "Probabilistically determine primality with the Miller-Rabin test."
  ([n] (prime? n 10))
  ([n certainty] (.isProbablePrime (biginteger n) certainty)))

(def primes
  (concat [2 3 5 7] (filter prime? (iterate inc 11))))

(println (apply + (take-while (partial > 2000000) primes)))
