package AbstractChallenge;

public class Ellipse extends Shape {
    // Major Axis
    private double majorAxis;

    // Minor Axis
    private double minorAxis;

    // Ellipse constructor to require two axes
    public Ellipse(double axisOne, double axisTwo) {
        // Set the larger axis to the major axis
        if (axisOne > axisTwo) {
            this.setMajorAxis(axisOne);
            this.setMinorAxis(axisTwo);
        }

        // Otherwise set the second axis to the major axis
        else {
            this.setMajorAxis(axisTwo);
            this.setMinorAxis(axisOne);
        }
    }

    // Getter method for major axis
    public double getMajorAxis() {
        return this.majorAxis;
    }

    // Setter method for major axis
    public void setMajorAxis(double majorAxis) {
        this.majorAxis = majorAxis;
    }

    // Getter method for minor axis
    public double getMinorAxis() {
        return this.minorAxis;
    }

    // Setter method for minor axis
    public void setMinorAxis(double minorAxis) {
        this.minorAxis = minorAxis;
    }

    // Override Shape's area method
    @Override
    public double getArea() {
        return Math.PI * this.getMajorAxis() * this.getMinorAxis();
    }
}
