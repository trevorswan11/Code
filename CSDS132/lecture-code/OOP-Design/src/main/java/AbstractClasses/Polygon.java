package AbstractClasses;

/**
 * A class that represents an arbitrary polygon
 * 
 * @author Trevor Swan
 */
public abstract class Polygon extends Shape {

	/** the number of sides of the polygon */
	private final int numSides;

	/**
	 * Creates a new polygon instance with a set number of sides
	 * 
	 * @param numSides the number of sides for this polygon
	 */
	public Polygon(int numSides) {
		this.numSides = numSides;
	}

	/**
	 * Returns the number of sides of this polygon
	 * 
	 * @return the number of sides
	 */
	public int getNumSides() {
		return numSides;
	}
}
