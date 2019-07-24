#!/bin/python3

import io

triangle = open('triangle.txt').read()
triangle = [
    [ int(s) for s in row.split(' ') ]
        for row in triangle.strip().split('\n')
]
# print(triangle)

'''
Strategy: DP
Let L(i, j) denote the maximum sum possible in sub-triangle with apex i, j.
Then,
    L(i, j) = V(i, j) + max( L(i, j + 1), L(i + 1, j + 1) )
'''
L = {}
for j in reversed(range(len(triangle))):
    row = triangle[j]
    for i, value in enumerate(row):
        if (i, j + 1) not in L:
            L[(i, j)] = value
        else:
            L[(i, j)] = value + max( L[(i, j + 1)], L[(i + 1, j + 1)])

print(L[(0, 0)])
