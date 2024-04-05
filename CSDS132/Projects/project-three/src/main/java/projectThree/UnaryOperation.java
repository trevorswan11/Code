package projectThree;

/**
 * An abstract class to group Unary Operations/Functions. These functions have
 * one operator, with the following included:
 * Log, Exp, Sin, Cos
 * 
 * This abstract class declares needed behaviors Unary Functions.
 * 
 * @author Trevor Swan
 * @version CSDS132 - Spring 2024
 */
public abstract class UnaryOperation extends AbstractFunction {
    // A private field that is the Operation for the Unary Function
    private final Unary operator;

    // A private field that is the operand
    private final Function operand;

    /**
     * An enum to generate the possible Unary operations as a nested type.
     * Possible Operations are:
     * LOG, EXP, SIN, COS
     */
    public enum Unary {
        // Required Unary Operations
        LOG("Log"),
        EXP("Exp"),
        SIN("Sin"),
        COS("Cos");

        // Immutable String for the operation
        private final String name;

        /**
         * A private constructor to declare the String representations of the Unary Operations.
         * @param unary The name of the operation as a String
         */
        private Unary(String name) {
            this.name = name;
        }

        /**
         * A method to return the name of the Unary Operation
         * @return A String that is the representation of the Unary enum
         */
        public String getUnaryName() {
            return this.name;
        }

        /**
         * A method to check the equality of an operator.
         * 
         * @param op An object of type Unary to compare
         * @return True if the operators are the same, false otherwise
         */
        public boolean equals(Unary op) {
            // Compare the equality of the operator names
            return this.getUnaryName().equals(op.getUnaryName());
        }
    }

    /**
     * A constructor to declare the operator and operand of a Unary Function.
     * @param operator The desired operator of type Unary
     * @param operand The desired operand of type Function
     */
    public UnaryOperation(Unary operator, Function operand) {
        super(operator, operand);
        this.operator = operator;
        this.operand = operand;
    }

    /**
     * A method to return the Unary Operator
     * @return The operator of type Unary
     */
    public Unary getOperator() {
        return this.operator;
    }

    /**
     * A method to return the Function operand
     * @return The operand of type Function
     */
    public Function getOperand() {
        return this.operand;
    }

    /**
     * The correct implementation of the equals method for all Unary Objects.
     * @param obj An Object to compare with the instance used
     * @return A boolean value indicating whether or not the instances are equal 
     */
    @Override
    public boolean equals(Object obj) {
        // Check if the input is even a Function
        if (!(obj instanceof Function)) {
            return false;
        }

        // Convert the input to type function
        Function f = (Function)obj;

        // If the input is not a UnaryOperation, the functions are not equal
        if (!(f instanceof UnaryOperation)) {
            return false;
        }

        // The input must be a UnaryOperation to get here, so typecast
        UnaryOperation input = (UnaryOperation)f;

        // Switch statement to handle the different operators
        switch (this.getOperator()) {
            // Step in if the instance is a Log
            case LOG:
                // Return false if the input is not of the same type
                if (!(input.getOperator().equals(Unary.LOG))) {
                    return false;
                }

                // Check the operand Functions against each other
                return this.getOperand().equals(input.getOperand());
                
            // Step in if the instance is an Exp
            case EXP:
                // Return false if the input is not of the same type
                if (!(input.getOperator().equals(Unary.EXP))) {
                    return false;
                }

                // Check the operand Functions against each other
                return this.getOperand().equals(input.getOperand());

            // Step in if the instance is a Sin
            case SIN:
                // Return false if the input is not of the same type
                if (!(input.getOperator().equals(Unary.SIN))) {
                    return false;
                }

                // Check the operand Functions against each other
                return this.getOperand().equals(input.getOperand());

            // TO get here, the operator must be Cos
            default:
                // Return false if the input is not of the same type
                if (!(input.getOperator().equals(Unary.COS))) {
                    return false;
                }
                
                // Check the operand Functions against each other
                return this.getOperand().equals(input.getOperand());
        }
    }
    
    /**
     * The correct conversion to a String for Unary Objects.
     * @return The correctly formatted String representation of a UnaryOperation
     */
    @Override
    public String toString() {
        // Create a StringBuilder with the name of the operation
        StringBuilder builder = new StringBuilder(this.getOperator().getUnaryName());
        
        // Append the opening bracket, the operator, and the closing bracket
        builder.append("[");
        builder.append(this.getOperand().toString());
        builder.append("]");

        // Return the builder as a String
        return builder.toString();
    }

    /**
     * Default implementation of the a value call with no input when one is expected. 
     * @throws UnsupportedOperationException If an input is expected, i.e. the
     *                                       Function is not constant
     */
    @Override
    public double value() throws UnsupportedOperationException{
        throw new UnsupportedOperationException("An input was expected, but not provided!");
    }

    /**
     * Default implementation of value at a point that calculates the instances value.
     * @param x A double value to evaluate a Function at
     * @return The double evaluated expression 
     */
    @Override
    public double value(double x) {
        return this.getOperand().value(x);
    }

    /**
     * Calculates the derivative of the Operand for inheritance use.
     * @return A Function that is the derivative of an instances operand
     */
    @Override
    public Function derivative() {
        return this.getOperand().derivative();
    }
}