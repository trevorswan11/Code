package AbstractClasses;

/** An implementation of a square. 
  * A square is a rectangle where the width and the height are the same. 
  */
public class Square extends Rectangle {
  // In this class we make the rectangle a square by making sure that
  // when one dimension changes, both are changed.
  
  /**
   * Creates a square with the given side length.
   * @param length the desired side length for this square.
   */
  public Square(double length) {
    super(length, length);
  }
  
  /**
   * Changes the width of the square.
   * @param width the new width of the square
   */
  @Override
  public void setWidth(double width) {
    super.setWidth(width);
    super.setHeight(width);
  }
  
  /**
   * Changes the height of the square.
   * @param height the new height of the square
   */
  @Override
  public void setHeight(double height) {
    this.setWidth(height);
  }
}