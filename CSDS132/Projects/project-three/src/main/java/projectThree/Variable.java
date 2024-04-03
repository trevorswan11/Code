package projectThree;

/**
 * The Variable class that represents the variable x, with necessary methods.
 * 
 * @author Trevor Swan
 * @version CSDS132 - Spring 2024
 */
public class Variable extends AbstractFunction {
    // A private final field that represents the only variable, x
    private final String variable;

    /**
     * The Variable Constructor. Sets the String Representation of the variable to x
     */
    public Variable() {
        this.variable = "x";
    }

    /**
     * Compares two Object instances.
     * 
     * @param obj An Object to be compared with Variable
     * @return true if the input is a Variable, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        // Check if the input is even a Function
        if (!(obj instanceof Function)) {
            return false;
        }

        // Convert the input to type function
        Function f = (Function)obj;

        // Check if the input is of the same type
        if (f instanceof Variable) {
            return true;
        }
        return false;
    }

    /**
     * Returns the letter x, as variables are only x for this project.
     * 
     * @return The letter x as a String
     */
    @Override
    public String toString() {
        return variable;
    }

    /**
     * Computes the value of the variable. Equal to the input as x is linear.
     * 
     * @param x A double representing the desired value to use
     * @return A double with the exact same value as the parameter
     */
    @Override
    public double value(double x) {
        return x;
    }

    /**
     * Computes the derivative of a linear Function x.
     * 
     * @return A constant as x is linear
     */
    @Override
    public Function derivative() {
        return new Number(1);
    }
}
