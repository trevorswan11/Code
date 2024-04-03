package projectTwo;

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

        // Test string lengths of 0 with comparison 1
        assertFalse(HW2.samePrefix("","",1));

        // Test string lengths of 0 with large comparison
        assertFalse(HW2.samePrefix("","",100));

        // Test a character amount of 1 with identical first letters
        assertTrue(HW2.samePrefix("h", "h", 0));

        // Test a character amount of 1 with different first letters and 0 comparison
        assertTrue(HW2.samePrefix("m", "h", 0));

        // Test a character amount of 1 with different first letters and negative comparison
        assertTrue(HW2.samePrefix("m", "h", -1));

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

        // Test the first example from the instructions
        assertTrue(HW2.samePrefix("this is a test", "this is a trial", 11));

        // Test the second example from the instructions
        assertFalse(HW2.samePrefix("this is a test", "this is a trial", 12));

        // Test the third example from the instructions
        assertFalse(HW2.samePrefix("this is a test", "This is a trial", 4));

        // Test the fourth example from the instructions
        assertFalse(HW2.samePrefix("this is a test", "this is a test", 100));
    }

    /* This tests the matchingParentheses method */
    @Test
    public void testMatchingParentheses() {
        // Test an empty string
        assertTrue(HW2.matchingParentheses(""));

        // Test a string of length 1
        assertTrue(HW2.matchingParentheses("i"));

        // Test a string of length one with only (
        assertFalse(HW2.matchingParentheses("("));

        // Test a string of length one with only )
        assertFalse(HW2.matchingParentheses(")"));

        // Test a string that has no parentheses
        assertTrue(HW2.matchingParentheses("this is a test!"));

        // Test a string with one set of matching parentheses
        assertTrue(HW2.matchingParentheses("(this) is a test"));

        // Test a string with one set of mismatching parentheses
        assertFalse(HW2.matchingParentheses("this )is( a test"));

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

        // Test the first example from the instructions
        assertTrue(HW2.matchingParentheses("This is a (test (of the) (matching)) parentheses"));

        // Test the first example from the instructions
        assertFalse(HW2.matchingParentheses("The (second closing) parenthesis) does not match"));
    }

    /* This tests the removeEveryKthWord method */
    @Test
    public void testRemoveEveryKthWord() {
        // Test an empty string with a negative k
        assertEquals("", HW2.removeEveryKthWord("", -10));

        // Test an empty string
        assertEquals("",HW2.removeEveryKthWord("", 0));

        // Test an empty string with k = 1
        assertEquals("", HW2.removeEveryKthWord("", 1));

        // Test an empty string with a positive k
        assertEquals("",HW2.removeEveryKthWord("", 100));

        // Test a single character string with a negative k
        assertEquals("h", HW2.removeEveryKthWord("h", -10));

        // Test a single character string with 0 k
        assertEquals("h",HW2.removeEveryKthWord("h", 0));

        // Test a single character string with k = 1, removing every single word
        assertEquals("", HW2.removeEveryKthWord("h", 1));

        // Test a single character string with a positive k
        assertEquals("h",HW2.removeEveryKthWord("h", 100));

        // Test a long string with a negative k
        assertEquals("Testing this method", HW2.removeEveryKthWord("Testing this method", -10));

        // Test a long string with a zero k
        assertEquals("Testing this method", HW2.removeEveryKthWord("Testing this method", 0));

        // Test a long string with k = 1, removing every single word
        assertEquals("", HW2.removeEveryKthWord("Testing this method", 1));

        // Test a long string with k > number of words, changing nothing
        assertEquals("Testing this method", HW2.removeEveryKthWord("Testing this method", 100));

        // Tests removing the last word in a string
        assertEquals("This is a test ", HW2.removeEveryKthWord("This is a test yeah!", 5));

        // Test removing every 4 in a string with 8 words
        assertEquals("This is such a test wow! ", HW2.removeEveryKthWord("This is such womp a test wow! womp", 4));

        // Test if trailing spaces are dropped completely
        assertEquals("This is a funky little trial",
            HW2.removeEveryKthWord("This womp is womp a womp funky womp little womp trial        ", 2));

        // Test the first example from the instructions 
        assertEquals("Four score seven years our fore ", 
            HW2.removeEveryKthWord("Four score and seven years ago our fore fathers", 3));

        // Test the second example from the instructions - leading zeros
        assertEquals(" Every down Whoville Christmas lot!", 
            HW2.removeEveryKthWord(" Every Who down in Whoville liked Christmas a lot!", 2));
    }

    /* This test the flipEachK method */
    @Test
    public void testFlipEachK() {
        // Test an empty string with negative k
        assertEquals("",HW2.flipEachK("", -10));

        // Test an empty string with k = 0
        assertEquals("", HW2.flipEachK("", 0));

        // Test an empty string with k = 1
        assertEquals("", HW2.flipEachK("", 1));

        // Test an empty string with a large k
        assertEquals("", HW2.flipEachK("", 100));

        // Test a single character string with negative k
        assertEquals("h",HW2.flipEachK("h", -10));

        // Test a single character string with k = 0
        assertEquals("h", HW2.flipEachK("h", 0));

        // Test a single character string with k = 1
        assertEquals("h", HW2.flipEachK("h", 1));

        // Test a single character string with a k larger than the length of the string
        assertEquals("h", HW2.flipEachK("h", 100));

        // Test a single word string with negative k
        assertEquals("this",HW2.flipEachK("this", -10));

        // Test a single word string with k = 0
        assertEquals("this", HW2.flipEachK("this", 0));

        // Test a single word string with k = 1
        assertEquals("this", HW2.flipEachK("this", 1));

        // Test a single word string with a k equal to the string length
        assertEquals("this", HW2.flipEachK("this", "this".length()));

        // Test a single word string with a k half of the string length
        assertEquals("thsi", HW2.flipEachK("this", "this".length() / 2));

        // Test a single word string with a k larger than the length of the string
        assertEquals("this", HW2.flipEachK("this", 100));

        // Test the string from the instructions to test a k such that statement.length() mod k != 0. 
        assertEquals("abcdhgfeijklnm", HW2.flipEachK("abcdefghijklmn", 4));

        // Test a very long string with a k such that statement.length() mod k != 0. 
        assertEquals("Thii ss ats rest sestfo  th simetdoh inht e socend orpjecf tor DSCS132", 
            HW2.flipEachK("This is a stress test of this method in the second project for CSDS132", 3));
    }

    /* This tests the reverseDigits method */
    @Test
    public void testReverseDigits() {
        // Test a string of length 0
        assertEquals("", HW2.reverseDigits(""));

        // Test a string of length 1 with no digits
        assertEquals("h", HW2.reverseDigits("h"));

        // Test a longer length string with no digits
        assertEquals("this is one test", HW2.reverseDigits("this is one test"));

        // Test a string with one digit
        assertEquals("1", HW2.reverseDigits("1"));

        // Test a string with numerically ordered digits
        assertEquals("321", HW2.reverseDigits("123"));

        // Test a string with disordered digits
        assertEquals("145849", HW2.reverseDigits("948541"));

        // Test a string with many digits mixed into a few words
        assertEquals("th3s w5rk0 1h", HW2.reverseDigits("th1s w0rk5 3h"));

        // Test the example from the instructions
        assertEquals("9 the d8gits of the4 string3 2 are1 reversed 0!",
            HW2.reverseDigits("0 the d1gits of the2 string3 4 are8 reversed 9!"));
    }

    /* This tests the replaceText method */
    @Test
    public void testReplaceText() {
        // Test strings of length 0
        assertEquals("", HW2.replaceText("", ""));

        // Test strings of length 1 with no replacements
        assertEquals("h", HW2.replaceText("h", "h"));

        // Test strings of length 5 with no replacements
        assertEquals("hello", HW2.replaceText("hello", "hello"));

        // Test strings of length 2 indicated with only the replacements
        assertEquals("", HW2.replaceText("()", "()"));

        // Test strings of length one with indicated replacements
        assertEquals("e", HW2.replaceText("(a)", "(e)"));

        // Test a base with no indicated substrings, but with ones indicated in replacements
        assertEquals("this is a test", HW2.replaceText("this is a test", "(this)"));

        // Test a base with indicated substrings but none indicated in replacements
        assertEquals("this  test", HW2.replaceText("this (is a) test", "nope"));

        // Test strings with 1 substring in the base and 2 substrings in the replacements
        assertEquals("hello", HW2.replaceText("(goodbye)", "(hello) (world)"));

        // Test strings with 2 substrings in the base and 1 substring in the replacements
        assertEquals("hello ", HW2.replaceText("(goodbye) (world)", "(hello)"));

        // Test strings with equal amounts of substrings in the two strings, but more than 1 word per substring
        assertEquals("this wasn't the first test", HW2.replaceText("this (is a) test", "(wasn't the first)"));

        // Test strings with equal amounts of substrings in the two strings, but more than 1 substring per input
        assertEquals("hello world", HW2.replaceText("(goodbye) (globe)", "(hello) (world)"));

        /* Test strings with equal amounts of substrings in the two strings, but with nested matched parentheses in the replacements
         * This is identical to the previous test but along with nested matched parentheses */
        assertEquals("hell()o wor()ld", HW2.replaceText("(goodbye) (globe)", "(hell()o) (wor()ld)"));

        /* Test strings with equal amounts of substrings in the two strings, but with nested matched parentheses in the base
         * This is identical to the previous test but along with nested matched parentheses */
        assertEquals("hello world", HW2.replaceText("(good()bye) (glo()be)", "(hello) (world)"));

        // Test strings with mismatched parentheses in the base but matched in the replacements
        assertEquals("this tests (a mismatched in the base",
            HW2.replaceText("this tests (a () in the base", "(mismatched)"));

        // Test strings with matched parentheses in the base but unmatched in the replacements
        assertEquals("this tests a thing in the replacements", 
            HW2.replaceText("this tests (mismatched parentheses) in the replacements", "(a thing))"));

        // Test mismatched parentheses in the base with mismatched parentheses in the replacements
        assertEquals("testing mismatches)", HW2.replaceText("testing (mis()matches))", "((mismatches)"));

        // Test a base with too many substrings that are also  mismatched, replacements are not mismatched
        assertEquals("hello (globworld ", HW2.replaceText("hello (glob(e) (nope)", "(world)"));

        // Test a base with correct everything but replacements with too many substrings that are also mismatched
        assertEquals("this is a trial of robustness", 
            HW2.replaceText("this is a (test) of robust()", "(trial)) (ness) (do not include)"));

        // Test a base with mismatched parentheses with too many substrings for mismatched replacements
        assertEquals("this is a trial of robustness from  me", 
            HW2.replaceText("this is a (test) of robust() (do not include) me", "((trial) of (ness from)"));
        
        // Test a base with mismatched parentheses with not enough substrings for matched replacements
        assertEquals("this is (a TRIAL of robustness from me", 
            HW2.replaceText("this is (a (test) of robust() me", "(TRIAL) (ness from) (please don't include me)"));

        // Test a base with mismatched parentheses with too many substrings for matched replacements
        assertEquals("this is (a TRIAL of robustness from me ", 
            HW2.replaceText("this is (a (test) of robust() me (please don't include me)", "(TRIAL) (ness from)"));

        // Test the first example from the instructions
        assertEquals("a cool programming problem", 
            HW2.replaceText("a (simple) programming (example)", "(cool) (problem)"));

        // Test the second example from the instructions
        assertEquals("a answer with really (two) not three replacements ", 
            HW2.replaceText("a ((nested) example) with (three) replacements (to (handle))", "the replacements are (answer) and (really (two) not three)"));
    }
    
    /* This tests the reverseAll method */
    @Test
    public void testReverseAll() {

    }
}
