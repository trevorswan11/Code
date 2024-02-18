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
        assertTrue(HW2.samePrefix("hello", "hey", 0));
        assertTrue(HW2.samePrefix("","",0));
        assertTrue(HW2.samePrefix("hello","hey",0));
        assertFalse(HW2.samePrefix("","",100));

    }

    /* This tests the matchingParentheses method */
    @Test
    public void testMatchingParentheses() {

    }

    /* This tests the removeEveryKthWord method */
    @Test
    public void testRemoveEveryKthWord() {

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
