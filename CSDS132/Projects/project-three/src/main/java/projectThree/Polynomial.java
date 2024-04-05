package projectThree;

import projectThree.BinaryOp.Op;

/**
 * A class to represent a single term of a polynomial. By definition, a
 * polynomial is a Function raised to a power.
 * 
 * @author Trevor Swan
 * @version CSDS132 - Spring 2024
 */
public class Polynomial extends AbstractFunction {
    // Field to contain the Function operand
    private final Function operand;

    // Field to contain the power of the polynomial term
    private final double power;

    /**
     * A constructor to create an instance with an inputted operand and power
     * 
     * @param operand The Function operand to set for the instance
     * @param power   A double to represent the power of the instance
     */
    public Polynomial(Function operand, double power) {
        super(operand);
        this.operand = operand;
        this.power = power;
    }

    /**
     * Returns the operand for the polynomial.
     * 
     * @return A Function that is the operand of the polynomial
     */
    public Function getOperand() {
        return this.operand;
    }

    /**
     * Returns the power for the polynomial.
     * 
     * @return A double that is the power of the polynomial
     */
    public double getPower() {
        return this.power;
    }

    /**
     * Checks the equality of a Polynomial with another Function.
     * 
     * @param obj An Object to compare with the Polynomial instance
     * @return True if both the power and operands of two Polynomials are
     *         equivalent. False otherwise
     */
    @Override
    public boolean equals(Object obj) {
        // Check if the input is even a Function
        if (!(obj instanceof Function)) {
            return false;
        }

        // Convert the input to type function
        Function f = (Function)obj;
        
        // Check to see if the input is a Polynomial
        if (!(f instanceof Polynomial)) {
            return false;
        }

        // Convert the input to a Polynomial now that the first conditional passed
        Polynomial p = (Polynomial) f;

        // Create a boolean variable to store equality of the operand Functions
        boolean operands = this.getOperand().equals(p.getOperand());

        // Create a boolean variable to store equality of the powers
        boolean powers = this.getPower() == (p.getPower());

        // Return boolean if both the operands and powers are equal
        return (operands && powers);
    }

    /**
     * Converts the Polynomial instance into its String representation.
     * 
     * @return A String that represents the Polynomial with ^ for powers
     */
    @Override
    public String toString() {
        // Create a StringBuilder to help with memory storage
        StringBuilder builder = new StringBuilder();

        // If the Operand is a BinaryOp, place it in parentheses
        if (this.getOperand() instanceof BinaryOp) {
            builder.append("(");
            builder.append(this.getOperand().toString());
            builder.append(")");
        }

        // Otherwise, convert the Function to a String using its own method
        else {
            builder.append(this.getOperand().toString());
        }

        // Now append a Carrot and the power itself
        builder.append("^");
        builder.append(this.getPower());

        // Return the builder as a String
        return builder.toString();
    }

    /**
     * Computes the value of a Polynomial with a constant Operand.
     * 0^0 is evaluated to 1, Complex numbers are NaN
     * 
     * @return A double representing the computed Polynomial
     * @throws UnsupportedOperationException If an input is expected
     */
    public double value() {
        // Try to compute the value of the Polynomial
        try {
            // Compute the value of the Operand
            double operandValue = this.getOperand().value();

            // Raise the Calculated value to the Polynomial's power to return
            return Math.pow(operandValue, this.getPower());
        }

        // Throw an exception if a value could not be calculated
        catch (Exception e) {
            throw new UnsupportedOperationException("One or more operands require an input or are undefined!");
        }
    }

    /**
     * Computes an evaluated Function that is evaluated at an inputted number.
     * 0^0 is evaluated to 1, Complex numbers are NaN
     * 
     * @param x A double to evaluate the Function Operand at
     * @return A double representing the computed Polynomial
     */
    public double value(double x) {
        // Compute the Operand of the Polynomial at the input
        double operandValue = this.getOperand().value(x);

        // Raise the operand value to the Polynomial's power to return
        return Math.pow(operandValue, this.getPower());
    }

    /**
     * Computes the derivative of a Polynomial using the chain rule.
     * 
     * @return A Function that is the derivative of the method instance.
     */
    public Function derivative() {
        // If a polynomial has a 0 power, then its derivative is just 0
        if (this.getPower() == 0) {
            return new Number(0);
        }

        // Create an Uninitialized variable to use as the first term
        Function firstTerm;

        // If the power of the Polynomial is 1, create a Number object of One
        if (this.getPower() == 1) {
            firstTerm = new Number(1);
        }

        // Otherwise follow the rules of the power rule
        else {
            // Create a new Polynomial that has a power one less than its original
            Polynomial innerFunction = new Polynomial(this.getOperand(), this.getPower() - 1);

            // Create a BinaryOp that is the the innerFunction times its original power
            firstTerm = new BinaryOp(Op.MULT, new Number(this.getPower()), innerFunction);
        }

        // Compute the true derivative of the Polynomial with a BinaryOp
        return new BinaryOp(Op.MULT, firstTerm, this.getOperand().derivative());
    }
}