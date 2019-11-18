require 'set'

$PRIMES = [2, 3, 5, 7, 11, 13, 17]
def divisible?(a)
  a.map(&:to_s).each_cons(3).with_index.all? do |s, i|
    s.join.to_i % $PRIMES[i] == 0
  end
end

pandigital_partials = (0..9).to_a.permutation(9)
valid_partials = pandigital_partials.filter { |a| divisible?(a) }

$NUMBERS = Set[*(0..9)]

pandigital_numbers = valid_partials.map do |a|
  missing_digits = $NUMBERS - a
  raise if missing_digits.length != 1
  (missing_digits + a).map(&:to_s).join.to_i
end

p pandigital_numbers.sum
