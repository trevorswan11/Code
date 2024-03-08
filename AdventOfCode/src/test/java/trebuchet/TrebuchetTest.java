package trebuchet;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;

public class TrebuchetTest  {
	@Test
	public void getPuzzleTest() throws IOException {
		String puzzle = Trebuchet.puzzle("C:/Users/Trevor/OneDrive/Documents/CWRU/Code/AdventOfCode/dayOnePuzzle.txt");
		System.out.println(puzzle);
	}
}
