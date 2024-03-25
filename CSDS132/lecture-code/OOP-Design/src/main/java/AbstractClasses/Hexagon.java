package AbstractClasses;

public class Hexagon extends Polygon implements RegularPolygon {
    private double sideLength;
    
    public Hexagon(double sideLength) {
        super(6);
        this.setSideLength(sideLength);
    }

    @Override    
    public double getArea() {
        // 'this' references the instance used to call getArea()
        return this.getAreaOfRegularPolygon();
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    public double getSideLength() {
        return this.sideLength;
   }
}
