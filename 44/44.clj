(defn pentagonal [^long n] (/ (* n (- (* 3 n) 1)) 2))

; (def pentagonals (map pentagonal (iterate inc 1)))
;
; (def pentagonal?
;   (let [pentagonal-seq (volatile! pentagonals)
;         pentagonal-set (volatile! (sorted-set))]
;     (fn [p]
;       (while (> p (or (first (rseq @pentagonal-set)) 0))
;         (vswap! pentagonal-set conj (first @pentagonal-seq))
;         (vswap! pentagonal-seq rest))
;       (contains? @pentagonal-set p))))

(defn fast-pentagonal? [^long x]
  (let [n (-> x
              (* 24)
              (+ 1)
              Math/sqrt)]
    (== (mod n 6) 5)))

;; to be honest, I don't know how this minimizes D. Somehow it just works out
;; that this returns with the minimal value of D
(loop [k 2 j 1]
  (let [pj (pentagonal j)
        pk (pentagonal k)]
    (cond
      (and (fast-pentagonal? (+ pk pj))
           (fast-pentagonal? (- pk pj))) [(- pk pj) pj pk]
      (= j 1) (recur (inc k) k)
      :else (recur k (dec j)))))

; (defn main- []
;   (set! *warn-on-reflection* true)
;   (let [^java.util.PriorityQueue queue (java.util.PriorityQueue. [[4 1 2]])]
;         ; ^java.util.HashSet seen (java.util.HashSet.)
;         ; add (fn [item] (.add queue item) (.add seen item))]
;     (loop [i 0]
;       (let [[D j k] (.poll queue)
;             pj (pentagonal j)
;             pk (pentagonal k)]
;         (when (zero? (mod i 1e4)) (println i D j k pj pk))
;         ;; TODO:
;         (if (and (fast-pentagonal? (+ pj pk))
;                  (fast-pentagonal? (- pk pj)))
;           [D pj pk]
;           (do
;             (when (> j 1)
;               (.add queue [(- pk (pentagonal (dec j))) (dec j) k]))
;             (when (= (- k j) 1)
;               (.add queue [(- (pentagonal (inc k)) pk) k (inc k)]))
;             (recur (inc i))))))))
