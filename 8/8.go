package main

import (
	"bufio"
	"fmt"
	"os"
)

func scanInput() []uint8 {
	var digits []uint8
	file, err := os.Open("8.in")
	if err != nil {
		panic("unable to open file 8.in")
	}
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		for _, digit := range scanner.Text() {
			digits = append(digits, uint8(digit-'0'))
		}
	}
	if err = scanner.Err(); err != nil {
		panic(fmt.Errorf("scanner error: %w", err))
	}
	return digits
}

func main() {
	digits := scanInput()

	var window int
	var max uint64
	var product uint64 = 1

	for i, digit := range digits {
		if digit == 0 {
			window = 0
			product = 1
		} else if window == 13 {
			product *= uint64(digit)
			product /= uint64(digits[i-13])
		} else {
			window += 1
			product *= uint64(digit)
		}
		if product > max {
			max = product
		}
	}
	fmt.Println(max)
}
