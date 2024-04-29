package IteratorChallenge;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntegerRangeTest {
    // compares an int to an Integer
    private void assertNextEquals(int expected, Integer next) {
        assertEquals(expected, next.intValue());
    }

    // Tests the exception thrown and iterator ability
    @Test
    public void nextTest() {
        IntegerRange tester = new IntegerRange(1, 10);
        Iterator<Integer> it = tester.iterator();

        assertTrue(it.hasNext());
        
        try {
            for (int i = tester.getMinimum(); i <= tester.getMaximum(); i++) {
                assertNextEquals(i, it.next());
            }
        } catch (NoSuchElementException e) {
            assertTrue(true);
        } catch (Exception e) {
            fail("Unhandled Exception thrown: " + e.getClass());
        }

        assertFalse(it.hasNext());
    }
}
