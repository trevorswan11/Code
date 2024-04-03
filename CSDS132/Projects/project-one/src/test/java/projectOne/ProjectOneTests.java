package projectOne;

/* Trevor Swan
 * Project 1 Testing
 * Formatted Differently for organization
 */

import static org.junit.Assert.*;
import org.junit.*;

public class ProjectOneTests {
    // These are the fields for the following class tests
    private String accountInput = "12345";
    private double currentBalance = 27.0;
    private int balLimit = 104;
    private double inputBookFine = 20.0;
    private double inputReserveFine = 10.0;
    private double inputRate = 0.1;
    private double inputMonthly = 46.0;
    private double inputPaid = 35.6;
    private double emptyBal = 0.0;
    private String studentName = "John";
    private String studentAddress = "11447 Juniper";

    // This initializes the account to be tested using the more complex constructor
    Account account = new Account(accountInput, balLimit);

// All Account Class Tests can be found below this line
    // This tests the constructor that has 2 inputs and get method
    @Test
    public void accConstructorTest() {
        String num = account.getAccountNumber();
        assertEquals(accountInput, num);
        int predLimit = account.getBalanceLimit();
        assertEquals(balLimit, predLimit);
    }

    // This tests the "getBalance" and "setBalance" methods
    @Test
    public void balanceTest() {
        account.setBalance(currentBalance);
        double bal = account.getBalance();
        assertEquals(currentBalance, bal, 0);
    }

    // This tests the "charge" method
    @Test
    public void chargeTest() {
        double actualCharge = account.getBalance() + 4;
        account.charge(4);
        double predCharge = account.getBalance();
        assertEquals(actualCharge, predCharge, 0);
    }

    // This tests the "credit" method
    @Test
    public void creditTest() {
        double actualCredit = account.getBalance() - 3.6;
        account.credit(3.6);
        double predCredit = account.getBalance();
        assertEquals(actualCredit, predCredit, 0);
    } 

// All LibraryAccount Class Tests can be found below this line
    // This initializes a new library account with the simple signature
    LibraryAccount simpleLibrary = new LibraryAccount(accountInput);

    // This initializes a library account to be tested using the complex constructor
    LibraryAccount libAccount = new LibraryAccount(accountInput, balLimit, inputBookFine, inputReserveFine);

    // This tests the simple LibraryAccount constructor
    @Test
    public void libSimpleTest() {
        String libAccountNum = simpleLibrary.getAccountNumber();
        assertEquals(accountInput, libAccountNum);
    }

    /* This tests the Large Constructor signature, and getter-setter methods for Book Fine 
     * and reserve fine as well */
    @Test
    public void libSignatureTest() {
        String libAccountNumber = libAccount.getAccountNumber();
        double bookFineAmount = libAccount.getBookFine();
        double reserveFineAmount = libAccount.getReserveFine();
        assertEquals(accountInput, libAccountNumber);
        assertEquals(balLimit, libAccount.getBalanceLimit());
        assertEquals(inputBookFine, bookFineAmount, 0);
        assertEquals(inputReserveFine, reserveFineAmount, 0);
    }

    /* This tests the decrement methods for the overdue Books and Reserve fields.
     * The "if (<= 0) {} branch is tested by attempting to subtract from 0 books"
     */
    @Test
    public void overdueDecrementTest() {
        libAccount.decrementOverdueBooks();
        int amountBook = libAccount.getNumberOverdueBooks();
        assertEquals(0, amountBook);
        libAccount.decrementOverdueReserve();
        int amountReserve = libAccount.getNumberOverdueReserve();
        assertEquals(0, amountReserve);
    }

    /* This tests the increment methods for the overdue books, followed by the else branch
     * in the decrement methods. This is done for both the Book and Reserve fields
     */
    @Test
    public void overdueAccountTests() {
        int finalIncrease = (int)(Math.random() + 6);
        for (int i = 0; i < finalIncrease; i++) {
            libAccount.incrementOverdueBooks();
            libAccount.incrementOverdueReserve();
        }

        libAccount.decrementOverdueBooks();
        libAccount.decrementOverdueReserve();
        assertEquals(finalIncrease - 1, libAccount.getNumberOverdueBooks());
        assertEquals(finalIncrease - 1, libAccount.getNumberOverdueReserve());
    }

