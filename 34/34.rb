$cache = { 0 => 1, 1 => 1 }
def factorial(n)
  if not $cache.include? n
    $cache[n] = n * factorial(n - 1)
  end
  return $cache[n]
end

A = (10..10 ** 7).filter do |n|
  n.to_s.chars.map(&:to_i).map { |i| factorial(i) }.sum == n
end
p A.sum
