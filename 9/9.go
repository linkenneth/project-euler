package main

import (
	"fmt"
)

func isPythagorean(a int, b int, c int) bool {
	return a*a+b*b == c*c
}

func main() {
	for a := 1; a < 1000; a++ {
		for b := a; b < 1000; b++ {
			c := 1000 - a - b
			if isPythagorean(a, b, c) {
				fmt.Println(a * b * c)
				return
			}
			if b > c {
				break
			}
		}
	}
}