    /* This tests the "setter" methods for the overdue books and overdue reserved items.
     * This makes use of the setter methods in conjunction with getter methods that have been established to work 
     */
    @Test
    public void setterOverdueTests() {
        libAccount.setNumberOverdue(29);
        assertEquals(29, libAccount.getNumberOverdueBooks());
        libAccount.setNumberOverdueReserve(113);
        assertEquals(113, libAccount.getNumberOverdueReserve());
    }

    /* This tests the canBorrow method by setting the current balance higher than the limit,
     * then setting it lower than the limit and observing the result of the method. This code
     * works because it gets the balance limit from the get method in Account
     */
    @Test
    public void canBorrowTest() {
        assertTrue(libAccount.canBorrow());
        libAccount.charge(libAccount.getBalanceLimit() + 1);
        assertFalse(libAccount.canBorrow());
    }

    /* This tests the endOfDay method by increasing the total number of overdue books and reserves to 5
     * then by calling the method to multiply these numbers with their respective fine amounts
     */
    @Test
    public void endOfDayTest() {
        for (int i = 0; i < 5; i++) {
            libAccount.incrementOverdueBooks();
            libAccount.incrementOverdueReserve();
        }
        double bookFine = libAccount.getNumberOverdueBooks() * libAccount.getBookFine();
        double reserveFine = libAccount.getNumberOverdueReserve() * libAccount.getReserveFine();
        double balanceTotal = bookFine + reserveFine + libAccount.getBalance();
        libAccount.endOfDay();
        assertEquals(balanceTotal, libAccount.getBalance(), 0);
    }

// All CreditAccount Class Tests can be found below this line
    // This initializes a credit account
    CreditAccount credAccount = new CreditAccount(accountInput, inputRate);

    /* This tests the getter setter methods for the interest rate */
    @Test 
    public void interestRateTest() {
        credAccount.setInterestRate(inputRate);
        assertEquals(inputRate, credAccount.getInterestRate(), 0);
    }

    /* This tests the getter setter methods for the monthly payment */
    @Test
    public void monthlyPaymentTest() {
        credAccount.setMonthlyPayment(inputMonthly);
        assertEquals(inputMonthly, credAccount.getMonthlyPayment(), 0);
    } 
    
    /* This tests the getter setter methods for the amount paid this month */
    @Test 
    public void amountPaidTest() {
        credAccount.setAmountPaidThisMonth(inputPaid);
        assertEquals(inputPaid, credAccount.getAmountPaidThisMonth(), 0);
    }

    /* This tests the overridden credit method */
    @Test
    public void overCredit() {
        double predBal = credAccount.getBalance() - 10;
        double predAmountPaid = credAccount.getAmountPaidThisMonth() + 10; 
        credAccount.credit(10);
        assertEquals(predBal, credAccount.getBalance(), 0);
        assertEquals(predAmountPaid, credAccount.getAmountPaidThisMonth(), 0);

    }

    /* This tests the end of the month method with interest being applied */
    @Test
    public void endOfMonthIntTest() {
        credAccount.setMonthlyPayment(inputMonthly);
        credAccount.setAmountPaidThisMonth(inputMonthly - 1);
        double oldBal = credAccount.getBalance();
        double predBal = (credAccount.getInterestRate() * credAccount.getBalance()) / 12 + oldBal;
        credAccount.endOfMonth();
        
        assertEquals(predBal, credAccount.getBalance(), 0);
        assertEquals(emptyBal, credAccount.getAmountPaidThisMonth(), 0);
        assertEquals(credAccount.getBalance(), credAccount.getMonthlyPayment(), 0);
    }

