require 'prime'
require 'set'

truncatables = Set.new
sieve = Prime::EratosthenesGenerator.new
primes = Set.new

while truncatables.length < 11 do
  prime = sieve.next.to_s
  primes << prime

  next if prime.length <= 1

  isTruncatable = true

  # ltr
  prime.length.times do |i|
    if not primes.include? prime[i, prime.length + 1]
      isTruncatable = false
    end
  end

  next if not isTruncatable

  # rtl
  prime.length.times do |i|
    if not primes.include? prime[0, i + 1]
      isTruncatable = false
    end
  end

  if isTruncatable
    truncatables << prime
  end
end

puts truncatables.map(&:to_i).sum
