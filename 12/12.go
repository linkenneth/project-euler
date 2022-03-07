package main

import (
	"fmt"
	"math"
)

func divisors(n int) []int {
	var divisors []int
	limit := int(math.Sqrt(float64(n)))
	for d := 1; d <= limit; d++ {
		if n % d == 0 {
			divisors = append(divisors, d)
			if d != n / d {
				divisors = append(divisors, n / d)
			}
		}
	}

	// fmt.Printf("%v: %v\n", n, divisors)
	return divisors
}

func main() {
	n := 1
	i := 1
	for len(divisors(n)) < 500 {
		i += 1
		n += i
	}
	fmt.Println(n)
}
