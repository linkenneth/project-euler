(require '[clojure.string :as str])

(def triangle
  (->> (slurp "triangle.txt")
      str/split-lines
      (mapv (fn [line] (->> (str/split line #" ")
                            (mapv #(Integer/parseInt %)))))))
(apply max
       (reduce (fn [totals row]
                 (vec (map #(+ (max %1 %2) %3)
                           (concat [0] totals)
                           (concat totals [0])
                           row)))
               []
               triangle))
