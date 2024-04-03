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
        assertFalse(new Number(1).equals(numOne.derivative())); // Test an incorrect derivative for a Number
    }

    // These fields are to be used with the BinaryOp Tests
    BinaryOp binaryOne;
    BinaryOp binaryTwo;
    BinaryOp binaryThree;
    BinaryOp binaryFour;
    BinaryOp binaryFive;
    BinaryOp binarySix;
    
    /**
     * This helper method creates BinaryOp Test Objects and stores them in the
     * BinaryOp fields stored in this class.
     */
    private void binaryTesters() {
        numTesters();
        varTesters();
        binaryOne = new BinaryOp(Op.PLUS, new Number(0), new Number(0));
        binaryTwo = new BinaryOp(Op.PLUS, numThree, numThree);

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
    @Test //(expected = UnsupportedOperationException.class)
    public void binaryOpTest() {
        binaryTesters();
    }

    /**
     * This helper method creates Polynomial Test Objects and stores them in the
     * Polynomial fields stored in this class.
     */
    private void polyTesters() {
        
    }

    /**
     * This JUnit Method tests a variety of Polynomial Objects.
     */
    @Test //(expected = UnsupportedOperationException.class)
    public void polynomialTest() {
        polyTesters();
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
