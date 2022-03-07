package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
	"strconv"
)

func scanInput() [20][20]int {
	var numbers [20][20]int

	file, err := os.Open("11.in")
	if err != nil {
		panic("error reading 11.in")
	}
	scanner := bufio.NewScanner(file)
	scanner.Split(bufio.ScanWords)
	for i := 0; scanner.Scan(); i++ {
		n, err := strconv.Atoi(scanner.Text())
		if err != nil {
			panic(fmt.Errorf("error reading number: %w", err))
		}
		numbers[i/20][i%20] = n
	}
	return numbers
}

func max(a int, b int, c int, d int) int {
	all := []int{a, b, c, d}
	sort.Ints(all)
	return all[3]
}

func main() {
	numbers := scanInput()

	maxHorizontal := make(chan int)
	maxVertical := make(chan int)
	maxLDiagonal := make(chan int)
	maxRDiagonal := make(chan int)

	// horizontal
	go func() {
		max := 0
		for r := 0; r < 20; r++ {
			for c := 0; c < 16; c++ {
				product := 1
				for i := 0; i < 4; i++ {
					product *= numbers[r][c+i]
				}
				if product > max {
					max = product
				}
			}
		}
		maxHorizontal <- max
	}()

	// vertical
	go func() {
		max := 0
		for r := 0; r < 16; r++ {
			for c := 0; c < 20; c++ {
				product := 1
				for i := 0; i < 4; i++ {
					product *= numbers[r+i][c]
				}
				if product > max {
					max = product
				}
			}
		}
		maxVertical <- max
	}()

	// left diagonal
	go func() {
		max := 0
		for r := 0; r < 16; r++ {
			for c := 0; c < 16; c++ {
				product := 1
				for i := 0; i < 4; i++ {
					product *= numbers[r+i][c+i]
				}
				if product > max {
					max = product
				}
			}
		}
		maxLDiagonal <- max
	}()

	// right diagonal
	go func() {
		max := 0
		for r := 0; r < 16; r++ {
			for c := 4; c < 20; c++ {
				product := 1
				for i := 0; i < 4; i++ {
					product *= numbers[r+i][c-i]
				}
				if product > max {
					max = product
				}
			}
		}
		maxRDiagonal <- max
	}()

	fmt.Println(max(<-maxHorizontal, <-maxVertical, <-maxLDiagonal, <-maxRDiagonal))
}
