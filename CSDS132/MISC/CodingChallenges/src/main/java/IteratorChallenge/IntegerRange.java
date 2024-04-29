package IteratorChallenge;

public class IntegerRange implements Iterable<Integer> {
    // Fields to store min and max
    private int minimum;
    private int maximum;

    /**
     * Returns the instance's minimum value.
     * 
     * @return The minimum value
     */
    public int getMinimum() {
        return minimum;
    }

    /**
     * Returns the instance's maximum value.
     * 
     * @return The maximum value
     */
    public int getMaximum() {
        return maximum;
    }

    /**
     * Sets the minimum value.
     * 
     * @param minimum The desired minimum value
     */
    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    /**
     * Sets the maximum value.
     * 
     * @param maximum The desired maximum value
     */
    public void setMaximum(int maximum) {
        this.maximum = maximum;
    } 

    /**
     * Create a range of integers: [min, max] inclusive.
     * 
     * @param minimum The desired minimum value
     * @param maximum The desired maximum value
     */
    public IntegerRange(int minimum, int maximum) {
        this.setMinimum(minimum);
        this.setMaximum(maximum);
    }

    /**
     * Returns an iterator that iterates from the integers in range from minimum to maximum
     * 
     * @return An iterator to iterate through the integers
     */
    public IntegerRangeIterator iterator() {
        return new IntegerRangeIterator(this);
    }
}
