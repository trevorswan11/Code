package dayFourChallenge;

import java.io.*;
import org.junit.*;

public class dayFourTest {
	// Field to hold the puzzle
	private int[][] puzzle;

	// Helper method to get puzzle from path
	private int[][] getPuzzle() throws IOException {
		return dayFourPuzzle.puzzle("C:/Users/Trevor/OneDrive/Documents/CWRU/Code/AoC/Puzzle Text Files/dayFourPuzzle.txt");
	}

	// Test the first part of the challenge
	@Test
	public void partOneTest() throws IOException {
		puzzle = this.getPuzzle();
		int solution = dayFourPuzzle.cardSum(puzzle);
		System.out.println(solution);
	} 

	// Test the second part of the challenge
	@Test
	public void partTwoTest() throws IOException {
	}
}
