package AbstractChallenge;

public class Circle extends Ellipse {
    public Circle(double radius) {
        super(radius, radius);
    }

    // Getter method for the radius
    public double getRadius() {
        return super.getMajorAxis();
    }

    // Setter method for the radius
    public void setRadius(double radius) {
        super.setMajorAxis(radius);
        super.setMinorAxis(radius);
    }

    // Override the set major axis method to change both axes
    @Override
    public void setMajorAxis(double radius) {
        setRadius(radius);
    }

    // Override the set minor axis method to change both axes
    @Override
    public void setMinorAxis(double radius) {
        setRadius(radius);
    }
}
