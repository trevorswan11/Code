package AbstractChallenge;

/**
 * This class represents an arbitrary shape
 * 
 * @author Trevor Swan
 */
public abstract class Shape {
	
	/**
	 * Returns the area of a shape.
	 * 
	 * @return the shape's area
	 */
	public abstract double getArea(); // Semicolon indicates no method body

	/**
	 * Compare this shape to the input and return true if this shape is larger
	 * 
	 * @param s a shape to compare this shape to
	 * @return true if this shape is larger than s
	 */
	public boolean isLargerThan(Shape s) {
		return this.getArea() > s.getArea();
	}
}