#!/bin/python3

DAYS_IN_MONTH = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ]

def isLeapMonth(year, month):
    if month != 2:
        return False
    return (
        year % 400 == 0 or
        (year % 4 == 0 and year % 100 != 0)
    )

dayOfWeek = 2  # Monday = 1, Sunday = 0
sundays = 0
for year in range(1901, 2001):
    for month in range(1, 13):
        if year == 2000 and month == 12:
            continue  # skip because we don't care about the next year
        dayOfWeek = (
            dayOfWeek +
            DAYS_IN_MONTH[month - 1] +
            (1 if isLeapMonth(year, month) else 0)
        ) % 7
        if dayOfWeek == 0:
            sundays += 1

print(sundays)
