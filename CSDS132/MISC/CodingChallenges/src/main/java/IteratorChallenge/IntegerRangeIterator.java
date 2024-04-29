package IteratorChallenge;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntegerRangeIterator implements Iterator<Integer> {
    // Fields to store the minium and range
    private int value;
    private IntegerRange range;

    /**
     * Returns the value of the iterator
     * 
     * @return The value associated with the iterator
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Creates an iterator for the range.
     * 
     * @param range An integer range
     */
    public IntegerRangeIterator(IntegerRange range) {
        this.value = range.getMinimum();
        this.range = range;
    }

    /**
     * Determines if there is a value succeeding the current one in the iterator.
     * 
     * @return true if there is a succeeding value
     */
    public boolean hasNext() {
        return value <= range.getMaximum();
    }

    /**
     * Returns the next value in the iterator.
     * 
     * @return The next value
     * @throws NoSuchElementException If there is not a succeeding element 
     */
    public Integer next() throws NoSuchElementException {
        if (!this.hasNext()) {
            throw new NoSuchElementException("No succeeding element!");
        } else {
            return value++;
        }
    }
}