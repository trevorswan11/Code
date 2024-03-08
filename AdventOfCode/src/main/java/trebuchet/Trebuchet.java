package trebuchet;

/* Imports are for File Readers */
import java.io.*;

public class Trebuchet {
	public static String puzzle(String path) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(path));

		// Create a String Builder to Create the puzzle locally
		StringBuilder builder = new StringBuilder();

		// read the file until a -1 is returned
		int currentCharacter;
		while ((currentCharacter = reader.read()) != -1) {
			builder.append((char) currentCharacter);
			builder.append(reader.readLine());
			builder.append("\n");
		}

		// Close the reader to prevent a resource leak
		reader.close();
	
		// Set the puzzle equal to the String Builder
		return builder.toString();
	}

	public static void main(String[] args) {

	}
}
