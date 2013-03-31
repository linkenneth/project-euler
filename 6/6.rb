sum_of_sqs = (1..100).map { |x| x ** 2 }.reduce :+
sq_of_sums = ((1..100).reduce :+) ** 2

puts sq_of_sums - sum_of_sqs
