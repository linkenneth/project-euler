S = (2..100).map do |a|
  (2..100).map do |b|
    (a ** b).to_s.chars.map(&:to_i).sum
  end.max
end.max
puts S
