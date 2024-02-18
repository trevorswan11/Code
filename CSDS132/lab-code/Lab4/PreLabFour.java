public class PreLabFour {
  public String compareValues(int x, int y) {
    if (x > y) {
      return x + " is larger than " + y;
    }
    else if (y > x) {
      return y + " is larger than " + x;
    }
    else {
      return "the two values are equal";
    }
  }
}