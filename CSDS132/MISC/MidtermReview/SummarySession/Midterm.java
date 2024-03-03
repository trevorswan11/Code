import java.util.Scanner;

/**
 * A collection of methods from questions 19 to 32 on the SI Summary sheet for
 * the CSDS132 Midterm
 */
public class Midterm {
    /**
     * Question 29 of Sheet
     * 
     * @param number The Number to be checked
     * @return A boolean value indicating whether the input is perfect or not
     */
    public static boolean isPerfectNumber(int number) {
        // Perfect numbers are only greater than 0, so return false otherwise
        if (number <= 0) {
            return false;
        }

        // 1 is a perfect number, return true if one is inputted
        else if (number == 1) {
            return true;
        }

        // Create a variable to keep track of the sum of factors
        int sum = 0;

        // Loop through the string until
        int i = 1;
        while (i < number) {
            if (number % i == 0) {
                // Add i to the sum if a factor is found
                sum += i;
            }

            i++;
        }

        // return a boolean based on the equality of the sum and the input
        return sum == number;
    }

    /**
     * Question 30 method
     * 
     * @param number An int to be looked at
     * @return Returns the largest prime divisor of the input
     */
    public static int largestPrimeDivisor(int number) {

    }

    /**
     * Question 31 method
     * 
     * @param principle The double principle value
     * @param rate      The double annual interest rate in percent
     * @param years     The int amount of years
     * @return A double that represents the calculated interest
     */
    public static double compoundInterest(double principal, double rate, int years) {

    }

    /**
     * Question 32 method
     * 
     * @param year  The desired year as an int
     * @param month The desired month as an int
     * @param week  The desired day as an int
     * @return The day of the week as an int. 1=Sunday, 2=Monday, etc.
     */
    public static int calculateDay(int year, int month, int week) {

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "Midterm Review:\n\t1. Perfect Number\n\t2. Prime Divisor\n\t3. Compound Interest\n\4. tCalculate Day\n\t5. Quit");
        boolean run = true;
        String input;
        int number;
        while (run) {
            sc.nextLine();
            System.out.print("What would you like to do: ");
            input = sc.next();
            switch (input) {
                case "1":
                    System.out.print("Input a number: ");
                    number = sc.nextInt();

                    System.out.println(isPerfectNumber(number));
                case "2":
                    System.out.print("Input a Number: ");
                    number = sc.nextInt();

                    System.out.println(largestPrimeDivisor(number));
                case "3":
                    System.out.print("Input a principle value: ");
                    double principal = sc.nextDouble();
                    System.out.print("Input an interest rate: ");
                    double rate = sc.nextDouble();
                    System.out.print("Input a number of years: ");
                    int years = sc.nextInt();

                    System.out.println(compoundInterest(principal, rate, years));
                case "4":
                    System.out.print("Input a year: ");
                    int year = sc.nextInt();
                    System.out.print("Input a month: ");
                    int month = sc.nextInt();
                    System.out.print("Input a day: ");
                    int day = sc.nextInt();

                    System.out.print(calculateDay(year, month, day));
                case "5":
                    System.out.println("Thank you!");
                    run = false;
                default:
                    System.out.println("Invalid Input :(");
            }
        }
        sc.close();
    }
}
