#!/bin/python3

from functools import reduce

def factorial(n):
    if n == 1:
        return 1
    return n * factorial(n - 1)

print(reduce(lambda x, y: x + y, [int(s) for s in list(str(factorial(100)))]))
