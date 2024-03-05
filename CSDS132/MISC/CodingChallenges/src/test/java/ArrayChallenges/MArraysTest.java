package ArrayChallenges;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class MArraysTest {
  @Test
  public void sumTest() {
    // Create the array for the first challenge
    double [] fillArray = new double[] {10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
    double[][] testArray1 = new double[10][10];
    for (int i1 = 0; i1 < 10; i1++) {
      testArray1[i1] = fillArray;
    }

    // Create the array for the second challenge
    double[][][] testArray2 = new double[10][10][10];
    for (int i2 = 0; i2 < 10; i2++) {
      for (int j = 0; j < 10; j++) {
        testArray2[i2][j] = fillArray;
      }
    } 

    //Create the two null arrays 
    double[][] nullArray1 = new double[10][0];
    double[][][] nullArray2 = new double[10][0][0];

    // Test the two methods
    assertEquals(1000, MArraysChallenge.sum(testArray1), 0);
    assertEquals(0, MArraysChallenge.sum(nullArray1), 0);
    assertEquals(10000, MArraysChallenge.sum(testArray2), 0);  
    assertEquals(0, MArraysChallenge.sum(nullArray2), 0);
  }
}
