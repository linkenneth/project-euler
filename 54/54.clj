(require '[clojure.string :as str])

;; --- card functions --

(def ^:const values
  {\1 1 \2 2 \3 3 \4 4 \5 5 \6 6 \7 7
   \8 8 \9 9 \T 10 \J 11 \Q 12 \K 13 \A 14})

(defn value [card] (values (first card)))
(defn suit [card] (second card))

(defn highest-card [cards] (apply max-key value cards))

;; --- hand functions --

;; TODO: function returns the sorted 5-card hand with rank
;; idea: "parse" the cards
(def sort-by-value (partial sort-by (comp - value)))

(defn get-straight [cards]
  (when (= (set (map value cards))
           (set (take 5 (iterate dec (value (highest-card cards))))))
    (sort-by-value cards)))

; (defn straight? [cards]
;   (= (set (map value cards))
;      (set (take 5 (iterate dec (value (highest-card cards)))))))

(defn get-flush [cards]
  (when (apply = (map suit cards))
    (sort-by-value cards)))

; (defn flush? [cards]
;   (apply = (map suit cards)))

(defn sort-by-freq [freqs cards]
  (partial sort-by (comp (juxt (comp - freqs) -) value) cards))

(defn n-of-a-kind [cards n]
  (first (filter (fn [[_ count]] (= count n)) (frequencies (map value cards)))))

; (defn get-four-of-a-kind [cards]
;   (when-let [[val _] (n-of-a-kind cards 4)]
;     (sort-by #(not= val (value %)) cards)))
(defn get-four-of-a-kind [cards]
  (let [freqs (frequencies (map value cards))]
    (sort-by #(not= val (value %)) cards)))

(def thp #{"5S" "5H" "5C" "3D" "TD"})

(defn get-three-of-a-kind [cards]
  (when-let [[val _] (n-of-a-kind cards 3)]
    (sort-by #(not= val (value %)) cards)))

(def tp #{"5S" "5H" "3H" "3D" "TD"})

(defn get-two-pair [cards]
  (let [freqs (frequencies (map value cards))]
    (when (= 2 (count (filter (fn [[_ count]] (= count 2)) freqs)))
      (sort-by-freq freqs cards))))


(defn get-pair [cards]
  (when-let [[val _] (n-of-a-kind cards 2)]
    (sort-by #(not= val (value %)) cards)))

(def get-high-card sort-by-value)

;; TODO: use this to try out spec clojure
(defn hand
  "cards is a set of 5 cards"
  [cards]
  (cond
    (and (flush? cards) (straight? cards))
    [:straight-flush [(highest-card cards)]]

    (four-of-a-kind? cards) [:four-of-a-kind 4]))

(defn play [cards-1 cards-2]
  ;; TODO: compare hands and determine winner
  :player-1)
  ; (let [hand-1 (hand cards-1)
  ;       hand-2 (hand cards-2)]))

(defn ^:private line->game [line]
  (vec (partition 5 (str/split line #" "))))

(defn main- []
  (let [games (->> (slurp "poker.txt")
                   str/split-lines
                   (map line->game))]
    (filter (fn [[cards-1 cards-2]]
              (= (play cards-1 cards-2) :player-1))
            games)))
