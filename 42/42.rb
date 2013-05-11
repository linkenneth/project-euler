words = File.read("words.txt").split(",").map! do |x|
  x.tr_s! '"', ""
end

triangles = (1..100).map { |n| n * (n + 1) / 2 }  # at most 100 such nums

words.select! do |word|
  score = word.split("").map! do |c|
    c.ord - 'A'.ord + 1
  end.reduce(:+)
  triangles.include? score
end

puts words.length
