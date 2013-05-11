require 'prime'
require 'set'

primes = Prime.take(1000).to_set;
squares = (0..100).map { |x| 2 * x * x }.to_set;

gb = primes.map do |p|
  squares.map do |s|
    p + s
  end
end.flatten.to_set;

p (((2..10000).to_set - gb) - primes).select { |x| x % 2 == 1 }.first
