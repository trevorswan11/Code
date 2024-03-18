package AbstractClasses;

/** Another implementation of a square. 
  * A square is a rectangle where the width and the height are the same.
  */
public class Square2 extends Rectangle {
  // In this class, we make the rectangle a square by using on the height
  // attribute.  Any access of or changes to the width also change the height.
  
  /**
   * Create a square with the given side length.
   * @param length the desired side length for this square
   */
  public Square2(double length) {
    super(length, length);
  }
  
  /**
   * Get the width of the square.
   * @return the width of the square.
   */
  @Override
  public double getWidth() {
    return getHeight();
  }
  
  /**
   * Change the width of the square.
   * @param width the new width for the square
   */
  @Override
  public void setWidth(double width) {
    setHeight(width);
  }
}