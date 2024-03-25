package AbstractClasses;

/** This is the RegularPolygon Interface 
 * @author Trevor Swan
 */
public interface RegularPolygon {
    /**
     * Return the number of sides for the regular polygon
     * @return the number of sides
      */
    int getNumSides(); // public abstract are dropped from modifiers bc implied with interface

    /**
     * Return the length of a side for the Regular Polygon
     * @return the length of sides
      */
    double getSideLength(); // These method declarations without modifiers are called method stubs

    default double getAreaOfRegularPolygon() {
        return getNumSides()*getSideLength()*getSideLength()/(4.0*Math.tan(Math.PI/getNumSides()));
    }
}
