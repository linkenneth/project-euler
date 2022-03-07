package main

import (
	"fmt"
)

func sumOfSquares(n int) int {
	sum := 0
	for i := 1; i <= n; i++ {
		sum += i * i
	}
	return sum
}

func squareOfSums(n int) int {
	sum := 0
	for i := 1; i <= n; i++ {
		sum += i
	}
	return sum * sum
}

func main() {
	fmt.Println(squareOfSums(100) - sumOfSquares(100))
}
