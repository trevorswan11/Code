package ArrayChallenges;

/**
 * Hello world!
 *
 */
public class MArraysChallenge {
  public static double sum(double[][] array) {
    if (array.length == 0 || array == null) {
      return 0;
    }
    else {
      double sum = 0;
      for (int i = 0; i < array.length; i++) {
        for (int j = 0; j < array[i].length; j++) {
          sum = sum + array[i][j];
        }
      }
      return sum;
    }
  }

  public static double sum(double[][][] array) {
    if (array.length == 0 || array == null) {
      return 0;
    }
    else {
      double sum = 0;
      for (int i = 0; i < array.length; i++) {
        for (int j = 0; j < array[i].length; j++) {
          for (int k = 0; k < array[i][j].length; k++) {
            sum = sum + array[i][j][k];
          }
        }
      }
      return sum;
    }
  }

  public static double divide(String s) {
					System.out.println(s);
  	double lengthString = s.length() / 2.0;
			return lengthString;
  }
}
