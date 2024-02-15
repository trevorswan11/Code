/* Trevor Swan
 * Simon Eskin
 * Curtis Li
 * 2/15/24 Lab5
 */

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Lab5Tester {

    @Test
    public void testCountSpaces() {
        assertEquals(0, Lab5.countSpaces("abcde"));
        assertEquals(4, Lab5.countSpaces("a b c d e"));
    }

    @Test
    public void testRemoveSpaces() {
        assertEquals("CSDS132", Lab5.removeSpaces("CSDS132"));
        assertEquals("CSDS132", Lab5.removeSpaces(" CSDS132"));
        assertEquals("CSDS132", Lab5.removeSpaces("CSDS132 "));
        assertEquals("CSDS132", Lab5.removeSpaces(" C S D S 1 3 2 "));
        assertEquals("CSDS132", Lab5.removeSpaces(" CS      DS 1  3 2        "));
    }

    @Test
    public void testEveryNthCharacter() {
        assertEquals("a c e g i k m", Lab5.everyNthChar("abcdefghijklmn", 2));
    }
}
