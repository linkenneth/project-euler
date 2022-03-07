package main

import (
	"bufio"
	"fmt"
	"math/big"
	"os"
)

func scanInput() []big.Int {
	var numbers []big.Int
	file, err := os.Open("13.in")
	if err != nil {
		panic("could not read file")
	}
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		var n big.Int
		n.SetString(scanner.Text(), 10)
		numbers = append(numbers, n)
	}
	return numbers
}

func main() {
	numbers := scanInput()

	sum := big.NewInt(0)
	for _, n := range numbers {
		sum.Add(sum, &n)
	}
	fmt.Println(sum.String()[:10])
}
