/* Trevor Swan
 * HW2 Class - contains static methods that make use of loops to manipulate and examine strings
 * CSDS132 - Project 2
 */

public class HW2 {
    /**
     * This is a 'helper' method. It cannot be accessed outside of this class, and
     * exists solely to remove large block of code in the methods below. It's only
     * purpose is to trim any leading or trailing spaces in a string and then return
     * the trimmed string. For ease of grading, this method is called 'strTrim',
     * similar to MATLAB's trim method. This project does not make use of the trim
     * method of the string class to align with the project's guidelines.
     * 
     * @param s A string that represents the users input
     * 
     * @return A string that represents the user's input, devoid of any leading or
     *         trailing spaces. The same string is returned with no other
     *         alterations
     */
    private static String strTrim(String s) {
        /* The user may input a string that is devoid of any characters (an empty string). 
         * The code should run regardless 
         */
        if (s.length() == 0) {
            return s;
        }

        else {
            /* The user may accidentally have whitespace leading or trailing, this takes
             * care of that 
             */
            if (s.charAt(0) == ' ' || s.charAt(s.length() - 1) == ' ') {
                // Loop through the input and determine where the first non-space character is
                int startIdx = 0;
                while (s.charAt(startIdx) == ' ') {
                    startIdx = startIdx + 1;
                }

                /* Loop through the input to determine where the last non-space character is
                 * Examination must start at the very end of the input and go backwards
                 */
                int endIdx = s.length() - 1;
                while (s.charAt(endIdx) == ' ') {
                    endIdx = endIdx - 1;
                }

                // Create a new StringBuilder in order to create a string without any whitespace
                StringBuilder stripped = new StringBuilder("");

                /* Append the characters from the input to the builder that lie in between the
                 * bounds found above
                 */
                for (int i = startIdx; i <= endIdx; i++) {
                    stripped.append(s.charAt(i));
                }

                // Return the Modified string to the same address to prevent too much confusion
                return stripped.toString();
            } else {
                return s;
            }
        }

    }
    /**
     * Asserts whether the first 'compareStr' values of a string are equal to each
     * other. This is case sensitive, and if the desired int value exceeds the
     * length of either string, then the result is false.
     * 
     * @param stringOne  The first desired string to use in comparison with the
     *                   second
     * @param stringTwo  The string to be compared with the first string inputted
     * @param compareStr An int that represents the amount of characters in the
     *                   string to compare
     * 
     * @return A boolean value that represents whether or not the first 'compareStr'
     *         values of the strings are equivalent
     */
    public static boolean samePrefix(String stringOne, String stringTwo, int compareStr) {
        // First check if the amount to compare exceeds the length of either inputted string
        if (compareStr > stringOne.length() || compareStr > stringTwo.length()) {
            return false;
        }

        // If the comparison lies within the bounds of both strings, continue
        else {
            // These instances of the StringBuilder API assist in the creation of strings for comparison
            StringBuilder newStringOne = new StringBuilder("");
            StringBuilder newStringTwo = new StringBuilder("");

            /* This loops aims to make the newStringOne and newStringTwo instances
             * contain the first 'compareStr' characters of their respective string inputs */
            for (int i = 0; i < compareStr; i++) {
                newStringOne.append(stringOne.charAt(i));
                newStringTwo.append(stringTwo.charAt(i));
            }

            // These statements convert the StringBuilder instances into actual strings
            String compStrOne = newStringOne.toString();
            String compStrTwo = newStringTwo.toString(); 

            // This return statement indicates whether or not the two prefixes are equal
            return compStrOne.equals(compStrTwo);
        }
    }

    /**
     * Asserts whether or not parentheses in an input are matched, meaning that
     * every string with parenthesis should have exactly one open parentheses '('
     * for every closed parentheses ')'. False is only returned if parentheses are
     * mismatched.
     *
     * @param statement A statement as a string with or without parentheses that
     *                  will be examined to determine if its parentheses are matched
     *                  correctly
     * 
     * @return A boolean value asserting whether the parentheses in the string are
     *         matched correctly
     */
    public static boolean matchingParentheses(String statement) {
        // First check to see if the first or last character is a closed or open parentheses, respectively
        if (statement.charAt(0) == ')' || statement.charAt(statement.length() - 1) == '(') {
            // The result must be false if either condition is met
            return false;
        }

        // If there are no mismatched parentheses at the start or end, continue
        else {
            // These variables initialize a counter for the parentheses and index for the loop
            int counter = 0;
            int i = 0;

            /* The goal of this loop is to determine if there are mismatched parentheses by treating '(' as +1 and ')' as -1 
             * The loop stops if the index exceeds the input String or the count becomes negative, indicating a ')' coming
             * before a '('
            */
            while (counter >= 0 && i < statement.length()) {
                // Add one to the counter if an open parentheses is found
                if (statement.charAt(i) == '(') {
                    counter = counter + 1;
                }

                // Subtract one from the counter if a closed parentheses is found
                else if (statement.charAt(i) == ')') {
                    counter = counter - 1;
                }
                i = i + 1;
            }

            // If there are an equal number of open and closed parentheses, the counter should be 0
            if (counter == 0) {
                return true;
            }

            // If the counter is not exactly 0, there is a mismatch
            else {
                return false;
            }
        }
    }

