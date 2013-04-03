require 'set'

class Fixnum
  def divisors
    if @divisors
      return @divisors
    end
    @divisors = Set.new [1, self]
    i = 2
    sqrt = Math.sqrt(self).floor
    while i <= sqrt
      if self % i == 0
        @divisors += [i, self / i]
        sub_divisors = (self / i).divisors
        @divisors += sub_divisors.map { |x| x * i }.to_set + sub_divisors
        break
      end
      i += 1
    end
    @divisors
  end
end

i = 1
n = 0

while n.divisors.length <= 500
  n += i
  i += 1
end

puts n
