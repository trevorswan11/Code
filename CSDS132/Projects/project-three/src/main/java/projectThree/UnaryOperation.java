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
    }

    /**
     * A constructor to declare the operator and operand of a Unary Function.
     * @param operator The desired operator of type Unary
     * @param operand The desired operand of type Function
     */
    public UnaryOperation(Unary operator, Function operand) {
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
     * The correct conversion to a String for Unary Objects.
     * @return The correctly formatted String representation of a UnaryOperation
     */
    @Override
    public String toString() {
        // Create a StringBuilder with the name of the operation
        StringBuilder builder = new StringBuilder(this.getOperator().getUnaryName());
        
        // Append the opening bracket, the operator, and the closing bracket
        builder.append("[");
        builder.append(this.getOperand());
        builder.append("]");

        // Return the builder as a String
        return builder.toString();
    }
}