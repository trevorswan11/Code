package projectThree;

/**
 * An abstract class to declare default method behaviors for all types
 * 
 * @author Trevor Swan
 * @version CSDS132 - Spring 2024
 */
public abstract class AbstractFunction implements Function {
    /**
     * Evaluates a constant Function.
     * 
     * @return A double value of the computed Function
     * @throws UnsupportedOperationException If an input is expected, i.e. the
     *                                       Function is not constant
     */
    @Override
    public double value() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("An input was expected, but not provided!");
    }

    /**
     * Evaluates a Function at a given value, or returns the value of a constant
     * function.
     * 
     * @param x The value to evaluate the Function at
     * @return A double value of the evaluated Function
     */
    @Override
    public abstract double value(double x);

    /**
     * Calculates the derivative of a Function.
     * 
     * @return A Function that is the derivative specific to the instance used
     */
    @Override
    public abstract Function derivative();
}
