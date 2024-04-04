package projectThree;

// Needed JUnit imports
import org.junit.*;
import static org.junit.Assert.*;

// Needed enum imports
import projectThree.BinaryOp.Op;
import projectThree.UnaryOperation.Unary;

/**
 * The Testing class for the Calculator OO Project.
 * Unused testers are suppressed for readability during coding.
 * Most tests expect UnsupportedOperationException due to the value() method
 * throwing it when needed.
 * 
 * @author Trevor Swan
 * @version CSDS132 - Spring 2024
 */
@SuppressWarnings({"unused", "unlikely-arg-type"})
public class CalculatorTest {
    // This field is to be used when wanting false assertions from .equals() calls
    String neqTester = new String("i am not a function :(");

    // These fields are to be used with the variable tests
    Variable varOne;
    Variable varTwo;

    /**
     * This helper method creates Variable Test Objects and stores them in the
     * Variable fields stored in this class.
     */
    private void varTesters() {
        varOne = new Variable();
        varTwo = new Variable();
    }

    /**
     * This JUnit Method tests a variety of Variable Objects. There is not a
     * standard test 0, 1, many for this because the variable must always be x. And
     * this variable is also immutable. 
     */
    @Test(expected = UnsupportedOperationException.class)
    public void variableTest() {
        varTesters();
        assertFalse(varOne.equals(neqTester)); // Test not equals
        assertTrue(varOne.equals(varTwo)); // Test equals
        assertEquals("x", varOne.toString()); // Test toString
        varOne.value(); // An exception should be thrown
        assertEquals(0, varOne.value(0), 0); // Test value of 0
        assertEquals(1, varOne.value(1), 0); // Test value of 1
        assertEquals(1, varOne.derivative()); // Test the derivative
    }

    // These fields are to be used with the Number tests
    Number numOne;
    Number numTwo;
    Number numThree;

    /**
     * This helper method creates Number Test Objects and stores them in the
     * Number fields stored in this class.
     */
    private void numTesters() {
        numOne = new Number(0);
        numTwo = new Number(0);
        numThree = new Number(1);
    }

    /**
     * This JUnit Method tests a variety of Number Object's equals and getter methods.
     */
    @Test
    public void numberEqualsTest() {
        numTesters();
        assertEquals(0, numOne.getNumber(), 0); // Test getter for numeric value
        assertTrue(numOne.equals(numOne)); // Test equals with itself
        assertTrue(numOne.equals(numTwo)); // Test equals method with a Number of same value
        assertFalse(numOne.equals(numThree)); // Test equals method with different Number
        assertFalse(numOne.equals(neqTester)); // Test equals method with a non-Number
    }

    /**
     * This JUnit Method tests the remainder of Number Object's methods
     */
    @Test
    public void numberMethodTest() {
        numTesters();
        assertEquals("0.0", numOne.toString()); // Test numOne toString
        assertEquals("1.0", numThree.toString()); // Test numThree toString
        assertEquals(0, numOne.value(), 0); // Test numOne value()
        assertEquals(1, numThree.value(), 0); // Test numThree value()
        assertEquals(0, numOne.value(0), 0); // Test value(x) with identical input 
        assertEquals(1, numThree.value(Math.random()), 0); // Test value(x) with random input
        assertTrue(new Number(0).equals(numOne.derivative())); // Test derivative method
        assertTrue(new Number(0).equals(numThree.derivative())); 
        assertFalse(new Number(1).equals(numOne.derivative())); // Test an incorrect derivative for a Number
    }

    // These fields are to be used with the BinaryOp Tests
    BinaryOp binaryOne;
    BinaryOp binaryTwo;
    BinaryOp binaryThree;
    BinaryOp binaryFour;
    BinaryOp binaryFive;
    BinaryOp binarySix;
    BinaryOp binarySeven;
    BinaryOp binaryEight;
    BinaryOp binaryNine;
    BinaryOp binaryTen;
    BinaryOp binaryEleven;
    BinaryOp binaryTwelve;
    
