package projectFour;

import java.lang.reflect.Method;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * A comprehensive class and game tester for the 2048 parody called SlideGame.
 * 
 * 
 * @author Trevor Swan
 * @version CSDS132 - Spring 2024
 */
@SuppressWarnings("unused")
public class SlideGameTest {
    // SlideGame object to be used in testing
    SlideGame game = new SlideGame();

    // Test the getter setter methods for the board dimensions
    @Test
    public void boardDimTest() {
        game.setBoardDim(null); // Test null
        assertEquals(null, game.getBoardDim());
        game.setBoardDim(new int[] {0, 0}); // Test 0
        assertArrayEquals(new int[] {0, 0}, game.getBoardDim());
        game.setBoardDim(new int[] {1, 1}); // Test 1
        assertArrayEquals(new int[] {1, 1}, game.getBoardDim());
        game.setBoardDim(new int[] {5, 5}); // Test many
        assertArrayEquals(new int[] {5, 5}, game.getBoardDim());
    }

    /**
     * This private helper method Compares two game boards.
     * 
     * @param expected The expected array 
     * @param received The actual array from the method call
     */
    private void assertBoardEquals(int[][] expected, int[][] received) {
        // Check to see if the arrays are the same length
        assertEquals(expected.length, received.length);

        // Loop through the parent array and compare each nested array
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], received[i]);
        }
    }

    // Test the getter methods for the game board array
    @Test
    public void gameBoardTest() {

    }
}
