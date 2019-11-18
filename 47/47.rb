require 'prime'

last = nil

656.step.each_cons(4) do |a, b, c, d|
  next if last and last > a
  distinct_count = [a, b, c, d].map { |x| Prime.prime_division(x).length }
  if distinct_count.all? { |d| d == 4 }
    puts a
    exit
  else
    cons = distinct_count.reverse_each.with_index.take_while { |x, i| x == 4 }
    last = (not cons.empty?) && (a + 3 - cons.length)
  end
end
