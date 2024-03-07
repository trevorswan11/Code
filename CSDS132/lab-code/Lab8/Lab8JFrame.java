import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.io.*;
/**
* A window with a text area that works as a simple text editor.
* @author <em>Your name goes here</em>
* @author <em>Your partner's name goes here</em>
*/
public class Lab8JFrame extends JFrame {
    /** Text area, in the center. */
    private JTextArea textArea;
    /**
    * Create a window with a 40x80-character text area in the center.
    */
    public Lab8JFrame() {
        // Create the text area.
        this.textArea = new JTextArea("An Editor Window", 40, 80); // 40 lines high, 80 chars wide.
        // Add the text area to this window.
        this.getContentPane().add(this.textArea, "Center");
        // Set the window size to automatically fit the center text area
        this.pack();
    }
    
    /**
    * Returns the text area of the window
    * @return the text area of the window
    */
    public JTextArea getTextArea() {
        return textArea;
    }
}
