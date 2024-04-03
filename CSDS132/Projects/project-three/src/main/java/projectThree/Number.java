package projectThree;

public class Number extends AbstractFunction {
    // A private field that immutably stores the declared number
    private final double number;

    /**
     * The constructor that declares the number to be associated with this instance
     * of Number.
     * 
     * @param number The value of the number as a double
     */
    public Number(double number) {
        this.number = number;
    }

    /**
     * The getter method for the number corresponding to this instance.
     * 
     * @return A double value representing the instantiated instance
     */
    public double getNumber() {
        return number;
    }

    /**
     * Compares the values of a Number with another Function.
     * 
     * @param obj An object to compare with a Number
     * @return True if both objects are Numbers and their numeric values are the
     *         same
     */
    @Override
    public boolean equals(Object obj) {
        // Check if the input is even a Function
        if (!(obj instanceof Function)) {
            return false;
        }

        // Convert the input to type function
        Function f = (Function)obj;

        // If the input is not a Number, return false by default
        if (!(f instanceof Number)) {
            return false;
        }

        // Typecast the input to number as the first conditional did not trigger
        Number n = (Number) f;

        // Compare the numeric values of the call and the input
        if (this.getNumber() != n.getNumber()) {
            return false;
        }

        // If the previous conditions weren't triggered, values are equal
        return true;
    }

    /**
     * Returns the String representation of the number associated with this
     * instance.
     * 
     * @return A String representation of the number
     */
    public String toString() {
        // Create a StringBuilder with the number of the instance
        StringBuilder result = new StringBuilder();
        result.append(this.getNumber());

        // Return the StringBuilder as a String
        return result.toString();
    }

    /**
     * Returns the double value of the Number instance.
     * 
     * @return The double value of the instance
     */
    public double value() {
        return this.getNumber();
    }

    /**
     * Returns the double value of the number instance regardless of the input
     * 
     * @param x An omitted double input
     * @return The double value of the instance
     */
    @Override
    public double value(double x) {
        return this.value();
    }

    /**
     * Computes the derivative of a Number. Numbers are constant so derivatives are
     * 0.
     * 
     * @return 0 as the derivative of a constant is 0
     */
    @Override
    public Function derivative() {
        return new Number(0);
    }
}