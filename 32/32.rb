require 'set'

PANDIGITAL = ('1'..'9').to_set

pandigitals = Set.new

(10 ** 5).downto(2).each do |a|
  a_digits = a.to_s.chars
  next if a_digits.length != a_digits.uniq.length

  2.upto(10 ** 5).each do |b|
    b_digits = b.to_s.chars
    next if b_digits.length != b_digits.uniq.length

    c = a * b
    # p a, b, c
    digits = a.to_s.chars + b.to_s.chars + c.to_s.chars
    if digits.length > 9
      break
    elsif digits.to_set == PANDIGITAL
      puts "#{a} * #{b} == #{c}"
      pandigitals << c
    end
  end
end

puts pandigitals.sum