    /**
     * This helper method creates BinaryOp Test Objects and stores them in the
     * BinaryOp fields stored in this class.
     */
    private void binaryTesters() {
        numTesters();
        varTesters();
        binaryOne = new BinaryOp(Op.PLUS, new Number(0), new Number(0)); // Test Zeros
        binaryTwo = new BinaryOp(Op.PLUS, numThree, numThree); // Test Ones
        binaryThree = new BinaryOp(Op.SUB, numThree, numThree); // Test Ones
        binaryFour = new BinaryOp(Op.MULT, numThree, numThree); // Test Ones
        binaryFive = new BinaryOp(Op.DIV, numThree, numThree); // Test Ones
        binarySix = new BinaryOp(Op.PLUS, new Number(5), numThree); // Test Many
        binarySeven = new BinaryOp(Op.SUB, new Number(5), numThree); // Test Many
        binaryEight = new BinaryOp(Op.MULT, new Number(5), new Number(2.3)); // Test many
        binaryNine = new BinaryOp(Op.DIV, new Number(5), new Number(2)); // Test many
        binaryTen = new BinaryOp(Op.DIV, new Number(4), new Number(2)); // Change left op
        binaryEleven = new BinaryOp(Op.DIV, new Number(5), new Number(6)); // Change right op
        binaryTwelve = new BinaryOp(Op.DIV, new Number(5), new Number(6)); // Identical BinaryOp
    }

    /**
     * This JUnit method tests the NullPointerException Constructor for BinaryOp
     */
    @Test (expected = NullPointerException.class)
    public void binaryNullTest() {
        binaryTesters();
        new BinaryOp(null, binaryFour, binaryFive); // null in first input
        new BinaryOp(Op.DIV, null, binaryFive); // null in middle input
        new BinaryOp(Op.DIV, binaryFour, null); // null in last input
        new BinaryOp(null, null, null); // null in all inputs
    }

    /**
     * This JUnit Method tests a variety of BinaryOp Objects.
     */
    @Test
    public void binaryOperatorTest() {
        binaryTesters();
        // Test operator getter methods
        assertEquals(Op.PLUS, binaryTwo.getOperator()); 
        assertEquals(Op.SUB, binaryThree.getOperator());
        assertEquals(Op.MULT, binaryFour.getOperator());
        assertEquals(Op.DIV, binaryFive.getOperator());
        
        // Test operand getter methods
        assertEquals(new Number(5), binaryEight.getLeftOperand());
        assertEquals(new Number(2.3), binaryEight.getRightOperand());
    }

    /**
     * This JUnit method tests the equals method of BinaryOp
     */
    @Test
    public void binaryEqualsTest() {
        binaryTesters();
        assertFalse(binaryOne.equals(neqTester)); // Test a non Function Object
        assertFalse(binaryOne.equals(new Number(2))); // Test a non BinaryOp Object
        assertFalse(binaryNine.equals(binaryTen)); // Test a BinaryOp with different left Operands
        assertFalse(binaryNine.equals(binaryEleven)); // Test a BinaryOp with different right operands
        assertFalse(binaryTwo.equals(binaryThree)); // Test a BinaryOp with a different operator
        assertTrue(binaryEleven.equals(binaryTwelve)); // Test with equivalent BinaryOps
    }

    /**
     * This helper method declares BinaryOps that have nested Types
     */
    private void binaryNested() {
        binaryOne = new BinaryOp(Op.PLUS, new Variable(), new Number(0)); // Test 0
        binaryTwo = new BinaryOp(Op.SUB, new Number(1), new Variable()); // Test 1
        binaryThree = new BinaryOp(Op.DIV, new Number(3), new Variable()); // Test Many
        binaryFour = new BinaryOp(Op.MULT, new Variable(), new Variable()); // Test Many
        binaryFive = new BinaryOp(Op.MULT, new Number(4), new BinaryOp(Op.MULT, new Number(3), new Variable())); // Nested same op
        binarySix = new BinaryOp(Op.MULT, new Variable(), new BinaryOp(Op.DIV, new Number(1), new Variable())); // Nested different op
        binarySeven = new BinaryOp(Op.DIV, new BinaryOp(Op.DIV, new Variable(), new Number(3)), new Number(5)); // Nested BinaryOp first same
        binaryEight = new BinaryOp(Op.DIV, new BinaryOp(Op.SUB, new Number(7), new Number(9)), new Variable()); // Nested BinaryOp first different
        binaryNine = new BinaryOp(Op.PLUS, new Number(1), new Number(1)); // Just a regular Numeric Only BinaryOp
        binaryTen = new BinaryOp(Op.MULT, new BinaryOp(Op.PLUS, new Number(2), new Variable()), new Variable()); // MULT and Nested PLUS
        binaryEleven = new BinaryOp(Op.MULT, new BinaryOp(Op.SUB, new Number(3), new Variable()), new Variable()); // MULT and Nested SUB

        // Create placeholders to prevent a large line
        BinaryOp plusHolder = new BinaryOp(Op.PLUS, new Number(2), new Variable());
        BinaryOp subHolder = new BinaryOp(Op.SUB, new Number(3), new Variable());
        binaryTwelve = new BinaryOp(Op.DIV,  plusHolder, subHolder); // DIV and Nested PLUS and SUB
    }

