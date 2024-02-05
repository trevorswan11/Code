import static org.junit.Assert.*;
import org.junit.*;

public class AccountTest {
    private String accountInput = "12345";
    private Double currentBalance = 20.0;

    // This initializes the account to be tested
    Account account = new Account("12345");

    // This tests the "getAccountNumber" method
    @Test
    public void accNumberTest() {
        String num = account.getAccountNumber();
        assertEquals(accountInput, num);
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

    // This tests the getter/setter methods for the Balance Limit
    @Test
    public void balanceLimitTest() {
        int actualLimit = 103;
        account.setBalanceLimit(actualLimit);
        int predLimit = account.getBalanceLimit();
        assertEquals(actualLimit, predLimit);
    } 
}
