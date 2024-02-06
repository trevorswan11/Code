/* Trevor Swan
 * Account Class
 * CSDS132 Spring 2024
 */
public class Account {
    // Stores Account Number of Student. It is final as it will never change
    private final String accountNumber;

    // Stores the Current Balance of the Student's Account
    private double accountBalance = 20.0;
    
    // Stores the Highest balance the Student may have
    private int balanceLimit = 100;

    // This constructor initializes the accountNumber to an inputted value value
    public Account(String inputNumber) {
        accountNumber = inputNumber;
    }

    // This constructor initializes the account number and its balance limit based on appropriate inputs
    public Account(String inputNumber, int inputLimit) {
        this(inputNumber);
        setBalanceLimit(inputLimit);
    }

    // This method gets the Number of the Student's Account. It is a string output
    public String getAccountNumber() {
        return accountNumber;
    }

    // This method returns the current Balance in the Students account. It is a double output
    public Double getBalance() {
        return accountBalance;
    }

    // This method increases the balance of the account by an inputted value. It is a void
    public void charge(double chargeAmount) {
        accountBalance = getBalance() + chargeAmount;
    }

    // This method decreases the balance of the account by inputted value. It is a void
    public void credit(double creditAmount) {
        accountBalance = getBalance() - creditAmount;
    }

    // This method sets the balance limit of the account to the inputted int value. It is a void 
    public void setBalanceLimit(int setLimit) {
        balanceLimit = setLimit;
    }

    // This methods returns the balance limit of the account. It is an int output
    public int getBalanceLimit() {
        return balanceLimit;
    }

    // This is the main method
    public static void main(String[] args) {

    }
}
