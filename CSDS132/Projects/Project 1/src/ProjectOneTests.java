/* Trevor Swan
 * Project 1 Testing
 * Formatted Differently for organization
 */
import static org.junit.Assert.*;
import org.junit.*;

public class ProjectOneTests {
    // These are the fields for the following class tests
    private String accountInput = "12345";
    private Double currentBalance = 20.0;
    private int balLimit = 104;
    private Double inputBookFine = 20.0;
    private Double inputReserve = 10.0;

    // This initializes the account to be tested using the more complex constructor
    Account account = new Account(accountInput, balLimit);

    // This initializes a new library account with the simple signature
    LibraryAccount simpleLibrary = new LibraryAccount(accountInput);

    // This initializes a library account to be tested using the complex constructor
    LibraryAccount libAccount = new LibraryAccount(accountInput, balLimit, inputBookFine, inputReserve); 

// All Account Class Tests can be found below this line
    // This tests the constructor that has 2 inputs and get method
    @Test
    public void accConstructorTest() {
        String num = account.getAccountNumber();
        assertEquals(accountInput, num);
        int predLimit = account.getBalanceLimit();
        assertEquals(balLimit, predLimit);
    }

    // This tests the "getBalance" method
    @Test
    public void balanceTest() {
        Double bal = account.getBalance();
        assertEquals(currentBalance, bal);
    }

    // This tests the "charge" method
    @Test
    public void chargeTest() {
        Double actualCharge = account.getBalance() + 4;
        account.charge(4);
        Double predCharge = account.getBalance();
        assertEquals(actualCharge, predCharge);
    }

    // This tests the "credit" method
    @Test
    public void creditTest() {
        Double actualCredit = account.getBalance() - 3.6;
        account.credit(3.6);
        Double predCredit = account.getBalance();
        assertEquals(actualCredit, predCredit);
    } 

// All LibraryAccount Class Tests can be found below this line
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
        Double bookFineAmount = libAccount.getBookFine();
        Double reserveFineAmount = libAccount.getReserveFine();
        assertEquals(accountInput, libAccountNumber);
        assertEquals(balLimit, libAccount.getBalanceLimit());
        assertEquals(inputBookFine, bookFineAmount);
        assertEquals(inputReserve, reserveFineAmount);
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
        int finalIncrease = (int)(Math.random()+6);
        for (int i = 0; i < finalIncrease; i++) {
            libAccount.incrementOverdueBooks();
            libAccount.incrementOverdueReserve();
        }

        libAccount.decrementOverdueBooks();
        libAccount.decrementOverdueReserve();
        assertEquals(finalIncrease - 1, libAccount.getNumberOverdueBooks());
        assertEquals(finalIncrease - 1, libAccount.getNumberOverdueReserve());
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
        Double bookFine = libAccount.getNumberOverdueBooks() * libAccount.getBookFine();
        Double reserveFine = libAccount.getNumberOverdueReserve() * libAccount.getReserveFine();
        Double balanceTotal = bookFine + reserveFine + libAccount.getBalance();
        libAccount.endOfDay();
        assertEquals(balanceTotal, libAccount.getBalance());
    }
// All CreditAccount Class Tests can be found below this line
}
