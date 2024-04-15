package guiPractice;

// You should know these by heart after the fourth project
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

@SuppressWarnings("unused")
public class ButtonGUI extends Application {
    /**
     * Overrides the start method.
     */
    @Override
    public void start(Stage primaryStage) {
        GridPane gridOne = new GridPane();
        Button[][] buttons = new Button[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Button b = new Button(Integer.toString((i + 1) * (j + 1)));
                gridOne.add(b, j, i);
                buttons[i][j] = b;
            }
        }

        HandlerNested h1 = new HandlerNested();
        HandlerStatic h2 = new HandlerStatic();

        buttons[1][0].setOnAction(h1);
        buttons[2][0].setOnAction(h2);

        // Yet another way to set button behavior
        buttons[1][buttons.length - 1].setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                System.out.println("You clicked me: anonymous");
            }

        });

        GridPane gridTwo = new GridPane();
        Button[][] buttons2 = new Button[2][2];

        int row;
        int col;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                final int[] position = {i, j};
                Button b = new Button(Integer.toString((i + 1) * (j + 1)));
                gridTwo.add(b, j, i);
                buttons2[i][j] = b;
                buttons2[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        buttons2[position[0]][position[1]].setText("OW");
                    }
                });
            }
        }

        BorderPane pane = new BorderPane();
        pane.setLeft(gridOne);
        pane.setRight(gridTwo);

        // Create the scene with layout gadget
        Scene scene = new Scene(pane);

        // Set the scene and show it
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Launches the application
     */
    public static void main(String[] args) {
        // You can launch it from any method, but its from main here for standalone
        Application.launch(args);
    }

    // non-static nested class to show button behavior
    public class  HandlerNested implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            System.out.println("You clicked me: non-static");
        }
    }

    // Static nested class to show button behavior in comparison to anon and non-static
    public static class HandlerStatic implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            System.out.println("You clicked me: static");
        }
    }
}
