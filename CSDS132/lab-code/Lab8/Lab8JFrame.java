import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.io.*;

/**
* A window with a text area that works as a simple text editor.
* @author Trevor Swan 
* @author Simon Eskin
* @author Curtis Li
* */

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
	
	/** 
    * Displays the contents of a file to the center text area.
    * @param fileName  the file to be displayed
    * @throws IOException if there is a problem reading from input file.
    */
	public void fileDisplay(String fileName) throws IOException {
		getTextArea().setText("");
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		// initialize a character and loop through the file until -1 is returned
		int currentChar;
		while ((currentChar = reader.read()) != -1) {
			String firstChar = Character.toString((char)currentChar);
			getTextArea().append(firstChar);
			getTextArea().append(reader.readLine());
			getTextArea().append("\n");
		}

		// pack the JFrame and close reader
		this.pack();
		reader.close();
	}

	/** Does not work as intended but should add text from JFrame to specified file
	 *
	 * @param fileDestination file path
	 * @return void
	 */
	public void saveFile(String fileDestination) throws IOException {
		PrintStream p = new PrintStream(new FileOutputStream(fileDestination));	
		p.print(this.getTextArea());
	}

	public static void main(String[] args) throws IOException {
		Lab8JFrame frame = new Lab8JFrame();
		frame.setVisible(true);
		frame.fileDisplay("C:/Users/Trevor/OneDrive/Documents/CWRU/Code/hello.txt");
		frame.saveFile("C:/Users/Trevor/OneDrive/Documents/CWRU/Code/hello.txt");
	}
}
