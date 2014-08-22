# PROGRESS

if __name__ == '__main__':
    fibs = [1, 1]
    last = 0
    s = 0
    while last < 4000000:
        last = fibs[-1] + fibs[-2]
        fibs.append(last)
        if last % 2 == 0:
            s += last
    print s
