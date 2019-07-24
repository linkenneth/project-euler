# NOTE: I don't think this works yet. Also, I suspect this kind of search
# solution will have to hold in memory O(2^N) paths in memory, for a triangle
# or tree of depth N. Better to DP for O(N^2).

require 'algorithms'

test_tri = <<-eos
3
7 4
2 4 6
8 5 9 3
eos

tri = <<-eos
75
95 64
17 47 82
18 35 87 10
20 04 82 47 65
19 01 23 75 03 34
88 02 77 73 07 63 67
99 65 04 28 06 16 70 92
41 41 26 56 83 40 80 70 33
41 48 72 33 47 32 37 16 94 29
53 71 44 65 25 43 91 52 97 51 14
70 11 33 28 77 73 17 78 39 68 17 57
91 71 52 38 17 14 91 43 58 50 27 29 48
63 66 04 68 89 53 67 30 73 16 69 87 40 31
04 62 98 27 23 09 70 98 73 93 38 53 60 04 23
eos

tri = tri.split("\n").map! { |row| row.split.map! &:to_i }
test_tri = test_tri.split("\n").map! { |row| row.split.map! &:to_i }

def astar(tri, goal)
  fringe = Containers::PriorityQueue.new { |x, y| (x <=> y) == -1 }
  fringe.push([0, 0, 0], 0)  # [sum, row, x]
  while not fringe.empty?
    sum, row, x = fringe.pop
    if row == goal
      return sum
    end
    puts "row: #{row}, x: #{x}"
    if row + 1 == goal
      sum += tri[row][x]
      fringe.push([sum, row + 1, x], sum)
      next
    end
    h1 = tri[row + 1][x]
    h2 = tri[row + 1][x + 1]
    puts "searching @ #{tri[row][x]} with sum #{sum}"
    sum += tri[row][x]
    fringe.push([sum, row + 1, x], sum + h1)
    fringe.push([sum, row + 1, x + 1], sum + h2)
  end
  puts "wtf, fringe empty?"
end
