i = 1
inc = 2
four = 0
sum = 0
$ONE_THOUSAND_AND_ONE_SQD = 1001 * 1001
while i <= $ONE_THOUSAND_AND_ONE_SQD
  sum += i
  i += inc
  four += 1
  if four % 4 == 0
    four = 0
    inc += 2
  end
end

puts sum
