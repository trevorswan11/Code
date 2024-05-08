package ComparableChallenge;

/* The class that represents some measurement */
public class Measurement extends Object implements Comparable<Measurement>{
    // the quantity of this measurement
    private double quantity;

    /** a constructor that creates an instance with a given quantity */
    public Measurement(double quantity) {
        this.quantity = quantity;
    }

    /** get the quantity value */
    public double getQuantity() {
        return this.quantity;
    }

    /** change the quantity value */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /** change the toString method to print the quantity */
    public String toString() {
        return "" + getQuantity();
    }

    /**
     * change the equals method to say two measurements are the same if they have
     * the same quantity
     */
    public boolean equals(Object o) {
        if (o instanceof Measurement) {
            Measurement m = (Measurement) o;
            return this.getQuantity() == m.getQuantity();
        } else
            return false;
    }

    /**
     * Compares two measurements.
     * 
     * @return -1 if input is larger than instance, 0 if equal, 1 if input is
     *         smaller than instance
     */
    @Override
    public int compareTo(Measurement m) {
        if (m.getQuantity() > this.getQuantity()) {
            return -1;
        } else if (m.getQuantity() < this.getQuantity()) {
            return 1;
        } else {
            return 0;
        }
    }
}