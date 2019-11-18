require 'prime'

9.downto(1) do |d|
  pandigitals = (1..d).to_a.permutation.sort.reverse!

  pandigitals.each do |a|
    if not [1, 3, 7, 9].include? a[-1]
      next
    end
    n = a.map(&:to_s).join.to_i
    if Prime.prime?(n)
      puts n
      break
    end
  end
end
