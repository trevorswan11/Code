package SummarySession;

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
        for (int i = number; i > 1;  i--) {
            if (number % i == 0 && isPrime(i)) {
                return i;
            }
        }
        return 0;
    }

    /** Helper method for Q30 that determines if an input is prime or not
     * @param k An int input to be checked
     * @return A boolean indicating prime or not
      */
    private static boolean isPrime(int k) {
        if (k <= 1) {
            return false;
        }   
        
        for (int i = 2; i * i < k; i++) {
            if (k % i == 0) {
                return false;
            }
        }
        return true;
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
        double product = principal;
        for (int i = 1; i < years; i++) {
            product = product * (1 + rate/100);
        }

        // Round the product to two decimal places
        return (int) (product * 100 + 0.5) / 100.0;
    }

    /**
     * Question 32 method
     * 
     * @param year  The desired year as an int
     * @param month The desired month as an int
     * @param day  The desired day as an int
     * @return The day of the week as an int. 1=Sunday, 2=Monday, etc.
     */
    public static int calculateDay(int year, int month, int day) {
        // This method only works for years after 1752
        if (year <= 1752) {
            return -1;
        }
        // January and February are considered 11 and 12
        else if (month < 3) {
            month = month + 10;
            year = year - 1;
        }
        // All other months should be decremented so march is 1
        else {
            month = month - 2;
        }

        // get the century of the year inputted
        int c = year / 100;

        // calculate the last two digits of the year inputted
        int y = year % 100;

        int[] components = new int[6];
        components[0] = day;
        components[1] = (13 * month - 1) / 5;
        components[2] = y;
        components[3] = y / 4;
        components[4] = c / 4;
        components[5] = -2 * c;
        
        // Sum the components in the array
        int sum = 0;
        for (int i = 0; i < components.length; i++) {
            sum = sum + components[i];
        }

        int result = sum % 7;
        if (result < 0) {
            return -1 * result;
        }

        return result;
    }

    public static void main(String[] args) {
        int output = calculateDay(2024, 3, 4);
        System.out.println(output);
    }
}