    /**
     * Changes an inputted string by removing the 'k'th word in the string plus the
     * whitespace to the right of the world. This is repeated until the string is no
     * longer able to be changed, essentially removing every 'k'th word in the
     * string. Non-positive inputs for the int will return the original string.
     * 
     * @param sentence A sentence or generic string that will be sifted through in
     *                 this method
     * @param k        An int corresponding to the the 'k'th word from the string
     *                 that will be returned
     * 
     * @return A new, modified string with every 'k'th word missing, assuming k is a
     *         positive integer
     */
    public static String removeEveryKthWord(String sentence, int k) {
        // Only remove the Kth word if the input is positive
        if (k > 0) {
            // If the user desires every 1st word removed, returns null to the user
            if (k == 1) {
                return null;
            }

            // If the user inputs any other positive value, continue as usual
            else {
                // Uses my helper method to strip the input's leading/trailing zeros 
                sentence = strTrim(sentence);

                // Creates a new StringBuilder to  assist in creating a modified string in loops
                StringBuilder newString = new StringBuilder("");

                // Create an omit variable to indicate when a word should be ignored
                boolean omit = false;

                // Because the first word in the string is counted, we must start at 1 space
                int spaces = 1;

                // Loop through the inputted string until fully filtered
                int i = 0;                
                while (i < sentence.length()) {
                    // Include words if omit is false
                    if (omit == false) {
                        newString.append(sentence.charAt(i));
                        
                        // If a space is found added to the builder, add one to the counter
                        if (sentence.charAt(i) == ' ') {
                            spaces = spaces + 1;
                        } 
                    }

                    // Disregard words otherwise
                    else {
                        /* Increase the index to the next word if it is time to omit 
                         * The loop should stop if the idex exceeds the length of the inputted string
                        */
                        while (sentence.charAt(i) != ' ' && i < sentence.length() - 1) {
                            i = i + 1;
                        }

                        // The loop above stops at a space, so indicate it with the counter and switch back to word inclusion
                        spaces = spaces + 1;
                        omit = false;
                    }

                    // If the number of spaces passed by is divisible by k, omit the next word
                    if (spaces != 0 && spaces % k == 0) {
                        omit = true;
                    }
                    i = i + 1;
                }

                // Convert the newString to an actual string and return it to the user
                return newString.toString();
            }
        }

        // If the user inputted a negative number, return the original string
        else {
            return sentence;
        }

    }

    /**
     * Changes an inputted string such that the first 'k'th characters in the string
     * are not altered, but the next 'k'th characters are flipped.
     * This means that the flipped characters will start with the 'k'th characters
     * in the group and end with its original starting character. If
     * the integer inputted exceeds the length of the string, then the string
     * remains unchanged. Every 'k' group of characters will be in the order:
     * unchanged, flipped, etc.
     * 
     * @param statement A string that is the desired string to be altered by the
     *                method
     * @param k       An int that represents the 'k'th number of characters to keep
     *                the same, then rotate, then repeat
     * 
     * @return A string that contains the exact same characters as the input string,
     *         just in a new order
     */
    public static String flipEachK(String statement, int k) {
        // Return the original string if k is less than or equal to 1 or is greater than the length
        if (k <= 1 || k >= statement.length()) {
            return statement;
        }

        // If neither of the above conditions are met, there must be real work done on the input
        else {
            // Create a new StringBuilder to assist in the flipping of the input
            StringBuilder flipped = new StringBuilder("");

            // This variable determines whether or not the next characters will be flipped or not
            boolean flip = false;
            
            // Loop through the input string to build a new string with flipped indices
            int i = 0;
            while (i < statement.length()) {
                // If flip is currently true, 
                if (flip == true) {
                    // Reduce i by one to prevent skipping of a character
                    i = i - 1;

                    // Set a variable to keep track of where to stop flipping
                    int stop = i;

                    // Set a variable that will determine where the reversed part will start
                    int flipIdx = i + k;

                    // Add to the StringBuilder if flipIdx is greater than the stopping index
                    while (flipIdx > stop) {
                        // If the flipIdx is outside the bounds of the string, decrement to ensure ending characters aren't lost
                        if (flipIdx > statement.length() - 1) {
                            flipIdx = flipIdx - 1;
                        }

                        // If the flipIdx isn't out of the bounds of the string, continue as usual
                        else {
                            // Each iteration, append the reversed character, increase the methods index, and Subtract one from the the flipIdx
                            flipped.append(statement.charAt(flipIdx));
                            flipIdx = flipIdx - 1; 
                        }
                    }

                    // Set the new index for the larger loop to the one after the flipped part of the original
                    i = stop + k + 1;

                    // Change flip back to false to go back to correct appending
                    flip = false;
                }

                // If flip is not currently true, build the new string as normal
                else {
                    flipped.append(statement.charAt(i));
                    i = i + 1;

                    /* Turn flip on if the index is non-zero and divisible by k.
                     * i + 1 is stated as k treats 1 as the first index
                     */
                    if ((i) % k == 0 && i != 0) {
                        flip = true;
                    }
                }
            }
            
            // Return the flipped string to the user
            return flipped.toString();
        }
    }

