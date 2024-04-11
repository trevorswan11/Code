package projectFour;

import java.util.NoSuchElementException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * A parody of the popular game, 2048. This version allows variable grid sizes
 * declared when launching the application.
 * 
 * @author Trevor Swan
 * @version CSDS132 - Spring 2024
 */
public class SlideGame extends Application {
    // An int array to store the size of the board
    private int[] boardDim;

    // An int array to store the state of the game board
    private int[][] gameBoard;

    /**
     * A helper method to parse the args of the command line to set the dimensions
     * of the board. Takes command line arguments and directly modifies the
     * dimensions.
     * 
     * @param params The command line arguments
     */
    private void parseArgs(Parameters params) {
        // Variables to store the length and width
        int length = -1;
        int width = -1;

        // Parse the command line arguments
        if (!params.getRaw().isEmpty()) {
            try {
                width = Integer.parseInt(params.getRaw().get(0));
                length = Integer.parseInt(params.getRaw().get(1));
            } catch (Exception e) {
                length = 4;
                width = 4;
            }
        }

        // Otherwise set length and width to 4
        else {
            length = 4;
            width = 4;
        }

        // If the length is unreasonable, change to a rounded value
        if (length < 4) {
            length = 4;
        }

        // I want 10 x 10 to be the max
        else if (length > 10) {
            length = 10;
        }

        // If the width is unreasonable, change to a rounded value
        if (width < 4) {
            width = 4;
        }

        // I want 10 x 10 to be the max
        else if (width > 10) {
            width = 10;
        }

        // Set the board dimensions based on length and width
        this.setBoardDim(new int[] { width, length });
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
     * A Method to set an individual value in the board game
     * 
     * @param val      The value to be placed in the array
     * @param position an array indicating the position in the game to alter as
     *                 {row, column}
     */
    public void setPosition(int val, int[] position) {
        // Create a temporary board
        int[][] gameBoard = this.getGameBoard();

        // Try to change the value in the array
        try {
            gameBoard[position[0]][position[1]] = val;
        }

        // Exception handling doesn't matter, set the board to the temp board
        finally {
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
        int length = this.getBoardDim()[0];
        int width = this.getBoardDim()[1];

        // Create a GridPane of buttons fitting the dimensions of the board
        Button[][] buttons = new Button[width][length];
        GridPane buttonPane = new GridPane();

        // Generate random indices for the original 1
        // int[] randomStart = this.randomIndex();

        // Generate the original board and set one random place to 1
        this.setGameBoard(this.emptyBoard(this.getBoardDim()));
        this.setPosition(1, new int[] {1,1});

        /*
         * Creates buttons and adds them to the button pane.
         * precondition: length and width have been determined
         * postcondition: button pane has array with dimensions matching gameBoard
         */
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                // Create a button and store it in the array of buttons
                Button button = new Button();
                buttons[j][i] = button;

                // Set the size and position of the button
                button.setMinSize(50, 50);
                button.setAlignment(Pos.CENTER);

                // Set the text of the button to the text in the game board
                String text = Integer.toString(getGameBoard()[j][i]);
                button.setText(text.equals("0") ? "" : text);

                // Add the button to the pane
                buttonPane.add(button, i, j);
            }
        }

        // Create/set/show the scene
        Scene scene = new Scene(buttonPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Slide Game");
        primaryStage.show();
    }

    /**
     * Creates an empty board of specified 2D size full of zeros.
     * 
     * @param dimensions Length x Width array to create the empty board
     */
    public int[][] emptyBoard(int[] dimensions) {
        // Create a array with width nested arrays
        int[][] board = new int[dimensions[1]][];

        // Add length arrays of zeros to the board
        for (int i = 0; i < dimensions[1]; i++) {
            board[i] = new int[dimensions[0]];
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
    public int[] randomIndex() throws NoSuchElementException {
        // retrieve the game board and the total number of elements in it
        int[][] board = this.getGameBoard();
        int size = this.getBoardDim()[0] * this.getBoardDim()[1];

        // int to store number of non-zero spaces
        int nonZeros = 0;

        // Check for a non-zero location in the board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // if a non-zero is found, add to the index
                if (board[i][j] != 0) {
                    nonZeros++;
                }
            }
        }

        // If there are as many non-zeros as there are possible positions, throw exception
        if (nonZeros == size) {
            throw new NoSuchElementException("There are no empty locations on the board.");
        }

        // boolean variable to indicate if a suitable index has been found
        boolean found = false;

        // Loop through the game board for as many elements are in it
        int element = 0;
        while (element < size && !found) {
            // Generate two random numbers to index into the board
            int randRow = (int) ((Math.random() * (size - 1)) + 1);
            int randCol = (int) ((Math.random() * (size - 1)) + 1);

            // If either index contains a non-zero value, do nothing

            element++;
        }
        return new int[] {1,1};
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