    /* This tests the end of the month method without interest being applied*/
    @Test
    public void endOfMonthTest() {
        credAccount.setMonthlyPayment(inputMonthly);
        credAccount.setAmountPaidThisMonth(inputMonthly);
        double oldBal = credAccount.getBalance();
        credAccount.endOfMonth();

        assertEquals(oldBal, credAccount.getBalance(), 0);
        assertEquals(emptyBal, credAccount.getAmountPaidThisMonth(), 0);
        assertEquals(credAccount.getBalance(), credAccount.getMonthlyPayment(), 0);
    }

// All StudentAccount tests can be found below this line
    /* This initializes a new StudentAccount */
    StudentAccount studentAccount = new StudentAccount(accountInput, studentName);

    /* This initializes a new LibraryAccount for these tests */
    LibraryAccount testLibraryAccount = new LibraryAccount(accountInput, balLimit, inputBookFine, inputReserveFine);

    /* This initializes a new CreditAccount called TuitionAccount for these tests */
    CreditAccount testTuitionAccount = new CreditAccount(accountInput, inputRate);

    /* This initializes a new CreditAccount called DiningAccount for these tests */
    CreditAccount testDiningAccount = new CreditAccount(accountInput, inputRate);

    /* This tests if the constructor worked properly, as well as the getter-setters for name methods from the student account class */
    @Test
    public void constructorTest() {
        assertEquals(accountInput, studentAccount.getAccountNumber());
        assertEquals(studentName, studentAccount.getName());
    }

    /* This tests the getter-setter methods for the address methods for the student account */
    @Test
    public void addressTest() {
        studentAccount.setAddress(studentAddress);
        assertEquals(studentAddress, studentAccount.getAddress());
    }

    /* This tests the getter setter methods for the LibraryAccount methods as well as its null initial declaration */
    @Test
    public void libraryGetSet() {
        // This code runs before the rest in order to test the null initialization and comparison technique I used
        LibraryAccount nullLibraryAccount = studentAccount.getLibraryAccount();
        assertTrue(nullLibraryAccount.getAccountNumber() == null);
        
        // This sets the class field to the correct value for testing 
        studentAccount.setLibraryAccount(testLibraryAccount);

        // this creates a model library account to extract information to test
        LibraryAccount modelLibraryAccount = studentAccount.getLibraryAccount();
        assertEquals(accountInput, modelLibraryAccount.getAccountNumber());
        assertEquals(balLimit, modelLibraryAccount.getBalanceLimit(), 0);
        assertEquals(inputBookFine, modelLibraryAccount.getBookFine(), 0);
        assertEquals(inputReserveFine, modelLibraryAccount.getReserveFine(), 0);
    }

    /* This tests the getter setter methods for the TuitionAccount Methods as its null initial declaration */
    @Test
    public void tuitionGetSet() {
        // This code runs before the rest in order to test the null initialization and comparison technique I used
        CreditAccount nullTuitionAccount = studentAccount.getTuitionAccount();
        assertTrue(nullTuitionAccount.getAccountNumber() == null);

        // This sets the class field to the correct value for testing 
        studentAccount.setTuitionAccount(testTuitionAccount);

        // This creates a model TuitionAccount to extract information and test
        CreditAccount modelTuitionAccount = studentAccount.getTuitionAccount();
        assertEquals(accountInput, modelTuitionAccount.getAccountNumber());
        assertEquals(inputRate, modelTuitionAccount.getInterestRate(), 0);
    }

    /* This tests the getter setter methods for the DiningAccount methods as its null initial declaration */
    @Test
    public void diningGetSet() {
        // This code runs before the rest in order to test the null initialization and comparison technique I used
        CreditAccount nullDiningAccount = studentAccount.getDiningAccount();
        assertTrue(nullDiningAccount.getAccountNumber() == null);

        // This sets the class field to the correct value for testing 
        studentAccount.setDiningAccount(testDiningAccount);

        // This creates a model DiningAccount to extract information and test
        CreditAccount modelDiningAccount = studentAccount.getDiningAccount();
        assertEquals(accountInput, modelDiningAccount.getAccountNumber());
        assertEquals(inputRate, modelDiningAccount.getInterestRate(), 0);
    }

