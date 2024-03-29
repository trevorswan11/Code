package dayOneChallenge; 

import org.junit.*;
import java.io.*;

public class dayOneTest {
	private String puzzle;

	// Helper method to get puzzle file
	private String getPuzzle() throws IOException {
		return dayOnePuzzle.puzzle("C:/Users/Trevor/OneDrive/Documents/CWRU/Code/AoC/Puzzle Text Files/dayOnePuzzle.txt");
	}

	// Run the test for the first part
	@Test
	public void partOne() throws IOException {
		puzzle = this.getPuzzle();
		int solution = dayOnePuzzle.sumPartOne(puzzle);
		System.out.println(solution);
	}

	// Run the test for the second part
	@Test
	public void partTwo() throws IOException {
		puzzle = this.getPuzzle();
		int solution = dayOnePuzzle.sumPartTwo(puzzle);
		System.out.println(solution);
	}
}
