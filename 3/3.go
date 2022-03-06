package main

import (
	"fmt"
)

func getPrimes(x int) []int {
	var primes []int
	for i := 3; i * i < x; i += 2 {
		for x % i == 0 {
			primes = append(primes, i)
			x /= i
		}
	}
	primes = append(primes, x)
	return primes
}

func main() {
	fmt.Println(getPrimes(600851475143))
}
