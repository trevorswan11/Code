package JavaFXChallenge;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class MyWindow extends Application {
    // Objects for the button
    private Button statusButton;
    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;
    private Button buttonFour;

    // Starts the application
    public void start(Stage primaryStage) {
        // Create the three buttons
        statusButton = new Button();
        buttonOne = new Button("im #1");
        buttonTwo = new Button("click count: 0");
        buttonThree = new Button("im #3");
        buttonFour = new Button("reset");

        // Establish behavior for buttons one and three
        ClickHandler handler = new ClickHandler();
        buttonOne.setOnAction(handler);
        buttonThree.setOnAction(handler);
        buttonFour.setOnAction(handler);
        buttonTwo.setOnAction(new EventHandler<ActionEvent>() {
            // A field to store the amount of presses
            private int numPressed = 0;

            public void handle(ActionEvent e) {
                buttonTwo.setText("click count: " + (numPressed += 1));
            }
        });

        // Establish status behavior
        statusButton.setStyle("-fx-background-color: green");
        statusButton.setOnAction(handler);
        statusButton.setOnAction(e -> statusButton.setStyle("-fx-background-color: red"));

        // Create the scene and its actual components
        FlowPane pane = new FlowPane(statusButton, buttonOne, buttonTwo, buttonThree, buttonFour);
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class ClickHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            // Determine the button pressed and proceed based off of object
            Button call = (Button) e.getSource();
            if (call == buttonOne) {
                System.out.println("You clicked the left button!");
            } else if (call == buttonThree) {
                System.out.println("You clicked the right button!");
            } else if (call == buttonFour) {
                buttonTwo.setText("click count: 0");
                statusButton.setStyle("-fx-background-color: green");
            }
        }
    }

    // Launches application, args not needed
    public static void main(String[] args) {
        Application.launch(args);
    }
}
