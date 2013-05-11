i = 1
str = ""
while str.length < 1000000
  str << i.to_s
  i += 1
end

d = [1, 10, 100, 1000, 10000, 100000, 1000000].map! do |i|
  str[i-1].to_i
end.reduce(:*)

puts d
