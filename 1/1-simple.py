sum = 0
for m in range(1000):
    if m % 3 == 0 or m % 5 == 0:
        sum += m

print(sum)
