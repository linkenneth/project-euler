; TODO: (1) improve re-use (2) use agents and process in parallel for practice

(require '[clojure.string :as str])

(def grid
  (->>
    (slurp "11.in")
    (str/split-lines)
    (map #(str/split % #" "))
    (mapv (fn [row] (mapv #(Integer/parseInt %) row)))))

(def dim (count grid))

(def horizontals
  (->>
    (for [r (range dim)
          c (range (- dim 3))]
      [r c])
    (partition 4 1)))

(def verticals
  (->>
    (for [r (range (- dim 3))
          c (range dim)]
      [r c])
    (partition 4 1)))

(def left-diagonals
  (for [r (range (- dim 3))
        c (range (- dim 3))]
    (->> [r c]
      (iterate (fn [[r c]] [(inc r) (inc c)]))
      (take 4))
    ))

(def right-diagonals
  (for [r (range (- dim 3))
        c (range 3 dim)]
    (->> [r c]
      (iterate (fn [[r c]] [(inc r) (dec c)]))
      (take 4))
  ))

(apply max
  (map
    (fn [seq]
      (->> seq
        (map #(get-in grid %))
        (reduce *)))
    (concat horizontals verticals left-diagonals right-diagonals)))
