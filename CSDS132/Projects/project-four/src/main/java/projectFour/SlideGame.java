package projectFour;

import java.util.ArrayList;
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
 * declared when launching the application.
 * 
 * @author Trevor Swan
 * @version CSDS132 - Spring 2024
 */
@SuppressWarnings("unused")
public class SlideGame extends Application {
    // An int array to store the size of the board
    private int[] boardDim;

    // An int array to store the state of the game board
    private int[][] gameBoard;

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
        // If the board is null, set it to 4 x 4
        if (boardDim == null) {
            boardDim = new int[] { 4, 4 };
        }

        // Prevent the board from being smaller than 4 x 4. check rows
        if (boardDim[0] < 4) {
            boardDim[0] = 4;
        }

        // Check cols for the same
        if (boardDim[1] < 4) {
            boardDim[1] = 4;
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
        this.gameBoard = gameBoard;
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

        // If the length is unreasonable, change to a rounded value
        if (col < 4) {
            col = 4;
        }

        // If the row is unreasonable, change to a rounded value
        if (row < 4) {
            row = 4;
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

        // Retrieve the board's dimensions
        int row = this.getBoardDim()[0];
        int col = this.getBoardDim()[1];

        // Create a GridPane of buttons fitting the dimensions of the board
        Button[][] buttons = new Button[row][col];
        GridPane buttonPane = new GridPane();

        // Generate the original board and set one random place to 1
        this.setGameBoard(this.emptyBoard(this.getBoardDim()));
        this.setPosition(1, this.randomIndex());

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
                buttonPane.add(button, j, i);
            }
        }

        // Create a handler for the button clicks
        EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // Get the button that was clicked
            }
        };

        // Organize the GUI with a BorderPane
        BorderPane slideGame = new BorderPane();
        slideGame.setCenter(buttonPane);

        // Create/set/show the scene
        Scene scene = new Scene(slideGame);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Slide Game");
        primaryStage.show();
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
     * Transposes the board horizontally. The board is transposed by reversing the
     * order of the values in each row.
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
     * Transposes the board vertically. The board is transposed by reversing the
     * order of the values in each column.
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
        // An arbitrary slide game for row manipulations
        SlideGame arbitraryGame = new SlideGame();

        /**
         * Slides the elements in a row to the left. Equivalent values are combined, and
         * vacant slots become zeros.
         * 
         * @param row The row to slide
         * @return A new row with the values slid to the left
         */
        private int[] mergeLeft(int[] row) {
            // Create a new row for sliding and returning
            int[] newRow = new int[row.length];



            // TODO
            return null;
        }

        /**
         * Slides the elements in a row to the right. Equivalent values are combined, and
         * vacant slots become zeros.
         * 
         * @param row The row to slide
         * @return A new row with the values slid to the right
         */
        private int[] mergeRight(int[] row) {
            // Set and retrieve the board after flipping vertically
            arbitraryGame.setGameBoard(new int[][] { row } );
            arbitraryGame.flipVertical();
            int[][] gameBoard = arbitraryGame.getGameBoard();

            // TODO
            return null;
        }

        /**
         * Slides the elements in a column to the up. Equivalent values are combined, and
         * vacant slots become zeros.
         * 
         * @param column The column to slide
         * @return A new column with the values slid to the up
         */
        private int[] mergeUp(int[] column) {
            // Set and retrieve the board
            arbitraryGame.setGameBoard(new int[][] { column } );
            int[][] gameBoard = arbitraryGame.getGameBoard();

            // TODO
            return null;
        }

        /**
         * Slides the elements in a column down. Equivalent values are combined, and
         * vacant slots become zeros.
         * 
         * @param column The column to slide
         * @return A new column with the values slid to the down
         */
        private int[] mergeDown(int[] column) {
            // Set and retrieve the board
            arbitraryGame.setGameBoard(new int[][] { column } );
            arbitraryGame.flipVertical();
            int[][] gameBoard = arbitraryGame.getGameBoard();

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
        // TODO
    }
    
    /**
     * Slides the board to the right. All zeros are moved to the right and all
     * non-zeroes are added together as possible. Follows standard sliding
     * algorithm from the actual 2048 game.
     */
    private void slideRight() {
        // TODO
    }

    /**
     * Slides the board to the up. All zeros are moved to the up and all
     * non-zeroes are added together as possible. Follows standard sliding
     * algorithm from the actual 2048 game.
     */
    private void slideUp() {
        // TODO
    }

    /**
     * Slides the board to the down. All zeros are moved to the down and all
     * non-zeroes are added together as possible. Follows standard sliding
     * algorithm from the actual 2048 game.
     */
    private void slideDown() {
        // TODO
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