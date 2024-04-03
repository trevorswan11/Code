package projectTwo;

/* Trevor Swan
 * HW2 Class - contains static methods that make use of loops to manipulate and examine strings
 * CSDS132 - Project 2
 */

/**
 * This class is the singular class for the second project in the spring of 2024
 * for CSDS132. The methods included are the ones required on the instructions
 * as well as one 'helper' method to remove leading and trailing spaces. The
 * helper method is private, but all other methods are public and have their
 * own javadoc. I was only allowed to use the methods in the API listed below:
 * 
 * @apiNote String Class:
 *          length, charAt
 * @apiNote StringBuilder Class:
 *          length, charAt, append, toString
 * @apiNote Character Class:
 *          any method
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
     * This is the second helper method used to assist in method involving
     * parentheses in this project. The only purpose of this method is to determine
     * whether or not an inputted string contains parentheses or not. The string can
     * be any length, and a boolean value will always be returned.
     * 
     * @param s A string as the users input
     * 
     * @return A boolean value indicating true only if there are present parentheses
     *         in the inputted string
     */
    private static boolean containsParentheses(String s) {
        // If an empty string is in s, then return false
        if (s.equals("")) {
            return false;
        }

        // Otherwise continue through the loop
        else {
            // This boolean variable contains the information regarding the inclusion of parentheses
            boolean contains = false;

            // Loop through the string s until all characters are exhausted or a parentheses is found
            int start = 0;
            int end = s.length() - 1;
            while (start <= end && contains == false) {
                // Check the current start against open or closed parentheses, if either is found, contains is true
                if (s.charAt(start) == '(' || s.charAt(start) == ')') {
                    contains = true;
                }

                // Check the current end against open or closed parentheses, if either is found, contains is true
                else if (s.charAt(end) == '(' || s.charAt(end) == ')') {
                    contains = true;
                }

                // Otherwise increment the start and decrement the end
                else {
                    start = start + 1;
                    end = end - 1;
                }
            }

            // Return the boolean result
            return contains;
        }
    }

    /**
     * This third helper method determines whether or not there is a potential
     * mismatch of parentheses in the inputted string.
     * 
     * @param s A string of desired length to be examined
     * @param k int value representing where in the string to start the search index
     *          wise
     * @return int value representing three different outcomes. If the result is 0,
     *         then a mismatch was not found. If the result is -1, then the test
     *         indicates that there are no more parentheses in the string after the
     *         inputted index for k. Any other returned int is the index of the
     *         mismatch.
     */
    private static int isMismatched(String s, int k) {
        // Loop through the input until a closing
        while (k < s.length() - 1) {
            // Increment the loops index
            k++;

            // If an open parentheses is found, return k indicating a mismatch
            if (s.charAt(k) == '(') {
                return k;
            }

            // If a closed parentheses is found, return 0 indicating a mismatch was not found
            else if (s.charAt(k) == ')') {
                return 0;
            }
        }  

        // If none of the conditionals in the loop found the right character, return null
        return -1;
    }

    /**
     * This fourth helper method determines whether there are nested parentheses in
     * the string starting at a given index k.
     * 
     * @param s A string of desired length
     *          to be examined
     * @param k int value representing where in the string to start
     *          index wise
     * @param initial An int representing the amount to add to the initial sum
     * @return int value representing 3 outcomes. If the return is 0, then there is
     *         not nested parentheses found. If the return is -1, then the end of the
     *         string was reached and the sum was not 0. If the return is any other
     *         value, it corresponds to the index at the end of the full substring
     */
    private static int nestedParentheses(String s, int k, int initial) {
        // Initialize a variable to keep track of the sum of parentheses
        int sum = initial;

        // Loop through the string until the sum is zero
        while (sum > 0 && k < s.length() - 1) {
            // Increment the loops index
            k++;

            // If an open parentheses is found, add one to the sum
            if (s.charAt(k) == '(') {
                sum = sum + 1;
            }

            // If a closed parentheses is found, subtract one from the sum
            else if (s.charAt(k) == ')') {
                sum = sum - 1;
            }
        }

        // If the sum is 0, then the parentheses are matched, return k
        if (sum == 0) {
            return k;
        }

        else if (k == s.length() - 1 && sum != 0) {
            return -1;
        }

        // Otherwise return 0
        return 0;
    }

    /**
     * This fifth helper method determines whether or not there is a substring found
     * given an index and a string. It makes use of the isMIsmatch and
     * nestedParentheses helper methods to work. I understand that this method does
     * a lot of work for the replaceText method, but writing this helped keep me
     * organized and prevented my code from being even more complex than it already
     * is.
     * 
     * @param s       A string to be examined as desired by the user
     * @param k       The index to start examining the string at
     * @param builder A StringBuilder that will be acted on based on the presence of
     *                a substring. Can be null
     * @return An int that has 3 states. If the return is a positive value, then it
     *         represents the index where the substring ends. If the return is 0,
     *         then there is no closing parentheses for the substring. If -1 is
     *         returned, then the method appended the rest of the characters in s to
     *         the StringBuilder inputted. -1 as an output indicates no action was
     *         taken if StringBuilder is null.
     */
    private static int isSubstring(String s, int k, StringBuilder builder) {
        // These variables hold mismatch and nested indicators, reflectively
        int mismatch;
        int nested;

        // This variable handles where any appending should start
        int appends = k;

        // while k is less than the length of the string, look for a complete substring
        while (k < s.length()) {
            // If there a closing parentheses found, then return its index
            if (s.charAt(k) == ')') {
                return k;
            }

            // If another open parentheses is found, check for a mismatch and nested
            else if (s.charAt(k) == '(') {
                mismatch = isMismatched(s, k);

                // Since an open parentheses has been found already, initial is 1
                nested = nestedParentheses(s, k, 1);

                // If a mismatch is found but it is not part of a nested set of parentheses, append the next characters until mismatch
                if (mismatch > 0 && nested <= 0) {
                    // append to the StringBuilder until the mismatch index is met
                    for (int i = appends; i < mismatch; i++) {
                        // null is a possible input for StringBuilder, so only append if not null
                        if (builder != null) {
                            builder.append(s.charAt(i));
                        }
                    }

                    // Set k to the mismatch index
                    k = mismatch;

                    // Set appends to the current k index
                    appends = k;
                }

                // If a mismatch is found that is part of a nested set of parentheses, return the end index of the nested set
                else if (mismatch > 0 && nested > 0) {

                    return nested;
                }

                // If a mismatch is not found but the current index is part of nested parentheses, return the index of the nested set
                else if (mismatch == 0 && nested > 0) {
                    // Append up to the first nested parentheses
                    for (int i = appends; i < k; i++) {
                        // null is a possible input for StringBuilder, so only append if not null
                        if (builder != null) {
                            builder.append(s.charAt(i));
                        }
                    }

                    // Return the index of the end of the nested set 
                    return nested;
                }

                // If a mismatch isn't found and there are no more parentheses in the string, append the rest of it
                else if (mismatch == -1 && nested == 0){
                    // Append the entire string to the builder
                    for (int i = appends; i < s.length(); i++) {
                        // null is a possible input for StringBuilder, so only append if not null
                        if (builder != null) {
                            builder.append(s.charAt(i));
                        }
                    }

                    // Return -1 to signify the full appending of the method
                    return -1;
                }
            }

            // Increment the loops index
            k++;
        }

        // If the entire loop ran without returning anything, there is no substring so return 0
        return 0;
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

        // If the input is less than or equal to 0, return true
        else if (compareStr <= 0) {
            return true;
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
        // If the string is empty, return true, otherwise continue with the checking part
        if (statement.equals("")) {
            return true;
        }

        // Using my helper method, If there are no parentheses in the input, return true 
        else if (!containsParentheses(statement)) {
            return true;
        }

        // Otherwise the data must be traversed as normal
        else {
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
        // Only remove the Kth word if the input is positive and in range
        if (k > 0  && k <= sentence.length()) {
            // If the user desires every 1st word removed, returns null to the user
            if (k == 1) {
                return "";
            }

            // If the user inputs any other positive value, continue as usual
            else {
                // Creates a new StringBuilder to  assist in creating a modified string in loops
                StringBuilder newString = new StringBuilder("");

                // If there is a space at the start of the string, then run this conditional
                if (sentence.charAt(0) == ' ') {
                    // Spaces before the sentence should be included, so count how many spaces there are
                    int leading = 0;
                    int leadIdx = 0;

                    // Every time this loop runs, it adds to the number of leading spaces until an actual character is found
                    while (sentence.charAt(leadIdx) == ' ') {
                        leading++;
                        leadIdx++;
                    }

                    // If there were leading spaces found, add the number of spaces to a string builder
                    if (leading != 1) {
                        // Add the number of spaces spaces to the newString builder 
                        for (int i = 0; i < leading; i++) {
                            newString.append(' ');
                        }
                    }

                    // Otherwise just append one space and move on
                    else {
                        newString.append(' ');
                    }
                }
            
                // Uses my helper method to strip the input's leading/trailing zeros 
                sentence = strTrim(sentence);

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
     * with substrings from another string using the same nomenclature. If the first
     * string contains more substrings than the second, they are removed. If the
     * second string contains more substrings than the first, then they are ignored.
     * Mismatched parentheses will be ignored in a way that they are still included
     * in the output, but are not considered for replacement. Substrings are
     * replaced in the exact same order as they appeared originally.
     * 
     * @param baseString  A string with zero or more indicated substrings to be
     *                    replaced
     * @param replacement A string with indicated substrings that will replace
     *                    those indicated in the baseString
     * 
     * @return A string that is an altered version of the baseString with the
     *         substrings replaced in the manner indicated above.
     */
    public static String replaceText(String baseString, String replacement) {
        // If there are no parentheses in the baseString, return it unaltered
        if (!containsParentheses(baseString)) {
            return baseString;
        }

        // Otherwise proper inspection of the strings is necessary
        else {
            // Create a StringBuilder to assist in the creation of a modified string
            StringBuilder newString = new StringBuilder();

            // Create two variables to keep track of indices within the two inputs
            int baseIndex = 0; 
            int replaceIndex = 0;

            // Create a variable to keep track of whether or not a replacement must be done
            boolean replacementTime = false;

            // Create a variable to manage potential parenthetical mismatches and indices based off of helper method
            int mismatch = 0;

            // This variable keeps track of the state of a substring for replacements
            int replaceState = 0;

            // This variable indicates that all further replacements will fail
            boolean failure = false;

            // Loop through the baseString until indices are exhausted
            while (baseIndex < baseString.length()) {
                // If it is not time to do a replacement and there is not an open parenthesis at the current index, append
                if (!replacementTime && baseString.charAt(baseIndex) != '(') {
                    // Append the next character in the base String
                    newString.append(baseString.charAt(baseIndex));
                    
                    // Increment the base index by 1
                    baseIndex = baseIndex + 1;
                }

                // If an open parentheses is at the current index, then look for a substring
                else if (!replacementTime && baseString.charAt(baseIndex) == '(') {
                    // Use the helper method to determine if a mismatch is present or not
                    mismatch = isMismatched(baseString, baseIndex);

                    // If no other parentheses are found after the current one, then append the rest of the baseString
                    if (mismatch == -1) {
                        for (int i = baseIndex; i < baseString.length(); i++) {
                            newString.append(baseString.charAt(i));
                        }

                        // Return the StringBuilder as the entire baseString was appended
                        return newString.toString();
                    }

                    // If parentheses are found, then mismatch is 0 and a replacement can be done as usual
                    else if (mismatch == 0 && failure == false) {
                        // Find the closing index of the matched parentheses through a loop
                        while (baseString.charAt(baseIndex) != ')') {
                            // Each iteration looks for a closing parentheses and if none is found then increments baseIndex
                            baseIndex++;
                        }

                        // It is replacement time since a substring in the base was found
                        replacementTime = true;

                        // To prevent overlap, increment the baseIndex by 1 to start at the next non-substring character
                        baseIndex = baseIndex + 1;
                    }

                    // Any other value for mismatch means a mismatch was found at the value stored in mismatch
                    else {
                        // First check if there are nested parentheses in the substring
                        int nested = nestedParentheses(baseString, baseIndex, 1);

                        // If there are nested parentheses, then indicate it is time for replacement and move the baseIndex up
                        if (nested > 0) {
                            replacementTime = true;

                            // The base index should become one greater than the outputted int value into nested
                            baseIndex = nested + 1;
                        }

                        // If there aren't any nested parentheses, look for a mismatch starting at the mismatch index
                        else if (nested == 0) {
                            // first, append up to the mismatch index as it is guaranteed to not be part of the substring
                            for (int i = baseIndex; i < mismatch; i++) {
                                newString.append(baseString.charAt(i));
                            }
                            
                            // This variable holds the possible index for the end of the substring
                            int substring = isSubstring(baseString, mismatch, newString);

                            // If a substring has been found, indicate it is replacement time
                            if (substring > 0 && failure == false) {
                                replacementTime = true;

                                // Set the index of the base to one after the substring index
                                baseIndex = substring + 1;
                            }

                            // If failure has ocurred, set the index of the base to one after the substring
                            else if (substring > 0 && failure) {
                                baseIndex = substring + 1;
                            }

                            // If the entire string was appended as a result of no more parentheses matching, return the newString
                            else if (substring == -1) {
                                return newString.toString();
                            }
                        } 

                        // Otherwise there is not nested parentheses, increment the index after appending the next character
                        else {
                            newString.append(baseString.charAt(baseIndex));
                            baseIndex++;
                        }
                    }

                    // If failure has ocurred, set replacement time to false
                    if (failure) {
                        replacementTime = false;
                    } 
                }

                // If it is time for a replacement, step into this branch
                if (replacementTime && failure == false) {
                    // Loop through the replacements to find the first parentheses
                    while (replaceIndex < replacement.length() && replacement.charAt(replaceIndex) != '(') {
                        // If the conditions above are met, then keep searching the string
                        replaceIndex++;
                    }

                    // If the replacementIndex is too large for its string, then it is no longer replacement time
                    if (replaceIndex >= replacement.length() - 1) {
                        // If the index exceeds the replacement string, then failure has ocurred and replacementTime is false
                        replacementTime = false;
                        failure = true;
                    }

                    // Otherwise, the end of the replacement must be found
                    else {
                        // Check to see if the found parentheses if near a mismatch
                        mismatch = isMismatched(replacement, replaceIndex);

                        // If a mismatch is found, set the replaceIndex to the mismatch value and continue
                        if (mismatch >= 0) {
                            // First check if there are nested parentheses associated with the mismatch
                            int nested = nestedParentheses(replacement, replaceIndex, 1);

                            // Change the replaceIndex to align with the mismatch value only if the parentheses are not nested
                            if (mismatch > 0 && nested <= 0) {
                                replaceIndex = mismatch;
                            }

                            // Use my helper method to determine if it is a substring or not/ the builder is null to not interfere
                            replaceState = isSubstring(replacement, replaceIndex, null);                        

                            // If the state of the replacement is positive, then append up to the value in replaceState
                            if (replaceState > 0) {
                                // Append the characters between replaceIndex and replaceState 
                                for (int i = replaceIndex + 1; i < replaceState; i++) {
                                    newString.append(replacement.charAt(i));
                                }

                                // A replacement has been done so indicate that it is no longer replacement time
                                replacementTime = false;

                                // Set the replaceIndex to the value after replaceState for the continuation of the method
                                replaceIndex = replaceState + 1;
                            }

                            // If the state is anything else, then a replacement can not be done, indicate it with the boolean
                            else {
                                replacementTime = false;

                                // Set the failure variable to true because nothing else can be used as a replacement
                                failure = true;
                            }
                        }
                        
                        // If the state is anything else, then a replacement can not be done, indicate it with the boolean
                        else {
                            replacementTime = false;

                            // Set the failure variable to true because nothing else can be used as a replacement
                            failure = true;
                        }
                        
                    }

                    // If this branch is entered, then replacement must now be false
                    replacementTime = false;
                }
            }

            // Return the StringBuilder to the user
            return newString.toString();
        }
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
}