    /**
     * This JUnit method tests the toString implementation in BinaryOp
     */
    @Test
    public void binaryToStringTest() {
        binaryNested();
        assertEquals("1.0 + 1.0", binaryNine.toString()); // Normal BinaryOp Conversion
        assertEquals("x + 0.0", binaryOne.toString()); // Variable + Number
        assertEquals("1.0 - x", binaryTwo.toString()); // Number - Variable
        assertEquals("3.0 / x", binaryThree.toString()); // Number / Variable
        assertEquals("x * x", binaryFour.toString()); // Variable * Variable
        assertEquals("4.0 * 3.0 * x", binaryFive.toString()); // Nested BinaryOp that is on right with same Op
        assertEquals("x * (1.0 / x)", binarySix.toString()); // Nested BinaryOp that is on right with different Op
        assertEquals("(x / 3.0) / 5.0", binarySeven.toString()); // Nested BinaryOp on left with same Op
        assertEquals("(7.0 - 9.0) / x", binaryEight.toString()); // Nested BinaryOp on left with different Op
        assertEquals("(2.0 + x) / (3.0 - x)", binaryTwelve.toString()); // Two nested BinaryOps in a BinaryOp
    }

    /**
     * This JUnit method tests the value implementation in BinaryOp.
     * This method is only used to test Numeric values
     */
    @Test
    public void binaryNumericTest() {
        binaryTesters();
        // Test Operations on Numbers only
        assertEquals(0, binaryOne.value(), 0); // Test 0
        assertEquals(2, binaryTwo.value(), 0); // Test 1
        assertEquals(0, binaryThree.value(), 0);
        assertEquals(1, binaryFour.value(), 0);
        assertEquals(1, binaryFive.value(), 0);
        assertEquals(6, binarySix.value(), 0); // Test many
        assertEquals(4, binarySeven.value(), 0);
        assertEquals(5 * 2.3, binaryEight.value(), 0);
        assertEquals(5.0 / 2.0, binaryNine.value(), 0);
    }

    /**
     * This JUnit method tests the value implementation in BinaryOp.
     * This method makes use of Variable types as well
     */
    @Test(expected = UnsupportedOperationException.class)
    public void binaryValueTest() {
        binaryNested(); 
        binaryOne.value(); // Test Exception Handler
        assertEquals(2, binaryNine.value(0), 0); // Test Zero
        assertEquals(0, binaryOne.value(0), 0); 
        assertEquals(0, binaryTwo.value(1), 0); // Test One
        assertEquals(-1, binaryThree.value(4), 0);
        assertEquals(16, binaryFour.value(4), 0); // Test Many 
        assertEquals(24, binaryFive.value(2), 0);
        assertEquals(12, binarySix.value(3), 0);
        assertEquals(2.0 / 5.0, binarySeven.value(6), 0);
        assertEquals(-0.5, binaryEight.value(4), 0);
        assertEquals(15, binaryTen.value(3), 0);
        assertEquals(-4, binaryEleven.value(4), 0);
        assertEquals(7.0 / -2.0, binaryTwelve.value(5), 0);
    }

    /**
     * This JUnit method tests the derivative implementation in BinaryOp.
     * The test analyzes basic derivatives, including product and quotient rules.
     * String representations are occasionally used to test derivative output
     */
    @Test
    public void binaryDerivative() {
        binaryNested();
        // Create a temporary variable whose derivative is the same as itself
        BinaryOp binaryZero = new BinaryOp(Op.PLUS, new Number(0), new Number(0));
        assertTrue(binaryZero.equals(binaryNine.derivative())); // Test Zero Variables
        assertTrue(new BinaryOp(Op.PLUS, new Number(1), new Number(0)).equals(binaryOne.derivative())); // Test one
        assertTrue(new BinaryOp(Op.SUB, new Number(0), new Number(1)).equals(binaryTwo.derivative()));
        assertTrue(new BinaryOp(Op.PLUS, new BinaryOp(Op.MULT, new Number(0), new Variable()), 
        new BinaryOp(Op.MULT, new Number(4), new Number(1)))
        .equals(new BinaryOp(Op.MULT, new Number(4), new Variable()).derivative())); // Basic Product Rule
        assertEquals("((0.0 * x) - (3.0 * 1.0)) / x^2.0", binaryThree.derivative().toString()); // Basic Quotient Rule
        assertFalse(binaryThree.derivative().equals(neqTester)); // Known False Assertion
        assertEquals("(1.0 * x) + (x * 1.0)", binaryFour.derivative().toString()); // Test Many (multiple nested)
        assertEquals("(0.0 * 3.0 * x) + (4.0 * ((0.0 * x) + (3.0 * 1.0)))", binaryFive.derivative().toString());
        assertEquals("(1.0 * (1.0 / x)) + (x * (((0.0 * x) - (1.0 * 1.0)) / x^2.0))", binarySix.derivative().toString());
        assertEquals("(((((1.0 * 3.0) - (x * 0.0)) / 3.0^2.0) * 5.0) - ((x / 3.0) * 0.0)) / 5.0^2.0", binarySeven.derivative().toString());
        assertEquals("(((0.0 - 0.0) * x) - ((7.0 - 9.0) * 1.0)) / x^2.0", binaryEight.derivative().toString());
        assertEquals("((0.0 + 1.0) * x) + ((2.0 + x) * 1.0)", binaryTen.derivative().toString());
        assertEquals("((0.0 - 1.0) * x) + ((3.0 - x) * 1.0)", binaryEleven.derivative().toString());
        assertEquals("(((0.0 + 1.0) * (3.0 - x)) - ((2.0 + x) * (0.0 - 1.0))) / (3.0 - x)^2.0", binaryTwelve.derivative().toString());
    }

