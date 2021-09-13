(->> (iterate (fn [[i a b]] [(inc i) b (+ a b)]) [1 0N 1N])
     (drop-while (fn [[i a b]] (< (count (str b)) 1000)))
     first
     first)
