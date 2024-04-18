package projectFour;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javafx.application.Application.Parameters;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * A comprehensive class and game tester for the 2048 parody called SlideGame.
 * 
 * 
 * @author Trevor Swan
 * @version CSDS132 - Spring 2024
 */
public class SlideGameTest {
    // The group of private Methods that will be pulled from the SlideGame class
    Method copyBoard;
    Method parseArgs;
    Method setPosition;
    Method emptyBoard;
    Method randomIndex;
    Method flipHorizontal;
    Method flipVertical;
    Method transpose;

    // SlideGame object to be used in testing
    SlideGame game = new SlideGame();

    /**
     * This helper method declares the private methods to be tested.
     * 
     * @throws SecurityException     when needed, java handles
     * @throws NoSuchMethodException when method name does not exist
     */
    private void privateMethods() throws SecurityException, NoSuchMethodException {
        // Grab the copyBoard method
        copyBoard = SlideGame.class.getDeclaredMethod("copyBoard", int[][].class);
        copyBoard.setAccessible(true);

        // grab the parseArgs method
        parseArgs = SlideGame.class.getDeclaredMethod("parseArgs", Parameters.class);
        parseArgs.setAccessible(true);

        // Grab the empty board helper method
        emptyBoard = SlideGame.class.getDeclaredMethod("emptyBoard", int[].class);
        emptyBoard.setAccessible(true);

        // grab the position setter method
        setPosition = SlideGame.class.getDeclaredMethod("setPosition", int.class, int[].class);
        setPosition.setAccessible(true);

        // Grab the randomIndex method
        randomIndex = SlideGame.class.getDeclaredMethod("randomIndex");
        randomIndex.setAccessible(true);

        // Grab the flipHorizontal method
        flipHorizontal = SlideGame.class.getDeclaredMethod("flipHorizontal");
        flipHorizontal.setAccessible(true);

        // Grab the flipVertical method
        flipVertical = SlideGame.class.getDeclaredMethod("flipVertical");
        flipVertical.setAccessible(true);

        // Grab the transpose method
        transpose = SlideGame.class.getDeclaredMethod("transpose");
        transpose.setAccessible(true);
    }

    /**
     * A nested class that will be used to test the parseArgs method. It extends
     * the Parameters class.
     * 
     * @author Trevor Swan
     * @version CSDS132 - Spring 2024
     */
    private static class Params extends Parameters {
        // Raw args
        private List<String> raw;

        public Params(List<String> args) {
            this.raw = args;
        }

        /**
         * Returns the args of the parameters.
         *
         * @return The args of the parameters
         */
        @Override
        public List<String> getRaw() {
            return this.raw;
        }

        // No needed implementation, return null
        @Override
        public List<String> getUnnamed() {
            return null;
        }

        // No needed implementation, return null
        @Override
        public Map<String, String> getNamed() {
            return null;
        }
    }

