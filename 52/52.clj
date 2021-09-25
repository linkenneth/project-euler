(require '[clojure.math.combinatorics :as combo])

(defn permuted-multiple? [n]
  (let [digits (set (str n))]
    (every? #(= digits (set (str (* n %)))) (range 6 1 -1))))

(defn possible-xs [seq-length]
  ;; only way for 6x to have the same digits is if first number is 1
  (map #(Integer/parseInt (apply str \1 %))
       (combo/permuted-combinations "023456789" (dec seq-length))))

(time (->> (range 2 10)
           (mapcat possible-xs)
           sort
           (filter permuted-multiple?)
           first))
