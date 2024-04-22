package guiPractice;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

/** Create a GUI with lots of buttons that do cool things */
public class CoolButtonGUI extends Application {

    // the amount to rotate each button on a "spin"
    private int rotation = 20;

    // Yhe 5 buttons of the GUI. They need better names than buttonX.
    // Maybe "rotateButton", "spinButton", "quitButton", etc.
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;

    /**
     * Creates the GUI.
     * 
     * @param primaryStage the main window
     */
    public void start(Stage primaryStage) {
        BorderPane layout = new BorderPane();
        button1 = new Button("click me");
        button2 = new Button("reset");
        button3 = new Button("rotate");
        button4 = new Button("button 4");
        button5 = new Button("spin");

        layout.setTop(button1);
        layout.setBottom(button2);
        layout.setLeft(button3);
        layout.setRight(button4);
        layout.setCenter(button5);

        // button 1 has a boring event handler
        button1.setOnAction(new EventHandler<ActionEvent>() {
            private int time = 0;
            public void handle(ActionEvent e) {
                ++time;
                System.out.println("I was clicked: " + time + " times!");
            }
        });

        // this button will rotate itself when clicked.
        // first is the "normal" anonymous class form and then is a "lambda syntax" form
        /*
         * button3.setOnAction(new EventHandler<ActionEvent>() {
         * public void handle(ActionEvent e) {
         * button3.setRotate(button3.getRotate() + 20);
         * }
         * });
         */
        button3.setOnAction(e -> button3.setRotate(button3.getRotate() + 20));
        
        /*
         * If you try to reference this, an error occurs because anon class lives in
         * heap but variable is in the stack.
         */        
        int rotateDelta = 25;
        button4.setOnAction(e -> {
            // need to have 'field' in lambda expression, cannot be private.
            int fourRotation = 0;
            fourRotation+=rotateDelta;
            button4.setRotate(button4.getRotate() + fourRotation);
        });

        // this button will rotate all the buttons of the gui and reduce the rotation
        // amount with each click
        // the first code is the "normal" anonymous class and the second is the "lambda
        // syntax" form
        /*
         * button5.setOnAction(new EventHandler<ActionEvent>() {
         * int rotation = 20;
         * public void handle(ActionEvent e) {
         * button1.setRotate(button1.getRotate() + rotation);
         * button2.setRotate(button2.getRotate() + rotation);
         * button3.setRotate(button3.getRotate() + rotation);
         * button4.setRotate(button4.getRotate() + rotation);
         * button5.setRotate(button5.getRotate() + rotation);
         * rotation -= 2;
         * }
         * });
         */

        button5.setOnAction(e -> {
            button1.setRotate(button1.getRotate() + rotation);
            button2.setRotate(button2.getRotate() + rotation);
            button3.setRotate(button3.getRotate() + rotation);
            button4.setRotate(button4.getRotate() + rotation);
            button5.setRotate(button5.getRotate() + rotation);
            rotation -= 2;
        });

        // the next button resets all the existing buttons
        // the first code is the "normal" anonymous class
        // the next is the "method reference" syntax
        /*
         * button2.setOnAction(new EventHandler<ActionEvent> () {
         * public void handle(ActionEvent e){
         * CoolButtonGUI.this.reset(e);
         * }
         * });
         */
        button2.setOnAction(this::reset);

        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Reset all the buttons to their default rotation and resets the "spin"
     * rotation.
     * 
     * @param e this input is ignored, but it is included so we can use "method
     *          reference" syntax on an event handler that calls this method.
     */
    public void reset(ActionEvent e) {
        rotation = 20;
        button1.setRotate(0);
        button2.setRotate(0);
        button3.setRotate(0);
        button4.setRotate(0);
        button5.setRotate(0);
    };

    /**
     * Launches the GUI
     * 
     * @param args command line arguments, currently ignored
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
}
