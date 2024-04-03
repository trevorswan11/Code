package projectOne;

/* Trevor Swan
 * Account Class
 * Represents a general use account that holds financial information and dues
 * CSDS132 Spring 2024 - Project 1
 */

public class Account {
    // Stores Account Number of Student. It is final as it will never change
    private final String accountNumber;

    // Stores the Current Balance of the Student's Account
    private double accountBalance = 20.0;
    
    // Stores the Highest balance the Student may have
    private int balanceLimit = 100;

    /** Sets the account number to an inputted value.
     * @param inputNumber The desired account number as a String
     */ 
    public Account(String inputNumber) {
        // Note: this is *not* initialized to an actual value to allow the constructors below to work
        this.accountNumber = inputNumber;
    }

    /** Sets the account number and its balance limit based on appropriate inputs. 
     * 
     * @param inputNumber The desired account number as a String
     * @param inputLimit The desired account balance limit as an int
     */
    public Account(String inputNumber, int inputLimit) {
        this(inputNumber);
        this.setBalanceLimit(inputLimit);
    }

    /** Gets the Number of the Student's Account. 
     *
     *  @return Account number as a String
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /** Sets the student's balance to a desired amount.
     * 
     * @param balanceAmount A double value that represents the desired new balance.
     * @return void
     */
    public void setBalance(double balanceAmount) {
        this.accountBalance = balanceAmount;
    }

    /** Returns the current Balance in the Students account.
     * 
     * @return Current balance of the account as a double
     */
    public double getBalance() {
        return accountBalance;
    }

    /** Increases the balance of the account by an inputted value.
     * 
     * @param chargeAmount Indicates the amount to increase the account's balance by as a double
     * @return void
     */
    public void charge(double chargeAmount) {
        this.setBalance(getBalance() + chargeAmount);
    }

    /** Decreases the balance of the account by inputted value.
     * 
     * @param creditAmount Indicates the amount to decrease the account's balance by as a double
     * @return void
     */
    public void credit(double creditAmount) {
        this.setBalance(getBalance() - creditAmount);
    }

    /** Sets the balance limit of the account to the inputted value. 
     * 
     * @param setLimit The desired value to set the account's balance limit to as an int
     * @return void
     */ 
    public void setBalanceLimit(int setLimit) {
        this.balanceLimit = setLimit;
    }

    /** Returns the balance limit of the account. 
     * 
     * @return Integer output that represents the account's balance limit
     */
    public int getBalanceLimit() {
        return balanceLimit;
    }

    // This is the main method
    public static void main(String[] args) {

    }
}
