require 'prime'

primes = Prime.each(10 ** 6).to_a

max = 0
maxPrime = nil
primes.reverse_each do |prime|
  i = 0
  j = 0
  sum = primes[i]
  loop do
    if sum == prime
      break
    elsif sum < prime
      j += 1
      sum += primes[j]
    elsif sum > prime
      sum -= primes[i]
      i += 1
      break if j - i + 1 < max  # later sequences cannot possibly be larger
    end
  end

  if j - i + 1 > max and primes[i..j].sum == prime
    max = j - i + 1
    maxPrime = prime
    puts "New max prime: #{maxPrime} with #{max} elements: #{primes[i..j]}"
  end
end
puts maxPrime
