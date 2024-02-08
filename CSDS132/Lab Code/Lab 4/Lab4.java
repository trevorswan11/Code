
/* Trevor Swan
 * Simon Eskin
 * Curtis Li
 */
import java.util.Scanner;

public class Lab4 {

  /** Input two doubles and return the larger of the two doubles */
  public static double maxDouble(double x, double y) {
    if (x > y) {
      return x;
    } else {
      return y;
    }
  }

  /** Input three doubles and return the middle value of the three */
  public static double middleValue(double x, double y, double z) {
    if ((x > y && x < z) || (x < z && x > y)) {
      return x;
    } else if ((y > x && y < z) || (y < z && y > x)) {
      return y;
    } else if ((z > x && z < y) || (z > y && z < x)) {
      return z;
    } else {
      return 0;
    }
  }

  /**
   * Input a double value and return the closest int value. The int value should
   * be rounded
   * so that if the fractional value is 0.5 or greater, it rounds up and if it is
   * less than 0.5
   * it rounds down.
   */
  public static int roundDouble(double val1) {
    return (int) (val1 + (int) ((val1 % 1) * 2));
  }

  /**
   * rounds 0.5 to even numbers only. Truncates the input if remainder is 0.5.
   * follows the previous method for the rest of it.
   */
  public static int unbiasedRoundDouble(double val2) {
    if ((val2 % 1 == 0.5)) {
      if (val2 % 2 == 0) {
        return (int) val2;
      } else {
        return roundDouble(val2);
      }
    }

    else {
      return roundDouble(val2);
    }
  }

  // All of the methods can be called in this main method, where user input is gotten from the command line
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Key for Testing:\n 1. max\n 2. middle\n 3. Bias Rounding\n 4. Unbiased Rounding\n 5. Quit");
    Boolean run = true;
    while (run) {
      System.out.print("Input: ");
      String input = sc.next();

      if (input.equals("1")) {
        System.out.print("\nx = ");
        double x = sc.nextDouble();
        System.out.print("\ny = ");
        double y = sc.nextDouble();
        double larger = maxDouble(x, y);
        System.out.println(larger);
      }

      else if (input.equals("2")) {
        // scanner for command line input
        System.out.print("\nx = ");
        double x = sc.nextDouble();
        System.out.print("\ny = ");
        double y = sc.nextDouble();
        System.out.print("\nz = ");
        double z = sc.nextDouble();

        // middle number method testing
        double middle = middleValue(x, y, z);
        System.out.println(middle);
      }

      else if (input.equals("3")) {
        // rounding method testing
        System.out.print("Value to be rounded: ");
        double value1 = sc.nextDouble();
        int result1 = roundDouble(value1);
        System.out.println(result1);
      }

      else if (input.equals("4")) {
        // biased rounding
        System.out.print("Value to be rounded: ");
        double value2 = sc.nextDouble();
        int result2 = roundDouble(value2);
        System.out.println(result2);
      } 

      else if (input.equals("5")) {
        System.out.println("Thank you!");
        run = false;
      }

      else {
        System.out.println("Invalid input");
      }
    }

    // closes the scanner
    sc.close();
  }
}
