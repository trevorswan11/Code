import static org.junit.Assert.*;
import org.junit.*;

public class ProjectOneTests {
    private String accountInput = "12345";
    private Double currentBalance = 20.0;
    private int balLimit = 104;

    // This initializes the account to be tested using the more complex constructor
    Account account = new Account("12345", 104);

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
}
