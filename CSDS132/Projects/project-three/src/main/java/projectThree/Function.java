package projectThree;

/**
 * An interface with needed abstract public methods for all inheritances.
 * 
 * @author Trevor Swan
 * @version CSDS132 - Spring 2024
 */
interface Function {
    /**
     * Compares two Function objects.
     * 
     * @param obj An object to be compared with its instance call
     * @return True if operands are equal, false otherwise
     */
    @Override
    boolean equals(Object obj);
    
    /**
     * Converts a Function to its String representation.
     * 
     * @return The unique String representation of a Function
     */
    @Override
    String toString();

    /**
     * Evaluates a Function with no variables in it.
     * 
     * @return The double value of the Function as a single number
     * @throws UnsupportedOperationException When a value is expected
     */
    double value() throws UnsupportedOperationException;
    
    /**
     * Evaluates a Function at an inputted double value.
     * 
     * @param x the requested double value to evaluate with
     * @return the function value at the input
     */
    double value(double x);

    /**
     * Returns the derivate of the Function used to call.
     * 
     * @return A Function representing the derivative of the call
     */
    Function derivative();

}
