package projectFour;

import java.util.List;

import javafx.application.Application;
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
     * of the board.
     * 
     * @param input A String List that is the raw arguments from the user
     * @return A 2x1 int array for the dimensions
     */
    private static int[] parseArgs(List<String> input) {
        // A temp array for the dimensions
        int[] dimensions = new int[] { 4, 4 };

        // The length of the board
        int length = -1;

        // The width of the board
        int width = -1;

        // A boolean variable indicating if the dimensions have been filled
        boolean filled = false;

        /*
         * Determine what the user requested for the board size
         * The loop parses the String to an int and stores the first two in an int array
         */
        for (String num : input) {
            // if length has not been filled, then try to fill it
            if (length == -1 && filled == false)
                // Try to parse the num as an int
                try {
                    // Temp length
                    int first = Integer.parseInt(num);

                    // Make sure first is not smaller than 4
                    if (first < 4) {
                        length = 4;
                    }

                    // Make sure first is not larger than 10
                    else if (first > 10) {
                        length = 10;
                    }

                    // Otherwise set length to the parsed value
                    else {
                        length = first;
                    }
                }

                // If an exception was caught, 4 is default 
                catch (Exception e) {
                    length = 4;
                }
            
            // if the length has been filled but the dimensions are not filled
            else if (filled == false) {
                // Try to parse the num as an int
                try {
                    // Temp width
                    int second = Integer.parseInt(num);

                    // Make sure first is not smaller than 4
                    if (second < 4) {
                        width = 4;
                    }

                    // Make sure first is not larger than 10
                    else if (second > 10) {
                        width = 10;
                    }

                    // Otherwise set width to the parsed value
                    else {
                        width = second;
                    }
                } 
                
                // if an exception was caught, 4 is the default
                catch (Exception e) {
                    width = 4;
                }

                // Set filled to true and width and length must've been reached
                filled = true;
            }
        }

        // If the parameters were filled, then set them to the dimensions
        if (filled) {
            dimensions[0] = length;
            dimensions[1] = width;
        }

        // Return the array
        return dimensions;
    }

    /**
     * A method to retrieve the board's dimensions.
     * @return A 2 column, single row array containing length X width data
     */
    public int[] getBoardDim() {
        return boardDim;
    }

    /**
     * A method to set the board's dimensions.
     * @param boardDim A 2 column, single row int array representing length x width
     */
    public void setBoardDim(int[] boardDim) {
        this.boardDim = boardDim;
    }

    /**
     * A method to retrieve the contents of the game board
     * @return A 2D array of ints signifying the game's state.
     */
    public int[][] getGameBoard() {
        return gameBoard;
    }

    /**
     * A method to set the entire state of the game.
     * @param gameBoard A full game board 2D array of ints
     */
    public void setGameBoard(int[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * A Method to set an individual value in the board game
     * @param val The value to be placed in the array
     * @param col The column position of the value
     * @param row The row position of the value
     */
    public void setPosition(int val, int row, int col) {
        // Create a temporary board
        int[][] gameBoard = this.getGameBoard();

        // Try to change the value in the array
        try {
            gameBoard[row][col] = val;
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
        List<String> input = getParameters().getRaw();
        this.setBoardDim(SlideGame.parseArgs(input));

        
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
