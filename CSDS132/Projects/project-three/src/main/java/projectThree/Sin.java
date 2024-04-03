package projectThree;

// import enum from BinaryOp
import projectThree.BinaryOp.Op;

/**
 * A class to represent the Unary operation of the Sine Function.
 * 
 * @author Trevor Swan
 * @version CSDS132 - Spring 2024
 */
public class Sin extends UnaryOperation {
    /**
     * Creates a new instance of the Sin Operation with a Function Operand.
     * @param operand The operand for the Sin Operator
     */
    public Sin(Function operand) {
        super(Unary.SIN, operand);
    }

    /**
     * Computes the value of a Constant Function with the Cos Operator.
     * @return The double value that is the computed expression
     * @throws UnsupportedOperationException If an input is expected, i.e. the
     *                                       Function is not constant
     */
    @Override
    public double value() throws UnsupportedOperationException {
        // Try to compute the value of the instance
        try {
            // Compute the value of the instance and return it with Math class method
            double operation = this.getOperand().value();
            return Math.sin(operation);
        } 
        
        // Throw an exception if a value could not be calculated
        catch (Exception e) {
            // Use the supertype value implementation to throw a consistent error.
            super.value();
            throw e;
        }
    }

    /**
     * Computes the value of a Function with the Sin operator at a given value.
     * @param x The double value to evaluate the Function at
     * @return The double value that is the computed expression
     */
    @Override
    public double value(double x) {
        // Compute the value of the instance at x and return it after Math is used
        double operation = super.value(x);
        return Math.sin(operation);
    }
    
    /**
     * Calculates the Derivative of a Sin Expression.
     * @return A Function that is the derivative of the input
     */
    @Override
    public Function derivative() {
        // Calculate the derivative of the operand
        Function operandDerivative = super.derivative();
        
        // Calculate the non-scalar part of the derivative
        Function derivative = new BinaryOp(Op.MULT, operandDerivative, new Cos(this.getOperand()));

        // Return the true derivative using BinaryOp and derivative rules
        return new BinaryOp(Op.MULT, new Number(-1), derivative);
    }
}
