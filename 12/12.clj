(defn divisors [n]
  (let [limit (int (Math/sqrt n))
        divisible? (fn [n d] (zero? (mod n d)))]
    (->> (range 1 (inc limit))
         (filter #(divisible? n %))
         (mapcat #(vector % (/ n %)))
         set)))

(defn triangle-numbers
  ([] (triangle-numbers 1 2))
  ([n i] (lazy-seq (cons n (triangle-numbers (+ n i) (inc i))))))

(->> (triangle-numbers)
     (filter (fn [n] (> (count (divisors n)) 500)))
     first
     (print))
