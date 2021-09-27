(require '[clojure.string :as str])
(require '[clojure.spec.alpha :as spec])
(require '[expound.alpha :as expound])

(set! spec/*explain-out* expound/printer)

;; --- card functions --

(def ^:const values
  {\1 1 \2 2 \3 3 \4 4 \5 5 \6 6 \7 7
   \8 8 \9 9 \T 10 \J 11 \Q 12 \K 13 \A 14})

(defn value [card] (values (first card)))
(defn suit [card] (second card))

(defn highest-card [cards] (apply max-key value cards))

;; --- hand functions --

(defn standard-sort [cards]
  (let [freqs (frequencies (map value cards))]
    (sort-by (comp (juxt (comp - freqs) -) value) cards)))

(defn straight? [cards]
  (let [vals (set (map value cards))]
    (or (= vals #{14 2 3 4 5})
        (= vals (set (take 5 (iterate dec (value (highest-card cards)))))))))

(defn flush? [cards]
  (apply = (map suit cards)))

(defn n-of-a-kind? [cards n]
  (some (fn [[_ count]] (= count n)) (frequencies (map value cards))))

(defn full-house? [cards]
  (and (n-of-a-kind? cards 3) (n-of-a-kind? cards 2)))

(defn two-pair? [cards]
  (let [freqs (frequencies (map value cards))]
    (= 2 (count ((group-by second freqs) 2)))))

;; TODO: use this to try out spec clojure, and also testing in clojure
(defn hand
  "cards is a set of 5 cards"
  [cards]
  (let [sorted-cards (vec (standard-sort cards))]
    (cond
      (and (flush? cards) (straight? cards)) [:straight-flush sorted-cards]
      (n-of-a-kind? cards 4) [:four-of-a-kind sorted-cards]
      (full-house? cards) [:full-house sorted-cards]
      (flush? cards) [:flush sorted-cards]
      (straight? cards) [:straight sorted-cards]
      (n-of-a-kind? cards 3) [:three-of-a-kind sorted-cards]
      (two-pair? cards) [:two-pair sorted-cards]
      (n-of-a-kind? cards 2) [:pair sorted-cards]
      :else [:high-card sorted-cards])))

(def hand-ranks
  (reduce-kv #(assoc %1 %3 %2) {}
             [:high-card :pair :two-pair
              :three-of-a-kind :straight :flush
              :full-house :four-of-a-kind :straight-flush]))

(defn compare-hand [hand-1 hand-2]
  (println "Comparing " hand-1 hand-2)
  (let [[rank-1 cards-1] hand-1
        [rank-2 cards-2] hand-2]
    (prn
      [(hand-ranks rank-1) (mapv value cards-1)]
      [(hand-ranks rank-2) (mapv value cards-2)])
    (compare
      [(hand-ranks rank-1) (mapv value cards-1)]
      [(hand-ranks rank-2) (mapv value cards-2)])))

;; -- manual testing --

(def a-four-of-a-kind #{"5S" "5H" "5C" "5D" "TD"})
(def a-three-of-a-kind #{"5S" "5H" "5C" "3D" "TD"})
(def a-two-pair #{"5S" "5H" "3H" "3D" "TD"})
(def a-pair #{"5S" "5H" "JH" "3D" "TD"})
(def a-straight-flush #{"TD" "JD" "QD" "KD" "AD"})
(def a-straight #{"AD" "2H" "3D" "4D" "5C"})
(def a-flush #{"6H" "7H" "8H" "2H" "AH"})
(def a-high-card #{"5S" "AH" "JC" "2D" "TD"})

;; -- parsing --

(defn ^:private line->game [line]
  (mapv set (partition 5 (str/split line #" "))))

(def games (->> (slurp "poker.txt")
                str/split-lines
                (map line->game)))

(defn main- []
  (let [games (->> (slurp "poker.txt")
                   str/split-lines
                   (map line->game))]
    (->> games
         (map (partial map hand))
         (filter #(> (apply compare-hand %) 0))
         count)))

(main-)
