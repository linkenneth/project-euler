// Approach: Sieve of Eratosthenes with boolean[]
// TODO: try this with BitSet

package main

import (
	"fmt"
)

func Sieve(max int) []int {
	var a []bool
	var out []int

	a = make([]bool, max)
	for i := 2; i < max; i++ {
		if a[i] {
			continue
		}
		for j := 1; i * j < max; j++ {
			a[i * j] = true
		}
		out = append(out, i)
	}
	return out
}

func main() {
	primes := Sieve(150000)
	fmt.Println(primes[10000])
}
