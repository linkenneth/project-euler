class Fixnum
  def palindrome?
    self.to_s.reverse! == self.to_s
  end
end

nums = 100..999
arr = []

nums.each do |x|
  nums.each do |y|
    s = x * y
    arr.push s if s.palindrome?
  end
end

puts arr.max
