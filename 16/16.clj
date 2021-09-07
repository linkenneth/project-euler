(def number (.pow (biginteger 2) 1000))

(->> (str number)
    (map #(Character/digit % 10))
    (reduce +))
