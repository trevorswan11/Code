package projectThree;

// import enum from BinaryOp
import projectThree.BinaryOp.Op;

/**
 * A class to represent the Unary operation of the Natural Logarithm.
 * 
 * @author Trevor Swan
 * @version CSDS132 - Spring 2024
 */
public class Log extends UnaryOperation {
    /**
     * Creates a new instance of the Log Operation with a Function Operand.
     * @param operand The operand for the Log Operator
     */
    public Log(Function operand) {
        super(Unary.LOG, operand);
    }

    /**
     * Computes the value of a Constant operand in the Log Operator.
     * @return A double value that is the computed expression
     * @throws UnsupportedOperationException If an input is expected, i.e. the
     *                                       Function is not constant
     */
    @Override
    public double value() throws UnsupportedOperationException {
        // Try to compute the value of the instance
        try {
            // Compute the value of the instance and return it with Math class method
            double operation = this.getOperand().value();
            return Math.log(operation);
        } 
        
        // Throw an exception if a value could not be calculated
        catch (Exception e) {
            // Use the supertype value implementation to throw a consistent error.
            super.value();
            throw e;
        }
    }

    /**
     * Computes the value of an operand in the Log Operator.
     * @param x The double value to evaluate the function at
     * @return The double value that is the computed expression
     */
    @Override
    public double value(double x) {
        // Compute the value of the instance at x and return it after Math is used
        double operation = super.value(x);
        return Math.log(operation);
    }

    /**
     * Calculates the Derivative of a Log Expression.
     * @return A Function that is the derivative of the input
     */
    @Override
    public Function derivative() {
        // Calculate the derivative of the operand
        Function operandDerivative = super.derivative();
        
        // Return the derivative using derivative rules and a BinaryOp
        return new BinaryOp(Op.DIV, operandDerivative, this.getOperand());
    }
}
