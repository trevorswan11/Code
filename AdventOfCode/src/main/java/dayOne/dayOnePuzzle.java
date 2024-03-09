package dayOne;

/* Imports are for File Readers */
import java.io.*;

public class dayOnePuzzle {
	/**
	 * This method extracts the puzzle from and inputted path.
	 * 
	 * @param path The system path of the AoC Puzzle
	 * @return A string containing the formatted puzzle
	 * @throws IOException Something went wrong when reading file
	 */
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

	/**
	 * The solution to the first part of AdventOfCode day 1
	 * 
	 * @param puzzle The formatted String for the Puzzle
	 * @return The sum of the digits as requested by the challenge
	 * @throws IOException Something went wrong when reading the String
	 */
	public static int sumPartOne(String puzzle) throws IOException {
		BufferedReader reader = new BufferedReader(new StringReader(puzzle));
		int currentCharacter;
		String line;
		String combination;
		StringBuilder digits = new StringBuilder();
		int sum = 0;

		// Loop through the string until exhuasted
		while ((currentCharacter = reader.read()) != -1) {
			// Assuming the loop ran, append the test character and the line
			digits.append((char) currentCharacter);
			digits.append(reader.readLine());

			// Save the line to its own varibale and reset the digits builder
			line = digits.toString();
			digits.setLength(0);

			// Loop through the line String and extract all digits
			for (int i = 0; i < line.length(); i++) {
				if (Character.isDigit(line.charAt(i))) {
					digits.append(line.charAt(i));
				}
			}
			String digitsLine = digits.toString();
			digits.setLength(0);

			// Now that digits has all the digits in the current line, create a number
			if (digitsLine.length() == 1) {
				digits.append(digitsLine.charAt(0));
				digits.append(digitsLine.charAt(0));
				combination = digits.toString();
			} else {
				digits.append(digitsLine.charAt(0));
				digits.append(digitsLine.charAt(digitsLine.length() - 1));
				combination = digits.toString();
			}

			// Create an int value based off the current combo
			try {
				int value = Integer.parseInt(combination);
				sum = sum + value;
			} catch (Exception e) {
				System.out.println("womp womp");
			}
			digits.setLength(0);
		}
		// Close readers
		reader.close();

		// return the sum
		return sum;
	}

	/**
	 * This method searches for the first real number, spelled or numerical, in a
	 * given string. It then
	 * looks through the string and looks for the last real number in the string. It
	 * returns theses values as a single int.
	 * 
	 * @param s A single line string to examine for the above criteria
	 * @return An int that cointains the combination for the puzzle line.
	 */
	public static int realInts(String s) {
		return 0;
	}

	public static void main(String[] args) throws IOException {
		// Import the Test file for the puzzle
		String puzzle = dayOnePuzzle
				.puzzle("C:/Users/Trevor/OneDrive/Documents/CWRU/Code/AdventOfCode/Puzzle Text Files/dayOnePuzzle.txt");
		// Print the solution to the first part of the puzzle
		System.out.println("Part One Solution:");
		int solution = dayOnePuzzle.sumPartOne(puzzle);
		System.out.println(solution);

		// Print the solution to the second part of the puzzle
		System.out.println("Part Two Solution:");
	}
}
