(defn diagonals
  ([] (concat [1] (diagonals 3 2)))
  ([start increment]
   (lazy-seq (concat (take 4 (iterate (partial + increment) start))
                     (diagonals (+ start (* 4 increment) 2) (+ increment 2))))))

;; in a 1001-by-1001 spiral, there are (1001 - 1) / 2 * 4 + 1 items
(apply + (take 2001 (diagonals)))
