package main

import (
	"fmt"
)

// from 7.go
func Sieve(max int) []int {
	var a []bool
	var out []int

	a = make([]bool, max)
	for i := 2; i < max; i++ {
		if a[i] {
			continue
		}
		for j := 1; i*j < max; j++ {
			a[i*j] = true
		}
		out = append(out, i)
	}
	return out
}

func main() {
	sum := 0
	for _, p := range Sieve(2_000_000) {
		sum += p
	}
	fmt.Println(sum)
}
