# CORRECT

if __name__ == '__main__':
    multiples = [m for m in xrange(1000) if m % 3 == 0 or m % 5 == 0]
    print sum(multiples)
