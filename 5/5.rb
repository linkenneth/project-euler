require 'prime'

min_factors = Hash.new
(1..10).each do |x|
  factors = x.prime_division
  # beauty - makes array => hash
  min_factors.merge! Hash[*factors.flatten] do |key, old_val, new_val|
    [old_val, new_val].max
  end
end

total = 1
min_factors.each_pair do |k, v|
  total *= k ** v
end

puts total
