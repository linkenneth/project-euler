require 'set'

triangles = Enumerator.new do |yielder|
  n = 1
  loop do
    yielder << n * (n + 1) / 2
    n += 1
  end
end

pentagonals = Enumerator.new do |yielder|
  n = 1
  loop do
    yielder << n * (3 * n - 1) / 2
    n += 1
  end
end

hexagonals = Enumerator.new do |yielder|
  n = 1
  loop do
    yielder << n * (2 * n - 1)
    n += 1
  end
end

loop do
  t, p, h = triangles.peek, pentagonals.peek, hexagonals.peek
  if t == p and p == h
    triangles.next and next if t == 1 or t == 40755
    puts t
    exit
  end
  m = [t, p, h].min
  case m
  when t
    triangles.next
  when p
    pentagonals.next
  when h
    hexagonals.next
  end
end
