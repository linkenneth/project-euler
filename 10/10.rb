require 'prime'

puts Prime.take_while { |x| x < 2000000 }.reduce :+
