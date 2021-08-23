(defn divisible-up-to? [n]
  (fn [x]
    (every? #(zero? (rem x (inc %))) (range n))))

(->>
    (iterate #(+ 2520 %) 2520)
    (filter (divisible-up-to? 20))
    first)
