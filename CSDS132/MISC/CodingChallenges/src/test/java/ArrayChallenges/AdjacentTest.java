package ArrayChallenges;

import org.junit.*;
import static org.junit.Assert.*;

public class AdjacentTest {
    // Fields to store the testing objects
    int[] testerOne;
    int[] testerTwo;
    int[] testerThree;
    int[][] testerFour;
    int[][] testerFive;
    int[][] testerSix;
    int[][] testerSeven;
    int[][] testerEight;

    // A helper method to declare the testing objects
    private void declareTesters() {
        testerOne = new int[] { 0, 0 };
        testerTwo = new int[] { 0, 1 };
        testerThree = new int[] { 1, 2, 3 };
        testerFour = new int[][] {
                { 0, 0, 0 },
                { 0, 0, 0 },
                { 0, 0, 0 }
        };
        testerFive = new int[][] {
                { 1, 1, 1 },
                { 1, 1, 1 },
                { 1, 1, 1 }
        };
        testerSix = new int[][] {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };
        testerSeven = new int[][] {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 1 }
        };
        testerEight = new int[][] {
                { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
                { 11, 22, 33, 44, 1 }
        };
    }

    // Test the packer checking method
    @Test
    public void packerTest() {
        declareTesters();
        assertFalse(AdjacentChecker.isPacked(testerOne));
        assertFalse(AdjacentChecker.isPacked(testerTwo));
        assertTrue(AdjacentChecker.isPacked(testerThree));
        assertFalse(AdjacentChecker.isPacked(testerFour));
        assertFalse(AdjacentChecker.isPacked(testerFive));
        assertTrue(AdjacentChecker.isPacked(testerSix));
        assertTrue(AdjacentChecker.isPacked(testerSeven));
        assertTrue(AdjacentChecker.isPacked(testerEight));
    }
}
