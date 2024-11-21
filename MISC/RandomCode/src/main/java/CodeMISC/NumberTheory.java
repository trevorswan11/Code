package CodeMISC;

import java.util.Scanner;

public class NumberTheory {
    /**
     * Checks to see if am input is prime or not
     * @param num an int input to check
     * @return true if input is prime, false otherwise
     */
    public static boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    /**
     * Prints all primes from a lower to an upper bound input
     * @param limit a double for the upper bound to check
     */    
    public static void allPrimes(double min, double limit) {
        if (min <= 1 || min >= limit)
            System.out.print("Unsupported inputs!");
        else
            for (double i = min; i <= limit; i++)
                if (isPrime((int) i))
                    System.out.println((int) i);
    }

    public static void main(String[] args) {
        // Welcome message and scanner declaration
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the prime checker:");

        // Run while the user wants it to
        boolean run = true;
        while (run) {
            System.out.print("Please input a strictly positive int (0 to exit): ");
            int query = sc.nextInt();

            // Proceed differently based on input
            switch (query) {
                case 0:
                    run = false;
                    break;
            
                default:
                    System.out.println(isPrime(query));
                    break;
            }
            
        }
        System.out.println("Bye!");
        sc.close();
    }
}
