package projectFour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A parody of the popular game, 2048. This version allows variable grid sizes
 * declared when launching the application. Grids range from 4 x 4 to 11 x 11,
 * though most methods theoretically work for any 2D array.
 * 
 * @author Trevor Swan
 * @version CSDS132 - Spring 2024
 */
public class SlideGame extends Application {
    // An int array to store the size of the board
    private int[] boardDim;

    // An int array to store the state of the game board with default 4 x 4
    private int[][] gameBoard;

    // The GridPane for the game
    private GridPane gameGrid;
    
    // The Text box for the game
    private HBox info = this.getMoveArea();

    // An object to store the background color
    private String color = "-fx-background-color: lightblue;";
    
    // An object to store the logic of the game
    private final Logic logic;
    
    // An object to store the buttons for the game
    private Button[][] buttons;

    // A field to store a board that is operated on
    private int[][] resultBoard;

    // An object to store the move
    private MoveOnAction move = new MoveOnAction();

    // A field to store the game state
    private boolean canPlay = true;

    /**
     * An enum to indicate the movement associated with a button.
     */
    private enum Direction {
        LEFT,
        RIGHT,
        UP,
        DOWN,
        UP_LEFT,
        UP_RIGHT,
        DOWN_LEFT,
        DOWN_RIGHT;
    }

    /**
     * A constructor for the SlideGame class to prevent stack overflows.
     */
    public SlideGame() {
        this.logic = new Logic();
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
        this.setButtons(new Button[gameBoard.length][gameBoard[0].length]);
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
     * Returns the info of the game.
     * 
     * @return The game's text info
     */
    public HBox getInfo() {
        return this.info;
    }

    /**
     * Sets the info of the game.
     * 
     * @param info The info to set
     */
    public void setInfo(HBox info) {
        this.info = info;
    }

    /**
     * A method to get the color of the board.
     * 
     * @return The color of the board
     */
    public String getColor() {
        return this.color;
    }

    /**
     * A getter for the buttons
     *
     * @return The game's buttons
     */
    public Button[][] getButtons() {
        return buttons;
    }

    /**
     * A setter for the buttons
     * 
     * @param buttons The game's buttons
     */
    public void setButtons(Button[][] buttons) {
        this.buttons = buttons;
    }
    
    /**
     * Gets the board for logical operations on the board
     * 
     * @return A board with specified contents to be altered
     */
    public int[][] getResultBoard() {
        return resultBoard;
    }

    /**
     * Sets the blank board for logical operations on the board
     * 
     * @param resultBoard A board with specified contents to be altered
     */
    public void setResultBoard(int[][] resultBoard) {
        this.resultBoard = resultBoard;
    }

    /**
     * Returns the status of the game
     * 
     * @return true if the user can play, false otherwise
     */
    public boolean getPlayStatus() {
        return this.canPlay;
    }

    /**
     * Sets the status of the game.
     * 
     * @param canPlay true if the user can play, false otherwise
     */
    public void setPlayStatus(boolean canPlay) {
        this.canPlay = canPlay;
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
        catch (ArrayIndexOutOfBoundsException e) {
            if (logic.play()) {
                this.setGameBoard(gameBoard);
            } 
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

        // Organize the GUI with a BorderPane - only add bottom padding when needed
        BorderPane slideGame = new BorderPane();
        slideGame.setPadding(new Insets(15, 15, 
                Arrays.equals(this.getBoardDim(), new int[] { 4, 4 }) ? 0 : 15, 0));
        slideGame.setRight(gameGrid);
        slideGame.setLeft(this.getMoveArea());
        this.setInfo(info);
        slideGame.setStyle(this.getColor());

        // Create/set/show the scene
        Scene scene = new Scene(slideGame);
        this.handleKeyPress(primaryStage, scene, buttonBoard);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Slide Game");
        primaryStage.setResizable(false);

        // Finally, show the stage
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
        Button[][] buttons = this.getButtons();

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
                button.setStyle("-fx-font-weight: bold; -fx-background-color: " + this.buttonColor(button) + "; -fx-border-color: black;");

                // Add the button to the pane
                pane.add(button, j, i);
            }
        }

        // return the array of buttons
        return buttons;
    }

