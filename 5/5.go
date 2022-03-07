package main

import (
	"fmt"
)

func isDivisible(n int) bool {
	for i := 1; i <= 20; i++ {
		if n%i != 0 {
			return false
		}
	}
	return true
}

func main() {
	inc := 2 * 3 * 5 * 7 * 11 * 13 * 17
	for n := inc; true; n += inc {
		if isDivisible(n) {
			fmt.Println(n)
			return
		}
	}
}
