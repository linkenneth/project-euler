a = 0

while true
  b = a + 1
  c = 1000 - b - a
  while b < c
    if a**2 + b**2 == c**2
      puts a * b * c
      abort
    end
    b += 1
    c -= 1
  end
  a += 1
end
