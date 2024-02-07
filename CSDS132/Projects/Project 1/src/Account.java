/* Trevor Swan
 * Account Class
 * CSDS132 Spring 2024 - Project 1
 */
public class Account {
    // Stores Account Number of Student. It is final as it will never change
    private final String accountNumber;

    // Stores the Current Balance of the Student's Account
    private double accountBalance = 20.0;
    
    // Stores the Highest balance the Student may have
    private int balanceLimit = 100;

    // This constructor initializes the accountNumber to an inputted value called inputNumber
    public Account(String inputNumber) {
        // Note: this is *not* initialized to an actual value to allow the constructors below to work
        accountNumber = inputNumber;
    }

    /* This constructor initializes the account number and its balance limit (inputLimit) 
     * based on appropriate inputs. The inputNumber is defined the same as the Account(String inputNumber) 
     * constructor.
     */
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

    /* This method increases the balance of the account by an inputted value that represents the amount to
     * be charged. This variable is called chargeAmount. It is a void method
     */
    public void charge(double chargeAmount) {
        accountBalance = getBalance() + chargeAmount;
    }

    /* This method decreases the balance of the account by inputted value that represents the amount to
     * be credited. This variable is called creditAmount. It is a void method
     */
    public void credit(double creditAmount) {
        accountBalance = getBalance() - creditAmount;
    }

    /* This method sets the balance limit of the account to the inputted int value called setLimit. 
     * It is a void method.
     */ 
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
