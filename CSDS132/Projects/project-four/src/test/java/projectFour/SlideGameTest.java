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
        assertEquals(expected.length, actual.length);

        // Loop through the parent array and compare each nested array
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i]);
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
    }

    // Test the parseArgs method
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

        // Test many (more than two entries)
        params = new Params(Arrays.asList("5", "6", "7"));
        parseArgs.invoke(game, (Parameters) params);
        assertArrayEquals(new int[] { 5, 6 }, game.getBoardDim());
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
    @Test(expected = InvocationTargetException.class)
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

        // Test many (a 7 x 9 board)
        int[][] boardManyThree = ascendBoard(7, 9);
        game.setGameBoard(boardManyThree);
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

    /**
     * This helper method declares the private Logic methods to be tested.
     * 
     * @throws SecurityException     when needed, java handles
     * @throws NoSuchMethodException when method name does not exist
     * @throws ClassNotFoundException wen class name does not exist
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    private void logicMethods() throws SecurityException, NoSuchMethodException, ClassNotFoundException, InstantiationException, 
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // Grab the SlideGame class
        Logic = Class.forName("projectFour.SlideGame$Logic");

        // Grab an instance of logic
        Constructor<?> logicConstructor = Logic.getDeclaredConstructor();
        logicConstructor.setAccessible(true);
        logicInstance = logicConstructor.newInstance();

        // Grab the mergeLeft method
        mergeLeft = Logic.getDeclaredMethod("mergeLeft", int[].class);
        mergeLeft.setAccessible(true);

        // Grab the mergeRight method
        mergeRight = Logic.getDeclaredMethod("mergeRight", int[].class);
        mergeRight.setAccessible(true);

        // Grab the mergeUp method
        mergeUp = Logic.getDeclaredMethod("mergeUp", int[].class);
        mergeUp.setAccessible(true);

        // Grab the mergeDown method
        mergeDown = Logic.getDeclaredMethod("mergeDown", int[].class);
        mergeDown.setAccessible(true);
    }

    // Test the slide left method
    @Test
    public void slideLeftTest() 
            throws SecurityException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException,
             IllegalArgumentException, InvocationTargetException, InstantiationException {
        // Use the private helper method to create the methods
        logicMethods();

        // Expected and actual result arrays
        int[] actualRow;
        int[] expectedRow;

        // Test an empty row
        int[] rowEmpty = new int[] {};

        // Use reflection to test empty
        actualRow = (int[]) mergeLeft.invoke(logicInstance, rowEmpty);
        assertArrayEquals(null, actualRow);
    }
}