A = (2..100).map do |a|
  (2..100).map do |b|
    a ** b
  end
end
puts A.flatten!.uniq!.length
