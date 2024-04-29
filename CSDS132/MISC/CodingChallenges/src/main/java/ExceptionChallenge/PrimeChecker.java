package ExceptionChallenge;

import java.math.BigInteger;
import java.util.Scanner;

public class PrimeChecker {
    private static BigInteger bigInt;
    /**
     * Receives user input to check if a number is prime or not.
     * @param args Not used here
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String userQuery;
        do {
            System.out.println("Please enter an integer to check >> ");
            userQuery = sc.next();
            try {
                bigInt = new BigInteger(userQuery);
                if (bigInt.doubleValue() < 0) throw new ArithmeticException();
                System.out.println(userQuery + (bigInt.isProbablePrime(100) ? " is prime." : " is not prime."));
            } catch (NumberFormatException e) {
                System.out.println("Input is improperly formatted!");
            } catch (ArithmeticException e) {
                System.out.println("Error! The input is " + (bigInt.doubleValue() < 0 ? "negative!" : "too large!"));
            } catch (Exception e) {
                System.out.println(userQuery.equals("quit") ? "Goodbye!" : "Invalid Input!");
            }
        } while (!userQuery.equals("quit"));
        sc.close();
    }
}
