package projectThree;

// Needed JUnit imports
import org.junit.*;
import org.junit.experimental.theories.suppliers.TestedOn;
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
        assertTrue(varOne.equals(varTwo)); // Test equals
        assertFalse(varOne.equals(neqTester)); // Test not equals
        assertEquals("x", varOne.toString()); // Test toString
        assertEquals(0, varOne.value(0), 0); // Test value of 0
        assertEquals(1.0, varOne.value(1), 0); // Test value of 1
        assertEquals(new Number(1), varOne.derivative()); // Test the derivative
        varOne.value(); // An exception should be thrown
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
    BinaryOp binaryZero = new BinaryOp(Op.PLUS, new Number(0), new Number(0)); // Zeros!
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
        assertEquals(2, binaryNine.value(0), 0); // Test Zero
        assertEquals(0, binaryOne.value(0), 0); 
        assertEquals(0, binaryTwo.value(1), 0); // Test One
        assertEquals(0.75, binaryThree.value(4), 0);
        assertEquals(16, binaryFour.value(4), 0); // Test Many 
        assertEquals(24, binaryFive.value(2), 0);
        assertEquals(1, binarySix.value(3), 0);
        assertEquals(2.0 / 5.0, binarySeven.value(6), 0);
        assertEquals(-0.5, binaryEight.value(4), 0);
        assertEquals(15, binaryTen.value(3), 0);
        assertEquals(-4, binaryEleven.value(4), 0);
        assertEquals(7.0 / -2.0, binaryTwelve.value(5), 0);
        binaryOne.value(); // Test Exception Handler
    }

    /**
     * This JUnit method tests the derivative implementation in BinaryOp.
     * The test analyzes basic derivatives, including product and quotient rules.
     * String representations are occasionally used to test derivative output
     */
    @Test
    public void binaryDerivativeTest() {
        binaryNested();
        // Create a temporary variable whose derivative is the same as itself
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
        assertEquals("(((((1.0 * 3.0) - (x * 0.0)) / 3.0^2.0) * 5.0) - ((x / 3.0) * 0.0)) / 5.0^2.0",
                binarySeven.derivative().toString());
        assertEquals("(((0.0 - 0.0) * x) - ((7.0 - 9.0) * 1.0)) / x^2.0", binaryEight.derivative().toString());
        assertEquals("((0.0 + 1.0) * x) + ((2.0 + x) * 1.0)", binaryTen.derivative().toString());
        assertEquals("((0.0 - 1.0) * x) + ((3.0 - x) * 1.0)", binaryEleven.derivative().toString());
        assertEquals("(((0.0 + 1.0) * (3.0 - x)) - ((2.0 + x) * (0.0 - 1.0))) / (3.0 - x)^2.0",
                binaryTwelve.derivative().toString());
    }

    // These fields are to be used with the Polynomial tests
    Polynomial polyZero = new Polynomial(new Number(0), 0); // Zeros!
    Polynomial polyOne;
    Polynomial polyTwo;
    Polynomial polyThree;
    Polynomial polyFour;
    Polynomial polyFive;
    Polynomial polySix;
    Polynomial polySeven;
    Polynomial polyEight;
    Polynomial polyNine;
    Polynomial polyTen;
    BinaryOp polyEleven;

    /**
     * This helper method creates Polynomial Test Objects and stores them in the
     * Polynomial fields stored in this class. This method is for basic tests only.
     */
    private void polySimpleTesters() {
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
    @Test
    public void polynomialGettersTest() {
        polySimpleTesters();
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
     * This helper method creates complex BinaryOp and Polynomial expressions to be tested.
     */
    private void polyComplexTesters() {
        polyOne = new Polynomial(new Polynomial(new Number(2), 2), 2); // Nested Numeric Polynomial
        polyTwo = new Polynomial(new Polynomial(new Variable(), 2), -3);
        polyThree = new Polynomial(new Polynomial(new Polynomial(new Variable(), 2), -0.5), 4); // Double Nesting
        binaryFour = new BinaryOp(Op.PLUS, new Polynomial(new Variable(), 2), new Number(2)); // Polynomial inside BinaryOp
        binaryFive = new BinaryOp(Op.SUB, new Polynomial(new Variable(), 3), new Number(10));
        binarySix = new BinaryOp(Op.MULT, new Number(4), new Polynomial(new Variable(), 6));
        binarySeven = new BinaryOp(Op.DIV, new Number(-2), new Polynomial(new Variable(), 4));
        polyEight = new Polynomial(new Polynomial(new BinaryOp(Op.MULT, new Number(2),
                new Variable()), 2.3), 2.4); // BinaryOp inside Polynomial
        polyNine = new Polynomial(new Polynomial(new BinaryOp(Op.MULT, new Number(2),
                new Polynomial(new Variable(), 2)), 1), 2); // Tons of Nesting
        polyTen = new Polynomial(new Polynomial(new BinaryOp(Op.DIV, new Number(2),
                new Polynomial(new Variable(), 2)), 3), 4); // Tons of Nesting
        polyEleven = new BinaryOp(Op.PLUS, new Polynomial(new Variable(), 2),
                new BinaryOp(Op.PLUS, new BinaryOp(Op.MULT, new Number(3), new Polynomial(new Variable(), 3)),
                new BinaryOp(Op.PLUS, new Polynomial(new Variable(), 4),
                new BinaryOp(Op.MULT, new Number(-1), new Polynomial(new Variable(), 5))))); // Long Polynomial Expression
    }

    /**
     * This JUnit Method tests a variety of Function Objects with true and nested Polynomial types.
     * This tests the equals method of these Objects
     */
    @Test
    public void polynomialEqualsTest() {
        polyComplexTesters();
        assertTrue(polyZero.equals(polyZero)); // Test 0
        assertTrue(new Polynomial(new Number(1), 1).equals(new Polynomial(new Number(1), 1))); // Test 1
        assertFalse(polyOne.equals(neqTester)); // Known False Assertions
        assertFalse(polyOne.equals(new Polynomial(new Polynomial(new Number(2), 2), 3))); 
        assertFalse(polyOne.equals(new Polynomial(new Polynomial(new Number(2), 4), 2))); 
        assertFalse(polyOne.equals(new Polynomial(new Polynomial(new Number(5), 2), 2)));
        assertTrue(polyOne.equals(new Polynomial(new Polynomial(new Number(2), 2), 2))); // Test Many
        assertTrue(polyThree.equals(new Polynomial(new Polynomial(new Polynomial(new Variable(), 2), -0.5), 4)));
        assertTrue(binaryFour.getLeftOperand().equals(new Polynomial(new Variable(), 2)));
    }

    /**
     * This JUnit Method tests complex Polynomials.
     * This tests the toString Method of these Objects
     */
    @Test
    public void polynomialToStringTest() {
        polyComplexTesters();
        assertEquals("0.0^0.0", polyZero.toString()); // Test 0
        assertEquals("1.0^1.0", new Polynomial(new Number(1), 1).toString()); // Test 1
        assertNotEquals(neqTester, new Polynomial(new Number(1), 1).toString()); // Known False Assertion
        assertEquals("2.0^2.0^2.0", polyOne.toString()); // Test Many
        assertEquals("x^2.0^-3.0", polyTwo.toString());
        assertEquals("x^2.0^-0.5^4.0", polyThree.toString());
        assertEquals("x^2.0 + 2.0", binaryFour.toString());
        assertEquals("x^3.0 - 10.0", binaryFive.toString());
        assertEquals("4.0 * x^6.0", binarySix.toString());
        assertEquals("-2.0 / x^4.0", binarySeven.toString());
        assertEquals("(2.0 * x)^2.3^2.4", polyEight.toString());
        assertEquals("(2.0 * x^2.0)^1.0^2.0", polyNine.toString());
        assertEquals("(2.0 / x^2.0)^3.0^4.0", polyTen.toString());
        assertEquals("x^2.0 + (3.0 * x^3.0) + x^4.0 + (-1.0 * x^5.0)", polyEleven.toString()); // Long Polynomial Expression
    }

    /**
     * This JUnit Method tests complex Polynomials.
     * This tests the value Methods of these Objects
     */
    @Test(expected = UnsupportedOperationException.class)
    public void polynomialValueTest() {
        polyComplexTesters();
        assertEquals(1, polyZero.value(), 0); // Test 0
        assertEquals(1, polyZero.value(0), 0);
        assertEquals(1, polyZero.value(1), 0);
        assertEquals(1, polyZero.value(10), 0);
        assertEquals(16, polyOne.value(), 0);
        assertEquals(16, polyOne.value(0), 0); // Test 1
        assertEquals(16, polyOne.value(1), 0);
        assertEquals(16, polyOne.value(10), 0);
        assertEquals(1, polyTwo.value(1), 0); // Test Many
        assertEquals(0.0625, polyThree.value(2), 0);
        assertEquals(11, binaryFour.value(3), 0);
        assertEquals(54, binaryFive.value(4), 0);
        assertEquals(62500, binarySix.value(5), 0);
        assertEquals(-2, binarySeven.value(-1), 0);
        assertEquals(324, polyNine.value(-3), 0);
        assertEquals(0, polyTen.value(-4), 0.0001); // Rounding error
        assertEquals(12, polyEleven.value(2), 0); 

        assertTrue(Double.isNaN(polyEight.value(-2))); // Undefined expected

        polyTwo.value(); // Test Exception
    }

    /**
     * This JUnit Method tests complex Polynomials.
     * This tests the derivative Method of these Objects. 
     * String representations are used to check equality.
     */
    @Test
    public void polynomialDerivativesTest() {
        polyComplexTesters();
        assertEquals("0.0", polyZero.derivative().toString()); // Test 0
        assertEquals("1.0 * 0.0", new Polynomial(new Number(1), 1).derivative().toString()); // Test 1
        assertEquals("(2.0 * 2.0^2.0^1.0) * (2.0 * 2.0^1.0) * 0.0", polyOne.derivative().toString()); // Test Many
        assertEquals("(-3.0 * x^2.0^-4.0) * (2.0 * x^1.0) * 1.0", polyTwo.derivative().toString());
        assertEquals("(4.0 * x^2.0^-0.5^3.0) * (-0.5 * x^2.0^-1.5) * (2.0 * x^1.0) * 1.0", 
                polyThree.derivative().toString());
        assertEquals("((2.0 * x^1.0) * 1.0) + 0.0", binaryFour.derivative().toString());
        assertEquals("((3.0 * x^2.0) * 1.0) - 0.0", binaryFive.derivative().toString());
        assertEquals("(0.0 * x^6.0) + (4.0 * (6.0 * x^5.0) * 1.0)", binarySix.derivative().toString());
        assertEquals("((0.0 * x^4.0) - (-2.0 * (4.0 * x^3.0) * 1.0)) / x^4.0^2.0",
                binarySeven.derivative().toString());
        assertEquals("(2.4 * (2.0 * x)^2.3^1.4) * (2.3 * (2.0 * x)^1.2999999999999998) * ((0.0 * x) + (2.0 * 1.0))",
                polyEight.derivative().toString());
        assertEquals("(2.0 * (2.0 * x^2.0)^1.0^1.0) * 1.0 * ((0.0 * x^2.0) + (2.0 * (2.0 * x^1.0) * 1.0))",
                polyNine.derivative().toString());
        assertEquals("(4.0 * (2.0 / x^2.0)^3.0^3.0) * (3.0 * (2.0 / x^2.0)^2.0) * (((0.0 * x^2.0) - (2.0 * (2.0 * x^1.0) * 1.0)) / x^2.0^2.0)",
                polyTen.derivative().toString());

        // The result of the derivative of polyEleven when converted to a String
        String longExpression = "((2.0 * x^1.0) * 1.0) + ((0.0 * x^3.0) + (3.0 * (3.0 * x^2.0) * 1.0)) + ((4.0 * x^3.0) * 1.0) + (0.0 * x^5.0) + (-1.0 * (5.0 * x^4.0) * 1.0)";
        assertEquals(longExpression, polyEleven.derivative().toString());
    }

    // These fields are to be used with the UnaryOperation tests
    UnaryOperation unaryOne;
    UnaryOperation unaryTwo;
    UnaryOperation unaryThree;
    UnaryOperation unaryFour; 

    /**
     * This helper method creates UnaryOperation Objects and stores them to fields.
     * These Objects are used to test the default implementations found in UnaryOperation.
     */
    private void unaryTesters() {
        unaryOne = new Log(new Variable());
        unaryTwo = new Exp(new Variable());
        unaryThree = new Sin(new Variable());
        unaryFour = new Cos(new Variable());
    }


    /**
     * This JUnit method tests the getter implementations in UnaryOperation.
     * This excludes equals, toString value(), and value(x).
     */
    @Test
    public void unaryTest() {
        unaryTesters();
        // Log operator/operand
        assertTrue(unaryOne.getOperator().equals(Unary.LOG));
        assertTrue(unaryOne.getOperand().equals(new Variable()));
        assertEquals("Log", unaryOne.getOperator().getUnaryName());
        assertFalse(unaryOne.getOperator().equals(neqTester));

        // Exp operator/operand
        assertTrue(unaryTwo.getOperator().equals(Unary.EXP));
        assertTrue(unaryTwo.getOperand().equals(new Variable()));
        assertEquals("Exp", unaryTwo.getOperator().getUnaryName());
        assertFalse(unaryTwo.getOperator().equals(neqTester));

        // Sin operator/operand
        assertTrue(unaryThree.getOperator().equals(Unary.SIN));
        assertTrue(unaryThree.getOperand().equals(new Variable()));
        assertEquals("Sin", unaryThree.getOperator().getUnaryName());
        assertFalse(unaryThree.getOperator().equals(neqTester));

        // Cos operator/operand
        assertTrue(unaryFour.getOperator().equals(Unary.COS));
        assertTrue(unaryFour.getOperand().equals(new Variable()));
        assertEquals("Cos", unaryFour.getOperator().getUnaryName());
        assertFalse(unaryFour.getOperator().equals(neqTester));
    }

    // These fields are for the Log Tests
    Log logNegative = new Log(new Number(-1)); // Negative Number!
    Log logZero = new Log(new Number(0)); // Zero!
    Log logOne;
    Log logTwo;
    Log logThree;
    Log logFour;
    Log logFive;
    BinaryOp logSix;

    /**
     * This helper method creates Log Test Objects and stores them in the Log
     * fields stored in this class.
     */
    private void logTesters() {
        logOne = new Log(new Number(1));
        logTwo = new Log(new Variable());
        logThree = new Log(new Polynomial(new Variable(), 2));
        logFour = new Log(new Polynomial(new Variable(), 3));
        logFive = new Log(new BinaryOp(Op.MULT, new Number(3), new Log(new Variable())));
        logSix = new BinaryOp(Op.PLUS, new Log(new Variable()), new Log(new Polynomial(new Log(new Variable()), 3)));
    }
        

    /**
     * This JUnit method tests the null point exception for Log
     */
    @Test(expected = NullPointerException.class) 
    public void nullLogTest() {
        unaryOne = new Log(null);
    }

    /**
     * This JUnit Method tests a variety of Log Objects.
     * This method specifically focuses on the equals method.
     */
    @Test
    public void logEqualsTest() {
        logTesters();
        unaryTesters();
        assertTrue(logZero.equals(new Log(new Number(0)))); // Test Zero
        assertTrue(logOne.equals(new Log(new Number(1)))); // Test One
        assertFalse(logOne.equals(neqTester)); // False Assertions
        assertFalse(logOne.equals(logSix));
        assertFalse(logOne.equals(unaryTwo));

        // Test Many
        assertFalse(logThree.equals(logFour)); // Nested Polynomials with different Powers
        assertTrue(logFive.equals(new Log(new BinaryOp(Op.MULT, new Number(3), new Log(new Variable()))))); // Nested BinaryOp

        // Long Expression
        Log leftOperand = new Log(new Variable());
        Log rightOperand = new Log(new Polynomial(new Log(new Variable()), 3));
        assertTrue(logSix.getLeftOperand().equals(leftOperand)); // Compare Left Operand
        assertTrue(logSix.getRightOperand().equals(rightOperand)); // Compare Right Operand
    }

    /**
     * This JUnit Method tests a variety of Log and nested Log Objects.
     * This method specifically focuses on the toString method.
     */
    @Test
    public void logToStringTest() {
        logTesters();
        assertEquals("Log[-1.0]", logNegative.toString()); // Test Negative
        assertEquals("Log[0.0]", logZero.toString()); // Test Zero
        assertEquals("Log[1.0]", logOne.toString()); // Test One
        assertEquals("Log[x]", logTwo.toString()); // Test Many
        assertEquals("Log[x^2.0]", logThree.toString());
        assertEquals("Log[x^3.0]", logFour.toString()); 
        assertEquals("Log[3.0 * Log[x]]", logFive.toString()); 
        assertEquals("Log[x] + Log[Log[x]^3.0]", logSix.toString()); 
    }

    /**
     * This JUnit Method tests a variety of Log and nested Log Objects.
     * This method specifically focuses on the value methods.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void logValueTest() {
        logTesters();
        assertTrue(Double.isNaN(logNegative.value())); // Test Negative
        assertTrue(Double.isNaN(logNegative.value(10)));
        assertTrue(Double.isInfinite(logZero.value())); // Test Zero
        assertTrue(Double.isInfinite(logZero.value(10)));
        assertEquals(0, logOne.value(), 0); // Test One
        assertEquals(0, logOne.value(10), 0);

        // Test Many - Margin of Error Included
        assertEquals(0.6931471806, logTwo.value(2), 0.0001);
        assertEquals(1.386294361, logThree.value(2), 0.0001);
        assertEquals(2.079441542, logFour.value(2), 0.0001);
        assertEquals(0.7320993681, logFive.value(2), 0.0001);
        assertEquals(-0.4063915812, logSix.value(2), 0.0001);

        logTwo.value(); // Test Exception Handler
    }

    /**
     * This JUnit Method tests a variety of Log and nested Log Objects.
     * This method specifically tests the derivative method.
     */
    @Test
    public void logDerivativeTest() {
        logTesters();
        assertEquals("0.0 / -1.0", logNegative.derivative().toString()); // Test Negative
        assertEquals("0.0 / 0.0", logZero.derivative().toString());// Test Zero - woah!
        assertEquals("0.0 / 1.0", logOne.derivative().toString());// Test One

        // Test Many
        assertEquals("1.0 / x", logTwo.derivative().toString());
        assertEquals("((2.0 * x^1.0) * 1.0) / x^2.0", logThree.derivative().toString());
        assertEquals("((3.0 * x^2.0) * 1.0) / x^3.0", logFour.derivative().toString());
        assertEquals("((0.0 * Log[x]) + (3.0 * (1.0 / x))) / (3.0 * Log[x])", logFive.derivative().toString());
        assertEquals("(1.0 / x) + (((3.0 * Log[x]^2.0) * (1.0 / x)) / Log[x]^3.0)", logSix.derivative().toString()); 
    }
    
    // These fields are to be used with the Exponential tests
    Exp expNegative = new Exp(new Number(-1)); // Negative Number!
    Exp expZero = new Exp(new Number(0)); // Zero!
    Exp expOne;
    Exp expTwo;
    Exp expThree;
    Exp expFour;
    Exp expFive;
    BinaryOp expSix;

    /**
     * This helper method creates Exp Test Objects and stores them in the Exp
     * fields stored in this class.
     */
    private void expTesters() {     
        expOne = new Exp(new Number(1));
        expTwo = new Exp(new Variable());
        expThree = new Exp(new Polynomial(new Variable(), 2));
        expFour = new Exp(new Polynomial(new Variable(), 3));
        expFive = new Exp(new BinaryOp(Op.MULT, new Number(3), new Exp(new Variable())));
        expSix = new BinaryOp(Op.PLUS, new Exp(new Variable()), new Exp(new Polynomial(new Exp(new Variable()), 3)));
    }

    /**
     * This JUnit method tests the null point exception for Exp
     */
    @Test(expected = NullPointerException.class) 
    public void nullExpTest() {
        unaryTwo = new Exp(null);
    }

    /**
     * This JUnit Method tests a variety of Exp Objects.
     * This method specifically focuses on the equals method.
     */
    @Test
    public void expEqualsTest() {
        expTesters();
        unaryTesters();
        assertTrue(expZero.equals(new Exp(new Number(0)))); // Test Zero
        assertTrue(expOne.equals(new Exp(new Number(1)))); // Test One
        assertFalse(expOne.equals(neqTester)); // False Assertions
        assertFalse(expOne.equals(expSix));
        assertFalse(expOne.equals(unaryTwo));

        // Test Many
        assertFalse(expThree.equals(logFour)); // Nested Polynomials with different Powers
        assertTrue(expFive.equals(new Exp(new BinaryOp(Op.MULT, new Number(3), new Exp(new Variable()))))); // Nested BinaryOp

        // Long Expression
        Exp leftOperand = new Exp(new Variable());
        Exp rightOperand = new Exp(new Polynomial(new Exp(new Variable()), 3));
        assertTrue(expSix.getLeftOperand().equals(leftOperand)); // Compare Left Operand
        assertTrue(expSix.getRightOperand().equals(rightOperand)); // Compare Right Operand
    }

    /**
     * This JUnit Method tests a variety of Exp and nested Exp Objects.
     * This method specifically focuses on the toString method.
     */
    @Test
    public void expToStringTest() {
        expTesters();
        assertEquals("Exp[-1.0]", expNegative.toString()); // Test Negative
        assertEquals("Exp[0.0]", expZero.toString()); // Test Zero
        assertEquals("Exp[1.0]", expOne.toString()); // Test One
        assertEquals("Exp[x]", expTwo.toString()); // Test Many
        assertEquals("Exp[x^2.0]", expThree.toString());
        assertEquals("Exp[x^3.0]", expFour.toString()); 
        assertEquals("Exp[3.0 * Exp[x]]", expFive.toString()); 
        assertEquals("Exp[x] + Exp[Exp[x]^3.0]", expSix.toString()); 
    }

    /**
     * This JUnit Method tests a variety of Exp and nested Exp Objects.
     * This method specifically focuses on the value methods.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void expValueTest() {
        expTesters();
        assertEquals(0.3678794412, expNegative.value(), 0.0001); // Test Negative
        assertEquals(0.3678794412, expNegative.value(10), 0.0001);
        assertEquals(1, expZero.value(), 0); // Test Zero
        assertEquals(1, expZero.value(10), 0);
        assertEquals(Math.E, expOne.value(), 0); // Test One
        assertEquals(Math.E, expOne.value(10), 0);
        
        // Test Many
        assertEquals(7.389056099, expTwo.value(2), 0.0001);
        assertEquals(54.59815003, expThree.value(2), 0.0001);
        assertEquals(2980.957987, expFour.value(2), 0.0001);
        assertEquals(3480.201542, expFive.value(1), 0.0001);
        assertEquals(90.03255445, expSix.value(0.5), 0.0001);

        expTwo.value(); // Test Exception Handler
    }

    /**
     * This JUnit Method tests a variety of Exp and nested Exp Objects.
     * This method specifically tests the derivative method.
     */
    @Test
    public void expDerivativeTest() {
        expTesters();
        assertEquals("0.0 * Exp[-1.0]", expNegative.derivative().toString()); // Test Negative
        assertEquals("0.0 * Exp[0.0]", expZero.derivative().toString()); // Test Zero
        assertEquals("0.0 * Exp[1.0]", expOne.derivative().toString()); // Test One

        // Test Many
        assertEquals("1.0 * Exp[x]", expTwo.derivative().toString());
        assertEquals("((2.0 * x^1.0) * 1.0) * Exp[x^2.0]", expThree.derivative().toString());
        assertEquals("((3.0 * x^2.0) * 1.0) * Exp[x^3.0]", expFour.derivative().toString());
        assertEquals("((0.0 * Exp[x]) + (3.0 * 1.0 * Exp[x])) * Exp[3.0 * Exp[x]]",
                expFive.derivative().toString());
        assertEquals("(1.0 * Exp[x]) + (((3.0 * Exp[x]^2.0) * 1.0 * Exp[x]) * Exp[Exp[x]^3.0])",
                expSix.derivative().toString());
    }

    // These fields are to be used with the Sine tests
    Sin sinZero = new Sin(new Number(0)); // Zero!
    Sin sinOne;
    Sin sinTwo;
    Sin sinThree;
    Sin sinFour;
    Sin sinFive;
    BinaryOp sinSix;

    /**
     * This helper method creates Sin Test Objects and stores them in the Sin
     * fields stored in this class.
     */
    private void sinTesters() {     
        sinOne = new Sin(new Number(1));
        sinTwo = new Sin(new Variable());
        sinThree = new Sin(new Polynomial(new Variable(), 2));
        sinFour = new Sin(new Polynomial(new Variable(), 3));
        sinFive = new Sin(new BinaryOp(Op.MULT, new Number(3), new Sin(new Variable())));
        sinSix = new BinaryOp(Op.PLUS, new Sin(new Variable()), new Sin(new Polynomial(new Sin(new Variable()), 3)));
    }

    /**
     * This JUnit method tests the null point exception for Sin
     */
    @Test(expected = NullPointerException.class) 
    public void nullSinTest() {
        unaryThree = new Sin(null);
    }

    /**
     * This JUnit Method tests a variety of Sin Objects.
     * This method specifically focuses on the equals method.
     */
    @Test
    public void sinEqualsTest() {
        sinTesters();
        unaryTesters();
        assertTrue(sinZero.equals(new Sin(new Number(0)))); // Test Zero
        assertTrue(sinOne.equals(new Sin(new Number(1)))); // Test One
        assertFalse(sinOne.equals(neqTester)); // False Assertions
        assertFalse(sinOne.equals(sinSix));
        assertFalse(sinOne.equals(unaryTwo));

        // Test Many
        assertFalse(sinThree.equals(logFour)); // Nested Polynomials with different Powers
        assertTrue(sinFive.equals(new Sin(new BinaryOp(Op.MULT, new Number(3), new Sin(new Variable()))))); // Nested BinaryOp

        // Long Expression
        Sin leftOperand = new Sin(new Variable());
        Sin rightOperand = new Sin(new Polynomial(new Sin(new Variable()), 3));
        assertTrue(sinSix.getLeftOperand().equals(leftOperand)); // Compare Left Operand
        assertTrue(sinSix.getRightOperand().equals(rightOperand)); // Compare Right Operand
    }

    /**
     * This JUnit Method tests a variety of Sin and nested Sin Objects.
     * This method specifically focuses on the toString method.
     */
    @Test
    public void sinToStringTest() {
        sinTesters();
        assertEquals("Sin[0.0]", sinZero.toString()); // Test Zero
        assertEquals("Sin[1.0]", sinOne.toString()); // Test One
        assertEquals("Sin[x]", sinTwo.toString()); // Test Many
        assertEquals("Sin[x^2.0]", sinThree.toString());
        assertEquals("Sin[x^3.0]", sinFour.toString()); 
        assertEquals("Sin[3.0 * Sin[x]]", sinFive.toString()); 
        assertEquals("Sin[x] + Sin[Sin[x]^3.0]", sinSix.toString()); 
    }

    /**
     * This JUnit Method tests a variety of Sin and nested Sin Objects.
     * This method specifically focuses on the value methods.
     */
    @Test(expected = UnsupportedOperationException.class) 
    public void sinValueTest() {
        sinTesters();
        assertEquals(0, sinZero.value(), 0); // Test Zero
        assertEquals(0, sinZero.value(10), 0);
        assertEquals(0.8414709848, sinOne.value(), 0.0001); // Test One
        assertEquals(0.8414709848, sinOne.value(10), 0.0001);
        
        // Test Many
        assertEquals(1, sinTwo.value(Math.PI / 2.0), 0.0001);
        assertEquals(-0.7568024953, sinThree.value(2), 0.0001);
        assertEquals(0.9893582466, sinFour.value(2), 0.0001);
        assertEquals(0.4020002804, sinFive.value(2), 0.0001);
        assertEquals(1.592271804, sinSix.value(2), 0.0001);

        sinTwo.value(); // Test Exception Handler
    }

    /**
     * This JUnit Method tests a variety of Sin and nested Sin Objects.
     * This method specifically tests the derivative method.
     */
    @Test
    public void sinDerivativeTest() {
        sinTesters();
        assertEquals("-1.0 * 0.0 * Cos[0.0]", sinZero.derivative().toString()); // Test Zero
        assertEquals("-1.0 * 0.0 * Cos[1.0]", sinOne.derivative().toString()); // Test One

        // Test Many
        assertEquals("-1.0 * 1.0 * Cos[x]", sinTwo.derivative().toString());
        assertEquals("-1.0 * ((2.0 * x^1.0) * 1.0) * Cos[x^2.0]", sinThree.derivative().toString());
        assertEquals("-1.0 * ((3.0 * x^2.0) * 1.0) * Cos[x^3.0]", sinFour.derivative().toString());
        assertEquals("-1.0 * ((0.0 * Sin[x]) + (3.0 * -1.0 * 1.0 * Cos[x])) * Cos[3.0 * Sin[x]]",
                sinFive.derivative().toString());
        assertEquals("(-1.0 * 1.0 * Cos[x]) + (-1.0 * ((3.0 * Sin[x]^2.0) * -1.0 * 1.0 * Cos[x]) * Cos[Sin[x]^3.0])",
                sinSix.derivative().toString());
    }

    // These fields are to be used with the Cosine tests
    Cos cosZero = new Cos(new Number(0)); // Zero!
    Cos cosOne;
    Cos cosTwo;
    Cos cosThree;
    Cos cosFour;
    Cos cosFive;
    BinaryOp cosSix;

    /**
     * This helper method creates Cos Test Objects and stores them in the Cos
     * fields stored in this class.
     */
    private void cosTesters() {
        cosOne = new Cos(new Number(1));
        cosTwo = new Cos(new Variable());
        cosThree = new Cos(new Polynomial(new Variable(), 2));
        cosFour = new Cos(new Polynomial(new Variable(), 3));
        cosFive = new Cos(new BinaryOp(Op.MULT, new Number(3), new Cos(new Variable())));
        cosSix = new BinaryOp(Op.PLUS, new Cos(new Variable()), new Cos(new Polynomial(new Cos(new Variable()), 3)));
    }

    /**
     * This JUnit method tests the null point exception for Cos
     */
    @Test(expected = NullPointerException.class) 
    public void nullCosTest() {
        unaryFour = new Cos(null);
    }
    
    /**
     * This JUnit Method tests a variety of Cos Objects.
     * This method specifically focuses on the equals method.
     */
    @Test
    public void cosEqualsTest() {
        cosTesters();
        unaryTesters();
        assertTrue(cosZero.equals(new Cos(new Number(0)))); // Test Zero
        assertTrue(cosOne.equals(new Cos(new Number(1)))); // Test One
        assertFalse(cosOne.equals(neqTester)); // False Assertions
        assertFalse(cosOne.equals(cosSix));
        assertFalse(cosOne.equals(unaryTwo));

        // Test Many
        assertFalse(cosThree.equals(logFour)); // Nested Polynomials with different Powers
        assertTrue(cosFive.equals(new Cos(new BinaryOp(Op.MULT, new Number(3), new Cos(new Variable()))))); // Nested BinaryOp

        // Long Expression
        Cos leftOperand = new Cos(new Variable());
        Cos rightOperand = new Cos(new Polynomial(new Cos(new Variable()), 3));
        assertTrue(cosSix.getLeftOperand().equals(leftOperand)); // Compare Left Operand
        assertTrue(cosSix.getRightOperand().equals(rightOperand)); // Compare Right Operand
    }

    /**
     * This JUnit Method tests a variety of Cos and nested Cos Objects.
     * This method specifically focuses on the toString method.
     */
    @Test
    public void cosToStringTest() {
        cosTesters();
        assertEquals("Cos[0.0]", cosZero.toString()); // Test Zero
        assertEquals("Cos[1.0]", cosOne.toString()); // Test One
        assertEquals("Cos[x]", cosTwo.toString()); // Test Many
        assertEquals("Cos[x^2.0]", cosThree.toString());
        assertEquals("Cos[x^3.0]", cosFour.toString()); 
        assertEquals("Cos[3.0 * Cos[x]]", cosFive.toString()); 
        assertEquals("Cos[x] + Cos[Cos[x]^3.0]", cosSix.toString()); 
    }

    /**
     * This JUnit Method tests a variety of Cos and nested Cos Objects.
     * This method specifically focuses on the value methods.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void cosValueTest() {
        cosTesters();
        assertEquals(1, cosZero.value(), 0); // Test Zero
        assertEquals(1, cosZero.value(10), 0);
        assertEquals(0.5403023059, cosOne.value(), 0.0001); // Test One
        assertEquals(0.5403023059, cosOne.value(10), 0.0001);
        
        // Test Many
        assertEquals(-1, cosTwo.value(Math.PI), 0.0001);
        assertEquals(-0.6536436209, cosThree.value(2), 0.0001);
        assertEquals(-0.1455000338, cosFour.value(2), 0.0001);
        assertEquals(0.3168019107, cosFive.value(2), 0.0001);
        assertEquals(0.5812574209, cosSix.value(2), 0.0001);

        cosTwo.value(); // Test Exception Handler
    }

    /**
     * This JUnit Method tests a variety of Cos and nested Cos Objects.
     * This method specifically tests the derivative method.
     */
    @Test
    public void cosDerivativeTest() {
        cosTesters();
        assertEquals("0.0 * Sin[0.0]", cosZero.derivative().toString()); // Test Zero
        assertEquals("0.0 * Sin[1.0]", cosOne.derivative().toString()); // Test One

        // Test Many
        assertEquals("1.0 * Sin[x]", cosTwo.derivative().toString());
        assertEquals("((2.0 * x^1.0) * 1.0) * Sin[x^2.0]", cosThree.derivative().toString());
        assertEquals("((3.0 * x^2.0) * 1.0) * Sin[x^3.0]", cosFour.derivative().toString());
        assertEquals("((0.0 * Cos[x]) + (3.0 * 1.0 * Sin[x])) * Sin[3.0 * Cos[x]]",
                cosFive.derivative().toString());
        assertEquals("(1.0 * Sin[x]) + (((3.0 * Cos[x]^2.0) * 1.0 * Sin[x]) * Sin[Cos[x]^3.0])",
                cosSix.derivative().toString());
    }
}