package test;

import java.util.Scanner;

public class Factorial {
	// Recursively calls itself to calculate the Factorial of an input
	public static int calculateFactorial(int num) {
		if (num <= 0) {
			return 0;
		}
		else if (num == 1) {
			return 1;
		}
		else {
			return num * calculateFactorial(num - 1);	
		}
	} 
	public static void main(String[] args) {
		System.out.print("Enter a positve integer: ");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		System.out.println("Result: " + Factorial.calculateFactorial(num));
		sc.close();
	}
}
