package ReverseInteger

import (
    "strconv"
)

func reverse(x int) int {
	result := ""

	// Check two edge cases
	if x == 0 {
		return 0
	} else if x < 0 {
		x = -x
		result += "-"
	}

	// Parse the integer
	parsed := strconv.Itoa(x)
	
	// Reverse the integer
	for i := len(parsed) - 1; i >= 0; i-- {
		result += string(parsed[i])
	}

	// Convert back to integer
	sol, err := strconv.ParseInt(result, 10, 32)
	if err != nil {
		return 0
	} else {
		return int(sol)
	}
}