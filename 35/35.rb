require 'set'
require 'prime'

primes = Prime.each(10 ** 6).to_set
circular = Set.new

primes.each do |p|
  if circular.include? p
    next
  end

  s = p.to_s.chars
  isCircular = s.all? do |unused|
    primes.include? s.rotate!.join.to_i
  end

  if isCircular
    s.length.times do |i|
      circular << s.rotate!.join.to_i
    end
  end
end

p circular.length
