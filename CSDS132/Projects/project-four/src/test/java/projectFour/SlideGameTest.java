package projectFour;

import java.lang.reflect.InvocationTargetException;
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
public class SlideGameTest{
    // The group of private Methods that will be pulled from the SlideGame class 
    Method setPosition;
    Method emptyBoard;
    Method randomIndex;

    // SlideGame object to be used in testing
    SlideGame game = new SlideGame();

    /**
     * This helper method declares the private methods to be tested.
     * @throws SecurityException when needed, java handles
     * @throws NoSuchMethodException when method name does not exist
     */
    private void privateMethods() throws NoSuchMethodException, SecurityException {
        // grab the position setter method
        setPosition = SlideGame.class.getDeclaredMethod("setPosition", int.class, int[].class);
        setPosition.setAccessible(true);

        // Grab the empty board helper method
        emptyBoard = SlideGame.class.getDeclaredMethod("emptyBoard", int[].class);
        emptyBoard.setAccessible(true);
        
        // Grab the randomIndex method
        randomIndex = SlideGame.class.getDeclaredMethod("randomIndex");
        randomIndex.setAccessible(true);
    }
    
    /**
     * This private helper method Compares two game boards.
     * 
     * @param expected The expected array 
     * @param actual The actual array from the method call
     */
    private void assertBoardEquals(int[][] expected, int[][] actual) {
        // Check to see if the arrays are the same length
        assertEquals(expected.length, actual.length);

        // Loop through the parent array and compare each nested array
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i]);
        }
    }

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


    // Test the methods for the game board array
    @Test
    public void gameBoardTest()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        // Use the private helper method to create the methods
        privateMethods();

        // Arrays to be used in the method calls
        int[] dim = new int[] { 5, 7 };
        int[][] board = { 
            { 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0 } 
        };

        // set the game boards dimensions to 5, 7
        game.setBoardDim(new int[] { 5, 7 });
        emptyBoard.invoke(game, dim);

        assertBoardEquals(board, game.getGameBoard());
    }
}
