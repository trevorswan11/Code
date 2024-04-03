package projectTwo;

/* Trevor Swan
 * This is the main method of HW2
 * This will not be submitted, and is exclusively for cmd line tests
 * Although this could've been placed in the main class main method, i was told not to
 */

// This import is specifically for the Scanner class in order to use the main method as desired
import java.util.Scanner;

public class HW2Main extends HW2 {
    /*
     * Practically, this is the main method of the HW2 class.
     * I wanted to work with my code through the command line as I was writing it,
     * so this was my way of accomplishing that.
     * Note: This is NOT my testing, that was done exclusively in the file:
     * HW2Test.java
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Starts Dialogue in cmd with method options
        System.out.println("Welcome to HW2 Interactions:");
        System.out.println("\t1. samePrefix\n\t2. matchingParentheses\n\t3. removeEveryKthWord");
        System.out.println("\t4. flipEachK\n\t5. reverseDigits\n\t6. replaceText");
        System.out.println("\t7. reverseAll\n\t8. Quit");

        // Starts a while loop to run until the user wants to quit
        boolean run = true;
        while (run) {
            // Asks the user what they would like to do, then adds a space for formatting
            System.out.println("");
            System.out.print("What would you like to do (1-8): ");
            String option = input.nextLine();
            System.out.println("");

            // Runs the first method - samePrefix
            if (option.equals("1")) {
                // Declares the method being tested
                System.out.println("samePrefix is being tested...");

                // Asks for the required inputs from the user
                System.out.print("String One: "); 
                String stringOne = input.nextLine();
                System.out.print("String Two: ");
                String stringTwo = input.nextLine();
                System.out.print("Comparison Value = ");
                int compareStr = input.nextInt();

                // Gives the output from the method
                System.out.println("Output: " + HW2.samePrefix(stringOne, stringTwo, compareStr));
                input.nextLine();
            }

            // Runs the second method - matchingParentheses
            else if (option.equals("2")) {
                // Declares the method being tested
                System.out.println("matchingParentheses is being tested...");

                // Asks for the required inputs from the user
                System.out.print("Statement: ");
                String statement = input.nextLine();

                // Gives the output of the method
                System.out.println("Output: " + HW2.matchingParentheses(statement));
            }

            // Runs the third method - removeEveryKthWord
            else if (option.equals("3")) {
                // Declares the method being tested
                System.out.println("removeEveryKthWord is being tested...");

                // Asks for the required inputs from the user
                System.out.print("Sentence: ");
                String sentence = input.nextLine();
                System.out.print("k = ");
                int k = input.nextInt();

                // Gives the output of the method
                System.out.println("Output: " + HW2.removeEveryKthWord(sentence, k));
                input.nextLine();
            }

            // Runs the fourth method - flipEachK
            else if (option.equals("4")) {
                // Declares the method being tested
                System.out.println("flipEachK is being tested...");

                // Asks for the required inputs from the user
                System.out.print("Statement: ");
                String statement = input.nextLine();
                System.out.print("k = ");
                int k = input.nextInt();

                // Gives the output of the method
                System.out.println("Output: " + HW2.flipEachK(statement, k));
                input.nextLine();
            }

            // Runs the fifth method - reverseDigits
            else if (option.equals("5")) {
                // Declares the method being tested
                System.out.println("reverseDigits is being tested...");

                // Asks for the required inputs from the user
                System.out.print("Statement: ");
                String statement = input.nextLine();

                // Gives the output of the method
                System.out.println("Output: " + HW2.reverseDigits(statement));
            }

            // Runs the sixth method - replaceText
            else if (option.equals("6")) {
                // Declares the method being tested
                System.out.println("replaceText is being tested...");

                // Asks for the required inputs from the user
                System.out.print("Base String: ");
                String baseString = input.nextLine();
                System.out.print("Replacements: ");
                String replacements = input.nextLine();

                // Gives the output of the method
                System.out.println("Output: " + HW2.replaceText(baseString, replacements));
            }

            // Runs the seventh method - replaceAll
            else if (option.equals("7")) {
                // Declares the method being tested
                System.out.println("replaceAll is being tested...");

                // Asks for the required inputs from the user
                System.out.print("Original String: ");
                String originalString = input.nextLine();

                // Gives the output of the method
                System.out.println("Output: " + HW2.reverseAll(originalString));
            }

            // Quits the program
            else if (option.equals("8")) {
                System.out.println("Thank you!");
                run = false;
            }

            // Indicates invalid input and continues loop
            else {
                System.out.println("Invalid Input :(");
            }
        }

        // Closes the scanner to stop java from yelling at me
        input.close();
    }
}
