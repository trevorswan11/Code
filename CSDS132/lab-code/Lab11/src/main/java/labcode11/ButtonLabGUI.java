package labcode11;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;

/**
 * A window with one button
 * 
 * @author Trevor Swan
 * @author Simon Eskin, Curtis Li
 */
public class ButtonLabGUI extends Application {
    /** A clickable button */
    private Button button;
    private int count;
    private Stage primaryStage;
    private TextArea textArea;

    /**
     * Overrides the start method of Application to create the GUI with one button
     * in the center of the main window.
     * 
     * @param primaryStage the JavaFX main window
     */
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        button = new Button("Click Me!");
        button.setOnAction(new ButtonAction());

        // Create a new text area and set it to the field
        TextArea textArea = new TextArea();
        this.textArea = textArea;

        BorderPane pane = new BorderPane(); // create a 5 region layout for the window
        pane.setTop(button); // add the button to the middle
        pane.setCenter(textArea);

        Scene scene = new Scene(pane); // Create a "scene" that contains this border area

        primaryStage.setTitle("Button Lab GUI");
        primaryStage.setScene(scene); // Add the "scene" to the main window
        primaryStage.show(); // Display the window
    }

    private class ButtonAction implements EventHandler<ActionEvent> {
        /**
         * React to a button click: change the text on the button
         * 
         * @param e information about the button click event that occurred
         */
        public void handle(ActionEvent e) {
            Button b = (Button) e.getSource(); // this points to what b1 points to!
            count++;
            b.setText("Click count: " + Integer.toString(count));
            primaryStage.sizeToScene();
            textArea.appendText("Click count: " + count + "\n");
        }
    }

    /**
     * The method to launch the program.
     * 
     * @param args The command line arguments. The arguments are passed on to the
     *             JavaFX application.
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

}