package AbstractClasses;

/** A class that represents a rectangle */
public class Rectangle extends Polygon {

	/** the width of the rectangle */
	private double width;

	/** the height of the rectangle */
	private double height;

	/**
	 * Creates a rectangle with the given dimensions
	 * 
	 * @param width  the width for the rectangle
	 * @param height the height for the rectangle
	 */
	public Rectangle(double width, double height) {
		super(4); // You must call a contructor from parent here!
		this.width = width;
		this.height = height;
	}

	/**
	 * Returns the rectangle's width.
	 * 
	 * @return the width of the rectangle
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Returns the rectangle's height.
	 * 
	 * @return the height of the rectangle
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Changes the rectangle's width.
	 * 
	 * @param width the new width for the rectangle.
	 */
	public void setWidth(double width) {
		this.width = width;
	}

	/**
	 * Changes the rectangle's height.
	 * 
	 * @param height the new height for the rectangle.
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * Returns the area of the rectangle.
	 * 
	 * @return the area of the rectangle
	 */
	@Override
	public double getArea() {
		return getWidth() * getHeight();
	}

	// Test the way the method works 
	public static void main(String[] args) {
		Rectangle rect = new Rectangle(2.0,3.5);
		System.out.println(rect.getArea());
	}
}
