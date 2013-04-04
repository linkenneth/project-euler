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

sum = 0
i = 2
while i < 10000
  b = i.divisors.clone.delete(i).reduce(:+)
  if b.divisors.clone.delete(b).reduce(:+) == i and b != i
    puts "#{b}, #{i} are amicable!"
    sum += i
  end
  i += 1
end
puts sum
