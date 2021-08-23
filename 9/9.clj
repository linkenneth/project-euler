(defn square [x] (Math/pow x 2))
(defn is-pythagorean?
  [a b c]
  (= (+ (square a) (square b)) (square c)))

(println
  (loop [a 1 b 1]
    (let [c (- 1000 a b)]
    (println a b c)
    (cond
      (is-pythagorean? a b c)
      (* a b c)
      (not (<= a b c))
      (recur (inc a) (inc a))
      :else
      (recur a (inc b))))))
