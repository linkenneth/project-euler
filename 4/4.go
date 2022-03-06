package main

import (
	"fmt"
	"strconv"
)

func isPalindrome(s string) bool {
	i, j := 0, len(s) - 1
	for i < j {
		if s[i] != s[j] {
			return false
		}
		i++
		j--
	}
	return true
}

func main() {
	max := 0
	for i := 100; i < 999; i++ {
		for j := 100; j < 999; j++ {
			product := i * j
			if isPalindrome(strconv.Itoa(product)) {
				if product > max {
					max = product
				}
			}
		}
	}
	fmt.Println(max)
}
