(require '[clojure.string :as str])

(def triangle
  (->> (slurp "triangle.in")
      str/split-lines
      (mapv (fn [line] (->> (str/split line #" ")
                            (mapv #(Integer/parseInt %)))))))

;; Dynamic programming.
;;
;; Let D(r, c) = maximum total to get to (r, c).
;;
;; D(r, c) = max(D(r-1, c-1), D(r-1, c)) + value(r, c)
;;
;; I guess formally that's what it is but in practice you can just repeatedly
;; reduce the list

;; semi-declarative version 1. there's probably a reduce in here
(loop [totals []
       row (first triangle)
       rst (rest triangle)]
  (if (empty? row)
    (apply max totals)
    (recur (vec (map-indexed #(+ %2 (max (get totals (dec %1) 0)
                                         (get totals %1 0)))
                             row))
           (first rst)
           (rest rst))))

;; reduce v1
(apply max
       (reduce (fn [totals row]
                 (vec (map-indexed #(+ %2 (max (get totals (dec %1) 0)
                                        (get totals %1 0)))
                                   row)))
               []
               triangle))

;; functional v2
(apply max
       (reduce (fn [totals row]
                 (vec (map #(+ (max %1 %2) %3)
                           (concat [0] totals)
                           (concat totals [0])
                           row)))
               []
               triangle))
