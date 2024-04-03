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
@SuppressWarnings("unused")
public class CalculatorTest {
    // This is a collection of fields used for testing in this class.
    Function testerOne;
    Function testerTwo;
    Function testerThree;
    Function testerFour;
    Function testerFive;
    Function testerSix;

    // This field is to be used when wanting false assertions from .equals() calls
    Number neqTester = new Number(-1.2346);

    /**
     * This helper method creates Variable Test Objects and stores them in the
     * Function fields stored in this class.
     */
    private void varTesters() {
        testerOne = new Variable();
        testerTwo = new Variable();
    }

    /**
     * This JUnit Method tests a variety of Variable Objects. There is not a
     * standard test 0, 1, many for this because the variable must always be x. And
     * this variable is also immutable. 
     */
    @Test(expected = UnsupportedOperationException.class)
    public void variableTest() {
        varTesters();
        assertFalse(testerOne.equals(neqTester)); // Test not equals
        assertTrue(testerOne.equals(testerTwo)); // Test equals
        assertEquals("x", testerOne.toString()); // Test toString
        testerOne.value(); // An exception should be thrown
        assertEquals(0, testerOne.value(0), 0); // Test value of 0
        assertEquals(1, testerOne.value(1), 0); // Test value of 1
        assertEquals(1, testerOne.derivative()); // Test the derivative
    }

    /**
     * This helper method creates Number Test Objects and stores them in the
     * Function fields stored in this class.
     */
    private void numTesters() {
        testerOne = new Number(0);
        testerTwo = new Number(1);
        testerThree = new Number(2);
    }

    /**
     * This JUnit Method tests a variety of Number Objects.
     */
    @Test //(expected = UnsupportedOperationException.class)
    public void numberTest() {
        numTesters();
    }

    /**
     * This helper method creates BinaryOp Test Objects and stores them in the
     * Function fields stored in this class.
     */
    private void binaryTesters() {
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
     * Function fields stored in this class.
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
     * This helper method creates Log Test Objects and stores them in the Function
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
     * This helper method creates Exp Test Objects and stores them in the Function
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
     * This helper method creates Sin Test Objects and stores them in the Function
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
     * This helper method creates Cos Test Objects and stores them in the Function
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