    // These fields are to be used with the Polynomial tests
    Polynomial polyOne;
    Polynomial polyTwo;
    Polynomial polyThree;
    Polynomial polyFour;
    Polynomial polyFive;
    Polynomial polySix;

    /**
     * This helper method creates Polynomial Test Objects and stores them in the
     * Polynomial fields stored in this class. This method is for basic tests only.
     */
    private void polyTesters() {
        polyOne = new Polynomial(new Number(0), 0);
        polyTwo = new Polynomial(new Number(1), 1);
        polyThree = new Polynomial(new Number(4), 4);
        polyFour = new Polynomial(new Variable(), 0);
        polyFive = new Polynomial(new Variable(), 1);
        polySix = new Polynomial(new Variable(), 4);
    }
    
    /**
     * This JUnit method tests the NullPointerException Constructor for Polynomial
     */
    @Test (expected = NullPointerException.class)
    public void polynomialNullTest() {
        new Polynomial(null, 4);
    }

    /**
     * This JUnit Method tests a variety of Polynomial Objects Getter Methods.
     */
    @Test //(expected = UnsupportedOperationException.class)
    public void polynomialGetters() {
        polyTesters();
        assertTrue(new Number(0).equals(polyOne.getOperand())); // Test 0
        assertEquals(0, polyOne.getPower(), 0);
        assertTrue(new Number(1).equals(polyTwo.getOperand())); // Test 1 
        assertEquals(1, polyTwo.getPower(), 0);
        assertTrue(new Number(4).equals(polyThree.getOperand())); // Test Many
        assertEquals(4, polyThree.getPower(), 0);
        assertTrue(new Variable().equals(polyFour.getOperand())); // Test Operand getter for a Nested Variable
        assertEquals(0, polyFour.getPower(), 0); // Test 0
        assertEquals(1, polyFive.getPower(), 0); // Test 1
        assertEquals(4, polySix.getPower(), 0); // Test Many
    }

    /**
     * This helper method creates Log Test Objects and stores them in the Log
     * fields stored in this class.
     */
    private void logTesters() {
    }

    /**
     * This JUnit Method tests a variety of Log Objects.
     */
    @Test //(expected = UnsupportedOperationException.class)
    public void logTest() {
        logTesters();
    }

    /**
     * This helper method creates Exp Test Objects and stores them in the Exp
     * fields stored in this class.
     */
    private void expTesters() {     
    }

    /**
     * This JUnit Method tests a variety of Exp Objects.
     */
    @Test //(expected = UnsupportedOperationException.class)
    public void expTest() {
        expTesters();
    }

    /**
     * This helper method creates Sin Test Objects and stores them in the Sin
     * fields stored in this class.
     */
    private void sinTesters() {     
    }

    /**
     * This JUnit Method tests a variety of Sin Objects.
     */
    @Test //(expected = UnsupportedOperationException.class)
    public void sinTest() {
        sinTesters();
    }

    /**
     * This helper method creates Cos Test Objects and stores them in the Cos
     * fields stored in this class.
     */
    private void cosTesters() {
    }

    /**
     * This JUnit Method tests a variety of Cos Objects.
     */
    @Test //(expected = UnsupportedOperationException.class)
    public void cosTest() {
        cosTesters();
    }
}
