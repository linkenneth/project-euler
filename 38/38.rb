require 'set'

pandigitals = []

(1..10 ** 5).each do |n|
  digits = n.to_s.chars
  next if digits.include?('0')
  next if digits != digits.uniq

  i = 2
  while digits.length < 9 do
    digits += (n * i).to_s.chars
    break if digits.length >= 9
    break if digits.include?('0')
    break if digits != digits.uniq
    i += 1
  end

  if (digits.length == 9) and
    (not digits.include?('0')) and
    (digits == digits.uniq)
    pandigitals << digits.join.to_i
  end
end

p pandigitals.max
