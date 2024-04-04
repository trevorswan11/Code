package projectThree;

/**
 * The BinaryOp class that represents an expression with two operands and an
 * operator.
 * The values contained in each instance are immutable.
 * 
 * @author Trevor Swan
 * @version CSDS132 - Spring 2024
 */
public class BinaryOp extends AbstractFunction {
    // A field to represent the operator from the enum
    private final Op operator;

    // A field to represent the left operand
    private final Function leftOperand;

    // A field to represent the right operand
    private final Function rightOperand;

    /**
     * An enum to generate the possible binary operations as a nested type.
     * Possible operations are:
     * PLUS, SUB, MULT, DIV
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

        /**
         * A method to check the equality of an operator.
         * 
         * @param op An object of Type Operator to compare
         * @return True if the operators are the same, false otherwise
         */
        public boolean equals(Op op) {
            // Compare the string symbol of the operators
            if (this.getSymbol().equals(op.getSymbol())) {
                return true;
            }
            return false;
        }
    }

    /**
     * A constructor that creates a binary operation with inputted left and right
     * operands as well as the binary operation itself.
     * 
     * @param operator     The desired operation to perform
     * @param leftOperand  The desired left operand
     * @param rightOperand The desired right operand
     * @throws NullPointerException if any inputs are null
     */
    public BinaryOp(Op operator, Function leftOperand, Function rightOperand) {
        super(operator, leftOperand, rightOperand);
        this.operator = operator;
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    /**
     * Returns the operator of the expression.
     * 
     * @return A Op of the nested enum type representing the operator
     */
    public Op getOperator() {
        return this.operator;
    }

    /**
     * Returns the left operand of the expression.
     * 
     * @return A Function representing the left operand
     */
    public Function getLeftOperand() {
        return this.leftOperand;
    }

    /**
     * Returns the right operand of the expression.
     * 
     * @return A Function representing the right operand
     */
    public Function getRightOperand() {
        return this.rightOperand;
    }

    /**
     * Compares a BinaryOp to another Function.
     * 
     * @param f Any Object to compare with BinaryOp
     * @return True if both the left operands and right operands are equal, as well
     *         as their operator. Otherwise false is returned
     */
    @Override
    public boolean equals(Object obj) {
        // Check if the input is even a Function
        if (!(obj instanceof Function)) {
            return false;
        }

        // Convert the input to type function
        Function f = (Function)obj;

        // If the inputted Function is not a BinaryOp, return false
        if (!(f instanceof BinaryOp)) {
            return false;
        }

        // Since the above did not fire, convert the input to a BinaryOp
        BinaryOp binary = (BinaryOp) f;

        // Compare the left operands
        if (!(this.getLeftOperand().equals(binary.getLeftOperand()))) {
            return false;
        }

        // Compare the right operands
        if (!(this.getRightOperand().equals(binary.getRightOperand()))) {
            return false;
        }

        // Compare the operator
        if (!(this.getOperator().equals(binary.getOperator()))) {
            return false;
        }

        // If the code hasn't returned false, then the BinaryOps are the same
        return true;
    }

    /**
     * Returns the String representation of the binary operation.
     * 
     * @return A formatted String representation of the operation
     */
    @Override
    public String toString() {
        // Declare two strings for the operands
        String leftOperand;
        String rightOperand;

        // Gather the String representation of the operator
        String operator = this.getOperator().getSymbol();

        // If the left operand is a BinaryOp, place it in parentheses
        if (this.getLeftOperand() instanceof BinaryOp) {
            StringBuilder delimiter = new StringBuilder("(");
            delimiter.append(this.getLeftOperand().toString());
            delimiter.append(")");

            // Convert the operand with parentheses to a String
            leftOperand = delimiter.toString();
        }

        // Otherwise, directly convert the left operand to a string
        else {
            leftOperand = this.getLeftOperand().toString();
        }

        /*
         * If the right operator is a binary op with a different operand
         * than this instance, place it in parentheses
         */
        if (this.getRightOperand() instanceof BinaryOp) {
            // Typecast to a BinaryOp as the conditional passed
            BinaryOp binary = (BinaryOp) this.getRightOperand();

            // If the operator of the right side is different than this instance, parenthesize
            if (!(binary.getOperator().equals(this.getOperator()))) {
                StringBuilder delimiter = new StringBuilder("(");
                delimiter.append(this.getRightOperand().toString());
                delimiter.append(")");

                // Convert the operand with parentheses to a String
                rightOperand = delimiter.toString();
            }

            // Otherwise, directly convert to a String
            else {
                rightOperand = this.getRightOperand().toString();
            }
        }

        // Otherwise, directly convert the right operand to a String
        else {
            rightOperand = this.getRightOperand().toString();
        }

        // Combine the three Strings to create the desired output
        StringBuilder result = new StringBuilder();
        result.append(leftOperand);
        result.append(" ");
        result.append(operator);
        result.append(" ");
        result.append(rightOperand);

        // Return the competed expression as a String
        return result.toString();
    }

    /**
     * Computes the value of two constant Functions in a BinaryOp.
     * 
     * @return The computed value of the BinaryOp
     * @throws UnsupportedOperationException If an input is expected
     */
    @Override
    public double value() throws UnsupportedOperationException {
        // Try to compute the actual value
        try {
            // Calculate the left and right operands
            double leftOperand = this.getLeftOperand().value();
            double rightOperand = this.getRightOperand().value();

            // A switch block to compute the BinaryOp based on the operator
            switch (this.getOperator()) {
                // Step in if Addition will occur
                case PLUS:
                    return leftOperand + rightOperand;

                // Step in if Subtraction will occur
                case SUB:
                    return leftOperand - rightOperand;

                // Step in if Multiplication will occur
                case MULT:
                    return leftOperand * rightOperand;

                // Step in if Division will occur
                case DIV:
                    return leftOperand / rightOperand;

                // Throw an unsupported exception by default
                default:
                    throw new UnsupportedOperationException("One or more operands require an input!");
            }
        } catch (Exception e) {
            throw new UnsupportedOperationException("One or more operands require an input!");
        }
    }

    /**
     * Computes the value of two functions at an inputted value.
     * 
     * @param x A double value to evaluate the BinaryOp at
     * @return A double that is equal to the evaluated BinaryOp
     */
    @Override
    public double value(double x) {
        // Calculate the left and right operands
        double leftOperand = this.getLeftOperand().value(x);
        double rightOperand = this.getRightOperand().value(x);

        // A switch block to compute the BinaryOp based on the operator
        switch (this.getOperator()) {
            // Step in if Addition will occur
            case PLUS:
                return leftOperand + rightOperand;

            // Step in if Subtraction will occur
            case SUB:
                return leftOperand - rightOperand;

            // Step in if Multiplication will occur
            case MULT:
                return leftOperand * rightOperand;

            // Default is division as it is the last case checked
            default:
                return leftOperand / rightOperand;
        }
    }

    /**
     * Computes the derivative of a BinaryOp.
     * 
     * @return A Function that represents the derivative of its call.
     */
    @Override
    public Function derivative() {
        // Compute the derivatives of both operands for ease of use
        Function leftDerivative = this.getLeftOperand().derivative();
        Function rightDerivative = this.getRightOperand().derivative();

        // A switch statement to handle the various ways derivatives occur
        switch (this.getOperator()) {
            // Step in for an addition operator
            case PLUS:
                return new BinaryOp(Op.PLUS, leftDerivative, rightDerivative);

            // Step in for a subtraction operation
            case SUB:
                return new BinaryOp(Op.SUB, leftDerivative, rightDerivative);

            // Step in for product rule
            case MULT:
                // Compute the left product of the sum
                BinaryOp leftProductSum = new BinaryOp(Op.MULT, leftDerivative, this.getRightOperand());

                // Compute the right product of the sum
                BinaryOp rightProductSum = new BinaryOp(Op.MULT, this.getLeftOperand(), rightDerivative);

                // Return the completed product rule
                return new BinaryOp(Op.PLUS, leftProductSum, rightProductSum);

            // Step in for quotient rule by default as no other branches caught
            default:
                // Compute the left product of the numerator
                BinaryOp leftProductNum = new BinaryOp(Op.MULT, leftDerivative, this.getRightOperand());

                // Compute the right product of the numerator
                BinaryOp rightProductNum = new BinaryOp(Op.MULT, this.getLeftOperand(), rightDerivative);

                // Compute the numerator
                BinaryOp numerator = new BinaryOp(Op.SUB, leftProductNum, rightProductNum);

                // Compute the denominator as a Polynomial
                Polynomial denominator = new Polynomial(this.getRightOperand(), 2);

                // Return the full quotient rule
                return new BinaryOp(Op.DIV, numerator, denominator);
        }
    }
}