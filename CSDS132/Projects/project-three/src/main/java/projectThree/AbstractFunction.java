package projectThree;

/**
 * An abstract class to declare default method behaviors for all types
 * 
 * @author Trevor Swan
 * @version CSDS132 - Spring 2024
 */
public abstract class AbstractFunction implements Function {
    /**
     * A constructor that must be called in every implementation. It checks for null
     * inputs, which are not allowed as all calculator functions need inputs to
     * work, as confirmed by any actual calculator.
     * 
     * @param inputs A variable number of Objects inputs to check against null
     * @throws NullPointerException if null is given as an input to any subtype
     *                              constructor
     */
    public AbstractFunction(Object... inputs) throws NullPointerException {
        // A for-each loop to check all the inputs
        for (Object input : inputs) {
            // If the current input is null, throw an error
            if (input == null) {
                throw new NullPointerException("Operations cannot be null!");
            }
        }
    }

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