    /**
     * Potentially changes an inputted string such that any present digits are
     * reordered in reverse order. Regardless of the other characters in the string,
     * the numbers 0-9 will be reordered in reverse order, remaining in the same
     * indices.
     * 
     * @param statement A string that potentially contains numbers. It will be
     *                  altered by the method if numbers are present, and will
     *                  remain untouched if not.
     * 
     * @return A potentially altered string that is identical to the input except
     *         the digits present are in reverse order.
     */
    public static String reverseDigits(String statement) {
        // Create a StringBuilder that will contain all of the digits in the input
        StringBuilder digits = new StringBuilder();

        // Create an index for a while loop goes through the input, storing Digits that are found
        int i = 0;
        while (i < statement.length()) {
            // Appends a character to the builder if it is a digit, ignores otherwise
            if (Character.isDigit(statement.charAt(i))) {
                digits.append(statement.charAt(i));
            }
            i = i + 1;
        }

        // Convert the digits builder to a string for proper interactions
        String digitsStr = digits.toString(); 

        // If the digits builder is empty, return the original string, otherwise continue with replacement
        if (digitsStr.length() == 0) {
            return statement;
        }
        
        // Continue altering the string if there are digits to change
        else {
            // Create a StringBuilder that will contain the original loop with reversed digits
            StringBuilder reverse = new StringBuilder();

            // Create a variable that keeps track of the index of the digits replacement builder
            int digitsIdx = digitsStr.length() - 1;

            // Reset the while loop index, and start going creating the new string with altered digits
            i = 0;
            while (i < statement.length()) {
                // Append a replaced digit if there is one present in the current index
                if (Character.isDigit(statement.charAt(i))) {
                    reverse.append(digitsStr.charAt(digitsIdx));

                    // Decrement the index of the digits as they are going in reverse order
                    digitsIdx = digitsIdx - 1;
                }
                
                // Append the old string index otherwise
                else {
                    reverse.append(statement.charAt(i));
                }
                i = i + 1;
            }

            // Return the altered statement
            return reverse.toString();
        }
    }

    /**
     * Replaces substrings of an inputted string, indicated by parentheses (...)
     * with substrings from another string using the same nomenclature. The second
     * string's substrings can contains parentheses inside of them and they will be
     * treated like any other character. If the first string contains more
     * substrings than the second, they are removed. If the second string contains
     * more substrings than the first, then they are ignored. Mismatched parentheses
     * will be ignored. Substrings are replaced in the exact same order as they
     * appeared originally.
     * 
     * @param baseString   A string with zero or more indicated substrings to be
     *                     replaced
     * @param replacements A string with indicated substrings that will replace
     *                     those indicated in the baseString
     * 
     * @return A string that is an altered version of the baseString with the
     *         substrings replaced in the manner indicated above.
     */
    public static String replaceText(String baseString, String replacements) {
        return null;
    }

    /**
     * Flips every single substring inside of an inputted string. Substrings are
     * indicated by characters inside of matched parentheses (...). This behavior is
     * observed for nested substrings as well. If a reversed substring contains
     * another substring, then it will then be reserved. Parentheses must be
     * properly matched in order to work properly, so an error is thrown if this is
     * not the case.
     * 
     * @param originalString A string with zero or more substrings to be altered by
     *                       this method
     * @return An altered originalString where all substrings are flipped in the
     *         manner described above.
     */
    public static String reverseAll(String originalString) {
        return null;
    }

    // This is the unused main method
    public static void main(String[] args) {
        String result = HW2.replaceText("this (is a) test" , "this (was) a (test)");
        System.out.println(result);
    }
}