(defn factorial [n] (reduce * (range 1N (inc n))))

(defn digits [n] (map #(Character/digit % 10) (str n)))

(apply + (digits (factorial 100)))
