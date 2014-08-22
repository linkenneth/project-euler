require 'prime'

four_digit_primes = Prime.take_while do |x|
  x < 10000
end - (1...1000).to_a

results = []

while not four_digit_primes.empty?
  x = four_digit_primes[0]
  y = x + 3330
  z = y + 3330
  permutations = x.to_s.split("").to_a.permutation.map do |x|
    x.join.to_i
  end.uniq
  primes_intersect = four_digit_primes - [x, y, z]
  perms_intersect = permutations - [x, y, z]
  len = permutations.length - perms_intersect.length
  if four_digit_primes.length - primes_intersect.length == 3 and
      permutations.length - perms_intersect.length == 3
    results += [x, y, z]
  end
  four_digit_primes = primes_intersect
end

puts results.last(3).join
