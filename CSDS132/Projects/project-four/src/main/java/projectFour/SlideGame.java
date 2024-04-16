package projectFour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * A parody of the popular game, 2048. This version allows variable grid sizes
 * declared when launching the application. Grids range from 4 x 4 to 11 x 11,
 * though most methods theoretically work for any 2D array.
 * 
 * @author Trevor Swan
 * @version CSDS132 - Spring 2024
 */
@SuppressWarnings("unused")
public class SlideGame extends Application {
    // An int array to store the size of the board
    private int[] boardDim;

    // An int array to store the state of the game board with default 4 x 4
    private int[][] gameBoard;

    // The GridPane for the game
    private GridPane gameGrid;

    // An object to store the logic of the game
    private final Logic logic;

    /**
     * A constructor for the SlideGame class to prevent stack overflows.
     */
    public SlideGame() {
        this.logic = new Logic(this);
    }

    /**
     * A method to retrieve the boards dimensions.
     * 
     * @return The boards dimensions as an int array in form {width, length}
     */
    public int[] getBoardDim() {
        return this.boardDim;
    }

    /**
     * A method to set the board's dimensions.
     * 
     * @param boardDim A 2 column, single row int array representing {width, length}
     */
    public void setBoardDim(int[] boardDim) {
        // If the board is null or dimensions are 1D, set it to 4 x 4
        if (boardDim == null || boardDim.length == 1) {
            boardDim = new int[] { 4, 4 };
        }

        // Prevent the board from being smaller than 4 x 4. check rows
        if (boardDim[0] < 4) {
            boardDim[0] = 4;
        }

        // Prevent board from exceeding 11 x 11. check rows
        else if (boardDim[0] > 11) {
            boardDim[0] = 11;
        }

        // Check cols for the same
        if (boardDim[1] < 4) {
            boardDim[1] = 4;
        }

        // Check cols for the same
        else if (boardDim[1] > 11) {
            boardDim[1] = 11;
        }

        // Do the actual setting part of this setter method
        this.boardDim = boardDim;
    }

    /**
     * A method to retrieve the contents of the game board
     * 
     * @return A 2D array of ints signifying the game's state.
     */
    public int[][] getGameBoard() {
        return gameBoard;
    }

    /**
     * A method to set the entire state of the game.
     * 
     * @param gameBoard A full game board 2D array of ints
     */
    public void setGameBoard(int[][] gameBoard) {
        this.setBoardDim(new int[] { gameBoard.length, gameBoard[0].length });
        this.gameBoard = gameBoard;
    }

    /**
     * A method to retrieve the game grid
     *
     * @return The game grid
     */
    public GridPane getGameGrid() {
        return gameGrid;
    }

    /**
     * A method to set the game grid
     * 
     * @param gameGrid The game grid
     */
    public void setGameGrid(GridPane gameGrid) {
        this.gameGrid = gameGrid;
    }

