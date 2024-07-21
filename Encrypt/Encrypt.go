package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

/*
 * Encrypts the text based on the encryption key.
*/
func Encrypt(encryptionKey byte) string {
	// assist result efficiently
	var builder strings.Builder
	fmt.Print("Enter text to encrypt:\n")

	// receive user input to encrypt
	reader := bufio.NewReader(os.Stdin)
	text, _ := reader.ReadString('\n')
	text = strings.ToLower(text)

	// use a basic ASCII shift to encrypt
	for i := 0; i < len(text); i++ {
		builder.WriteString(string(text[i] + encryptionKey))
	}
	return builder.String()
}

/*
 * Decrypts the text based on the encryption key.
 */
func Decrypt(encryptionKey byte) string {
	// assist result efficiently
	var builder strings.Builder
	fmt.Print("Enter text to decrypt:\n")

	// receive user input to encrypt
	reader := bufio.NewReader(os.Stdin)
	text, _ := reader.ReadString('\n')
	text = strings.ToLower(text)

	// reverse encryption
	for i := 0; i < len(text); i++ {
		builder.WriteString(string(text[i] - encryptionKey))
	}
	return builder.String()
}

/*
 * Runs the program, allowing the user to choose whether to encrypt or decrypt.
 * Also requires the user to enter the encryption key.
 */
func main() {
	// ask user if they want to encrypt or decrypt
	var input string
	fmt.Print("Encrypt or Decrypt?\n")
	fmt.Scanln(&input)
	input = strings.ToLower(input)

	// ask for key
	var key byte
	fmt.Print("Enter encryption key:\n")
	fmt.Scanln(&key)

	// proceed based on user input
	switch input {
	case "encrypt":
		fmt.Println(Encrypt(key))
	case "en":
		fmt.Println(Encrypt(key))
	case "e":
		fmt.Println(Encrypt(key))
	case "decrypt":
		fmt.Println(Decrypt(key))
	case "de":
		fmt.Println(Decrypt(key))
	case "d":
		fmt.Println(Decrypt(key))
	default:
		fmt.Println("Invalid input. Please try again.")
	}
}