(require '[clojure.string :as str])

(defn alpha-value [name]
  (reduce + (map #(- (int %) (int \@)) name)))

(let [names (-> (slurp "names.txt")
                (str/replace #"\"" "")
                str/trim
                (str/split #","))]
  (reduce-kv (fn [sum idx name]
               (+ sum (* (inc idx) (alpha-value name))))
             0
             (vec (sort names))))
