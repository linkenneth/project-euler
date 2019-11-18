require 'set'

pentagonals = Enumerator.new do |yielder|
  n = 1
  loop do
    yielder << n * (3 * n - 1) / 2
    n += 1
  end
end

pentagonals_set = SortedSet.new
pentagonals.each do |p0|
  pentagonals_set.reverse_each do |p1|
    sum, diff = p0 + p1, p0 - p1
    if pentagonals_set.include?(sum) and pentagonals_set.include?(diff)
      p p0, p1
      exit
    end
  end
  pentagonals_set << p0
end
