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
        assertEquals(currentBalance, bal,0);
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
    CreditAccount credAccount = new CreditAccount(accountInput, currentBalance);

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
    CreditAccount testTuitionAccount = new CreditAccount(accountInput, currentBalance);

    /* This initializes a new CreditAccount called DiningAccount for these tests */\
    CreditAccount testDiningAccount = new CreditAccount(accountInput, currentBalance);

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

    /* This tests the getter setter methods for the LibraryAccount methods */
    @Test
    public void libraryGetSet() {
        studentAccount.setLibraryAccount(testLibraryAccount);

        // this creates a model library account to extract information to test
        LibraryAccount modelLibraryAccount = studentAccount.getLibraryAccount();
        assertEquals(accountInput, modelLibraryAccount.getAccountNumber());
        assertEquals(balLimit, modelLibraryAccount.getBalanceLimit(), 0);
        assertEquals(inputBookFine, modelLibraryAccount.getBookFine(), 0);
        assertEquals(inputReserveFine, modelLibraryAccount.getReserveFine(), 0);
    }

    /* This tests the getter setter methods for the TuitionAccount Methods */
    @Test
    public void tuitionGetSet() {
        studentAccount.setTuitionAccount(testTuitionAccount);

        // This creates a model TuitionAccount to extract information and test
    }
}