    /* This is the overridden getBalance method */
    @Test
    public void balanceOverride() {
        // This sets all of the manipulatable instances of classes in the parent class to usable values
        studentAccount.setLibraryAccount(testLibraryAccount);
        studentAccount.setTuitionAccount(testTuitionAccount);
        studentAccount.setDiningAccount(testDiningAccount);

        // This retrieves all of the previously mentioned instances
        LibraryAccount balanceLibraryAccount = studentAccount.getLibraryAccount();
        CreditAccount balanceTuitionAccount = studentAccount.getTuitionAccount();
        CreditAccount balanceDiningAccount = studentAccount.getDiningAccount();

        double balance = 20;
        balanceLibraryAccount.setBalance(balance);
        balanceDiningAccount.setBalance(balance);
        balanceTuitionAccount.setBalance(balance);

        // This prepares variables for the actual JUnit test
        double actualBalanceSum = studentAccount.getBalance();
        double expectedBalanceSum = balance * 3 - 20;

        assertEquals(expectedBalanceSum, actualBalanceSum, 0);
    }

    /* This is the overridden charge method for the if branch */
    @Test
    public void chargeOverrideIf() {
        studentAccount.setTuitionAccount(testTuitionAccount);
        CreditAccount chargeTuitionAccount = studentAccount.getTuitionAccount();

        chargeTuitionAccount.charge(10);
        double expectedTuition = 30;
        assertEquals(expectedTuition, chargeTuitionAccount.getBalance(), 0);
    }

    /* This is the overridden charge method for the else branch. This test works through the stage necessity of the balance override being - the super class */
    @Test
    public void chargeOverrideElse() {
        double chargeReal = 120;
        double expectedBalance = 20;
        studentAccount.setBalance(expectedBalance);
        assertEquals(-20, studentAccount.getBalance(), 0);
        studentAccount.charge(chargeReal);
        assertEquals(-140,studentAccount.getBalance(),0);
    }

    /* This tests the error message of this method */
    @Test
    public void creditError() {
        studentAccount.credit(-10);
    }

    /* This first test tests the first if branch of the tuition Owed conditional */
    @Test
    public void tuitionTestIf() {
        studentAccount.setTuitionAccount(testTuitionAccount);
        CreditAccount creditTuitionAccount = studentAccount.getTuitionAccount();
        creditTuitionAccount.setAmountPaidThisMonth(10);
        creditTuitionAccount.setMonthlyPayment(20);

        studentAccount.credit(30);
        assertEquals(20, creditTuitionAccount.getAmountPaidThisMonth(), 0);
    }

    /* This tests the else if branch of the Tuition Account conditional */
    @Test
    public void tuitionTestElseIf() {
        studentAccount.setTuitionAccount(testTuitionAccount);
        CreditAccount creditTuitionAccount = studentAccount.getTuitionAccount();
        creditTuitionAccount.setAmountPaidThisMonth(10);
        creditTuitionAccount.setMonthlyPayment(20);

        studentAccount.credit(5);
        assertEquals(15, creditTuitionAccount.getAmountPaidThisMonth(), 0);
    }

    /* This tests the DiningAccount If branch by preventing the other accounts from existing */
    @Test
    public void diningAccountIf() {
        studentAccount.setDiningAccount(testDiningAccount);
        CreditAccount creditDiningAccount = studentAccount.getDiningAccount();
        creditDiningAccount.setAmountPaidThisMonth(10);
        creditDiningAccount.setMonthlyPayment(20);

        studentAccount.credit(30);
        assertEquals(20, creditDiningAccount.getAmountPaidThisMonth(), 0);
    }

    /* This tests the DiningAccount Else If branch by preventing the other accounts from existing */
    @Test
    public void diningAccountElseIf() {
        studentAccount.setDiningAccount(testDiningAccount);
        CreditAccount creditDiningAccount = studentAccount.getDiningAccount();
        creditDiningAccount.setAmountPaidThisMonth(10);
        creditDiningAccount.setMonthlyPayment(20);

        studentAccount.credit(5);
        assertEquals(15, creditDiningAccount.getAmountPaidThisMonth(), 0);
    }

