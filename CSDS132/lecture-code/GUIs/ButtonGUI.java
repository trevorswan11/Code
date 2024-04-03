// You should know these by heart after the fourth project
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ButtonGUI extends Application {
    /**
     * Overrides the start method.
     */
    @Override
    public void start(Stage primaryStage) {
        BorderPane layout = new BorderPane();
        // Need some gadgets for the scene
        Button b1 = new Button("TOUCH ME");
        Button b2 = new Button("FEEL ME");
        Button b3 = new Button("KNEED ME");
        Button b4 = new Button("womp womp");
        
        // Set location of layout
        layout.setTop(b4);
        layout.setBottom(b1);
        layout.setLeft(b2);
        layout.setRight(b3);

        // Create the scene with layout gadget
        Scene scene = new Scene(layout);

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
}