    /**
     * A helper method to copy a 2D array that is the game board.
     * 
     * @param board The board to copy
     * @return The copied board
     */
    private int[][] copyBoard(int[][] board) {
        // Return null if the board is null of 0 length
        if (board == null || board.length == 0) {
            return null;
        }

        // Establish the new board with correct dimensions
        int[][] copy = new int[board.length][board[0].length];

        // Use System.arraycopy to copy the board
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, board[i].length);
        }

        // Return the copied board
        return copy;
    }

    /**
     * A helper method to parse the args of the command line to set the dimensions
     * of the board. Takes command line arguments and directly modifies the
     * dimensions.
     * 
     * @param params The command line arguments
     */
    private void parseArgs(Parameters params) {
        // Variables to store the row and column value
        int row = -1;
        int col = -1;

        // Parse the command line arguments
        if (!params.getRaw().isEmpty()) {
            // Try to parse the row value
            try {
                row = Integer.parseInt(params.getRaw().get(0));
            } 
            
            // If the slot has a non-numeric value, set row to 4
            catch (Exception e) {
                row = 4;
            }

            // Try to parse the column value
            try {
                col = Integer.parseInt(params.getRaw().get(1));
            } 
            
            // If the slot has a non-numeric value, set column to 4
            catch (Exception e) {
                col = 4;
            }
        }

        // Otherwise set length and row to 4
        else {
            col = 4;
            row = 4;
        }

        // If the row is unreasonable, change to a rounded value
        if (row < 4) {
            row = 4;
        }

        // For space and efficiency, boards are limited to 11 by 11
        else if (row > 11) {
            row = 11;
        }

        // If the length is unreasonable, change to a rounded value
        if (col < 4) {
            col = 4;
        }

        // For space and efficiency, boards are limited to 11 by 11
        else if (col > 11) {
            col = 11;
        }

        // Set the board dimensions based on length and width
        this.setBoardDim(new int[] { row, col });
    }

    /**
     * A Method to set an individual value in the board game
     * 
     * @param val      The value to be placed in the array
     * @param position an array indicating the position in the game to alter as
     *                 {row, column}
     */
    private void setPosition(int val, int[] position) {
        // Create a temporary board
        int[][] gameBoard = this.getGameBoard();

        // Try to change the value in the array
        try {
            gameBoard[position[0]][position[1]] = val;
            this.setGameBoard(gameBoard);
        }

        // If indices are out of bounds, return the base board
        catch (Exception e) {
            this.setGameBoard(gameBoard);
        }
    }

    /**
     * The entrypoint for the application. Is called directly when the application
     * is launched.
     * 
     * @param primaryStage A Stage that will be used to create the JavaFX Window
     */
    @Override
    public void start(Stage primaryStage) {
        // Gather the command line arguments and parse it
        this.parseArgs(getParameters());

        // Create the pane
        this.setGameGrid(new GridPane());

        // Generate the original board and set one random place to 1
        this.setGameBoard(this.emptyBoard(this.getBoardDim()));
        this.setPosition(1, this.randomIndex());

        // base update the board and set button actions
        Button[][] buttonBoard = this.updateGameBoard(gameGrid);
        this.setButtonActions(buttonBoard);

        // Organize the GUI with a BorderPane
        BorderPane slideGame = new BorderPane();
        slideGame.setCenter(gameGrid);

        // Create/set/show the scene
        Scene scene = new Scene(slideGame);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Slide Game");
        primaryStage.show();
    }
    
    /**
     * This method updates the game board, the current state of the game is used to
     * update the button string content. This method cannot be tested using JUint
     * based off of my current knowledge, but everything it uses is thoroughly
     * tested.
     * 
     * @param pane The pane to be updated that lives in the scene
     */
    private Button[][] updateGameBoard(GridPane pane) {
        // Retrieve the board's dimensions
        int row = this.getBoardDim()[0];
        int col = this.getBoardDim()[1];

        // Create a GridPane of buttons fitting the dimensions of the board
        Button[][] buttons = new Button[row][col];

        /*
         * Creates buttons and adds them to the button pane.
         * precondition: row and col have been determined
         * postcondition: button pane has array with dimensions matching gameBoard
         */
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // Create a button and store it in the array of buttons
                Button button = new Button();
                buttons[i][j] = button;

                // Set the size and position of the button
                button.setMinSize(50, 50);
                button.setAlignment(Pos.CENTER);

                // Set the text of the button to the text in the game board
                String text = Integer.toString(this.getGameBoard()[i][j]);
                button.setText(text.equals("0") ? "" : text);

                // Add the button to the pane
                pane.add(button, j, i);
            }
        }

        // return the array of buttons
        return buttons;
    }

    /**
     * Defines the behavior of the key buttons around the board. Testing not possible here. 
     *
     * @param buttons The array of buttons to have their actions set
     */
    private void setButtonActions(Button[][] buttons) {
        // Loop through all the buttons in the array
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                // Set the top corner button to up left
                if (i == 0 && j == 0) {
                    buttons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                        /**
                         * Handles button presses of the upper left corner.
                         * 
                         * @param e the event that is the button press
                         */
                        @Override
                        public void handle(ActionEvent e) {
                            System.out.println("You clicked me: up-left");
                        }
                    });
                }
                
                // Set the top row minus the last button to up
                else if (i == 0 && j > 0 && j < buttons[i].length - 1) {
                    buttons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                        /**
                         * Handles button presses of the upper row minus corners.
                         * 
                         * @param e the event that is the button press
                         */
                        @Override
                        public void handle(ActionEvent e) {
                            // Copy the board for comparison
                            int[][] copy = copyBoard(getGameBoard());

                            // Slide the board up
                            slideUp();

                            // If nothing changed, return
                            if (Arrays.deepEquals(copy, getGameBoard())) {
                                return;
                            }

                            // Otherwise set a random position to 1
                            setPosition(1, randomIndex());

                            // Update the button pane
                            Button[][] buttonBoard = updateGameBoard(getGameGrid());

                            // Reset the button actions
                            setButtonActions(buttonBoard);
                        }
                    });
                }
                
                // Set the top right button to up right
                else if (i == 0 && j == buttons[i].length - 1) {
                    buttons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                        /**
                         * Handles button presses of the upper right corner.
                         * 
                         * @param e the event that is the button press
                         */
                        @Override
                        public void handle(ActionEvent e) {
                            System.out.println("You clicked me: up-right");
                        }
                    });
                }

                // Set the left column minus the top and bottom rows to left
                else if (i > 0 && i < buttons.length - 1 && j == 0) {
                    buttons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                        /**
                         * Handles button presses of the left side minus corners.
                         * 
                         * @param e the event that is the button press
                         */
                        @Override
                        public void handle(ActionEvent e) {
                            // Copy the board for comparison
                            int[][] copy = copyBoard(getGameBoard());

                            // Slide the board up
                            slideLeft();

                            // If nothing changed, return
                            if (Arrays.deepEquals(copy, getGameBoard())) {
                                return;
                            }

                            // Otherwise set a random position to 1
                            setPosition(1, randomIndex());

                            // Update the button pane
                            Button[][] buttonBoard = updateGameBoard(getGameGrid());

                            // Reset the button actions
                            setButtonActions(buttonBoard);
                        }
                    });
                }

                // Set the right column minus the top and bottom rows to right
                else if (i > 0 && i < buttons.length - 1 && j == buttons[i].length - 1) {
                    buttons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                        /**
                         * Handles button presses of the right side minus corners.
                         * 
                         * @param e the event that is the button press
                         */
                        @Override
                        public void handle(ActionEvent e) {
                            // Copy the board for comparison
                            int[][] copy = copyBoard(getGameBoard());

                            // Slide the board up
                            slideRight();

                            // If nothing changed, return
                            if (Arrays.deepEquals(copy, getGameBoard())) {
                                return;
                            }

                            // Otherwise set a random position to 1
                            setPosition(1, randomIndex());

                            // Update the button pane
                            Button[][] buttonBoard = updateGameBoard(getGameGrid());

                            // Reset the button actions
                            setButtonActions(buttonBoard);
                        }
                    });
                }
                
                // Set the bottom left button to down left
                else if (i == buttons.length - 1 && j == 0) {
                    buttons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                        /**
                         * Handles button presses of the lower left corner.
                         * 
                         * @param e the event that is the button press
                         */
                        @Override
                        public void handle(ActionEvent e) {
                            System.out.println("You clicked me: down-left");
                        }
                    });
                }

                // Set the bottom row minus the first and last buttons to down
                else if (i == buttons.length - 1 && j > 0 && j < buttons[i].length - 1) {
                    buttons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                        /**
                         * Handles button presses of the bottom row minus corners.
                         * 
                         * @param e the event that is the button press
                         */
                        @Override
                        public void handle(ActionEvent e) {
                            // Copy the board for comparison
                            int[][] copy = copyBoard(getGameBoard());

                            // Slide the board up
                            slideDown();

                            // If nothing changed, return
                            if (Arrays.deepEquals(copy, getGameBoard())) {
                                return;
                            }

                            // Otherwise set a random position to 1
                            setPosition(1, randomIndex());

                            // Update the button pane
                            Button[][] buttonBoard = updateGameBoard(getGameGrid());

                            // Reset the button actions
                            setButtonActions(buttonBoard);
                        }
                    });
                }
                
                // Set the bottom right button to down right
                else if (i == buttons.length - 1 && j == buttons[i].length - 1) {
                    buttons[i][j].setOnAction(new EventHandler<ActionEvent>() {
                        /**
                         * Handles button presses of the lower right corner.
                         * 
                         * @param e the event that is the button press
                         */
                        @Override
                        public void handle(ActionEvent e) {
                            System.out.println("You clicked me: down-right");
                        }
                    });
                }
            }
        }
    }

    /**
     * Creates an empty board of specified 2D size full of zeros.
     * 
     * @param dimensions {row, col} array to create the empty board
     */
    private int[][] emptyBoard(int[] dimensions) {
        // Return null if the dimensions are null
        if (dimensions == null) {
            return null;
        }

        // Return null if the dimensions are 0
        if (dimensions[0] == 0 || dimensions[1] == 0) {
            return null;
        }

        // Create a array with row nested arrays
        int[][] board = new int[dimensions[0]][dimensions[1]];

        // set all the indices to zero
        for (int i = 0; i < dimensions[0]; i++) {
            for (int j = 0; j < dimensions[1]; j++) {
                board[i][j] = 0;
            }
        }

        // Return the full board
        return board;
    }

    /**
     * Generates a pseudo-random position in the board to alter. The position's
     * criteria is that it contains 0.
     * 
     * @return An array in form {row, column} which is randomly chosen from a pool
     *         of zero-values
     * @throws NoSuchElementException when no indices fir the criteria of being zero 
     */
    private int[] randomIndex() throws NoSuchElementException {
        // Grab the board field
        int[][] board = this.getGameBoard();

        // Create an empty array list to store potential zero indices
        List<int[]> zeroIndices = new ArrayList<>();

        // Find all indices with zero values
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    zeroIndices.add(new int[] { i, j });
                }
            }
        }

        // If no zero values found, throw exception
        if (zeroIndices.isEmpty()) {
            throw new NoSuchElementException("No spaces on the board are empty.");
        }

        // Select a random index from zeroIndices
        Random rand = new Random();
        int[] randomIndex = zeroIndices.get(rand.nextInt(zeroIndices.size()));
        return randomIndex;
    }

    /**
     * Transposes the board vertically. The board is transposed by reversing the
     * order of the values in each row.
     * 
     * @return The transposed board, flipped along the vertical axis
     */
    private int[][] flipVertical() {
        // Retrieve the board
        int[][] board = this.getGameBoard();

        // Loop through the board dor as many rows there are
        for (int i = 0; i < board.length; i++) {
            // Create pointers for left and right
            int left = 0;
            int right = board[i].length - 1;

            // While the left pointer is less than the right, swap the values
            while (left < right) {
                // Store the value in temp to swap properly
                int temp = board[i][left];

                // Swap the values using the temp and pointers
                board[i][left] = board[i][right];
                board[i][right] = temp;

                // Increment and decrement as needed
                left++;
                right--;
            }
        }

        // Return the board
        return board;
    }

    /**
     * Transposes the board horizontally. The board is transposed by reversing the
     * order of the values in each column.
     * 
     * ! This method may not be necessary, but it is included and tested just in case
     * ! it is needed for future logic.
     * 
     * @return The transposed board, flipped along the horizontal axis
     */
    private int[][] flipHorizontal() {
        // Retrieve the board
        int[][] board = this.getGameBoard();

        // Loop through the board for as many columns there are
        for (int i = 0; i < board[0].length; i++) {
            // Create pointers for top and bottom
            int top = 0;
            int bottom = board.length - 1;

            // While the top pointer is less than the bottom, swap the values
            while (top < bottom) {
                // Store the value in temp to swap properly
                int temp = board[top][i];

                // Swap the values using temp and pointers
                board[top][i] = board[bottom][i];
                board[bottom][i] = temp;

                // increment and decrement as needed
                top++;
                bottom--;
            }
        }

        // Return the board
        return board;
    }

    /**
     * Transposes the board by definition. The board is transposed by changing the
     * first column to the first row, and the first row to the first column, etc.
     * 
     * @return The transposed board, uses definition of matrix transposition
     */
    private int[][] transpose() {
        // Retrieve the board
        int[][] board = this.getGameBoard();

        // Create a new array with column and row swapped
        int[][] transposed = new int[board[0].length][board.length];

        // Loop through the boards full contents
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                transposed[j][i] = board[i][j];
            }
        }

        // Return the new array
        return transposed;
    }

    /**
     * A class that contains all the logic for the game. It handles the sliding
     * mechanics for individual rows and columns.
     *  
     * @author Trevor Swan
     * @version CSDS132 - Spring 2024
     */
    private static class Logic {
        private SlideGame arbitraryGame;

        /**
         * A constructor for the logic class to prevent stack overflows.
         *
         * @param game An instance of the game
         */
        public Logic(SlideGame game) {
            this.arbitraryGame = game;
        }

        /**
         * Slides the elements in a row to the left. Equivalent values are combined, and
         * vacant slots become zeros.
         * 
         * @param row The row to slide
         * @return A new row with the values slid to the left
         */
        private int[] mergeLeft(int[] row) {
            // Return null if the row is null or empty
            if (row == null || row.length == 0) {
                return null;
            }

            // Return the same array if the row has one value
            if (row.length == 1) {
                return row;
            }

            // Create a new array for the result
            int[] resultRow = new int[row.length];

            // Index to keep track of the position in the result array
            int resultIndex = 0;

            // Loop through the row
            for (int i = 0; i < row.length; i++) {
                // If the current value is non-zero
                if (row[i] != 0) {
                    // Find the leftmost position to push the current number
                    while (resultIndex > 0 && resultRow[resultIndex - 1] == 0) {
                        resultIndex--;
                    }

                    // If the leftmost position already has the same number, double it
                    if (resultIndex > 0 && resultRow[resultIndex - 1] == row[i]) {
                        resultRow[resultIndex - 1] *= 2;
                    } 
                    
                    // Otherwise, push the current number to the left
                    else {
                        resultRow[resultIndex] = row[i];
                        resultIndex++;
                    }
                }
            }

            // Fill the remaining slots with zeros
            while (resultIndex < row.length) {
                resultRow[resultIndex] = 0;
                resultIndex++;
            }

            // Return the new row
            return resultRow;
        }

        /**
         * Slides the elements in a row to the right. Equivalent values are combined, and
         * vacant slots become zeros.
         * 
         * @param row The row to slide
         * @return A new row with the values slid to the right
         */
        private int[] mergeRight(int[] row) {
            // Return null if the row is null or empty
            if (row == null || row.length == 0) {
                return null;
            }

            // Return the same array if the row has one value
            if (row.length == 1) {
                return row;
            }

            // copy the game board to prevent overwriting
            int[][] copyBoard = arbitraryGame.copyBoard(arbitraryGame.getGameBoard());

            /*
             * Set and retrieve the board after flipping vertically. mergeLeft is used once
             * board is flipped, as they are effectively the same.
             */
            arbitraryGame.setGameBoard(new int[][] { row } );
            arbitraryGame.setGameBoard(arbitraryGame.flipVertical());
            int [][] gameBoard = arbitraryGame.getGameBoard();
            
            // Use the mergeLeft method to shift the array
            int[] shiftedRight = this.mergeLeft(gameBoard[0]);
            
            // Flip the board back
            arbitraryGame.setGameBoard(new int[][] { shiftedRight } );
            gameBoard = arbitraryGame.flipVertical();

            // Set the game board to its original state
            arbitraryGame.setGameBoard(copyBoard);
            
            // Return the shifted array
            return gameBoard[0];
        }

        /**
         * Slides the elements in a full gameBoard up. Equivalent values are combined, and
         * vacant slots become zeros.
         * 
         * @param board The board to slide
         * @return A new board with the values slid up
         */
        private int[][] mergeUp(int[][] board) {
            // Return null if the row is null or empty
            if (board == null || board.length == 0) {
                return null;
            }

            // Return the same array if the row has one value
            if (board.length == 1 && board[0].length == 1) {
                return board;
            }

            /*
             * Set and retrieve the board after transposing. mergeLeft is used once
             * board is transposed, as they are effectively the same.
             */
            arbitraryGame.setGameBoard(board);
            arbitraryGame.setGameBoard(arbitraryGame.transpose());
            int[][] gameBoard = arbitraryGame.getGameBoard();

            /*
             * Create a resulting board with different dimensions to account for non-square
             * boards
             */
            int[][] resultBoard = new int[gameBoard.length][gameBoard[0].length];
            
            // Loop through the gameBoard and merge each row
            for (int i = 0; i < gameBoard.length; i++) {
                resultBoard[i] = this.mergeLeft(gameBoard[i]);
            }

            /*
             * Transpose the result board to return to the original orientation. Do this using the arbitraryGame
             * object, which is effectively a wrapper for the gameBoard. 
             */
            arbitraryGame.setGameBoard(resultBoard);
            arbitraryGame.setGameBoard(arbitraryGame.transpose());

            // Return the new board
            return arbitraryGame.getGameBoard();
        }

        /**
         * Slides the elements in a full gameBoard down. Equivalent values are combined, and
         * vacant slots become zeros.
         * 
         * @param board The board to slide
         * @return A new board with the values slid down
         */
        private int[][] mergeDown(int[][] board) {
            // Return null if the row is null or empty
            if (board == null || board.length == 0) {
                return null;
            }

            // Return the same array if the row has one value
            if (board.length == 1 && board[0].length == 1) {
                return board;
            }

            /*
             * Set and retrieve the board after transposing. A vertical flip is used once
             * board is transposed, as this simulates downward movement
             */
            arbitraryGame.setGameBoard(board);
            arbitraryGame.setGameBoard(arbitraryGame.transpose());
            arbitraryGame.setGameBoard(arbitraryGame.flipVertical());
            int[][] gameBoard = arbitraryGame.getGameBoard();

            /*
             * Create a resulting board with different dimensions to account for non-square
             * boards
             */
            int[][] resultBoard = new int[gameBoard.length][gameBoard[0].length];
            
            // Loop through the gameBoard and merge each row
            for (int i = 0; i < gameBoard.length; i++) {
                resultBoard[i] = this.mergeLeft(gameBoard[i]);
            }

            
            // Perform the inverse transformation to get the original orientation.
            arbitraryGame.setGameBoard(resultBoard);
            arbitraryGame.setGameBoard(arbitraryGame.flipVertical());
            arbitraryGame.setGameBoard(arbitraryGame.transpose());

            // Return the new board
            return arbitraryGame.getGameBoard();
        }

        /**
         * A helper method that divides a board into its diagonals.
         * 
         * @param board The board to divide
         * @return The extracted diagonals
         */
        private int[][] extractDiagonals(int[][] board) {
            // TODO
            return null;
        }

        /**
         * A helper method that reconstructs a board from its diagonals.
         * 
         * @param diagonals The diagonals to reconstruct
         * @return The reconstructed board
         */
        private int[][] reconstructDiagonals(int[][] diagonals) {
            // TODO
            return null;
        }

        /**
         * Slides the elements in an extracted array of diagonals. The board is then
         * rewritten such that the diagonals become how they were before being
         * extracted, except all elements are shifted towards the top left corner.
         * 
         * @param extracted The extracted diagonals
         * @return A new board with the values slid 'up-left'
         */
        private int[][] mergeTopLeft(int[][] extracted) {
            // TODO
            return null;
        }

        /**
         * Slides the elements in an extracted array of diagonals. The board is then
         * rewritten such that the diagonals become how they were before being
         * extracted, except all elements are shifted towards the top right corner.
         * 
         * @param extracted The extracted diagonals
         * @return A new board with the values slid to 'up-right'
         */
        private int[][] mergeTopRight(int[][] extracted) {
            // TODO
            return null;
        }

        /**
         * Slides the elements in an extracted array of diagonals. The board is then
         * rewritten such that the diagonals become how they were before being
         * extracted, except all elements are shifted towards the bottom left corner.
         * 
         * @param extracted The extracted diagonals
         * @return A new board with the values slid 'down-left'
         */
        private int[][] mergeBottomLeft(int[][] extracted) {
            // TODO
            return null;
        }

        /**
         * Slides the elements in an extracted array of diagonals. The board is then
         * rewritten such that the diagonals become how they were before being
         * extracted, except all elements are shifted towards the bottom right corner.
         * 
         * @param extracted The extracted diagonals
         * @return A new board with the values slid 'down-right'
         */
        private int[][] mergeBottomRight(int[][] extracted) {
            // TODO
            return null;
        }
    }

    /**
     * Slides the board to the left. All zeros are moved to the left and all
     * non-zeroes are added together as possible. Follows standard sliding
     * algorithm from the actual 2048 game.
     */
    private void slideLeft() {
        // Create a new empty board of the same length
        int[][] blankBoard = this.emptyBoard(new int[] {this.getBoardDim()[0], this.getBoardDim()[1]});

        // Set each row of the new board to the shifted left row of the original
        for (int i = 0; i < this.getGameBoard().length; i++) {
            blankBoard[i] = logic.mergeLeft(this.getGameBoard()[i]);
        }

        // Set the new board
        this.setGameBoard(blankBoard);
    }
    
    /**
     * Slides the board to the right. All zeros are moved to the right and all
     * non-zeroes are added together as possible. Follows standard sliding
     * algorithm from the actual 2048 game.
     */
    private void slideRight() {
        // Create a new empty board of the same length
        int[][] blankBoard = this.emptyBoard(new int[] {this.getBoardDim()[0], this.getBoardDim()[1]});

        // Set each row of the new board to the shifted left row of the original
        for (int i = 0; i < this.getGameBoard().length; i++) {
            blankBoard[i] = logic.mergeRight(this.getGameBoard()[i]);
        }

        // Set the new board
        this.setGameBoard(blankBoard);
    }

    /**
     * Slides the board to the up. All zeros are moved to the up and all
     * non-zeroes are added together as possible. Follows standard sliding
     * algorithm from the actual 2048 game.
     */
    private void slideUp() {
        // Invoke Logic to slide up, it handles everything, just needing arguments
        this.setGameBoard(logic.mergeUp(this.getGameBoard()));
    }

    /**
     * Slides the board to the down. All zeros are moved to the down and all
     * non-zeroes are added together as possible. Follows standard sliding
     * algorithm from the actual 2048 game.
     */
    private void slideDown() {
        // Invoke Logic to slide up, it handles everything, just needing arguments
        this.setGameBoard(logic.mergeDown(this.getGameBoard()));
    }

    /**
     * Slides the board to the upper left corner. All zeros are moved to the upper left 
     * and all non-zeroes are added together as possible. Follows standard sliding
     * algorithm from the actual 2048 game, but diagonally.
     */
    private void slideUpLeft() {
        // TODO
    }

    /**
     * Slides the board to the upper right corner. All zeros are moved to the upper right 
     * and all non-zeroes are added together as possible. Follows standard sliding
     * algorithm from the actual 2048 game, but diagonally.
     */
    private void slideUpRight() {
        // TODO
    }

    /**
     * Slides the board to the lower left corner. All zeros are moved to the lower left 
     * and all non-zeroes are added together as possible. Follows standard sliding
     * algorithm from the actual 2048 game, but diagonally.
     */
    private void slideDownLeft() {
        // TODO
    }

    /**
     * Slides the board to the lower right corner. All zeros are moved to the lower right 
     * and all non-zeroes are added together as possible. Follows standard sliding
     * algorithm from the actual 2048 game, but diagonally.
     */
    private void slideDownRight() {
        // TODO
    }

    /**
     * The main method of the application. Launches the game with arguments.
     * 
     * @param args The size of the board formatted as "length width". With no input,
     *             defaults to 4 x 4.
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
}