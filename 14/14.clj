;; This will re-do a lot of work for intermediate nodes as only a single change
;; is made to the hash for each n. eh whatever
(def collatz
  (loop [n 1 collatz (transient {1 0})]
    (if (>= n 1000000)
      (persistent! collatz)
      (recur
        (inc n)
        (assoc! collatz n
          (loop [n n count 0]
            (if (contains? collatz n)
              (+ count (collatz n))
              (recur
                (if (even? n) (/ n 2) (inc (* 3 n)))
                (inc count)))
            )
          )
        )
      )
    )


(println (apply max-key val collatz))