    /**
     * This helper method creates an array of zeros with given row and column
     * values.
     * 
     * @param row the desired number of rows
     * @param col the desired number of columns
     * @return an array of zeros with the given row and column values
     */
    private int[][] zeroBoard(int row, int col) {
        // Create the actual board for results
        int[][] board = new int[row][col];

        // Loop through the array and set all the indices to zero
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = 0;
            }
        }

        // Return the array
        return board;
    }

    /**
     * This private helper method Compares two game boards.
     * 
     * @param expected The expected array
     * @param actual   The actual array from the method call
     */
    private void assertBoardEquals(int[][] expected, int[][] actual) {
        // Check to see if the arrays are the same length
        assertEquals("Length mismatch",expected.length, actual.length);

        // Loop through the parent array and compare each nested array
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals("Nested mismatch @ " + i, expected[i], actual[i]);
        }
    }

    /**
     * This private helper method creates a game board with the given row and
     * column values. Values in the array ascend from 0 to row * col - 1.
     * 
     * @param row the desired number of rows
     * @param col the desired number of columns
     * @return an array of zeros with the given row and column values
     */
    private int[][] ascendBoard(int row, int col) {
        // Create the actual board for results
        int[][] board = new int[row][col];

        int value = 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = value++;
            }
        }

        // Return the array
        return board;
    }

    // Test the getter setter methods for the board dimensions
    @Test
    public void boardDimTest() {
        game.setBoardDim(null); // Test null
        assertArrayEquals(new int[] { 4, 4 }, game.getBoardDim());
        game.setBoardDim(new int[] { 0, 0 }); // Test 0
        assertArrayEquals(new int[] { 4, 4 }, game.getBoardDim());
        game.setBoardDim(new int[] { 1, 1 }); // Test 1
        assertArrayEquals(new int[] { 4, 4 }, game.getBoardDim());
        game.setBoardDim(new int[] { 5, 5 }); // Test many
        assertArrayEquals(new int[] { 5, 5 }, game.getBoardDim());
        game.setBoardDim(new int[] { 6, 5 }); // Test unequal many
        assertArrayEquals(new int[] { 6, 5 }, game.getBoardDim());
        game.setBoardDim(new int[] { 1 }); // Test unusual 1D
        assertArrayEquals(new int[] { 4, 4 }, game.getBoardDim());
    }

    // Test the copy board method
    @Test
    public void copyBoardTest() throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, SecurityException, NoSuchMethodException {
        // Use the private helper method to create the methods
        privateMethods();

        // Create arrays to store expected and actual results
        int[][] expectedBoard;
        int[][] actualBoard;

        // Test null
        assertArrayEquals(null, (int[][]) copyBoard.invoke(game, (Object) null));

        // Test zero length
        expectedBoard = null;
        actualBoard = (int[][]) copyBoard.invoke(game, (Object) zeroBoard(0, 0));
        assertArrayEquals(expectedBoard, actualBoard);

        // Test 1D
        expectedBoard = zeroBoard(1, 1);
        actualBoard = (int[][]) copyBoard.invoke(game, (Object) expectedBoard);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a bigger square board
        expectedBoard = ascendBoard(5, 5);
        actualBoard = (int[][]) copyBoard.invoke(game, (Object) expectedBoard);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a bigger rectangle board
        expectedBoard = ascendBoard(5, 4);
        actualBoard = (int[][]) copyBoard.invoke(game, (Object) expectedBoard);
        assertBoardEquals(expectedBoard, actualBoard);
    }

    // Test the parseArgs method and getter setters by association
    @Test
    public void parseArgsTest()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
            SecurityException {
        // Use the private helper method to create the methods
        privateMethods();

        // Params object to use
        Params params;

        // Test non-numeric entires in both slots
        params = new Params(Arrays.asList("a", "b"));
        parseArgs.invoke(game, (Parameters) params);
        assertArrayEquals(new int[] { 4, 4 }, game.getBoardDim());

        // Test non-numeric entires in one slot
        params = new Params(Arrays.asList("a", "7"));
        parseArgs.invoke(game, (Parameters) params);
        assertArrayEquals(new int[] { 4, 7 }, game.getBoardDim());

        // Test non-negative entries in the other slot
        params = new Params(Arrays.asList("7", "a"));
        parseArgs.invoke(game, (Parameters) params);
        assertArrayEquals(new int[] { 7, 4 }, game.getBoardDim());

        // Test negative one
        params = new Params(Arrays.asList("-1", "-1"));
        parseArgs.invoke(game, (Parameters) params);
        assertArrayEquals(new int[] { 4, 4 }, game.getBoardDim());

        // Test zero
        params = new Params(Arrays.asList("0", "0"));
        parseArgs.invoke(game, (Parameters) params);
        assertArrayEquals(new int[] { 4, 4 }, game.getBoardDim());

        // Test invalid dim in row slot
        params = new Params(Arrays.asList("0", "4"));
        parseArgs.invoke(game, (Parameters) params);
        assertArrayEquals(new int[] { 4, 4 }, game.getBoardDim());

        // test invalid dim in col slot
        params = new Params(Arrays.asList("4", "0"));
        parseArgs.invoke(game, (Parameters) params);
        assertArrayEquals(new int[] { 4, 4 }, game.getBoardDim());

        // Test one
        params = new Params(Arrays.asList("1", "1"));
        parseArgs.invoke(game, (Parameters) params);
        assertArrayEquals(new int[] { 4, 4 }, game.getBoardDim());

        // Test default
        params = new Params(Arrays.asList("4", "4"));
        parseArgs.invoke(game, (Parameters) params);
        assertArrayEquals(new int[] { 4, 4 }, game.getBoardDim());

        // Test many (larger than 4 in both slots)
        params = new Params(Arrays.asList("5", "5"));
        parseArgs.invoke(game, (Parameters) params);
        assertArrayEquals(new int[] { 5, 5 }, game.getBoardDim());

        // Test many (larger than 4 in both slots but not equal)
        params = new Params(Arrays.asList("5", "6"));
        parseArgs.invoke(game, (Parameters) params);
        assertArrayEquals(new int[] { 5, 6 }, game.getBoardDim());

        // Test many (same as above but reversed positions)
        params = new Params(Arrays.asList("6", "5"));
        parseArgs.invoke(game, (Parameters) params);
        assertArrayEquals(new int[] { 6, 5 }, game.getBoardDim());

        // Test many (one dimension exceeding max of 11)
        params = new Params(Arrays.asList("10", "12"));
        parseArgs.invoke(game, (Parameters) params);
        assertArrayEquals(new int[] { 10, 11 }, game.getBoardDim());

        // Test many (other dimension exceeding max of 11)
        params = new Params(Arrays.asList("12", "10"));
        parseArgs.invoke(game, (Parameters) params);
        assertArrayEquals(new int[] { 11, 10 }, game.getBoardDim());

        // Test many (both dimensions exceeding max of 11)
        params = new Params(Arrays.asList("12", "12"));
        parseArgs.invoke(game, (Parameters) params);
        assertArrayEquals(new int[] { 11, 11 }, game.getBoardDim());

        // Test many (more than two entries)
        params = new Params(Arrays.asList("5", "6", "7"));
        parseArgs.invoke(game, (Parameters) params);
        assertArrayEquals(new int[] { 5, 6 }, game.getBoardDim());

        // Test 'few' (only one argument)
        params = new Params(Arrays.asList("5"));
        parseArgs.invoke(game, (Parameters) params);
        assertArrayEquals(new int[] { 5, 4 }, game.getBoardDim());
    }

    // Test the methods for the game board array empty initializer
    @Test
    public void gameBoardTest()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        // Use the private helper method to create the methods
        privateMethods();

        // Stores actual result
        int[][] actualBoard;

        // Array to be used in zero (null size)
        int[] dimZero = null;

        // Use reflection to test zero
        actualBoard = (int[][]) emptyBoard.invoke(game, dimZero);
        assertArrayEquals(null, actualBoard);

        // Array to be used in zero (null size)
        int[] dimZero2 = new int[] { 0, 0 };
        int[][] boardZero = null;

        // Use reflection to test zero again
        actualBoard = (int[][]) emptyBoard.invoke(game, dimZero2);
        assertArrayEquals(boardZero, actualBoard);

        // Array to be used in one (Smallest size, unusable in actual game play)
        int[] dimOne = new int[] { 1, 1 };
        int[][] boardOne = { { 0 } };

        // use reflection to test one
        actualBoard = (int[][]) emptyBoard.invoke(game, dimOne);
        assertBoardEquals(boardOne, actualBoard);

        // Array to be used in medium test (medium size)
        int[] dimMedium = new int[] { 5, 5 };
        int[][] boardMedium = zeroBoard(5, 5);

        // use reflection to test many
        actualBoard = (int[][]) emptyBoard.invoke(game, dimMedium);
        assertBoardEquals(boardMedium, actualBoard);

        // Array to be used in the method call for many (larger size)
        int[] dimLarge = new int[] { 10, 10 };
        int[][] boardLarge = zeroBoard(10, 10);

        // use reflection to test many
        actualBoard = (int[][]) emptyBoard.invoke(game, dimLarge);
        assertBoardEquals(boardLarge, actualBoard);
    }

    // Test the set position method
    @Test
    public void setPositionTest()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        // Use the private helper method to create the methods
        privateMethods();

        // Set the game's instance to an empty 5 x 5
        game.setGameBoard((int[][]) emptyBoard.invoke(game, new int[] { 5, 5 }));

        // Create the actual board for results
        int[][] actualBoard = this.zeroBoard(5, 5);

        // Test changing a slot outside the bounds
        setPosition.invoke(game, 90, new int[] { 6, 6 });
        assertBoardEquals(actualBoard, game.getGameBoard());

        // Test 0, changing nothing effectively
        setPosition.invoke(game, 0, new int[] { 1, 1 });
        assertBoardEquals(actualBoard, game.getGameBoard());

        // Test 1, changing one spot
        int[][] boardOne = actualBoard;
        boardOne[1][1] = 1;
        setPosition.invoke(game, 1, new int[] { 1, 1 });
        assertBoardEquals(boardOne, game.getGameBoard());

        // Test Many, changing many slots
        int[][] boardMany = actualBoard;
        boardMany[1][1] = 1;
        boardMany[2][2] = 1;
        boardMany[3][3] = 1;
        boardMany[4][4] = 1;
        setPosition.invoke(game, 1, new int[] { 1, 1 });
        setPosition.invoke(game, 1, new int[] { 2, 2 });
        setPosition.invoke(game, 1, new int[] { 3, 3 });
        setPosition.invoke(game, 1, new int[] { 4, 4 });
        assertBoardEquals(boardMany, game.getGameBoard());
    }

    // Test the methods for random index
    @Test (expected = InvocationTargetException.class)
    public void randomIndexTest()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        // Use the private helper method to create the methods
        privateMethods();

        // Create the actual result index
        int[] actualIndex = new int[2];

        // Test one (a board with one empty spot)
        int[][] boardOne = new int[][] {
                { 1, 1, 1, 1, 1 },
                { 1, 0, 1, 1, 1 },
                { 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1 }
        };
        game.setGameBoard(boardOne);

        // Use reflection to test one. Test many times to ensure correctness
        for (int i = 0; i < 100; i++) {
            actualIndex = (int[]) randomIndex.invoke(game);
            assertArrayEquals(new int[] { 1, 1 }, actualIndex);
        }

        // Test many (a board with mostly zeros)
        int[][] boardManyOne = new int[5][5];
        boardManyOne[0][0] = 1;
        boardManyOne[1][1] = 1;
        boardManyOne[2][2] = 1;
        boardManyOne[3][3] = 1;
        boardManyOne[4][4] = 1;
        game.setGameBoard(boardManyOne);

        // Use reflection to test many. Test many times to ensure randomness
        for (int i = 0; i < 100; i++) {
            actualIndex = (int[]) randomIndex.invoke(game);
            assertEquals(0, game.getGameBoard()[actualIndex[0]][actualIndex[1]]);
        }

        // Test many (a board with mostly ones)
        int[][] boardTwo = new int[6][9];

        // Go through and set all the values to 1
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                boardTwo[i][j] = 1;
            }
        }

        // Set some values to zero
        boardTwo[0][0] = 0;
        boardTwo[1][1] = 0;
        boardTwo[2][2] = 0;
        boardTwo[3][3] = 0;
        boardTwo[4][4] = 0;
        boardTwo[5][5] = 0;
        game.setGameBoard(boardTwo);

        // Use reflection to test many. Test many times to ensure randomness
        for (int i = 0; i < 100; i++) {
            actualIndex = (int[]) randomIndex.invoke(game);
            assertEquals(0, game.getGameBoard()[actualIndex[0]][actualIndex[1]]);
        }

        // Test many (a board with all zeros)
        int[][] boardManyThree = new int[5][5];
        game.setGameBoard(boardManyThree);

        // Use reflection to test many. Test many times to ensure randomness
        for (int i = 0; i < 100; i++) {
            actualIndex = (int[]) randomIndex.invoke(game);
            assertEquals(0, game.getGameBoard()[actualIndex[0]][actualIndex[1]]);
        }

        // Test zero (a board with no empty spots)
        int[][] boardZero = new int[][] {
                { 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1 }
        };

        // Set the board with no empty spots and catch exception
        game.setGameBoard(boardZero);
        randomIndex.invoke(game);
    }

    // Test the flip horizontal method
    @Test
    public void flipHorizontalTest()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        // Use the private helper method to create the methods
        privateMethods();

        // Board to represent the actual and expected result
        int[][] actualBoard;
        int[][] expectedBoard;

        // Test zero (a 1 x 1 board)
        int[][] boardZero = new int[][] { { 0 } };
        game.setGameBoard(boardZero);

        // Create the resulting board
        expectedBoard = new int[][] { { 0 } };

        // Use reflection to test zero
        actualBoard = (int[][]) flipHorizontal.invoke(game);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test one (a 2 x 2 board)
        int[][] boardOne = ascendBoard(2, 2);
        game.setGameBoard(boardOne);

        // Create the resulting board
        expectedBoard = new int[][] {
                { 3, 4 },
                { 1, 2 }
        };

        // Use reflection to test one
        actualBoard = (int[][]) flipHorizontal.invoke(game);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test Many (a 5 x 5 board)
        int[][] boardManySquare = ascendBoard(5, 5);
        game.setGameBoard(boardManySquare);

        // Create the resulting board
        expectedBoard = new int[][] {
                { 21, 22, 23, 24, 25 },
                { 16, 17, 18, 19, 20 },
                { 11, 12, 13, 14, 15 },
                { 6, 7, 8, 9, 10 },
                { 1, 2, 3, 4, 5 }
        };

        // Use reflection to test many that is a square
        actualBoard = (int[][]) flipHorizontal.invoke(game);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test many (a 4 x 5 board)
        int[][] boardManyOne = ascendBoard(4, 5);
        game.setGameBoard(boardManyOne);

        // Create the resulting board
        expectedBoard = new int[][] {
                { 16, 17, 18, 19, 20 },
                { 11, 12, 13, 14, 15 },
                { 6, 7, 8, 9, 10 },
                { 1, 2, 3, 4, 5 }
        };

        // Use reflection to test many
        actualBoard = (int[][]) flipHorizontal.invoke(game);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test many (a 5 x 6 board)
        int[][] boardManyTwo = ascendBoard(5, 6);
        game.setGameBoard(boardManyTwo);

        // Create the resulting board
        expectedBoard = new int[][] {
                { 25, 26, 27, 28, 29, 30 },
                { 19, 20, 21, 22, 23, 24 },
                { 13, 14, 15, 16, 17, 18 },
                { 7, 8, 9, 10, 11, 12 },
                { 1, 2, 3, 4, 5, 6 },
        };

        // Use reflection to test many
        actualBoard = (int[][]) flipHorizontal.invoke(game);
        assertBoardEquals(expectedBoard, actualBoard);
    }

    // Test the flip vertical method
    @Test
    public void flipVerticalTest()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        // Use the private helper method to create the methods
        privateMethods();

        // Board to represent the actual and expected result
        int[][] actualBoard;
        int[][] expectedBoard;

        // Test zero (a 1 x 1 board)
        int[][] boardZero = new int[][] { { 0 } };
        game.setGameBoard(boardZero);

        // Create the resulting board
        expectedBoard = new int[][] { { 0 } };

        // Use reflection to test zero
        actualBoard = (int[][]) flipVertical.invoke(game);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test one (a 2 x 2 board)
        int[][] boardOne = ascendBoard(2, 2);
        game.setGameBoard(boardOne);

        // Create the resulting board
        expectedBoard = new int[][] {
                { 2, 1 },
                { 4, 3 }
        };

        // Use reflection to test one
        actualBoard = (int[][]) flipVertical.invoke(game);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test Many (a 5 x 5 board)
        int[][] boardManySquare = ascendBoard(5, 5);
        game.setGameBoard(boardManySquare);

        // Create the resulting board
        expectedBoard = new int[][] {
                { 5, 4, 3, 2, 1 },
                { 10, 9, 8, 7, 6 },
                { 15, 14, 13, 12, 11 },
                { 20, 19, 18, 17, 16 },
                { 25, 24, 23, 22, 21 }
        };

        // Use reflection to test many that is a square
        actualBoard = (int[][]) flipVertical.invoke(game);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test many (a 4 x 5 board)
        int[][] boardManyOne = ascendBoard(4, 5);
        game.setGameBoard(boardManyOne);

        // Create the resulting board
        expectedBoard = new int[][] {
                { 5, 4, 3, 2, 1 },
                { 10, 9, 8, 7, 6 },
                { 15, 14, 13, 12, 11 },
                { 20, 19, 18, 17, 16 }
        };

        // Use reflection to test many
        actualBoard = (int[][]) flipVertical.invoke(game);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test many (a 5 x 6 board)
        int[][] boardManyTwo = ascendBoard(5, 6);
        game.setGameBoard(boardManyTwo);

        // Create the resulting board
        expectedBoard = new int[][] {
                { 6, 5, 4, 3, 2, 1 },
                { 12, 11, 10, 9, 8, 7 },
                { 18, 17, 16, 15, 14, 13 },
                { 24, 23, 22, 21, 20, 19 },
                { 30, 29, 28, 27, 26, 25 }
        };

        // Use reflection to test many
        actualBoard = (int[][]) flipVertical.invoke(game);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test many (a 7 x 9 board - two odd cases)
        int[][] boardManyThree = ascendBoard(7, 9);
        game.setGameBoard(boardManyThree);

        // Create the resulting board
        expectedBoard = new int[][] {
                { 9, 8, 7, 6, 5, 4, 3, 2, 1 },
                { 18, 17, 16, 15, 14, 13, 12, 11, 10 },
                { 27, 26, 25, 24, 23, 22, 21, 20, 19 },
                { 36, 35, 34, 33, 32, 31, 30, 29, 28 },
                { 45, 44, 43, 42, 41, 40, 39, 38, 37 },
                { 54, 53, 52, 51, 50, 49, 48, 47, 46 },
                { 63, 62, 61, 60, 59, 58, 57, 56, 55 }
        };

        // Use reflection to test many
        actualBoard = (int[][]) flipVertical.invoke(game);
        assertBoardEquals(expectedBoard, actualBoard);
    }

    // Test the flip diagonal method
    @Test
    public void transposeTest()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        // Use the private helper method to create the methods
        privateMethods();

        // Board to represent the actual and expected result
        int[][] actualBoard;
        int[][] expectedBoard;

        // Test zero (a 1 x 1 board)
        int[][] boardZero = new int[][] { { 0 } };
        game.setGameBoard(boardZero);

        // Create the resulting board
        expectedBoard = new int[][] { { 0 } };

        // Use reflection to test zero
        actualBoard = (int[][]) transpose.invoke(game);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test one (a 2 x 2 board)
        int[][] boardOne = ascendBoard(2, 2);
        game.setGameBoard(boardOne);

        // Create the resulting board
        expectedBoard = new int[][] {
                { 1, 3 },
                { 2, 4 }
        };

        // Use reflection to test one
        actualBoard = (int[][]) transpose.invoke(game);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test many (a 4 x 5 board)
        int[][] boardManyOne = ascendBoard(4, 5);
        game.setGameBoard(boardManyOne);

        // Create the resulting board
        expectedBoard = new int[][] {
                { 1, 6, 11, 16 },
                { 2, 7, 12, 17 },
                { 3, 8, 13, 18 },
                { 4, 9, 14, 19 },
                { 5, 10, 15, 20 }
        };

        // Use reflection to test many
        actualBoard = (int[][]) transpose.invoke(game);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test many (a 5 x 6 board)
        int[][] boardManyTwo = ascendBoard(5, 6);
        game.setGameBoard(boardManyTwo);

        // Create the resulting board
        expectedBoard = new int[][] {
                { 1, 7, 13, 19, 25 },
                { 2, 8, 14, 20, 26 },
                { 3, 9, 15, 21, 27 },
                { 4, 10, 16, 22, 28 },
                { 5, 11, 17, 23, 29 },
                { 6, 12, 18, 24, 30 }
        };

        // Use reflection to test many
        actualBoard = (int[][]) transpose.invoke(game);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test many (a 7 x 9 board - two odd cases)
        int[][] boardManyThree = ascendBoard(7, 9);
        game.setGameBoard(boardManyThree);

        // Create the resulting board
        expectedBoard = new int[][] {
                { 1, 10, 19, 28, 37, 46, 55 },
                { 2, 11, 20, 29, 38, 47, 56 },
                { 3, 12, 21, 30, 39, 48, 57 },
                { 4, 13, 22, 31, 40, 49, 58 },
                { 5, 14, 23, 32, 41, 50, 59 },
                { 6, 15, 24, 33, 42, 51, 60 },
                { 7, 16, 25, 34, 43, 52, 61 },
                { 8, 17, 26, 35, 44, 53, 62 },
                { 9, 18, 27, 36, 45, 54, 63 }
        };

        // Use reflection to test many
        actualBoard = (int[][]) transpose.invoke(game);
        assertBoardEquals(expectedBoard, actualBoard);
    }

    // A group of objects to be pulled from the Logic nested class
    Class<?> Logic;
    Object logicInstance;
    Method mergeLeft;
    Method mergeRight;
    Method mergeUp;
    Method mergeDown;
    Method extractDiagonals;
    Method reconstructDiagonals;
    Method mergeTopLeft;
    Method mergeTopRight;
    Method mergeBottomLeft;
    Method mergeBottomRight;

    /**
     * This helper method declares the private Logic methods to be tested.
     * 
     * @throws SecurityException         when needed, java handles
     * @throws NoSuchMethodException     when method name does not exist
     * @throws ClassNotFoundException    when class name does not exist
     * @throws InvocationTargetException when method throws an exception
     * @throws IllegalArgumentException  when method throws an exception
     * @throws IllegalAccessException    when access is denied
     * @throws InstantiationException    when class cannot be instantiated
     */
    private void logicMethods()
            throws SecurityException, NoSuchMethodException, ClassNotFoundException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // Grab the SlideGame class
        Logic = SlideGame.class.getDeclaredClasses()[1];

        // Grab an instance of logic
        Constructor<?> logicConstructor = Logic.getDeclaredConstructor(SlideGame.class);
        logicConstructor.setAccessible(true);
        logicInstance = logicConstructor.newInstance(game);

        // Grab the mergeLeft method
        mergeLeft = Logic.getDeclaredMethod("mergeLeft", int[].class);
        mergeLeft.setAccessible(true);

        // Grab the mergeRight method
        mergeRight = Logic.getDeclaredMethod("mergeRight", int[].class);
        mergeRight.setAccessible(true);

        // Grab the mergeUp method
        mergeUp = Logic.getDeclaredMethod("mergeUp", int[][].class);
        mergeUp.setAccessible(true);

        // Grab the mergeDown method
        mergeDown = Logic.getDeclaredMethod("mergeDown", int[][].class);
        mergeDown.setAccessible(true);

        // Grab the diagonal deconstructor
        extractDiagonals = Logic.getDeclaredMethod("extractDiagonals", int[][].class);
        extractDiagonals.setAccessible(true);

        // Grab the diagonal reconstructor
        reconstructDiagonals = Logic.getDeclaredMethod("reconstructDiagonals", int[].class, int[][].class);
        reconstructDiagonals.setAccessible(true);

        // Grab the merge top left
        mergeTopLeft = Logic.getDeclaredMethod("mergeTopLeft", int[][].class);
        mergeTopLeft.setAccessible(true);

        // Grab the merge top right
        mergeTopRight = Logic.getDeclaredMethod("mergeTopRight", int[][].class);
        mergeTopRight.setAccessible(true);

        // Grab the merge bottom left
        mergeBottomLeft = Logic.getDeclaredMethod("mergeBottomLeft", int[][].class);
        mergeBottomLeft.setAccessible(true);

        // Grab the merge bottom right
        mergeBottomRight = Logic.getDeclaredMethod("mergeBottomRight", int[][].class);
        mergeBottomRight.setAccessible(true);
    }

    // Test the slide left method
    @Test
    public void mergeRowLeftTest()
            throws SecurityException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, InstantiationException {
        // Use the private helper method to create the methods
        logicMethods();

        // Expected and actual result arrays
        int[] actualRow;
        int[] expectedRow;

        // Test a null row
        int[] rowNull = null;

        actualRow = (int[]) mergeLeft.invoke(logicInstance, rowNull);
        assertArrayEquals(null, actualRow);

        // Test an empty row (test zero)
        int[] rowEmpty = new int[] {};

        // Use reflection to test empty
        actualRow = (int[]) mergeLeft.invoke(logicInstance, rowEmpty);
        assertArrayEquals(null, actualRow);

        // Test a row with a single number (test one)
        int[] rowSingle = new int[] { 1 };
        expectedRow = new int[] { 1 };

        // Use reflection to test single
        actualRow = (int[]) mergeLeft.invoke(logicInstance, rowSingle);
        assertArrayEquals(expectedRow, actualRow);

        // Test a row with different numbers, no empty
        int[] rowMultiple = new int[] { 1, 2, 3, 4 };
        expectedRow = new int[] { 1, 2, 3, 4 };

        // Use reflection to test multiple
        actualRow = (int[]) mergeLeft.invoke(logicInstance, rowMultiple);
        assertArrayEquals(expectedRow, actualRow);

        // Test a row with all zeros
        int[] rowAllZeros = new int[] { 0, 0, 0, 0 };
        expectedRow = new int[] { 0, 0, 0, 0 };

        // Use reflection to test all zeros
        actualRow = (int[]) mergeLeft.invoke(logicInstance, rowAllZeros);
        assertArrayEquals(expectedRow, actualRow);

        // Test a row with different numbers, empty at start
        int[] rowStartEmpty = new int[] { 0, 1, 2, 3 };
        expectedRow = new int[] { 1, 2, 3, 0 };

        // Use reflection to test start empty
        actualRow = (int[]) mergeLeft.invoke(logicInstance, rowStartEmpty);
        assertArrayEquals(expectedRow, actualRow);

        // Test a row with different numbers, empty in middle
        int[] rowMiddleEmpty = new int[] { 1, 0, 2, 3 };
        expectedRow = new int[] { 1, 2, 3, 0 };

        // Use reflection to test middle empty
        actualRow = (int[]) mergeLeft.invoke(logicInstance, rowMiddleEmpty);
        assertArrayEquals(expectedRow, actualRow);

        // Test a row with different numbers, empty at end
        int[] rowEndEmpty = new int[] { 1, 2, 3, 0 };
        expectedRow = new int[] { 1, 2, 3, 0 };

        // Use reflection to test end empty
        actualRow = (int[]) mergeLeft.invoke(logicInstance, rowEndEmpty);
        assertArrayEquals(expectedRow, actualRow);

        // Test a row with a single number at the first position (test first)
        int[] rowFirst = new int[] { 1, 0 };
        expectedRow = new int[] { 1, 0 };

        // Use reflection to test first
        actualRow = (int[]) mergeLeft.invoke(logicInstance, rowFirst);
        assertArrayEquals(expectedRow, actualRow);

        // Test a row with a single number at the middle position (test middle)
        int[] rowMiddle = new int[] { 0, 1, 0 };
        expectedRow = new int[] { 1, 0, 0 };

        // Use reflection to test middle
        actualRow = (int[]) mergeLeft.invoke(logicInstance, rowMiddle);
        assertArrayEquals(expectedRow, actualRow);

        // Test a row with a single number at the last position (test last)
        int[] rowLast = new int[] { 0, 1 };
        expectedRow = new int[] { 1, 0 };

        // Use reflection to test last
        actualRow = (int[]) mergeLeft.invoke(logicInstance, rowLast);
        assertArrayEquals(expectedRow, actualRow);

        // Test a row with two numbers at first two positions (test first)
        int[] rowFirstTwo = new int[] { 1, 1, 0 };
        expectedRow = new int[] { 2, 0, 0 };

        // Use reflection to test first two
        actualRow = (int[]) mergeLeft.invoke(logicInstance, rowFirstTwo);
        assertArrayEquals(expectedRow, actualRow);

        // Test a row with two numbers at middle two positions (test middle)
        int[] rowMiddleTwo = new int[] { 0, 1, 1, 0 };
        expectedRow = new int[] { 2, 0, 0, 0 };

        // Use reflection to test middle two
        actualRow = (int[]) mergeLeft.invoke(logicInstance, rowMiddleTwo);
        assertArrayEquals(expectedRow, actualRow);

        // Test a row with two separated numbers at middle two positions (test middle)
        int[] rowMiddleTwoSeparated = new int[] { 0, 1, 0, 1, 0 };
        expectedRow = new int[] { 2, 0, 0, 0, 0 };

        // Use reflection to test middle two separated
        actualRow = (int[]) mergeLeft.invoke(logicInstance, rowMiddleTwoSeparated);
        assertArrayEquals(expectedRow, actualRow);

        // Test a row with two numbers at last two positions (test last)
        int[] rowLastTwo = new int[] { 0, 1, 1 };
        expectedRow = new int[] { 2, 0, 0 };

        // Use reflection to test last two
        actualRow = (int[]) mergeLeft.invoke(logicInstance, rowLastTwo);
        assertArrayEquals(expectedRow, actualRow);

        // Test example one
        int[] rowExampleOne = new int[] { 0, 1, 1, 1, 1 };
        expectedRow = new int[] { 2, 2, 0, 0, 0 };

        // Use reflection to test example one
        actualRow = (int[]) mergeLeft.invoke(logicInstance, rowExampleOne);
        assertArrayEquals(expectedRow, actualRow);

        // Test example 2
        int[] rowExampleTwo = new int[] { 0, 1, 1, 2, 0 };
        expectedRow = new int[] { 4, 0, 0, 0, 0 };

        // Use reflection to test example 2
        actualRow = (int[]) mergeLeft.invoke(logicInstance, rowExampleTwo);
        assertArrayEquals(expectedRow, actualRow);
    }

    // Test the slide right method
    @Test
    public void mergeRowRightTest()
            throws SecurityException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, InstantiationException {
        // Use the private helper method to create the methods
        logicMethods();

        // Declare board dimensions to prevent null pointer exceptions
        game.setGameBoard(this.zeroBoard(5, 5));

        // Expected and actual result arrays
        int[] actualRow;
        int[] expectedRow;

        // Test a null row
        int[] rowNull = null;

        actualRow = (int[]) mergeRight.invoke(logicInstance, rowNull);
        assertArrayEquals(null, actualRow);

        // Test an empty row (test zero)
        int[] rowEmpty = new int[] {};

        // Use reflection to test empty
        actualRow = (int[]) mergeRight.invoke(logicInstance, rowEmpty);
        assertArrayEquals(null, actualRow);

        // Test a row with a single number (test one)
        int[] rowSingle = new int[] { 1 };
        expectedRow = new int[] { 1 };

        // Use reflection to test single
        actualRow = (int[]) mergeRight.invoke(logicInstance, rowSingle);
        assertArrayEquals(expectedRow, actualRow);

        // Test a row with all zeros
        int[] rowAllZeros = new int[] { 0, 0, 0, 0 };
        expectedRow = new int[] { 0, 0, 0, 0 };

        // Use reflection to test all zeros
        actualRow = (int[]) mergeRight.invoke(logicInstance, rowAllZeros);
        assertArrayEquals(expectedRow, actualRow);

        // Test a row with different numbers, no empty
        int[] rowMultiple = new int[] { 1, 2, 3, 4 };
        expectedRow = new int[] { 1, 2, 3, 4 };

        // Use reflection to test multiple
        actualRow = (int[]) mergeRight.invoke(logicInstance, rowMultiple);
        assertArrayEquals(expectedRow, actualRow);

        // Test a row with different numbers, empty at start
        int[] rowStartEmpty = new int[] { 0, 1, 2, 3 };
        expectedRow = new int[] { 0, 1, 2, 3 };

        // Use reflection to test start empty
        actualRow = (int[]) mergeRight.invoke(logicInstance, rowStartEmpty);
        assertArrayEquals(expectedRow, actualRow);

        // Test a row with different numbers, empty in middle
        int[] rowMiddleEmpty = new int[] { 1, 0, 2, 3 };
        expectedRow = new int[] { 0, 1, 2, 3 };

        // Use reflection to test middle empty
        actualRow = (int[]) mergeRight.invoke(logicInstance, rowMiddleEmpty);
        assertArrayEquals(expectedRow, actualRow);

        // Test a row with different numbers, empty at end
        int[] rowEndEmpty = new int[] { 1, 2, 3, 0 };
        expectedRow = new int[] { 0, 1, 2, 3 };

        // Use reflection to test end empty
        actualRow = (int[]) mergeRight.invoke(logicInstance, rowEndEmpty);
        assertArrayEquals(expectedRow, actualRow);

        // Test a row with a single number at the first position (test first)
        int[] rowFirst = new int[] { 1, 0 };
        expectedRow = new int[] { 0, 1 };

        // Use reflection to test first
        actualRow = (int[]) mergeRight.invoke(logicInstance, rowFirst);
        assertArrayEquals(expectedRow, actualRow);

        // Test a row with a single number at the middle position (test middle)
        int[] rowMiddle = new int[] { 0, 1, 0 };
        expectedRow = new int[] { 0, 0, 1 };

        // Use reflection to test middle
        actualRow = (int[]) mergeRight.invoke(logicInstance, rowMiddle);
        assertArrayEquals(expectedRow, actualRow);

        // Test a row with a single number at the last position (test last)
        int[] rowLast = new int[] { 0, 1 };
        expectedRow = new int[] { 0, 1 };

        // Use reflection to test last
        actualRow = (int[]) mergeRight.invoke(logicInstance, rowLast);
        assertArrayEquals(expectedRow, actualRow);

        // Test a row with two numbers at first two positions (test first)
        int[] rowFirstTwo = new int[] { 1, 1, 0 };
        expectedRow = new int[] { 0, 0, 2 };

        // Use reflection to test first two
        actualRow = (int[]) mergeRight.invoke(logicInstance, rowFirstTwo);
        assertArrayEquals(expectedRow, actualRow);

        // Test a row with two numbers at middle two positions (test middle)
        int[] rowMiddleTwo = new int[] { 0, 1, 1, 0 };
        expectedRow = new int[] { 0, 0, 0, 2 };

        // Use reflection to test middle two
        actualRow = (int[]) mergeRight.invoke(logicInstance, rowMiddleTwo);
        assertArrayEquals(expectedRow, actualRow);

        // Test a row with two separated numbers at middle two positions (test middle)
        int[] rowMiddleTwoSeparated = new int[] { 0, 1, 0, 1, 0 };
        expectedRow = new int[] { 0, 0, 0, 0, 2 };

        // Use reflection to test middle two separated
        actualRow = (int[]) mergeRight.invoke(logicInstance, rowMiddleTwoSeparated);
        assertArrayEquals(expectedRow, actualRow);

        // Test a row with two numbers at last two positions (test last)
        int[] rowLastTwo = new int[] { 0, 1, 1 };
        expectedRow = new int[] { 0, 0, 2 };

        // Use reflection to test last two
        actualRow = (int[]) mergeRight.invoke(logicInstance, rowLastTwo);
        assertArrayEquals(expectedRow, actualRow);

        // Test example one
        int[] rowExampleOne = new int[] { 0, 1, 1, 1, 1 };
        expectedRow = new int[] { 0, 0, 0, 2, 2 };

        // Use reflection to test example one
        actualRow = (int[]) mergeRight.invoke(logicInstance, rowExampleOne);
        assertArrayEquals(expectedRow, actualRow);

        // Test example 2
        int[] rowExampleTwo = new int[] { 0, 1, 1, 2, 0 };
        expectedRow = new int[] { 0, 0, 0, 2, 2 };

        // Use reflection to test example 2
        actualRow = (int[]) mergeRight.invoke(logicInstance, rowExampleTwo);
        assertArrayEquals(expectedRow, actualRow);
    }

    // Test the merge up method - slideUp is NOT explicitly tested as it copies this behavior
    @Test
    public void mergeUpTest()
            throws SecurityException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, InstantiationException {
        // Use the private helper method to create the methods
        logicMethods();

        // Expected and actual result arrays
        int[][] actualBoard;
        int[][] expectedBoard;

        // Test a null board
        int[][] nullBoard = null;
        expectedBoard = null;

        // Use reflection to test null
        actualBoard = (int[][]) mergeUp.invoke(logicInstance, (Object) nullBoard);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test an empty board
        int[][] emptyBoard = new int[0][0];
        expectedBoard = null;

        // Use reflection to test empty
        actualBoard = (int[][]) mergeUp.invoke(logicInstance, (Object) emptyBoard);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a board with one row
        int[][] boardOneRow = new int[1][1];
        expectedBoard = new int[1][1];

        // Use reflection to test one row
        actualBoard = (int[][]) mergeUp.invoke(logicInstance, (Object) boardOneRow);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a board with two rows full of zeros
        int[][] boardTwoRows = new int[2][2];
        expectedBoard = new int[][] {
                { 0, 0 },
                { 0, 0 }
        };

        // Use reflection to test two empty rows
        actualBoard = (int[][]) mergeUp.invoke(logicInstance, (Object) boardTwoRows);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a column board with all different values
        int[][] columnBoardDiff = new int[][] {
                { 1 },
                { 2 },
                { 3 },
                { 4 }
        };

        // Assign expected board
        expectedBoard = columnBoardDiff;

        // Use reflection to test column
        actualBoard = (int[][]) mergeUp.invoke(logicInstance, (Object) columnBoardDiff);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a column board with different values, empty on top (test first)
        int[][] columnBoardSameFirst = new int[][] {
                { 0 },
                { 1 },
                { 2 },
                { 3 }
        };

        // Assign expected board
        expectedBoard = new int[][] {
                { 1 },
                { 2 },
                { 3 },
                { 0 }
        };

        // Use reflection to test column
        actualBoard = (int[][]) mergeUp.invoke(logicInstance, (Object) columnBoardSameFirst);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a column board with different values, empty on middle (test middle)
        int[][] columnBoardSameMiddle = new int[][] {
                { 1 },
                { 0 },
                { 2 },
                { 3 }
        };

        // Assign expected board
        expectedBoard = new int[][] {
                { 1 },
                { 2 },
                { 3 },
                { 0 }
        };

        // Use reflection to test column
        actualBoard = (int[][]) mergeUp.invoke(logicInstance, (Object) columnBoardSameMiddle);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a column board with different values, empty on bottom (test last)
        int[][] columnBoardSameLast = new int[][] {
                { 1 },
                { 2 },
                { 3 },
                { 0 }
        };

        // Assign expected board
        expectedBoard = columnBoardSameLast;

        // Use reflection to test column
        actualBoard = (int[][]) mergeUp.invoke(logicInstance, (Object) columnBoardSameLast);
        assertBoardEquals(expectedBoard, actualBoard);
        
        // Test a column board with equal numbers in first two rows (test first)
        int[][] boardFirstTwo = new int[][] {
                { 1, 1, 1, 1 },
                { 1, 1, 1, 1 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
        };

        // Assign expected board to be used for the following few tests
        expectedBoard = new int[][] {
            { 2, 2, 2, 2 },
            { 0, 0, 0, 0 },
            { 0, 0, 0, 0 },
            { 0, 0, 0, 0 }
        };

        // Use reflection to test columns
        actualBoard = (int[][]) mergeUp.invoke(logicInstance, (Object) boardFirstTwo);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a column board with equal numbers in middle two rows (test middle)
        int[][] boardMiddleTwo = new int[][] {
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 },
                { 1, 1, 1, 1 },
                { 0, 0, 0, 0 }
        };

        // Use reflection to test columns - expected board should not change
        actualBoard = (int[][]) mergeUp.invoke(logicInstance, (Object) boardMiddleTwo);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a column board with equal numbers in separated rows (test middle)
        int[][] boardSeparateMiddle = new int[][] {
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 },
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 }
        };

        // Use reflection to test columns - expected board should not change
        actualBoard = (int[][]) mergeUp.invoke(logicInstance, (Object) boardSeparateMiddle);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a column board with equal numbers in last two rows (test last)
        int[][] boardLastTwo = new int[][] {
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 },
                { 1, 1, 1, 1 }
        };

        // Use reflection to test columns - expected board should not change
        actualBoard = (int[][]) mergeUp.invoke(logicInstance, (Object) boardLastTwo);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test an example one variant in board format
        int[][] boardExampleOne = new int[][] {
                { 0, 1, 1, 1, 1 },
                { 1, 0, 1, 1, 1 },
                { 1, 1, 0, 1, 1 }
        };

        // Assign the expected board
        expectedBoard = new int[][] {
                { 2, 2, 2, 2, 2 },
                { 0, 0, 0, 1, 1 },
                { 0, 0, 0, 0, 0 }
        };

        // Use reflection to test columns
        actualBoard = (int[][]) mergeUp.invoke(logicInstance, (Object) boardExampleOne);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test an example two variant in board format
        int[][] boardExampleTwo = new int[][] {
                { 0, 0, 0, 0, 0 },
                { 1, 1, 0, 3, 1 },
                { 1, 0, 1, 1, 4 },
                { 2, 1, 2, 1, 1 }
        };
        
        // Assign the expected board
        expectedBoard = new int[][] {
                { 4, 2, 1, 3, 1 },
                { 0, 0, 2, 2, 4 },
                { 0, 0, 0, 0, 1 },
                { 0, 0, 0, 0, 0 }
        };

        // Use reflection to test columns
        actualBoard = (int[][]) mergeUp.invoke(logicInstance, (Object) boardExampleTwo);
        assertBoardEquals(expectedBoard, actualBoard);
    }

    // Test the mergeDown method, slideDown is NOT explicitly tested as it copies this behavior
    @Test
    public void mergeDownTest()
            throws SecurityException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, InstantiationException {
        // Use the private helper method to create the methods
        logicMethods();

        // Expected and actual result arrays
        int[][] actualBoard;
        int[][] expectedBoard;

        // Test a null board
        int[][] nullBoard = null;
        expectedBoard = null;

        // Use reflection to test null
        actualBoard = (int[][]) mergeDown.invoke(logicInstance, (Object) nullBoard);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test an empty board
        int[][] emptyBoard = new int[0][0];
        expectedBoard = null;

        // Use reflection to test empty
        actualBoard = (int[][]) mergeDown.invoke(logicInstance, (Object) emptyBoard);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a board with one row
        int[][] boardOneRow = new int[1][1];
        expectedBoard = new int[1][1];

        // Use reflection to test one row
        actualBoard = (int[][]) mergeDown.invoke(logicInstance, (Object) boardOneRow);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a board with two rows full of zeros
        int[][] boardTwoRows = new int[2][2];
        expectedBoard = new int[][] {
                { 0, 0 },
                { 0, 0 }
        };

        // Use reflection to test two empty rows
        actualBoard = (int[][]) mergeDown.invoke(logicInstance, (Object) boardTwoRows);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a column board with all different values
        int[][] columnBoardDiff = new int[][] {
                { 1 },
                { 2 },
                { 3 },
                { 4 }
        };

        // Assign expected board
        expectedBoard = columnBoardDiff;

        // Use reflection to test column
        actualBoard = (int[][]) mergeDown.invoke(logicInstance, (Object) columnBoardDiff);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a column board with different values, empty on top (test first)
        int[][] columnBoardSameFirst = new int[][] {
                { 0 },
                { 1 },
                { 2 },
                { 3 }
        };

        // Assign expected board
        expectedBoard = columnBoardSameFirst;

        // Use reflection to test column
        actualBoard = (int[][]) mergeDown.invoke(logicInstance, (Object) columnBoardSameFirst);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a column board with different values, empty on middle (test middle)
        int[][] columnBoardSameMiddle = new int[][] {
                { 1 },
                { 0 },
                { 2 },
                { 3 }
        };

        // Assign expected board
        expectedBoard = new int[][] {
                { 0 },
                { 1 },
                { 2 },
                { 3 }
        };

        // Use reflection to test column
        actualBoard = (int[][]) mergeDown.invoke(logicInstance, (Object) columnBoardSameMiddle);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a column board with different values, empty on bottom (test last)
        int[][] columnBoardSameLast = new int[][] {
                { 1 },
                { 2 },
                { 3 },
                { 0 }
        };

        // Use reflection to test column - expected not changed from previous
        actualBoard = (int[][]) mergeDown.invoke(logicInstance, (Object) columnBoardSameLast);
        assertBoardEquals(expectedBoard, actualBoard);
        
        // Test a column board with equal numbers in first two rows (test first)
        int[][] boardFirstTwo = new int[][] {
                { 1, 1, 1, 1 },
                { 1, 1, 1, 1 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
        };

        // Assign expected board to be used for the following few tests
        expectedBoard = new int[][] {
            { 0, 0, 0, 0 },
            { 0, 0, 0, 0 },
            { 0, 0, 0, 0 },
            { 2, 2, 2, 2 }
        };

        // Use reflection to test columns
        actualBoard = (int[][]) mergeDown.invoke(logicInstance, (Object) boardFirstTwo);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a column board with equal numbers in middle two rows (test middle)
        int[][] boardMiddleTwo = new int[][] {
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 },
                { 1, 1, 1, 1 },
                { 0, 0, 0, 0 }
        };

        // Use reflection to test columns - expected board should not change
        actualBoard = (int[][]) mergeDown.invoke(logicInstance, (Object) boardMiddleTwo);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a column board with equal numbers in separated rows (test middle)
        int[][] boardSeparateMiddle = new int[][] {
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 },
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 }
        };

        // Use reflection to test columns - expected board should not change
        actualBoard = (int[][]) mergeDown.invoke(logicInstance, (Object) boardSeparateMiddle);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a column board with equal numbers in last two rows (test last)
        int[][] boardLastTwo = new int[][] {
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 },
                { 1, 1, 1, 1 }
        };

        // Use reflection to test columns - expected board should not change
        actualBoard = (int[][]) mergeDown.invoke(logicInstance, (Object) boardLastTwo);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test an example one variant in board format
        int[][] boardExampleOne = new int[][] {
                { 0, 1, 1, 1, 1 },
                { 1, 0, 1, 1, 1 },
                { 1, 1, 0, 1, 1 }
        };

        // Assign the expected board
        expectedBoard = new int[][] {
                { 0, 0, 0, 0, 0 },
                { 0, 0, 0, 1, 1 },
                { 2, 2, 2, 2, 2 }
        };

        // Use reflection to test columns
        actualBoard = (int[][]) mergeDown.invoke(logicInstance, (Object) boardExampleOne);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test an example two variant in board format
        int[][] boardExampleTwo = new int[][] {
                { 0, 0, 0, 0, 0 },
                { 1, 1, 0, 3, 1 },
                { 1, 0, 1, 1, 4 },
                { 2, 1, 2, 1, 1 }
        };
        
        // Assign the expected board
        expectedBoard = new int[][] {
                { 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1 },
                { 2, 0, 1, 3, 4 },
                { 2, 2, 2, 2, 1 }
        };

        // Use reflection to test columns
        actualBoard = (int[][]) mergeDown.invoke(logicInstance, (Object) boardExampleTwo);
        assertBoardEquals(expectedBoard, actualBoard);
    }

    // Test the diagonal extractor
    @Test
    public void diagonalExtractorTest()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, 
            NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException {
        // Use the private helper method to create the methods
        logicMethods();

        // Create arrays to store input, expected, and actual results
        int[][] inputArray;
        int[][] expectedDiagonals;
        int[][] actualDiagonals;

        // Test a null array
        expectedDiagonals = null;
        actualDiagonals = (int[][]) extractDiagonals.invoke(logicInstance, (Object) null);
        assertArrayEquals(expectedDiagonals, actualDiagonals);

        // Test an array of 0 length
        inputArray = new int[0][0];
        expectedDiagonals = null;
        actualDiagonals = (int[][]) extractDiagonals.invoke(logicInstance, (Object) inputArray);
        assertArrayEquals(expectedDiagonals, actualDiagonals);

        // Test an array of 1 length
        inputArray = new int[][] { { 1 } };
        expectedDiagonals = inputArray;
        actualDiagonals = (int[][]) extractDiagonals.invoke(logicInstance, (Object) inputArray);
        assertArrayEquals(expectedDiagonals, actualDiagonals);

        // Test an empty square array
        inputArray = zeroBoard(2, 2);
        expectedDiagonals = new int[][] {
                { 0 },
                { 0, 0 },
                { 0 }
        };
        actualDiagonals = (int[][]) extractDiagonals.invoke(logicInstance, (Object) inputArray);
        assertArrayEquals(expectedDiagonals, actualDiagonals);

        // Test a full square array
        inputArray = ascendBoard(2, 2);
        expectedDiagonals = new int[][] {
                { 1 },
                { 3, 2 },
                { 4 }
        };
        actualDiagonals = (int[][]) extractDiagonals.invoke(logicInstance, (Object) inputArray);
        assertArrayEquals(expectedDiagonals, actualDiagonals);

        // Test a standard game board (4 x 4)
        inputArray = ascendBoard(4, 4);
        expectedDiagonals = new int[][] {
                { 1 },
                { 5, 2 },
                { 9, 6, 3 },
                { 13, 10, 7, 4 },
                { 14, 11, 8 },
                { 15,12 },
                { 16 }
        };
        actualDiagonals = (int[][]) extractDiagonals.invoke(logicInstance, (Object) inputArray);
        assertArrayEquals(expectedDiagonals, actualDiagonals);
        
        // Test an empty rectangular array (more columns than rows)
        inputArray = zeroBoard(4, 7);
        expectedDiagonals = new int[][] {
                { 0 },
                { 0, 0 },
                { 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0 },
                { 0, 0 },
                { 0 }
        };
        actualDiagonals = (int[][]) extractDiagonals.invoke(logicInstance, (Object) inputArray);
        assertArrayEquals(expectedDiagonals, actualDiagonals);

        // Test a full rectangular array (more columns than rows)
        inputArray = ascendBoard(3, 4);
        expectedDiagonals = new int[][] {
                { 1 },
                { 5, 2 },
                { 9, 6, 3 },
                { 10, 7, 4 },
                { 11, 8 },
                { 12 }
        };
        actualDiagonals = (int[][]) extractDiagonals.invoke(logicInstance, (Object) inputArray);
        assertArrayEquals(expectedDiagonals, actualDiagonals);

        // Test an empty rectangular array (more rows than columns)
        inputArray = zeroBoard(4, 3);
        expectedDiagonals = new int[][] {
                { 0 },
                { 0, 0 },
                { 0, 0, 0 },
                { 0, 0, 0 },
                { 0, 0 },
                { 0 }
        };
        actualDiagonals = (int[][]) extractDiagonals.invoke(logicInstance, (Object) inputArray);
        assertArrayEquals(expectedDiagonals, actualDiagonals);

        // Test a full rectangular array (more rows than columns)
        inputArray = ascendBoard(4, 3);
        expectedDiagonals = new int[][] {
                { 1 },
                { 4, 2 },
                { 7, 5, 3 },
                { 10, 8, 6 },
                { 11, 9 },
                { 12 }
        };
        actualDiagonals = (int[][]) extractDiagonals.invoke(logicInstance, (Object) inputArray);
        assertArrayEquals(expectedDiagonals, actualDiagonals);
    }

    // Test the diagonal reconstructor
    @Test
    public void diagonalReconstructorTest()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, 
            NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException {
        // Use the private helper method to create the methods
        logicMethods();

        // Create arrays to store input, expected, and actual results
        int[][] inputDiagonals;
        int[][] expectedBoard;
        int[][] actualBoard;

        // Test a null array
        expectedBoard = null;
        actualBoard = (int[][]) reconstructDiagonals.invoke(logicInstance, null, (Object) null);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test dimensions with one 0
        inputDiagonals = new int[0][0];
        expectedBoard = null;
        actualBoard = (int[][]) reconstructDiagonals.invoke(logicInstance, new int[] { 5, 0 }, (Object) inputDiagonals);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test dimensions with other 0
        inputDiagonals = new int[0][0];
        expectedBoard = null;
        actualBoard = (int[][]) reconstructDiagonals.invoke(logicInstance, new int[] { 0, 5 }, (Object) inputDiagonals);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test dimensions with both 0
        inputDiagonals = new int[0][0];
        expectedBoard = null;
        actualBoard = (int[][]) reconstructDiagonals.invoke(logicInstance, new int[] { 0, 0 }, (Object) inputDiagonals);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test an array of 1 length with null dimensions
        inputDiagonals = new int[][] { { 1 } };
        expectedBoard = null;
        actualBoard = (int[][]) reconstructDiagonals.invoke(logicInstance, null, (Object) inputDiagonals);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test an array of 1 length with real dimensions
        expectedBoard = new int[][] { { 1 } };
        actualBoard = (int[][]) reconstructDiagonals.invoke(logicInstance, new int[] { 3, 2 }, (Object) inputDiagonals);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a valid array with null dimensions
        inputDiagonals = new int[][] {
                { 1 },
                { 3, 2 },
                { 4 }
        };
        expectedBoard = null;
        actualBoard = (int[][]) reconstructDiagonals.invoke(logicInstance, null, (Object) inputDiagonals);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a valid array with 1D dimensions
        inputDiagonals = new int[][] {
                { 1 },
                { 3, 2 },
                { 4 }
        };
        expectedBoard = null;
        actualBoard = (int[][]) reconstructDiagonals.invoke(logicInstance, new int[] { 3 }, (Object) inputDiagonals);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test an array of zeros - all tests below here have proper dimensions
        inputDiagonals = new int[][] {
                { 0 },
                { 0, 0 },
                { 0 }
        };
        expectedBoard = zeroBoard(2, 2);
        actualBoard = (int[][]) reconstructDiagonals.invoke(logicInstance, new int[] { 2, 2 }, (Object) inputDiagonals);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a small array with actual numbers
        inputDiagonals = new int[][] {
                { 1 },
                { 3, 2 },
                { 4 }
        };
        expectedBoard = ascendBoard(2, 2);
        actualBoard = (int[][]) reconstructDiagonals.invoke(logicInstance, new int[] { 2, 2 }, (Object) inputDiagonals);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a standard 4 x 4 array with actual values
        inputDiagonals = new int[][] {
                { 1 },
                { 5, 2 },
                { 9, 6, 3 },
                { 13, 10, 7, 4 },
                { 14, 11, 8 },
                { 15, 12 },
                { 16 }
        };
        expectedBoard = ascendBoard(4, 4);
        actualBoard = (int[][]) reconstructDiagonals.invoke(logicInstance, new int[] { 4, 4 }, (Object) inputDiagonals);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test an empty rectangular array (col > row)
        inputDiagonals = new int[][] {
                { 0 },
                { 0, 0 },
                { 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0 },
                { 0, 0 },
                { 0 }
        };
        expectedBoard = zeroBoard(4, 7);
        actualBoard = (int[][]) reconstructDiagonals.invoke(logicInstance, new int[] { 4, 7 }, (Object) inputDiagonals);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a non-zero rectangular board (col > row)
        inputDiagonals = new int[][] {
                { 1 },
                { 5, 2 },
                { 9, 6, 3 },
                { 10, 7, 4 },
                { 11, 8 },
                { 12 }
        };
        expectedBoard = ascendBoard(3, 4);
        actualBoard = (int[][]) reconstructDiagonals.invoke(logicInstance, new int[] { 3, 4 }, (Object) inputDiagonals);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test an empty rectangular array (row > col)
        inputDiagonals = new int[][] {
                { 0 },
                { 0, 0 },
                { 0, 0, 0 },
                { 0, 0, 0 },
                { 0, 0 },
                { 0 }
        };
        expectedBoard = zeroBoard(4, 3);
        actualBoard = (int[][]) reconstructDiagonals.invoke(logicInstance, new int[] { 4, 3 }, (Object) inputDiagonals);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a non-zero rectangular board (row > col)
        inputDiagonals = new int[][] {
                { 1 },
                { 4, 2 },
                { 7, 5, 3 },
                { 10, 8, 6 },
                { 11, 9 },
                { 12 }
        };
        expectedBoard = ascendBoard(4, 3);
        actualBoard = (int[][]) reconstructDiagonals.invoke(logicInstance, new int[] { 4, 3 }, (Object) inputDiagonals);
        assertArrayEquals(expectedBoard, actualBoard);
    }

    // Test the merge up right method
    @Test
    public void mergeTopRightTest()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, 
            NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException {
        // Use the private helper method to create the methods
        logicMethods();

        // Declare board dimensions to prevent null pointer exceptions
        game.setGameBoard(this.zeroBoard(5, 5));

        // Create arrays to store expected and actual results
        int[][] expectedBoard;
        int[][] actualBoard;

        // Test a null board
        int[][] nullBoard = null;
        expectedBoard = null;
        actualBoard = (int[][]) mergeTopRight.invoke(logicInstance, (Object) nullBoard);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a board of 0 length
        int[][] emptyBoard = new int[0][0];
        expectedBoard = null;
        actualBoard = (int[][]) mergeTopRight.invoke(logicInstance, (Object) emptyBoard);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a board of 1 length
        int[][] oneLengthBoard = new int[][] { { 1 } };
        expectedBoard = oneLengthBoard;
        actualBoard = (int[][]) mergeTopRight.invoke(logicInstance, (Object) oneLengthBoard);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a board with two rows full of zeros
        int[][] boardTwoRows = new int[2][2];
        expectedBoard = new int[][] {
                { 0, 0 },
                { 0, 0 }
        };

        // Use reflection to test two empty rows
        actualBoard = (int[][]) mergeTopRight.invoke(logicInstance, (Object) boardTwoRows);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a column board with all different values
        int[][] columnBoardDiff = new int[][] {
                { 1 },
                { 2 },
                { 3 },
                { 4 }
        };
        expectedBoard = columnBoardDiff;

        // Use reflection to test column board
        actualBoard = (int[][]) mergeTopRight.invoke(logicInstance, (Object) columnBoardDiff);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a column board with different values, empty on top (test first)
        int[][] columnBoardSameFirst = new int[][] {
                { 0 },
                { 1 },
                { 2 },
                { 3 }
        };
        expectedBoard = columnBoardSameFirst;

        // Use reflection to test column board
        actualBoard = (int[][]) mergeTopRight.invoke(logicInstance, (Object) columnBoardSameFirst);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a column board with different values, empty on middle (test middle)
        int[][] columnBoardSameMiddle = new int[][] {
                { 1 },
                { 0 },
                { 2 },
                { 3 }
        };
        expectedBoard = columnBoardSameMiddle;

        // Use reflection to test column board
        actualBoard = (int[][]) mergeTopRight.invoke(logicInstance, (Object) columnBoardSameMiddle);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a column board with different values, empty on bottom (test last)
        int[][] columnBoardSameLast = new int[][] {
                { 1 },
                { 2 },
                { 3 },
                { 0 }
        };
        expectedBoard = columnBoardSameLast;

        // Use reflection to test column board
        actualBoard = (int[][]) mergeTopRight.invoke(logicInstance, (Object) columnBoardSameLast);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a column board with equal numbers in first two rows (test first)
        int[][] boardFirstTwo = new int[][] {
                { 1, 1, 1, 1 },
                { 1, 1, 1, 1 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
        };
        expectedBoard = new int[][] {
                { 1, 2, 2, 2 },
                { 0, 0, 0, 1 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeTopRight.invoke(logicInstance, (Object) boardFirstTwo);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a column board with equal numbers in middle two rows (test middle)
        int[][] boardMiddleTwo = new int[][] {
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 },
                { 1, 1, 1, 1 },
                { 0, 0, 0, 0 }
        };
        expectedBoard = new int[][] {
                { 0, 1, 2, 2 },
                { 0, 0, 0, 2 },
                { 0, 0, 0, 1 },
                { 0, 0, 0, 0 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeTopRight.invoke(logicInstance, (Object) boardMiddleTwo);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a column board with equal numbers in separated rows (test middle)
        int[][] boardSeparateMiddle = new int[][] {
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 },
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 }
        };
        expectedBoard = new int[][] {
                { 0, 1, 1, 2 },
                { 0, 0, 0, 2 },
                { 0, 0, 0, 1 },
                { 0, 0, 0, 1 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeTopRight.invoke(logicInstance, (Object) boardSeparateMiddle);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a column board with equal numbers in last two rows (test last)
        int[][] boardLastTwo = new int[][] {
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 },
                { 1, 1, 1, 1 }
        };
        expectedBoard = new int[][] {
                { 0, 0, 1, 2 },
                { 0, 0, 0, 2 },
                { 0, 0, 0, 2 },
                { 0, 0, 0, 1 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeTopRight.invoke(logicInstance, (Object) boardLastTwo);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test an example one variant in board format
        int[][] boardExampleOne = new int[][] {
                { 0, 1, 1, 1, 1 },
                { 1, 0, 1, 1, 1 },
                { 1, 1, 0, 1, 1 }
        };
        expectedBoard = new int[][] {
                { 0, 2, 2, 2, 2 },
                { 0, 0, 1, 0, 2 },
                { 0, 0, 0, 0, 1 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeTopRight.invoke(logicInstance, (Object) boardExampleOne);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test an example two variant in board format
        int[][] boardExampleTwo = new int[][] {
                { 0, 0, 0, 0, 0 },
                { 1, 1, 0, 3, 1 },
                { 1, 0, 1, 1, 4 },
                { 2, 1, 2, 1, 1 }
        };
        expectedBoard = new int[][] {
                { 0, 1, 2, 2, 3 },
                { 0, 0, 0, 2, 4 },
                { 0, 0, 0, 0, 4 },
                { 0, 0, 0, 1, 1 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeTopRight.invoke(logicInstance, (Object) boardExampleTwo);
        assertArrayEquals(expectedBoard, actualBoard);
    }

    // Test the merge down right method
    @Test
    public void mergeBottomRightTest()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, 
            NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException {
        // Use the private helper method to create the methods
        logicMethods();

        // Declare board dimensions to prevent null pointer exceptions
        game.setGameBoard(this.zeroBoard(5, 5));

        // Create arrays to store expected and actual results
        int[][] expectedBoard;
        int[][] actualBoard;

        // Test a null board
        int[][] nullBoard = null;
        expectedBoard = null;
        actualBoard = (int[][]) mergeBottomRight.invoke(logicInstance, (Object) nullBoard);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a board of 0 length
        int[][] emptyBoard = new int[0][0];
        expectedBoard = null;
        actualBoard = (int[][]) mergeBottomRight.invoke(logicInstance, (Object) emptyBoard);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a board of 1 length
        int[][] oneLengthBoard = new int[][] { { 1 } };
        expectedBoard = oneLengthBoard;
        actualBoard = (int[][]) mergeBottomRight.invoke(logicInstance, (Object) oneLengthBoard);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a board with two rows full of zeros
        int[][] boardTwoRows = new int[2][2];
        expectedBoard = new int[][] {
                { 0, 0 },
                { 0, 0 }
        };

        // Use reflection to test two empty rows
        actualBoard = (int[][]) mergeBottomRight.invoke(logicInstance, (Object) boardTwoRows);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a column board with all different values
        int[][] columnBoardDiff = new int[][] {
                { 1 },
                { 2 },
                { 3 },
                { 4 }
        };
        expectedBoard = new int[][] {
                { 1 },
                { 2 },
                { 3 },
                { 4 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeBottomRight.invoke(logicInstance, (Object) columnBoardDiff);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a column board with different values, empty on top (test first)
        int[][] columnBoardSameFirst = new int[][] {
                { 0 },
                { 1 },
                { 2 },
                { 3 }
        };
        expectedBoard = new int[][] {
                { 0 },
                { 1 },
                { 2 },
                { 3 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeBottomRight.invoke(logicInstance, (Object) columnBoardSameFirst);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a column board with different values, empty on middle (test middle)
        int[][] columnBoardSameMiddle = new int[][] {
                { 1 },
                { 0 },
                { 2 },
                { 3 }
        };
        expectedBoard = new int[][] {
                { 1 },
                { 0 },
                { 2 },
                { 3 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeBottomRight.invoke(logicInstance, (Object) columnBoardSameMiddle);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a column board with different values, empty on bottom (test last)
        int[][] columnBoardSameLast = new int[][] {
                { 1 },
                { 2 },
                { 3 },
                { 0 }
        };
        expectedBoard = new int[][] {
                { 1 },
                { 2 },
                { 3 },
                { 0 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeBottomRight.invoke(logicInstance, (Object) columnBoardSameLast);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a column board with equal numbers in first two rows (test first)
        int[][] boardFirstTwo = new int[][] {
                { 1, 1, 1, 1 },
                { 1, 1, 1, 1 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
        };
        expectedBoard = new int[][] {
                { 0, 0, 0, 1 },
                { 0, 0, 0, 2 },
                { 0, 0, 0, 2 },
                { 0, 0, 1, 2 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeBottomRight.invoke(logicInstance, (Object) boardFirstTwo);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a column board with equal numbers in middle two rows (test middle)
        int[][] boardMiddleTwo = new int[][] {
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 },
                { 1, 1, 1, 1 },
                { 0, 0, 0, 0 }
        };
        expectedBoard = new int[][] {
                { 0, 0, 0, 0 },
                { 0, 0, 0, 1 },
                { 0, 0, 0, 2 },
                { 0, 1, 2, 2 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeBottomRight.invoke(logicInstance, (Object) boardMiddleTwo);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a column board with equal numbers in separated rows (test middle)
        int[][] boardSeparateMiddle = new int[][] {
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 },
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 }
        };
        expectedBoard = new int[][] {
                { 0, 0, 0, 0 },
                { 0, 0, 0, 1 },
                { 0, 0, 0, 1 },
                { 1, 1, 2, 2 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeBottomRight.invoke(logicInstance, (Object) boardSeparateMiddle);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a column board with equal numbers in last two rows (test last)
        int[][] boardLastTwo = new int[][] {
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 },
                { 1, 1, 1, 1 }
        };
        expectedBoard = new int[][] {
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 1 },
                { 1, 2, 2, 2 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeBottomRight.invoke(logicInstance, (Object) boardLastTwo);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test an example one variant in board format
        int[][] boardExampleOne = new int[][] {
                { 0, 1, 1, 1, 1 },
                { 1, 0, 1, 1, 1 },
                { 1, 1, 0, 1, 1 }
        };
        expectedBoard = new int[][] {
                { 0, 0, 0, 0, 1 },
                { 0, 0, 1, 1, 2 },
                { 1, 2, 0, 2, 2 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeBottomRight.invoke(logicInstance, (Object) boardExampleOne);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test an example two variant in board format
        int[][] boardExampleTwo = new int[][] {
                { 0, 0, 0, 0, 0 },
                { 1, 1, 0, 3, 1 },
                { 1, 0, 1, 1, 4 },
                { 2, 1, 2, 1, 1 }
        };
        expectedBoard = new int[][] {
                { 0, 0, 0, 0, 0 },
                { 0, 0, 0, 3, 1 },
                { 0, 1, 1, 0, 4 },
                { 2, 2, 2, 2, 2 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeBottomRight.invoke(logicInstance, (Object) boardExampleTwo);
        assertBoardEquals(expectedBoard, actualBoard);
    }

    // Test the merge up left method
    @Test
    public void mergeTopLeftTest()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, 
            NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException {
        // Use the private helper method to create the methods
        logicMethods();

        // Declare board dimensions to prevent null pointer exceptions
        game.setGameBoard(this.zeroBoard(5, 5));

        // Create arrays to store expected and actual results
        int[][] expectedBoard;
        int[][] actualBoard;

        // Test a null board
        int[][] nullBoard = null;
        expectedBoard = null;
        actualBoard = (int[][]) mergeTopLeft.invoke(logicInstance, (Object) nullBoard);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a board of 0 length
        int[][] emptyBoard = new int[0][0];
        expectedBoard = null;
        actualBoard = (int[][]) mergeTopLeft.invoke(logicInstance, (Object) emptyBoard);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a board of 1 length
        int[][] oneLengthBoard = new int[][] { { 1 } };
        expectedBoard = oneLengthBoard;
        actualBoard = (int[][]) mergeTopLeft.invoke(logicInstance, (Object) oneLengthBoard);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a board with two rows full of zeros
        int[][] boardTwoRows = new int[2][2];
        expectedBoard = new int[][] {
                { 0, 0 },
                { 0, 0 }
        };

        // Use reflection to test two empty rows
        actualBoard = (int[][]) mergeTopLeft.invoke(logicInstance, (Object) boardTwoRows);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a column board with all different values
        int[][] columnBoardDiff = new int[][] {
                { 1 },
                { 2 },
                { 3 },
                { 4 }
        };
        expectedBoard = new int[][] {
                { 1 },
                { 2 },
                { 3 },
                { 4 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeTopLeft.invoke(logicInstance, (Object) columnBoardDiff);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a column board with different values, empty on top (test first)
        int[][] columnBoardSameFirst = new int[][] {
                { 0 },
                { 1 },
                { 2 },
                { 3 }
        };
        expectedBoard = new int[][] {
                { 0 },
                { 1 },
                { 2 },
                { 3 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeTopLeft.invoke(logicInstance, (Object) columnBoardSameFirst);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a column board with different values, empty on middle (test middle)
        int[][] columnBoardSameMiddle = new int[][] {
                { 1 },
                { 0 },
                { 2 },
                { 3 }
        };
        expectedBoard = new int[][] {
                { 1 },
                { 0 },
                { 2 },
                { 3 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeTopLeft.invoke(logicInstance, (Object) columnBoardSameMiddle);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a column board with different values, empty on bottom (test last)
        int[][] columnBoardSameLast = new int[][] {
                { 1 },
                { 2 },
                { 3 },
                { 0 }
        };
        expectedBoard = new int[][] {
                { 1 },
                { 2 },
                { 3 },
                { 0 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeTopLeft.invoke(logicInstance, (Object) columnBoardSameLast);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a column board with equal numbers in first two rows (test first)
        int[][] boardFirstTwo = new int[][] {
                { 1, 1, 1, 1 },
                { 1, 1, 1, 1 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
        };
        expectedBoard = new int[][] {
                { 2, 2, 2, 1 },
                { 1, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeTopLeft.invoke(logicInstance, (Object) boardFirstTwo);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a column board with equal numbers in middle two rows (test middle)
        int[][] boardMiddleTwo = new int[][] {
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 },
                { 1, 1, 1, 1 },
                { 0, 0, 0, 0 }
        };
        expectedBoard = new int[][] {
                { 2, 2, 1, 0 },
                { 2, 0, 0, 0 },
                { 1, 0, 0, 0 },
                { 0, 0, 0, 0 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeTopLeft.invoke(logicInstance, (Object) boardMiddleTwo);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a column board with equal numbers in separated rows (test middle)
        int[][] boardSeparateMiddle = new int[][] {
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 },
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 }
        };
        expectedBoard = new int[][] {
                { 2, 1, 1, 0 },
                { 2, 0, 0, 0 },
                { 1, 0, 0, 0 },
                { 1, 0, 0, 0 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeTopLeft.invoke(logicInstance, (Object) boardSeparateMiddle);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a column board with equal numbers in last two rows (test last)
        int[][] boardLastTwo = new int[][] {
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 },
                { 1, 1, 1, 1 }
        };
        expectedBoard = new int[][] {
                { 2, 1, 0, 0 },
                { 2, 0, 0, 0 },
                { 2, 0, 0, 0 },
                { 1, 0, 0, 0 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeTopLeft.invoke(logicInstance, (Object) boardLastTwo);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test an example one variant in board format
        int[][] boardExampleOne = new int[][] {
                { 0, 1, 1, 1, 1 },
                { 1, 0, 1, 1, 1 },
                { 1, 1, 0, 1, 1 }
        };
        expectedBoard = new int[][] {
                { 0, 2, 2, 2, 1 },
                { 2, 0, 1, 1, 0 },
                { 1, 0, 0, 0, 0 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeTopLeft.invoke(logicInstance, (Object) boardExampleOne);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test an example two variant in board format
        int[][] boardExampleTwo = new int[][] {
                { 0, 0, 0, 0, 0 },
                { 1, 1, 0, 3, 1 },
                { 1, 0, 1, 1, 4 },
                { 2, 1, 2, 1, 1 }
        };
        expectedBoard = new int[][] {
                { 2, 2, 3, 1, 0 },
                { 1, 1, 0, 4, 0 },
                { 2, 2, 0, 0, 0 },
                { 2, 0, 0, 0, 0 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeTopLeft.invoke(logicInstance, (Object) boardExampleTwo);
        assertArrayEquals(expectedBoard, actualBoard);
    }

    // Test the merge down left method
    @Test
    public void mergeBottomLeftTest()
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, 
            NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException {
        // Use the private helper method to create the methods
        logicMethods();

        // Declare board dimensions to prevent null pointer exceptions
        game.setGameBoard(this.zeroBoard(5, 5));

        // Create arrays to store expected and actual results
        int[][] expectedBoard;
        int[][] actualBoard;

        // Test a null board
        int[][] nullBoard = null;
        expectedBoard = null;
        actualBoard = (int[][]) mergeBottomLeft.invoke(logicInstance, (Object) nullBoard);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a board of 0 length
        int[][] emptyBoard = new int[0][0];
        expectedBoard = null;
        actualBoard = (int[][]) mergeBottomLeft.invoke(logicInstance, (Object) emptyBoard);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a board of 1 length
        int[][] oneLengthBoard = new int[][] { { 1 } };
        expectedBoard = oneLengthBoard;
        actualBoard = (int[][]) mergeBottomLeft.invoke(logicInstance, (Object) oneLengthBoard);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a board with two rows full of zeros
        int[][] boardTwoRows = new int[2][2];
        expectedBoard = new int[][] {
                { 0, 0 },
                { 0, 0 }
        };

        // Use reflection to test two empty rows
        actualBoard = (int[][]) mergeBottomLeft.invoke(logicInstance, (Object) boardTwoRows);
        assertBoardEquals(expectedBoard, actualBoard);

        // Test a column board with all different values
        int[][] columnBoardDiff = new int[][] {
                { 1 },
                { 2 },
                { 3 },
                { 4 }
        };
        expectedBoard = columnBoardDiff;

        // Use reflection to test column board
        actualBoard = (int[][]) mergeBottomLeft.invoke(logicInstance, (Object) columnBoardDiff);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a column board with different values, empty on top (test first)
        int[][] columnBoardSameFirst = new int[][] {
                { 0 },
                { 1 },
                { 2 },
                { 3 }
        };
        expectedBoard = columnBoardSameFirst;

        // Use reflection to test column board
        actualBoard = (int[][]) mergeBottomLeft.invoke(logicInstance, (Object) columnBoardSameFirst);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a column board with different values, empty on middle (test middle)
        int[][] columnBoardSameMiddle = new int[][] {
                { 1 },
                { 0 },
                { 2 },
                { 3 }
        };
        expectedBoard = columnBoardSameMiddle;

        // Use reflection to test column board
        actualBoard = (int[][]) mergeBottomLeft.invoke(logicInstance, (Object) columnBoardSameMiddle);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a column board with different values, empty on bottom (test last)
        int[][] columnBoardSameLast = new int[][] {
                { 1 },
                { 2 },
                { 3 },
                { 0 }
        };
        expectedBoard = columnBoardSameLast;

        // Use reflection to test column board
        actualBoard = (int[][]) mergeBottomLeft.invoke(logicInstance, (Object) columnBoardSameLast);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a column board with equal numbers in first two rows (test first)
        int[][] boardFirstTwo = new int[][] {
                { 1, 1, 1, 1 },
                { 1, 1, 1, 1 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
        };
        expectedBoard = new int[][] {
                { 1, 0, 0, 0 },
                { 2, 0, 0, 0 },
                { 2, 0, 0, 0 },
                { 2, 1, 0, 0 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeBottomLeft.invoke(logicInstance, (Object) boardFirstTwo);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a column board with equal numbers in middle two rows (test middle)
        int[][] boardMiddleTwo = new int[][] {
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 },
                { 1, 1, 1, 1 },
                { 0, 0, 0, 0 }
        };
        expectedBoard = new int[][] {
                { 0, 0, 0, 0 },
                { 1, 0, 0, 0 },
                { 2, 0, 0, 0 },
                { 2, 2, 1, 0 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeBottomLeft.invoke(logicInstance, (Object) boardMiddleTwo);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a column board with equal numbers in separated rows (test middle)
        int[][] boardSeparateMiddle = new int[][] {
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 },
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 }
        };
        expectedBoard = new int[][] {
                { 0, 0, 0, 0 },
                { 1, 0, 0, 0 },
                { 1, 0, 0, 0 },
                { 2, 2, 1, 1 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeBottomLeft.invoke(logicInstance, (Object) boardSeparateMiddle);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test a column board with equal numbers in last two rows (test last)
        int[][] boardLastTwo = new int[][] {
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 },
                { 1, 1, 1, 1 }
        };
        expectedBoard = new int[][] {
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 1, 0, 0, 0 },
                { 2, 2, 2, 1 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeBottomLeft.invoke(logicInstance, (Object) boardLastTwo);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test an example one variant in board format
        int[][] boardExampleOne = new int[][] {
                { 0, 1, 1, 1, 1 },
                { 1, 0, 1, 1, 1 },
                { 1, 1, 0, 1, 1 }
        };
        expectedBoard = new int[][] {
                { 0, 0, 0, 0, 0 },
                { 2, 0, 1, 0, 0 },
                { 2, 2, 2, 2, 1 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeBottomLeft.invoke(logicInstance, (Object) boardExampleOne);
        assertArrayEquals(expectedBoard, actualBoard);

        // Test an example two variant in board format
        int[][] boardExampleTwo = new int[][] {
                { 0, 0, 0, 0, 0 },
                { 1, 1, 0, 3, 1 },
                { 1, 0, 1, 1, 4 },
                { 2, 1, 2, 1, 1 }
        };
        expectedBoard = new int[][] {
                { 0, 0, 0, 0, 0 },
                { 1, 0, 0, 0, 0 },
                { 2, 0, 3, 2, 4 },
                { 2, 2, 2, 1, 1 }
        };

        // Use reflection to test column board
        actualBoard = (int[][]) mergeBottomLeft.invoke(logicInstance, (Object) boardExampleTwo);
        assertArrayEquals(expectedBoard, actualBoard);
    }

    /*
     * These methods are used to test behavioral methods outside of the Logic class
     * I do not anticipate testing any other behavioral methods, as they are
     * directly invoked using the Logic class.
     */
    Method slideLeft;
    Method slideRight;

    /**
     * This helper method declares the private methods to be tested.
     * 
     * @throws SecurityException     when needed, java handles
     * @throws NoSuchMethodException when method name does not exist
     */
    private void behavior() throws SecurityException, NoSuchMethodException {
        // grab the parseArgs method
        slideLeft = SlideGame.class.getDeclaredMethod("slideLeft");
        slideLeft.setAccessible(true);

        // Grab the empty board helper method
        slideRight = SlideGame.class.getDeclaredMethod("slideRight");
        slideRight.setAccessible(true);
    }

    // Tests the slide left method
    @Test
    public void slideLeftTest() throws SecurityException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // Use the private helper method to create the methods
        privateMethods();
        behavior();

        // Expected array
        int[][] expectedBoard;

        // Test a board with four rows full of zeros
        game.setGameBoard((int[][]) emptyBoard.invoke(game, new int[] { 4, 4 }));
        game.setBoardDim(new int[] { 4, 4 });
        expectedBoard = new int[][] {
            { 0, 0, 0, 0 },
            { 0, 0, 0, 0 },
            { 0, 0, 0, 0 },
            { 0, 0, 0, 0 }
        };

        // Use reflection to test four empty rows
        slideLeft.invoke(game);
        assertBoardEquals(expectedBoard, game.getGameBoard());

        // Test a column board with all different values
        int[][] columnBoardDiff = new int[][] {
                { 1 },
                { 2 },
                { 3 },
                { 4 }
        };
        game.setGameBoard(columnBoardDiff);

        // Assign expected board
        expectedBoard = columnBoardDiff;

        // Use reflection to test column
        slideLeft.invoke(game);
        assertBoardEquals(expectedBoard, game.getGameBoard());

        // Test a column board with different values, empty on top (test first)
        int[][] columnBoardSameFirst = new int[][] {
                { 0 },
                { 1 },
                { 2 },
                { 3 }
        };
        game.setGameBoard(columnBoardSameFirst);

        // Assign expected board
        expectedBoard = columnBoardSameFirst;

        // Use reflection to test column
        slideLeft.invoke(game);
        assertBoardEquals(expectedBoard, game.getGameBoard());

        // Test a column board with different values, empty on middle (test middle)
        int[][] columnBoardSameMiddle = new int[][] {
                { 1 },
                { 0 },
                { 2 },
                { 3 }
        };
        game.setGameBoard(columnBoardSameMiddle);

        // Assign expected board
        expectedBoard = columnBoardSameMiddle;

        // Use reflection to test column
        slideLeft.invoke(game);
        assertBoardEquals(expectedBoard, game.getGameBoard());

        // Test a column board with different values, empty on bottom (test last)
        int[][] columnBoardSameLast = new int[][] {
                { 1 },
                { 2 },
                { 3 },
                { 0 }
        };
        game.setGameBoard(columnBoardSameLast);

        // Assign expected board
        expectedBoard = columnBoardSameLast;

        // Use reflection to test column
        slideLeft.invoke(game);
        assertBoardEquals(expectedBoard, game.getGameBoard());
        
        // Test a column board with equal numbers in first two rows (test first)
        int[][] boardFirstTwo = new int[][] {
                { 1, 1, 1, 1 },
                { 1, 1, 1, 1 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
        };
        game.setGameBoard(boardFirstTwo);

        // Assign expected board
        expectedBoard = new int[][] {
            { 2, 2, 0, 0 },
            { 2, 2, 0, 0 },
            { 0, 0, 0, 0 },
            { 0, 0, 0, 0 }
        };

        // Use reflection to test columns
        slideLeft.invoke(game);
        assertBoardEquals(expectedBoard, game.getGameBoard());

        // Test a column board with equal numbers in middle two rows (test middle)
        int[][] boardMiddleTwo = new int[][] {
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 },
                { 1, 1, 1, 1 },
                { 0, 0, 0, 0 }
        };
        game.setGameBoard(boardMiddleTwo);

        // Assign expected board
        expectedBoard = new int[][] {
                { 0, 0, 0, 0 },
                { 2, 2, 0, 0 },
                { 2, 2, 0, 0 },
                { 0, 0, 0, 0 }
        };

        // Use reflection to test columns - expected board should not change
        slideLeft.invoke(game);
        assertBoardEquals(expectedBoard, game.getGameBoard());

        // Test a column board with equal numbers in separated rows (test middle)
        int[][] boardSeparateMiddle = new int[][] {
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 },
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 }
        };
        game.setGameBoard(boardSeparateMiddle);

        // Assign expected board
        expectedBoard = new int[][] {
                { 0, 0, 0, 0 },
                { 2, 2, 0, 0 },
                { 0, 0, 0, 0 },
                { 2, 2, 0, 0 }
        };

        // Use reflection to test columns - expected board should not change
        slideLeft.invoke(game);
        assertBoardEquals(expectedBoard, game.getGameBoard());

        // Test a column board with equal numbers in last two rows (test last)
        int[][] boardLastTwo = new int[][] {
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 },
                { 1, 1, 1, 1 }
        };
        game.setGameBoard(boardLastTwo);

        // Assign expected board
        expectedBoard = new int[][] {
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 2, 2, 0, 0 },
                { 2, 2, 0, 0 }
        };

        // Use reflection to test columns - expected board should not change
        slideLeft.invoke(game);
        assertBoardEquals(expectedBoard, game.getGameBoard());

        // Test an example one variant in board format
        int[][] boardExampleOne = new int[][] {
                { 0, 1, 1, 1, 1 },
                { 1, 0, 1, 1, 1 },
                { 1, 1, 0, 1, 1 },
                { 0, 0, 0, 0, 0 }
        };
        game.setGameBoard(boardExampleOne);

        // Assign the expected board
        expectedBoard = new int[][] {
                { 2, 2, 0, 0, 0 },
                { 2, 2, 0, 0, 0 },
                { 2, 2, 0, 0, 0 },
                { 0, 0, 0, 0, 0 }
        };

        // Use reflection to test columns
        slideLeft.invoke(game);
        assertBoardEquals(expectedBoard, game.getGameBoard());

        // Test an example two variant in board format
        int[][] boardExampleTwo = new int[][] {
                { 0, 1, 1, 2, 0 },
                { 1, 1, 0, 3, 1 },
                { 1, 0, 1, 1, 4 },
                { 2, 1, 2, 1, 1 }
        };
        game.setGameBoard(boardExampleTwo);
        
        // Assign the expected board
        expectedBoard = new int[][] {
                { 4, 0, 0, 0, 0 },
                { 2, 3, 1, 0, 0 },
                { 2, 1, 4, 0, 0 },
                { 2, 1, 2, 2, 0 }
        };

        // Use reflection to test columns
        slideLeft.invoke(game);
        assertBoardEquals(expectedBoard, game.getGameBoard());
    }

    // Test the slide right method
    @Test
    public void slideRightTest() throws SecurityException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // Use the private helper method to create the methods
        privateMethods();
        behavior();

        // Expected array
        int[][] expectedBoard;

        // Test a board with four rows full of zeros
        game.setGameBoard((int[][]) emptyBoard.invoke(game, new int[] { 4, 4 }));
        expectedBoard = new int[][] {
            { 0, 0, 0, 0 },
            { 0, 0, 0, 0 },
            { 0, 0, 0, 0 },
            { 0, 0, 0, 0 }
        };

        // Use reflection to test four empty rows
        slideRight.invoke(game);
        assertBoardEquals(expectedBoard, game.getGameBoard());

        // Test a column board with all different values
        int[][] columnBoardDiff = new int[][] {
                { 1 },
                { 2 },
                { 3 },
                { 4 }
        };
        game.setGameBoard(columnBoardDiff);

        // Assign expected board
        expectedBoard = columnBoardDiff;

        // Use reflection to test column
        slideRight.invoke(game);
        assertBoardEquals(expectedBoard, game.getGameBoard());

        // Test a column board with different values, empty on top (test first)
        int[][] columnBoardSameFirst = new int[][] {
                { 0 },
                { 1 },
                { 2 },
                { 3 }
        };
        game.setGameBoard(columnBoardSameFirst);

        // Assign expected board
        expectedBoard = columnBoardSameFirst;

        // Use reflection to test column
        slideRight.invoke(game);
        assertBoardEquals(expectedBoard, game.getGameBoard());

        // Test a column board with different values, empty on middle (test middle)
        int[][] columnBoardSameMiddle = new int[][] {
                { 1 },
                { 0 },
                { 2 },
                { 3 }
        };
        game.setGameBoard(columnBoardSameMiddle);

        // Assign expected board
        expectedBoard = columnBoardSameMiddle;

        // Use reflection to test column
        slideRight.invoke(game);
        assertBoardEquals(expectedBoard, game.getGameBoard());

        // Test a column board with different values, empty on bottom (test last)
        int[][] columnBoardSameLast = new int[][] {
                { 1 },
                { 2 },
                { 3 },
                { 0 }
        };
        game.setGameBoard(columnBoardSameLast);

        // Assign expected board
        expectedBoard = columnBoardSameLast;

        // Use reflection to test column
        slideRight.invoke(game);
        assertBoardEquals(expectedBoard, game.getGameBoard());
        
        // Test a column board with equal numbers in first two rows (test first)
        int[][] boardFirstTwo = new int[][] {
                { 1, 1, 1, 1 },
                { 1, 1, 1, 1 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
        };
        game.setGameBoard(boardFirstTwo);

        // Assign expected board
        expectedBoard = new int[][] {
            { 0, 0, 2, 2 },
            { 0, 0, 2, 2 },
            { 0, 0, 0, 0 },
            { 0, 0, 0, 0 }
        };

        // Use reflection to test columns
        slideRight.invoke(game);
        assertBoardEquals(expectedBoard, game.getGameBoard());

        // Test a column board with equal numbers in middle two rows (test middle)
        int[][] boardMiddleTwo = new int[][] {
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 },
                { 1, 1, 1, 1 },
                { 0, 0, 0, 0 }
        };
        game.setGameBoard(boardMiddleTwo);

        // Assign expected board
        expectedBoard = new int[][] {
            { 0, 0, 0, 0 },
            { 0, 0, 2, 2 },
            { 0, 0, 2, 2 },
            { 0, 0, 0, 0 }
        };

        // Use reflection to test columns - expected board should not change
        slideRight.invoke(game);
        assertBoardEquals(expectedBoard, game.getGameBoard());

        // Test a column board with equal numbers in separated rows (test middle)
        int[][] boardSeparateMiddle = new int[][] {
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 },
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 }
        };
        game.setGameBoard(boardSeparateMiddle);

        // Assign expected board
        expectedBoard = new int[][] {
            { 0, 0, 0, 0 },
            { 0, 0, 2, 2 },
            { 0, 0, 0, 0 },
            { 0, 0, 2, 2 }
        };

        // Use reflection to test columns - expected board should not change
        slideRight.invoke(game);
        assertBoardEquals(expectedBoard, game.getGameBoard());

        // Test a column board with equal numbers in last two rows (test last)
        int[][] boardLastTwo = new int[][] {
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 1, 1, 1, 1 },
                { 1, 1, 1, 1 }
        };
        game.setGameBoard(boardLastTwo);

        // Assign expected board
        expectedBoard = new int[][] {
            { 0, 0, 0, 0 },
            { 0, 0, 0, 0 },
            { 0, 0, 2, 2 },
            { 0, 0, 2, 2 }
        };

        // Use reflection to test columns - expected board should not change
        slideRight.invoke(game);
        assertBoardEquals(expectedBoard, game.getGameBoard());

        // Test an example one variant in board format
        int[][] boardExampleOne = new int[][] {
                { 0, 1, 1, 1, 1 },
                { 1, 0, 1, 1, 1 },
                { 1, 1, 0, 1, 1 },
                { 0, 0, 0, 0, 0 }
        };
        game.setGameBoard(boardExampleOne);

        // Assign the expected board
        expectedBoard = new int[][] {
                { 0, 0, 0, 2, 2 },
                { 0, 0, 0, 2, 2 },
                { 0, 0, 0, 2, 2 },
                { 0, 0, 0, 0, 0 }
        };

        // Use reflection to test columns
        slideRight.invoke(game);
        assertBoardEquals(expectedBoard, game.getGameBoard());

        // Test an example two variant in board format
        int[][] boardExampleTwo = new int[][] {
                { 0, 1, 1, 2, 0 },
                { 1, 1, 0, 3, 1 },
                { 1, 0, 1, 1, 4 },
                { 2, 1, 2, 1, 1 }
        };
        game.setGameBoard(boardExampleTwo);
        
        // Assign the expected board
        expectedBoard = new int[][] {
                { 0, 0, 0, 2, 2 },
                { 0, 0, 2, 3, 1 },
                { 0, 0, 1, 2, 4 },
                { 0, 0, 2, 1, 4 }
        };

        // Use reflection to test columns
        slideRight.invoke(game);
        assertBoardEquals(expectedBoard, game.getGameBoard());
    }
}