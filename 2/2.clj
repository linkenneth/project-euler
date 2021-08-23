(defn fibonacci
  ([] (fibonacci 1 1))
  ([a b] (lazy-seq (cons a (fibonacci b (+ a b)))))
  )

(println
  (reduce +
    (filter even? (take-while (partial > 4000000) (fibonacci)))
    )
  )
