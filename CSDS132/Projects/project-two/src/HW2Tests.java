/* Trevor Swan
 * HW2Tests Class - contains all commented JUnit tests for the HW2 Class 
 * CSDS132 - Project 2 Tests
 */

 import org.junit.*;
 import static org.junit.Assert.*;

public class HW2Tests {
    /* This tests the samePrefix method */
    @Test
    public void testSamePrefix() {
        // Test string lengths of 0
        assertTrue(HW2.samePrefix("","",0));

        // Test string lengths of 0 with Large comparison index
        assertFalse(HW2.samePrefix("","",1));

        // Test a character amount of 1 with identical first letters
        assertTrue(HW2.samePrefix("h", "h", 1));

        // Test a character amount of 1 with different first letters
        assertFalse(HW2.samePrefix("H","h",1));

        // Test a character amount of 4 with identical strings
        assertTrue(HW2.samePrefix("this", "this", 4));

        // Test a character amount of 4 with different strings
        assertFalse(HW2.samePrefix("This", "this", 4));

        // Test a character amount greater than the length of one string
        assertFalse(HW2.samePrefix("this", "this is", 5));

        // Test a character amount much greater than both strings
        assertFalse(HW2.samePrefix("this", "this", 100));

        // Test strings that are identical up to the integer inputted
        assertTrue(HW2.samePrefix("this test", "this trial", 6));

        // Test strings that differ at the integer inputted
        assertFalse(HW2.samePrefix("this test", "this trial", 7));
    }

    /* This tests the matchingParentheses method */
    @Test
    public void testMatchingParentheses() {
        // Test an empty string
        assertTrue(HW2.matchingParentheses(""));

        // Test a string that has no parentheses
        assertTrue(HW2.matchingParentheses("this is a test!"));

        // Test a string with one set of matching parentheses
        assertTrue(HW2.matchingParentheses("(this) is a test"));

        // Test a string with multiple sets of matching parentheses
        assertTrue(HW2.matchingParentheses("(this) is a ((test) of parentheses)"));

        // Test a string with closing parentheses first
        assertFalse(HW2.matchingParentheses(") (wow!)"));

        // Test a string with all matching parentheses besides a ( in the middle
        assertFalse(HW2.matchingParentheses("(this) is a ((test) of parenthe(ses)"));

        // Test a string with all matching parentheses besides a ) in the middle
        assertFalse(HW2.matchingParentheses("(this) is a ((test) of parenthe)ses)"));

        // Test a string with opening parentheses last
        assertFalse(HW2.matchingParentheses("(wow!) ("));

        // Test a string with only parentheses, that are matched
        assertTrue(HW2.matchingParentheses("(((())))"));

        // Test a string with only parentheses, that are unmatched
        assertFalse(HW2.matchingParentheses("(((()))"));
    }

    /* This tests the removeEveryKthWord method */
    @Test
    public void testRemoveEveryKthWord() {
        // Test an empty string
        assertEquals("",HW2.removeEveryKthWord("", 0));

        // Test an empty string with a positive k
        assertEquals("",HW2.removeEveryKthWord("", 100));

        // Test a long string with a zero k
        assertEquals("Testing this method", HW2.removeEveryKthWord("Testing this method", 0));

        // Test a long string with a negative k
        assertEquals("Testing this method", HW2.removeEveryKthWord("Testing this method", -10));
        
        // Test a long string with k = 1, removing every single word
        assertEquals("", HW2.removeEveryKthWord("Testing this method", 1));

        // Tests removing the last word in a string
        assertEquals("This is a test ", HW2.removeEveryKthWord("This is a test yeah!", 5));

        // Test removing every 4 in a string with 8 words
        assertEquals("This is such a test wow! ", HW2.removeEveryKthWord("This is such womp a test wow! womp", 4));

        // Test the first example from the instructions 
        assertEquals("Four score seven years our fore ", HW2.removeEveryKthWord("Four score and seven years ago our fore fathers", 3));

        // Test the second example from the instructions - leading zeros
        assertEquals(" Every down Whoville Christmas lot!",HW2.removeEveryKthWord(" Every Who down in Whoville liked Christmas a lot!", 2));

        // Test if trailing zeros are dropped completely
        assertEquals("This is a funky little trial", HW2.removeEveryKthWord("This womp is womp a womp funky womp little womp trial        ", 2));
    }

    /* This test the flipEachK method */
    @Test
    public void testFlipEachK() {

    }

    /* This tests the reverseDigits method */
    @Test
    public void testReverseDigits() {

    }

    /* This tests the replaceText method */
    @Test
    public void testReplaceText() {

    }
    
    /* This tests the reverseAll method */
    @Test
    public void testReverseAll() {

    }
}
