(require '[clojure.string :as str])

(def numbers
  (->> (slurp "13.in")
       (str/split-lines)
       (map bigint)))

(-> (reduce + numbers)
    str
    (subs 0 10)
    println)
