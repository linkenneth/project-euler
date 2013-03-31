n = 600851475143

class Fixnum
  def prime_factors
    return [] if self == 1
    factor = (2..self).find { |x| self % x == 0 }
    [factor] + (self / factor).prime_factors
  end
end

puts 600851475143.prime_factors.max
