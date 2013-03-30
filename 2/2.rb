# 33 < 4 mil
(fibs = Hash.new { |h, k| h[k] = k <= 2 ? 1 : h[k-1] + h[k-2] })[33]
puts fibs.values.select { |x| x.even? }.reduce :+
