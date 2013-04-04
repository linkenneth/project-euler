class Fixnum
  def collatz
    @collatz ||= 1 + (self.odd? ? 3 * self + 1 : self / 2).collatz
  end
end
1.instance_variable_set('@collatz', 1)

max = 1
i = 1000000 / 3
max_number = 1
while i < 1000000
  c = i.collatz
  if c > max
    max_number = i
    max = c
  end
  i += 2
end

puts max_number
