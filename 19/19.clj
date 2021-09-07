(defn divisible? [x d] (zero? (mod x d)))

(defn leap-year?
  [year]
  (and (divisible? year 4)
       (or (not (divisible? year 100))
           (divisible? year 400))))

(def days-in-month [31 28 31 30 31 30 31 31 30 31 30 31])

(defn days-in-year-month [year month]
  (if (and (= month 1) (leap-year? year))
    29
    (days-in-month month)))

(def sunday? zero?)

(let [all-days-in-month (for [year (range 1901 2001) month (range 12)]
                          (days-in-year-month year month))]
  (reduce (fn [[sunday-count current-day] days]
            [(if (sunday? current-day)
               (inc sunday-count)
               sunday-count)
             (mod (+ current-day days) 7)])
          [0 2] ; (sunday-count current-day) (0 is sunday)
          all-days-in-month))
