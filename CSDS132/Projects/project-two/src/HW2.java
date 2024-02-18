/* Trevor Swan
 * HW2 Class - contains static methods that make use of loops to manipulate and examine strings
 * CSDS132 - Project 2
 */

public class HW2 {
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
        return true;
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
        return true;
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
        return null;
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
        return null;
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
        return null;
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
        
    }
}