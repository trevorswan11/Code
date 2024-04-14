package projectFour;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import javafx.application.Application;
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
            try {
                row = Integer.parseInt(params.getRaw().get(0));
                col = Integer.parseInt(params.getRaw().get(1));
            } catch (Exception e) {
                col = 4;
                row = 4;
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
                    zeroIndices.add(new int[]{i, j});
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
     * The main method of the application. Launches the game with arguments.
     * 
     * @param args The size of the board formatted as "length width". With no input,
     *             defaults to 4 x 4.
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
}