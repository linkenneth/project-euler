def isPalindrome(s)
  i = 0
  while i <= s.length / 2 do
    if s[i] != s[-i - 1]
      return false
    end
    i += 1
  end
  return true
end

puts ((1..10 ** 6).filter do |n|
  base2, base10 = n.to_s(2), n.to_s(10)
  [base2, base10].all? { |b| isPalindrome(b) }
end).sum
