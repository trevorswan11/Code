package projectThree;

/**
 * An interface with value and derivative public methods for all inheritances.
 * 
 * @author Trevor Swan
 * @version CSDS132 - Spring 2024
 */
interface Function {
    /**
     * Evaluates a Function at an inputted double value.
     * 
     * @param x the requested double value to evaluate with
     * @return the function value at the input
     */
    double value(double x);

    /**
     * Evaluates a Function with no variables in it.
     * 
     * @return The double value of the Function as a single number
     * @throws UnsupportedOperationException When a value is expected
     */
    double value() throws UnsupportedOperationException;

    /**
     * Returns the derivate of the Function used to call.
     * 
     * @return A Function representing the derivative of the call
     */
    Function derivative();
}
