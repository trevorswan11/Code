package prelabcode11;

// imports for the GUI
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextArea;

/**
 * Displays some lines from most important piece of literature.
 * 
 * @author Trevor Swan
 */
public class LoraxGUI extends Application {
    // A field to store the line from the book
    private static String text;

    // This method sets the text to that required by the assignment
    public static void declareText() {
        // Create a StringBuilder for elegant formatting
        StringBuilder b = new StringBuilder("I yelled at the Lorax, \"Now listen here, Dad!");
        b.append("\nAll you do is yap-yap and say, 'Bad! Bad! Bad! Bad!'\n...");
        b.append("\nAnd, for your information, you Lorax, I'm figuring");
        b.append("\non biggering");
        b.append("\n\tand Biggering");
        b.append("\n\t\tand BIGGERING");
        b.append("\n\t\t\tand BIGGERING,");
        b.append("\nturning MORE Truffula Trees into Thneeds");
        b.append("\nwhich everyone, Everyone, EVERYONE needs!\"");

        // Set the field to the StringBuilder text to be used in the GUI
        text = b.toString();
    }

    // This method returns the text to the user
    public static String getText() {
        return text;
    }

    /**
     * Creates a GUI with some text.
     * 
     * @param primaryStage The main window of the GUI
     */
    @Override
    public void start(Stage primaryStage) {
        // Create a TextArea
        TextArea textArea = new TextArea();
        textArea.setText(getText());

        // Create the actual GUI
        BorderPane root = new BorderPane();
        root.setCenter(textArea);
        Scene scene = new Scene(root);

        // Show the stage
        primaryStage.setTitle("The Lorax");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // This is the main method used to launch the application
    public static void main(String[] args) {
        declareText();
        Application.launch(args);
    }
}
