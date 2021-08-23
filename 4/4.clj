(defn is-palindrome?
  [s]
  (loop [i 0]
    (def len (count s))
    (cond
      (>= i (/ len 2)) true
      (= (nth s i) (nth s (- len i 1))) (recur (inc i))
      :else false)))

(def three-digits (range 999 99 -1))
(apply max (for [x three-digits y three-digits
                 :let [product (* x y)]
                 :when (is-palindrome? (str product))]
             product))
