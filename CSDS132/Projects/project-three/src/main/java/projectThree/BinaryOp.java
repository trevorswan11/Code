package projectThree;

public class BinaryOp implements Function {
    /**
     * An enum to generate the possible binary operations as a nested type.
     */
     public enum Op {
        // Required operations for calculator
        PLUS("+"),
        SUB("-"),
        MULT("*"),
        DIV("/");

        // Immutable String to represent operation
        private final String symbol;

        /**
         * A private enum constructor to assign a symbol to an operator name.
         * 
         * @param symbol the String representation of the operator
         */
        private Op(String symbol) {
            this.symbol = symbol;
        }

        /**
         * A method to return the symbol equivalent for an operation.
         * 
         * @return The String representation of an operator
         */
        public String getSymbol() {
            return this.symbol;
        }
    }

    // Fields for the left and right operands and operator
    private Function leftOperand;
    private Function rightOperand;
    private Op operator;

    /**
     * A constructor to create a Binary Operation Expression.
     * @param operator
     * @param leftOperand
     * @param rightOperand
     */
    public BinaryOp(Op operator, Function leftOperand, Function rightOperand) {
        this.setOperator(operator);
        this.setLeftOperand(leftOperand);
        this.setRightOperand(rightOperand);
    }

    /**
     * A method to return the left operand of an expression.
     * @return A Function that is the left operand
     */
    public Function getLeftOperand() {
        return leftOperand;
    }

    /**
     * A method to set the left operand of an expression.
     * @param leftOperand A Function to set the left operand to
     */
    public void setLeftOperand(Function leftOperand) {
        this.leftOperand = leftOperand;
    }

    /**
     * A method to return the right operand of an expression.
     * @return A Function that is the right operand
     */
    public Function getRightOperand() {
        return rightOperand;
    }

    /**
     * A method to set the right operand of an expression.
     * @param rightOperand A Function to set the right operand to
     */
    public void setRightOperand(Function rightOperand) {
        this.rightOperand = rightOperand;
    }

    /**
     * A method to return the operator for the expression
     * @return An Op generated from the Op enum
     */
    public Op getOperator() {
        return this.operator;
    }

    /**
     * A method to set the operator of the current expression.
     * @param operator An Op of the enum of the same name to set for the expression
     */
    public void setOperator(Op operator) {
        this.operator = operator;
    }

    @Override
    public double value() {

    }

    @Override
    public double value(double x) {
        
    }
}
