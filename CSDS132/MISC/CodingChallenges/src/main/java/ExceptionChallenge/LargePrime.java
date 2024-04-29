package ExceptionChallenge;

import java.math.BigInteger;

public class LargePrime {
    // Stores the input integer from the command line
    private static BigInteger input;
    
    /**
     * Takes user input from the command line and attempts to return the next prime,
     * handling possible exceptions.
     * 
     * @param args The command line arguments, args[0] only considered!
     */
    public static void main(String[] args) {
        try {
            input = new BigInteger(args[0]);
            System.out.println("The next prime after your input is:");
            System.out.println(input.nextProbablePrime());
        } catch (ArithmeticException e) {
            String note = input.doubleValue() < 0 ? "negative!" : "too large!";
            System.out.println("Error! The input is " + note);
            System.out.println("2 is printed by default, as requested.");
        } catch (Exception e) {
            System.out.println("Invalid input. Integer needed!");
        }
    }
}