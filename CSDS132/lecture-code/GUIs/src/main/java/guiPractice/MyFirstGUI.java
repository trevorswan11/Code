package guiPractice;

// You should know these by heart after the fourth project
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * A GUI example from lecture.
 */
public class MyFirstGUI extends Application {
    /**
     * Starts the application.
     */
    @Override
    public void start(Stage primaryStage){
        Button button = new Button("TOUCH ME");
        Scene scene = new Scene(button);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * Launches the application.
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
}
