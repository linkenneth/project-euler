(def pow-5 (memoize #(do (int (Math/pow % 5)))))

(defn digits [n] (map #(Character/digit % 10) (str n)))

(time
  (doall
    (filter (fn [n]
              (= n (->> n digits (map pow-5) (apply +))))
            (range 10 1e6))))
