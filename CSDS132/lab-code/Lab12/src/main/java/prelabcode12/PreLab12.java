package prelabcode12;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;

/**
 * A JavaFX class that creates a GUI with two buttons that print 
 * a required statement to the screen when pressed.
 * 
 * @author Trevor Swan
 * @version CSDS132 - Spring 2024  
 */
public class PreLab12 extends Application {
    
    /**
     * Override start method to create the GUI
     * @param primaryStage the main window for JavaFX
     */
    @Override
    public void start(Stage primaryStage) {
        // Initialize the two buttons
        Button buttonOne = new Button("Button 1");
        Button buttonTwo = new Button("Button 2");

        // Event handler for buttonOne
        buttonOne.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("You clicked Button 1");
            }
        });

        // Event handler for buttonTwo
        buttonTwo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("You clicked Button 2");
            }
        });

        // Create border pane to organize the buttons
        BorderPane pane = new BorderPane();
        pane.setLeft(buttonOne);
        pane.setRight(buttonTwo);

        // Create a scene to contain the pane
        Scene scene = new Scene(pane);

        // Set a title to the stage and set/show it
        primaryStage.setTitle("Pre-Lab for Lab 12");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Launches applications with command line arguments
     * @param args Command line input
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
}
