package main

import (
    "bufio"
    "fmt"
    "os"
    "strings"

    "github.com/skip2/go-qrcode"
)

func main() {
    reader := bufio.NewReader(os.Stdin)
    fmt.Print("Enter the website URL: ")
    url, _ := reader.ReadString('\n')
    url = strings.TrimSpace(url)

    // Generate QR code and save as an image file
    err := qrcode.WriteFile(url, qrcode.Medium, 256, "qrcode.png")
    if err != nil {
        fmt.Println("Error generating QR code:", err)
        return
    }

    fmt.Println("QR code generated and saved as qrcode.png")
}