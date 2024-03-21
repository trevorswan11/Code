package SessionSolutions;

// *The provided solutions are not the only ways to do these problems*

public class Midterm {
    // Question 29
    public static boolean isPerfectNumber(int n) {
        int sum = 0; // Keeps track of the sum

        // For loop to loop through all the possible divisors of n
        for (int i = 1; i < n; i++) { // Loops through 1 to n - 1
            if (n % i == 0) // Checks if i is a divisor of n
                sum = sum + i; // If so, we add it to the sum
        }

        return sum == n; // Return whether the sum is equal the original number
    }

    // Question 30
    public static int largestPrimeDivisor(int n) {
        // For loop to loop through all the possible divisors of n
        for (int i = n; i > 1; i--) { // Loops from n to 1
            if (n % i == 0 && isPrime(i)) // Checks to see if it is a prime divisor
                return i;
        }

        return 0; // No prime divisor found
    }

    // Question 30 Helper Method
    private static boolean isPrime(int n) {
        // Loops through numbers from 2 to sqrt(n)
        // Note: sqrt(n) is the farthest we have to go because if we found a divisor greater than sqrt(n),
        // then we must have found a divisor earlier, so it's not necessary
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) // If a divisor is found, it's not prime
                return false;
        }

        return true; // No divisors found ==> prime
    }

    // Question 31
    public static double compoundInterest(double principal, double interestRate, int numYears) {
        double balance = principal; // Stores the balance

        // Loops and computes interest after each year
        for (int i = 0; i < numYears; i++) {
            balance = balance * (1 + interestRate / 100); // Compounds interest annually
        }

        return (int) (balance * 100 + 0.5) / 100.0; // Rounds to 2 decimal places
    }

    // Question 32
    public static int calculateDay(int year, int month, int day) {
        // Change month so 1 = March, 2 = April, etc.
        if (month < 3) {
            year--; // Subtract year if month is Jan or Feb
            month += 10;
        } else
            month -= 2;

        int c = year / 100; // Gets the century of the year
        int y = year % 100; // Gets the last 2 digits of the year

        int result = day + (13 * month - 1) / 5 + y + y / 4 + c / 4 - 2 * c; // Computes the day of the week
        result = result % 7; // Applies mod operator

        // In the event that it's negative, we want to make it positive by adding 7
        if (result < 0)
            result = result + 7;

        // Right now Sunday = 0, Monday = 1, etc. We want it so Sunday = 1, Monday = 2, ... so we add 1
        return result + 1;
    }

    // Question 33
    public static String capitalizeWords(String s) {
        StringBuilder sb = new StringBuilder();

        int index = 0; // Used to index the String
        while (index < s.length()) {
            while (index < s.length() && Character.isWhitespace(s.charAt(index))) // Copy whitespaces until word is reached
                sb.append(s.charAt(index++)); // Adds char to StringBuilder and increments index at the same time

            if (index < s.length())
                sb.append(Character.toUpperCase(s.charAt(index++))); // Capitalizes first letter of next word and increments index

            while (index < s.length() && !Character.isWhitespace(s.charAt(index))) // Copy word until another space is reached
                sb.append(s.charAt(index++)); // Adds char to StringBuilder and increments index at the same time
        }

        return sb.toString(); // Return finished String
    }

    // Question 34
    public static String swapUntil(String s, char c1, char c2, char c3) {
        StringBuilder sb = new StringBuilder();

        int index = 0; // Used to iterate String
        while (index < s.length() && s.charAt(index) != c3) {
            if (s.charAt(index) == c1) // Swap c1 to c2
                sb.append(c2);
            else if (s.charAt(index) == c2) // Swap c2 to c1
                sb.append(c1);
            else
                sb.append(s.charAt(index)); // Else do not change anything

            index++; // Iterate loop index
        }

        return sb.toString(); // Return finished String
    }

    // Question 35
    public static int sumOfDigits(String s) {
        int sum = 0; // Stores the sum

        // For loop used to iterate through String
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                int charValue = s.charAt(i) - 48; // '0' has an int value of 48, ..., '9' has an int value of 57
                /**
                 * Alternative ways to get the value of the char:
                 * int charValue = Character.digit(s.charAt(i), 10);
                 * OR 
                 * int charValue = s.charAt(i) - '0';
                 */
                sum += charValue; // Adds digit value to sum
            }
        }

        return sum; // Return sum of all digits
    }
    
    // Question 36
    public static String incrementString(String s) {
        StringBuilder sb = new StringBuilder();

        // For loop used to iterate through String
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                int charValue = c - 48; // '0' has an int value of 48, ..., '9' has an int value of 57
                sb.append((charValue + 1) % 10); // Adds one to the digit value and appends (the %10 is so 9 wraps around to 0)
            } else
                sb.append(c); // Append unmodified character if not digit
        }

        return sb.toString(); // Return finished String
    }

    // Question 37
    public static String alternateCase(String s) {
        StringBuilder builder = new StringBuilder();
        
        int i = 0; // Loop index
        
        // Repeat until the String is done
        while (i < s.length()) {
            // Copy everything until we get to the first letter
            while (i < s.length() && s.charAt(i) != ' ') 
              builder.append(s.charAt(i++));
            
            // Copy everything and make it uppercase until we hit end of word
            while (i < s.length() && s.charAt(i) == ' ') 
              builder.append(Character.toUpperCase(s.charAt(i++)));
            
            // Copy everything and until we get to next letter
            while (i < s.length() && s.charAt(i) != ' ')
              builder.append(s.charAt(i++));
            
            // Copy everything and make it lowercase until we hit end of word
            while (i < s.length() && s.charAt(i) == ' ')
              builder.append(Character.toLowerCase(s.charAt(i++)));
        } 
        
        return builder.toString(); // Return String
    }
}