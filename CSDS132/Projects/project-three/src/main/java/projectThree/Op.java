package projectThree;

enum Op {
    // Required operations for calculator
    PLUS("+"),
    SUB("-"),
    MULT("*"),
    DIV("/");

    // Immutable String to represent operation
    private final String operator;

    /**
     * A private enum constructor to assign a symbol to an operator name
     * 
     * @param operator the String representation of the operator
     */
    private Op(String operator) {
        this.operator = operator;
    }

    /**
     * A method to return the symbol equivalent for an operation
     * 
     * @return The String representation of an operator
     */
    public String getOperator() {
        return this.operator;
    }
}
