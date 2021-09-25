(require '[clojure.math.numeric-tower :as math])

(defn count-digits [n] (count (str (bigint n))))

;; only possible between 1-20-ish
(->> (range 30)
     (mapcat (fn [n]
               (->> (range 1 1e6)
                    (drop-while (fn [x]
                                  (< (count-digits (math/expt x n)) n)))
                    (take-while (fn [x]
                                  (= (count-digits (math/expt x n)) n))))))
     count)
