package Solution

func longestCommonPrefix(strs []string) string {
    result := ""
	for i := 0; i < len(strs[0]); i++ {
		char := strs[0][i]
		for j := 1; j < len(strs); j++ {
			if i >= len(strs[j]) || strs[j][i] != char {
				return result
			}
		}
		result += string(char)
	}
	return result
}