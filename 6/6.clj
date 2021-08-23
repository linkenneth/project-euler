(defn square [x] (long (Math/pow x 2)))
(defn sum-of-squares [n]
  (->> (range n) (map inc) (reduce +) square))
(defn square-of-sums [n]
  (->> (range n) (map inc) (map square) (reduce +)))

(- (sum-of-squares 100) (square-of-sums 100))
