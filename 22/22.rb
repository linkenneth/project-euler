total = 0
names = File.read("names.txt").split(",").map! do |x|
  x.tr_s! '"', ""
end.sort!.each_with_index do |x, i|
  total += x.split("").map! do |c|
    c.ord - 'A'.ord + 1
  end.reduce(:+) * (i + 1)
end

puts total