    /* This tests the possibility of not having any remaining credit from the call */
    @Test
    public void diningAccountNone() {
        studentAccount.setDiningAccount(testDiningAccount);
        CreditAccount creditDiningAccount = studentAccount.getDiningAccount();
        creditDiningAccount.setAmountPaidThisMonth(10);
        creditDiningAccount.setMonthlyPayment(20);

        studentAccount.credit(0);
        assertEquals(10, creditDiningAccount.getAmountPaidThisMonth(), 0);
    }

    /* This tests the LibraryAccount If branch by preventing the other accounts from existing */
    @Test
    public void libraryAccountIf() {
        studentAccount.setLibraryAccount(testLibraryAccount);
        LibraryAccount creditLibraryAccount = studentAccount.getLibraryAccount();
        creditLibraryAccount.setBalance(20);

        studentAccount.credit(30);
        assertEquals(0, creditLibraryAccount.getBalance(), 0);
    }

    /* This tests the LibraryAccount Else bIf ranch by preventing the other accounts from existing */
    @Test
    public void libraryAccountElseIf() {
        studentAccount.setLibraryAccount(testLibraryAccount);
        LibraryAccount creditLibraryAccount = studentAccount.getLibraryAccount();
        creditLibraryAccount.setBalance(20);

        studentAccount.credit(15);
        assertEquals(5, creditLibraryAccount.getBalance(), 0);
    }

    /* This tests the possibility of not having any remaining credit from the method call */
    @Test
    public void libraryAccountNone() {
        studentAccount.setLibraryAccount(testLibraryAccount);
        LibraryAccount creditLibraryAccount = studentAccount.getLibraryAccount();
        creditLibraryAccount.setBalance(20);

        studentAccount.credit(0);
        assertEquals(20, creditLibraryAccount.getBalance(), 0);
    }

    /* Finally, this tests the possibility of all accounts existing and extra money being put into the call */
    @Test
    public void testAllBranches() {
        // This preps the original balance for the final test
        double originalBalance = studentAccount.getBalance();

        // This sets a TuitionAccount with reasonable values
        studentAccount.setTuitionAccount(testTuitionAccount);
        CreditAccount creditTuitionAccount = studentAccount.getTuitionAccount();
        creditTuitionAccount.setAmountPaidThisMonth(10);
        creditTuitionAccount.setMonthlyPayment(20);

        // This sets a DiningAccount with reasonable values
        studentAccount.setDiningAccount(testDiningAccount);
        CreditAccount creditDiningAccount = studentAccount.getDiningAccount();
        creditDiningAccount.setAmountPaidThisMonth(10);
        creditDiningAccount.setMonthlyPayment(20);

        // This sets a LibraryAccount with reasonable values
        studentAccount.setLibraryAccount(testLibraryAccount);
        LibraryAccount creditLibraryAccount = studentAccount.getLibraryAccount();
        creditLibraryAccount.setBalance(20);

        studentAccount.credit(1000);
        
        // This tests to see if the money want through all accounts
        assertEquals(20, creditTuitionAccount.getAmountPaidThisMonth(), 0);
        assertEquals(20, creditDiningAccount.getAmountPaidThisMonth(), 0);
        assertEquals(0, creditLibraryAccount.getBalance(), 0);

        // This tests the final conditional that adds the remaining credit to the account itself
        double remainingCredit = 1000 - (10 + 10 + 20);

        /*  Note: the value in the expected position is negated because the instructions say that the overridden "getBalance"
         * method should subtract the current refund amount, or the parent class's balance, from the summation of the balances
         * in the rest of the class instances. Because the initial value is 0 and I am crediting all of the money i possibly can
         * the ending balance in the class instances will be 0, leaving me with the negated remainder of the credit
         */
        assertEquals(-(remainingCredit + originalBalance), studentAccount.getBalance(), 0);
    }
}
