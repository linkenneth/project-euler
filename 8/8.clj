(require '[clojure.string :as str])

(def n
  (->>
    (-> (slurp "8.in") (str/split #"\s?"))
    (remove empty?)
    (map #(Integer/parseInt %))))

(->>
  (partition 13 1 n)
  (map #(reduce * %))
  (apply max))
