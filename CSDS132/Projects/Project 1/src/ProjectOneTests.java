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

    // This initializes a new library account with the simple signiture
    LibraryAccount simpleLibrary = new LibraryAccount(accountInput);

    // This initializes a library account to be tested using the complex constructor
    LibraryAccount libAccount = new LibraryAccount(accountInput, balLimit, inputBookFine, inputReserve); 

// All Acount Class Tests can be found below this line
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
    // This tests the simple libraryaccount constructor
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
        Double reserveFineAount = libAccount.getReserveFine();
        assertEquals(accountInput, libAccountNumber);
        assertEquals(balLimit,libAccount.getBalanceLimit());

    }
}
