package main

import (
	"fmt"
	"strings"
	"os"
)

// TODO: clean up, use scanners
func main() {
	var digits string
	data, err := os.ReadFile("8.in")
	if err != nil {
		panic("unable to read file 8.in")
	}
	digits = strings.ReplaceAll(string(data), "\n", "")
	fmt.Println(digits)

	max := 0
	window := 0
	product := 1
	for i, digit := range digits {
		if digit == '0' {
			window = 0
			product = 1
		} else if window == 13 {
			product *= int(digit - '0')
			product /= int(digits[i - 13] - '0')
		} else {
			window += 1
			product *= int(digit - '0')
		}
		if product > max {
			max = product
		}
		fmt.Println("window", digits[i - window + 1:i + 1])
		fmt.Println("product", product)
	}
	fmt.Println(max)
}
