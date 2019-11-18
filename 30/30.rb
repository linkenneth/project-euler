A = (10..1000000).select do |n|
  n.to_s.chars.map(&:to_i).map do |i|
    i ** 5
  end.sum == n
end
puts A.sum
