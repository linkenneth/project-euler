(set! *warn-on-reflection* true)

(defn n-choose-k [^long n ^long k]
; user=> (time (dotimes [i 1e6] (n-choose-k 5 3)))
; "Elapsed time: 117.744536 msecs"
  (let [n-k (- n k)]
    (loop [acc 1
           numer n
           denom 1]
      (cond
        ; *' autopromotes
        (< k numer) (recur (*' acc numer) (dec numer) denom)
        (<= denom n-k) (recur (/ acc denom) numer (inc denom))
        :else acc))))

(def zip (partial map vector))

(time (->> (range 1 101)
           (mapcat #(zip (repeat %) (range 1 (inc %))))
           (partition-all (inc (/ 5050 4)))
           (pmap (fn [group] ;; pmap is 60ms, map is 120ms. more significant at 1 <= n <= 400
                   (->> group
                        (filter (fn [[n k]] (> (n-choose-k n k) 1e6)))
                        count)))
           (apply +)))