    /**
     * This method returns a color based on the text in the button.
     * 
     * @return The color of the button
     */
    private String buttonColor(Button button) {
        // Switch based on number in button
        switch (button.getText()) {
            case "":
                return "darkgray";
            case "1":
                return "orangered";
            case "2":
                return "orange";
            case "4":
                return "lemonchiffon";
            case "8":
                return "lightgreen";
            case "16":
                return "lightskyblue";
            case "32":
                return "thistle";
            case "64":
                return "pink";
            case "128":
                return "rosybrown";
            case "256":
                return "darksalmon";
            case "512":
                return "crimson";
            case "1024":
                return "coral";
            default:
                return "gold";
        }
    }

    /**
     * This helper method informs the user that they lost.
     */
    private void lost() {
        this.setLoseArea();
    }

    /**
     * This helper method resets the state of the game.
     */
    private void reset() {
        // Reset the game board
        this.setGameBoard(this.emptyBoard(this.getBoardDim()));
        this.setPosition(1, this.randomIndex());
        this.setButtonActions(this.updateGameBoard(this.getGameGrid()));
        this.setInfo(this.getMoveArea());
        this.setPlayStatus(true);
    }

    /**
     * Defines the behavior of the key buttons around the board. Testing not
     * possible here.
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
                            // Use nested class to process event
                            move.setDirection(Direction.UP_LEFT);
                            move.performAction(buttons);
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
                            // Use nested class to process event
                            move.setDirection(Direction.UP);
                            move.performAction(buttons);
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
                            // Use nested class to process event
                            move.setDirection(Direction.UP_RIGHT);
                            move.performAction(buttons);
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
                            // Use nested class to process event
                            move.setDirection(Direction.LEFT);
                            move.performAction(buttons);
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
                            move.setDirection(Direction.RIGHT);
                            move.performAction(buttons);
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
                            // Use nested class to process event
                            move.setDirection(Direction.DOWN_LEFT);
                            move.performAction(buttons);
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
                            // Use nested class to process event
                            move.setDirection(Direction.DOWN);
                            move.performAction(buttons);
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
                            // Use nested class to process event
                            move.setDirection(Direction.DOWN_RIGHT);
                            move.performAction(buttons);
                        }
                    });
                }
            }
        }
    }

    /**
     * Defines the keymappings for the board moves. Testing not possible here.
     * 
     * @param stage The stage to alter if needed
     * @param scene The scene to add the keymappings to
     * @param buttonBoard the board that is acted on
     */
    private void handleKeyPress(Stage stage, Scene scene, Button[][] buttonBoard) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            /**
             * Handles keyboard presses. The following keys are supported:
             * A, S, D, F, H, J, K, L, R, Q. They are mapped according to game directions.
             *
             * @param e the event that is the keyboard press
             */
            @Override
            public void handle(KeyEvent e) {
                // Board copy and button pane
                Button[][] buttons = buttonBoard;
                switch (e.getCode()) {
                    case H:
                        // Use nested class to process event
                        move.setDirection(Direction.LEFT);
                        move.performAction(buttons);
                        break;
                    case LEFT:
                        // Use nested class to process event
                        move.setDirection(Direction.LEFT);
                        move.performAction(buttons);
                        break;
                    case J:
                        // Use nested class to process event
                        move.setDirection(Direction.DOWN);
                        move.performAction(buttons);
                        break;
                    case DOWN:
                        // Use nested class to process event
                        move.setDirection(Direction.DOWN);
                        move.performAction(buttons);
                        break;
                    case K:
                        // Use nested class to process event
                        move.setDirection(Direction.UP);
                        move.performAction(buttons);
                        break;
                    case UP:
                        // Use nested class to process event
                        move.setDirection(Direction.UP);
                        move.performAction(buttons);
                        break;
                    case L:
                        // Use nested class to process event
                        move.setDirection(Direction.RIGHT);
                        move.performAction(buttons);
                        break;
                    case RIGHT:
                        // Use nested class to process event
                        move.setDirection(Direction.RIGHT);
                        move.performAction(buttons);
                        break;
                    case A:
                        // Use nested class to process event
                        move.setDirection(Direction.UP_LEFT);
                        move.performAction(buttons);
                        break;
                    case S:
                        // Use nested class to process event
                        move.setDirection(Direction.DOWN_LEFT);
                        move.performAction(buttons);
                        break;
                    case D:
                        // Use nested class to process event
                        move.setDirection(Direction.DOWN_RIGHT);
                        move.performAction(buttons);
                        break;
                    case F:
                        // Use nested class to process event
                        move.setDirection(Direction.DOWN_RIGHT);
                        move.performAction(buttons);
                        break;
                    case R:
                        // Reset the game
                        reset();
                        break;
                    case Q:
                        // Exit the game
                        stage.close();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * A class that contains all the actions for the game. It contains all actions
     * for the game.
     * 
     * @author Trevor Swan
     * @version CSDS132 - Spring 2024
     */
    private class MoveOnAction {
        // The direction of the move
        private Direction direction;

        // A local copy of the board
        private int[][] copyBoard;

        /**
         * Returns the direction of the move.
         * 
         * @return The direction of the move
         */
        private Direction getDirection() {
            return direction;
        }

        /**
         * A constructor for the MoveOnAction class to set the direction.
         * 
         * @param direction The direction of the move from the enum
         */
        public void setDirection(Direction direction) {
            this.direction = direction;
        }

        /**
         * Returns the local copy of the board.
         * 
         * @return The local copy of the board
         */
        public int[][] getCopyBoard() {
            return copyBoard;
        }

        /**
         * Sets the local copy of the board.
         * 
         * @param copyBoard The local copy of the board
         */
        public void setCopyBoard(int[][] copyBoard) {
            this.copyBoard = copyBoard;
        }

        /**
         * A helper method to perform the action of a button.
         *
         * @param buttonBoard The buttons to act on
         */
        private void performAction(Button[][] buttonBoard) {
            // Copy the board for comparison and slide up
            this.setCopyBoard(SlideGame.this.copyBoard(SlideGame.this.getGameBoard()));

            if (SlideGame.this.getPlayStatus()) {
                // Perform the move based on enum value
                switch (this.getDirection()) {
                    case LEFT:
                        SlideGame.this.slideLeft();
                        break;
                    case RIGHT:
                        SlideGame.this.slideRight();
                        break;
                    case UP:
                        SlideGame.this.slideUp();
                        break;
                    case DOWN:
                        SlideGame.this.slideDown();
                        break;
                    case UP_LEFT:
                        SlideGame.this.slideUpLeft();
                        break;
                    case DOWN_LEFT:
                        SlideGame.this.slideDownLeft();
                        break;
                    case DOWN_RIGHT:
                        SlideGame.this.slideDownRight();
                        break;
                    case UP_RIGHT:
                        SlideGame.this.slideUpRight();
                        break;
                    default:
                        break;
                }

                // If nothing changed, return
                if (Arrays.deepEquals(this.getCopyBoard(), SlideGame.this.getGameBoard())) {
                    if (!logic.play()) {
                        SlideGame.this.setPlayStatus(false);
                    }
                    return;
                }

                // Otherwise set a random position to 1
                SlideGame.this.setPosition(1, SlideGame.this.randomIndex());

                // Update the button pane
                buttonBoard = SlideGame.this.updateGameBoard(SlideGame.this.getGameGrid());

                // Reset the button actions
                SlideGame.this.setButtonActions(buttonBoard);
            } else {
                SlideGame.this.lost();
            }
        }
    }

    /**
     * This helper method declares the text area for the rules.
     * 
     * @return The text area for the rules
     */
    private HBox getMoveArea() {
        // Create a string for the background color
        String color = this.getColor();

        // Create a VBox and header for the rules
        VBox moves = new VBox();
        Text header = new Text("Welcome to Slide Game!");

        // Create and style the header
        header.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Create a StringBuilder to store the rules
        StringBuilder builder = new StringBuilder();
        builder.append("Press Tiles or Keys to Move...\n");
        builder.append("\t\u2022 J : Move Down\n");
        builder.append("\t\u2022 H : Move Left\n");
        builder.append("\t\u2022 L : Move Right\n");
        builder.append("\t\u2022 K : Move Up\n");
        builder.append("\t\u2022 A : Move Up-Left\n");
        builder.append("\t\u2022 S : Move Down-Left\n");
        builder.append("\t\u2022 D : Move Down-Right\n");
        builder.append("\t\u2022 F : Move Up-Right\n");
        builder.append("Press R to Reset, Q to Quit");

        // Add the button and text to the VBox
        moves.getChildren().add(header);
        TextArea possibleMoves = new TextArea(builder.toString());
        possibleMoves.setEditable(false);
        moves.getChildren().add(possibleMoves);
        
        // Set the vertical box max width
        moves.setMaxWidth(header.getLayoutBounds().getWidth());
        moves.setMaxHeight(possibleMoves.getMaxHeight());

        // Set the size properly using an HBox
        HBox formatBox = new HBox(moves);
        formatBox.setPadding(new Insets(0, 15, 15, 15));

        // Set the color of the two boxes
        moves.setStyle(color);
        formatBox.setStyle(color);
        formatBox.setMouseTransparent(true);
        formatBox.setFocusTraversable(false);

        // Return the text area
        return formatBox;
    }

    /**
     * This helper method declares the losing text. 
     *
     * @return An HBox for the lost info
     */
    private void setLoseArea() {
        // Create a StringBuilder to store the rules
        StringBuilder builder = new StringBuilder();
        builder.append("You Lose...\n");
        builder.append("Press R to Reset, Q to Quit");

        // Grab and set the info
        ((TextArea) ((VBox) this.getInfo().getChildren().get(0))
                .getChildren().get(1)).setText(builder.toString());;
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
     *         of zero-values. Out of bounds if doesn't exists
     */
    private int[] randomIndex() {
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
            return new int[] { -1, -1 };
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
    private class Logic {
        // An object to store the game board
        private int[][] gameBoard;

        // An object to store the resulting board
        private int[][] resultBoard;

        // An object to store a row board
        private int[][] rowBoard = { { 0 } };

        // An object to store the resulting row
        private int[] resultRow;

        // An object to store a resulting shift
        private int[] resultShift;

        // An object to store a copy of the board
        private int[][] copyBoard;

        // An object to store the diagonals
        private int[][] diagonals;

        // An object to store a deconstructed board
        private int[][] deconstructed;

        // An object to store the dimensions of the board
        private int[] dimensions;

        // An object to store the check
        private int[][] check;

        /**
         * Sets the game board in the local pool.
         *
         * @param gameBoard
         */
        private void setGameBoard(int[][] gameBoard) {
            this.gameBoard = gameBoard;
        }

        /**
         * Sets the result board in the local pool.
         *
         * @param resultBoard A resulting board from operations
         */
        private void setResultBoard(int[][] resultBoard) {
            this.resultBoard = resultBoard;
        }

        /**
         * Sets the row board in the local pool.
         *
         * @param rowBoard A 1D row to place in a 2D array
         */
        private void setRowBoard(int[] rowBoard) {
            this.rowBoard[0] = rowBoard;
        }

        /**
         * Sets the result row in the local pool.
         *
         * @param resultRow A resulting row from operations
         */
        private void setResultRow(int[] resultRow) {
            this.resultRow = resultRow;
        }

        /**
         * Sets the result shift in the local pool.
         *
         * @param resultShift A resulting row from shifting operations
         */
        private void setResultShift(int[] resultShift) {
            this.resultShift = resultShift;
        }

        /**
         * Sets the copy board in the local pool.
         *
         * @param copyBoard A copy of the game board
         */
        private void setCopyBoard(int[][] copyBoard) {
            this.copyBoard = copyBoard;
        }

        /**
         * Sets the diagonals in the local pool.
         *
         * @param diagonals The diagonals of a board
         */
        private void setDiagonals(int[][] diagonals) {
            this.diagonals = diagonals;
        }

        /**
         * Sets the deconstructed board in the local pool.
         *
         * @param deconstructed A deconstructed board that isn't necessarily the diagonals
         */
        private void setDeconstructed(int[][] deconstructed) {
            this.deconstructed = deconstructed;
        }

        /**
         * Sets the dimensions in the local pool.
         *
         * @param dimensions The dimensions to be used for reconstruction
         */
        private void setDimensions(int[] dimensions) {
            this.dimensions = dimensions;
        }

        /**
         * Sets the checking position in the pool.
         *
         * @param check The check variable
         */
        private void setCheck(int[][] check) {
            this.check = check;
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
            this.setResultRow(new int[row.length]);

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
         * Slides the elements in a row to the right. Equivalent values are combined,
         * and
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
            this.setCopyBoard(SlideGame.this.copyBoard(SlideGame.this.getGameBoard()));

            /*
             * Set and retrieve the board after flipping vertically. mergeLeft is used once
             * board is flipped, as they are effectively the same.
             */
            this.setRowBoard(row);
            SlideGame.this.setGameBoard(rowBoard);
            SlideGame.this.setGameBoard(SlideGame.this.flipVertical());
            this.setGameBoard(SlideGame.this.getGameBoard());

            // Use the mergeLeft method to shift the array
            this.setResultShift(this.mergeLeft(gameBoard[0]));

            // Flip the board back
            this.setRowBoard(resultShift);
            SlideGame.this.setGameBoard(rowBoard);
            this.setGameBoard(SlideGame.this.flipVertical());

            // Set the game board to its original state
            SlideGame.this.setGameBoard(copyBoard);

            // Return the shifted array
            return gameBoard[0];
        }

        /**
         * Slides the elements in a full gameBoard up. Equivalent values are combined,
         * and
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
            SlideGame.this.setGameBoard(board);
            SlideGame.this.setGameBoard(SlideGame.this.transpose());
            this.setGameBoard(SlideGame.this.getGameBoard());

            /*
             * Create a resulting board with different dimensions to account for non-square
             * boards
             */
            this.setResultBoard(new int[gameBoard.length][gameBoard[0].length]);

            // Loop through the gameBoard and merge each row
            for (int i = 0; i < gameBoard.length; i++) {
                resultBoard[i] = this.mergeLeft(gameBoard[i]);
            }

            /*
             * Transpose the result board to return to the original orientation. Do this
             * using the SlideGame.this
             * object, which is effectively a wrapper for the gameBoard.
             */
            SlideGame.this.setGameBoard(resultBoard);
            SlideGame.this.setGameBoard(SlideGame.this.transpose());

            // Return the new board
            return SlideGame.this.getGameBoard();
        }

        /**
         * Slides the elements in a full gameBoard down. Equivalent values are combined,
         * and
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
            SlideGame.this.setGameBoard(board);
            SlideGame.this.setGameBoard(SlideGame.this.transpose());
            SlideGame.this.setGameBoard(SlideGame.this.flipVertical());
            this.setGameBoard(SlideGame.this.getGameBoard());

            /*
             * Create a resulting board with different dimensions to account for non-square
             * boards
             */
            this.setResultBoard(new int[gameBoard.length][gameBoard[0].length]);

            // Loop through the gameBoard and merge each row
            for (int i = 0; i < gameBoard.length; i++) {
                resultBoard[i] = this.mergeLeft(gameBoard[i]);
            }

            // Perform the inverse transformation to get the original orientation.
            SlideGame.this.setGameBoard(resultBoard);
            SlideGame.this.setGameBoard(SlideGame.this.flipVertical());
            SlideGame.this.setGameBoard(SlideGame.this.transpose());

            // Return the new board
            return SlideGame.this.getGameBoard();
        }

        /**
         * A helper method that divides a board into its diagonals. Diagonals are
         * formatted by drawing a line from bottom left to top right.
         * 
         * @param board The board to divide
         * @return The extracted diagonals
         */
        private int[][] extractDiagonals(int[][] board) {
            // Return null if the row is null or empty
            if (board == null || board.length == 0) {
                return null;
            }

            // Return the same array if the row has one value
            if (board.length == 1 && board[0].length == 1) {
                return board;
            }

            // Extract the row and column values from the input board
            int row = board.length;
            int col = board[0].length;

            // Create a diagonal array based on diag formula
            int numDiag = (row + col - 1);
            this.setDiagonals(new int[numDiag][]);

            // Extract main diagonals
            for (int i = 0; i < row; i++) {
                // Calculate the length of the current diagonal
                int diagonalLength = Math.min(i + 1, col);
                diagonals[i] = new int[diagonalLength];

                // Fill the diagonal array
                for (int j = 0; j < diagonalLength; j++) {
                    // Offset the indices in the board per the diag formula
                    diagonals[i][j] = board[i - j][j];
                }
            }

            // Extract secondary diagonals, this is the 'reverse' of main diagonals
            for (int i = 1; i < col; i++) {
                // Calculate the length of the current diagonal
                int diagonalLength = Math.min(row, col - i);
                diagonals[row + i - 1] = new int[diagonalLength];

                // Fill the diagonal array
                for (int j = 0; j < diagonalLength; j++) {
                    // Similarly offset the indices in the board per the diag formula
                    diagonals[row + i - 1][j] = board[row - j - 1][i + j];
                }
            }

            // Return the extracted diagonals
            return diagonals;
        }

        /**
         * A helper method that reconstructs a board from its diagonals.
         * 
         * @param dimensions {row, col} array to create the empty board
         * @param diagonals  The diagonals to reconstruct
         * @return The reconstructed board
         */
        private int[][] reconstructDiagonals(int[] dimensions, int[][] diagonals) {
            // Check for null dimensions or 1D
            if (dimensions == null || dimensions.length == 1) {
                return null;
            }

            // Return null if the diagonals are null or empty
            if (diagonals == null || diagonals.length == 0) {
                return null;
            }

            // Return the same array if the row has one value
            if (diagonals.length == 1 && diagonals[0].length == 1) {
                return diagonals;
            }

            // Return null if dimensions are 0
            if (dimensions[0] == 0 || dimensions[1] == 0) {
                return null;
            }

            // Extract the row and column values from the input board
            int requestedRow = dimensions[0];
            int requestedCol = dimensions[1];

            // Create the resulting board with requested dimensions
            this.setResultBoard(new int[requestedRow][requestedCol]);

            // Loop through the diagonals
            for (int i = 0; i < diagonals.length; i++) {
                // Calculate the starting row and column index in the resultBoard
                int startRow = Math.min(requestedRow - 1, i);
                int startCol = Math.max(0, i - requestedRow + 1);

                // Loop through the elements of the diagonal array
                for (int j = 0; j < diagonals[i].length; j++) {
                    // Calculate the row and column index in the resultBoard
                    int row = startRow - j;
                    int col = startCol + j;

                    // Check if the calculated indices are within bounds of the resultBoard
                    if (row >= 0 && col < requestedCol) {
                        // Fill the value from the diagonal array into the resultBoard
                        resultBoard[row][col] = diagonals[i][j];
                    }
                }
            }

            return resultBoard;
        }

        /**
         * Slides the elements on an up-right diagonal. The board is then
         * rewritten such that the diagonals become how they were before being
         * extracted, except all elements are shifted towards the top right corner.
         * 
         * @param extracted The extracted diagonals
         * @return A new board with the values slid to 'up-right'
         */
        private int[][] mergeTopRight(int[][] board) {
            // Return null if the row is null or empty
            if (board == null || board.length == 0) {
                return null;
            }

            // Return the same array if the row has one value
            if (board.length == 1 && board[0].length == 1) {
                return board;
            }

            // Deconstruct the board first and preserve the dimensions of the board
            this.setDeconstructed(this.extractDiagonals(board));
            this.setDimensions(new int[] { board.length, board[0].length });

            // Merge the diagonals right in each row
            for (int i = 0; i < deconstructed.length; i++) {
                deconstructed[i] = this.mergeRight(deconstructed[i]);
            }

            // Reconstruct the board
            return this.reconstructDiagonals(dimensions, deconstructed);
        }

        /**
         * Slides the elements on a down-right diagonal. The board is then
         * rewritten such that the diagonals become how they were before being
         * extracted, except all elements are shifted towards the bottom right corner.
         * 
         * @param board The extracted diagonals
         * @return A new board with the values slid 'down-right'
         */
        private int[][] mergeBottomRight(int[][] board) {
            // Return null if the row is null or empty
            if (board == null || board.length == 0) {
                return null;
            }

            // Return the same array if the row has one value
            if (board.length == 1 && board[0].length == 1) {
                return board;
            }

            /*
             * Set and retrieve the board after flipping horizontally. mergeLeft is used
             * once board is diagonalized, as this accomplishes the shift
             */
            SlideGame.this.setGameBoard(board);
            SlideGame.this.setGameBoard(SlideGame.this.flipHorizontal());
            this.setGameBoard(SlideGame.this.getGameBoard());

            // Deconstruct the board first and preserve the dimensions of the board
            this.setDeconstructed(this.extractDiagonals(gameBoard));
            this.setDimensions(new int[] { gameBoard.length, gameBoard[0].length });

            // Merge the diagonals right in each row
            for (int i = 0; i < deconstructed.length; i++) {
                deconstructed[i] = this.mergeRight(deconstructed[i]);
            }

            /*
             * Flip the board back to its original orientation.
             * Do this to the SlideGame.this object after reconstructing the board.
             */
            SlideGame.this.setGameBoard(this.reconstructDiagonals(dimensions, deconstructed));
            SlideGame.this.setGameBoard(SlideGame.this.flipHorizontal());

            // Reconstruct the board
            return SlideGame.this.getGameBoard();
        }

        /**
         * Slides the elements on an up-left diagonal. The board is then
         * rewritten such that the diagonals become how they were before being
         * extracted, except all elements are shifted towards the top left corner.
         * 
         * @param board The game board to shift
         * @return A new board with the values slid 'up-left'
         */
        private int[][] mergeTopLeft(int[][] board) {
            // Return null if the row is null or empty
            if (board == null || board.length == 0) {
                return null;
            }

            // Return the same array if the row has one value
            if (board.length == 1 && board[0].length == 1) {
                return board;
            }

            /*
             * Set and retrieve the board after flipping horizontally. mergeLeft is used
             * once board is diagonalized, as this accomplishes the shift
             */
            SlideGame.this.setGameBoard(board);
            SlideGame.this.setGameBoard(SlideGame.this.flipHorizontal());
            this.setGameBoard(SlideGame.this.getGameBoard());

            // Deconstruct the board first and preserve the dimensions of the board
            this.setDeconstructed(this.extractDiagonals(gameBoard));
            this.setDimensions(new int[] { gameBoard.length, gameBoard[0].length });

            // Merge the diagonals right in each row
            for (int i = 0; i < deconstructed.length; i++) {
                deconstructed[i] = this.mergeLeft(deconstructed[i]);
            }

            /*
             * Flip the board back to its original orientation.
             * Do this to the SlideGame.this object after reconstructing the board.
             */
            SlideGame.this.setGameBoard(this.reconstructDiagonals(dimensions, deconstructed));
            SlideGame.this.setGameBoard(SlideGame.this.flipHorizontal());

            // Reconstruct the board
            return SlideGame.this.getGameBoard();
        }

        /**
         * Slides the elements on a down-left diagonal. The board is then
         * rewritten such that the diagonals become how they were before being
         * extracted, except all elements are shifted towards the bottom left corner.
         * 
         * @param board The extracted diagonals
         * @return A new board with the values slid 'down-left'
         */
        private int[][] mergeBottomLeft(int[][] board) {
            // Return null if the row is null or empty
            if (board == null || board.length == 0) {
                return null;
            }

            // Return the same array if the row has one value
            if (board.length == 1 && board[0].length == 1) {
                return board;
            }

            // Deconstruct the board and preserve the dimensions of the board
            this.setDeconstructed(this.extractDiagonals(board));
            this.setDimensions(new int[] { board.length, board[0].length });

            // Merge the diagonals left in each row
            for (int i = 0; i < deconstructed.length; i++) {
                deconstructed[i] = this.mergeLeft(deconstructed[i]);
            }

            // Reconstruct the board
            return this.reconstructDiagonals(dimensions, deconstructed);
        }

        /**
         * Sweeps around a position in the board and determines if there are any
         * non-zero matches to the board
         * 
         * @param position An array representing the coordinates to check.
         * @param board    The board to examine
         * @return true if the position is zero or is non-zero with equivalent value in
         *         adjacent position. False otherwise
         */
        private boolean adjacentSweep(int[] position, int[][] board) {
            int positionValue = board[position[0]][position[1]];

            // Return true if zero is in position
            if (positionValue == 0) {
                return true;
            }

            // Create an array to store indices in a square around position
            this.setCheck(new int[][] {
                { position[0] - 1, position[1] - 1},
                { position[0] - 1, position[1] },
                { position[0] - 1, position[1] + 1 },
                { position[0], position[1] - 1},
                { position[0], position[1] + 1 },
                { position[0] + 1, position[1] - 1},
                { position[0] + 1, position[1] },
                { position[0] + 1, position[1] + 1 },
            });

            for (int i = 0; i < 8; i++) {
                try {
                    if (positionValue == board[this.check[i][0]][this.check[i][1]]) {
                        return true;
                    }
                } catch (Exception e) {}
            }

            return false;
        }

        /**
         * Checks to see if the user lost or not.
         * 
         * @return true if the user can continue playing, false otherwise
         */
        private boolean play() {
            // Check each position with adjacent method
            for (int i = 0; i < SlideGame.this.getGameBoard().length; i++) {
                for (int j = 0; j < SlideGame.this.getGameBoard()[0].length; j++) {
                    if (this.adjacentSweep(new int[] { i, j }, SlideGame.this.getGameBoard())) {
                        return true;
                    }
                }
            }

            // If none of the positions triggered, then the user lost
            return false;
        }
    }

    /**
     * Slides the board to the left. All zeros are moved to the left and all
     * non-zeroes are added together as possible. Follows standard sliding
     * algorithm from the actual 2048 game.
     */
    private void slideLeft() {
        // Create a new empty board of the same length
        this.setResultBoard(this.emptyBoard(new int[] { this.getBoardDim()[0], this.getBoardDim()[1] }));

        // Set each row of the new board to the shifted left row of the original
        for (int i = 0; i < this.getGameBoard().length; i++) {
            this.getResultBoard()[i] = logic.mergeLeft(this.getGameBoard()[i]);
        }

        // Set the new board
        this.setGameBoard(this.getResultBoard());
    }

    /**
     * Slides the board to the right. All zeros are moved to the right and all
     * non-zeroes are added together as possible. Follows standard sliding
     * algorithm from the actual 2048 game.
     */
    private void slideRight() {
        // Create a new empty board of the same length
        this.setResultBoard(this.emptyBoard(new int[] { this.getBoardDim()[0], this.getBoardDim()[1] }));

        // Set each row of the new board to the shifted left row of the original
        for (int i = 0; i < this.getGameBoard().length; i++) {
            this.getResultBoard()[i] = logic.mergeRight(this.getGameBoard()[i]);
        }

        // Set the new board
        this.setGameBoard(this.getResultBoard());
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
     * Slides the board to the upper left corner. All zeros are moved to the upper
     * left
     * and all non-zeroes are added together as possible. Follows standard sliding
     * algorithm from the actual 2048 game, but diagonally.
     */
    private void slideUpLeft() {
        // Invoke Logic to slide, it handles everything, just needing arguments
        this.setGameBoard(logic.mergeTopLeft(this.getGameBoard()));
    }

    /**
     * Slides the board to the upper right corner. All zeros are moved to the upper
     * right
     * and all non-zeroes are added together as possible. Follows standard sliding
     * algorithm from the actual 2048 game, but diagonally.
     */
    private void slideUpRight() {
        // Invoke Logic to slide, it handles everything, just needing arguments
        this.setGameBoard(logic.mergeTopRight(this.getGameBoard()));
    }

    /**
     * Slides the board to the lower left corner. All zeros are moved to the lower
     * left
     * and all non-zeroes are added together as possible. Follows standard sliding
     * algorithm from the actual 2048 game, but diagonally.
     */
    private void slideDownLeft() {
        // Invoke Logic to slide, it handles everything, just needing arguments
        this.setGameBoard(logic.mergeBottomLeft(this.getGameBoard()));
    }

    /**
     * Slides the board to the lower right corner. All zeros are moved to the lower
     * right
     * and all non-zeroes are added together as possible. Follows standard sliding
     * algorithm from the actual 2048 game, but diagonally.
     */
    private void slideDownRight() {
        // Invoke Logic to slide, it handles everything, just needing arguments
        this.setGameBoard(logic.mergeBottomRight(this.getGameBoard()));
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