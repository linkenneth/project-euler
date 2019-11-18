# Generating Pythagorean triples:
# https://en.wikipedia.org/wiki/Pythagorean_triple

require 'set'

triples = Enumerator.new do |yielder|
  1.step do |n|
    break if n > 1000
    (n + 1).step do |m|
      a = m ** 2 - n ** 2
      b = 2 * m * n
      c = m ** 2 + n ** 2
      p0 = a + b + c
      break if p0 > 1000
      1.step do |k|
        p1 = k * p0
        break if p1 > 1000
        yielder << [k * a, k * b, k * c]
      end
    end
  end
end

solutions = Hash.new

triples.each do |a, b, c|
  p1 = a + b + c
  if not solutions.include? p1
    solutions[p1] = Set.new
  end
  solutions[p1] << Set[a, b, c]
end

puts solutions.max_by { |k, v| v.length }[0]
