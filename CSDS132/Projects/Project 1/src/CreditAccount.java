/* Trevor Swan
 * CreditAccount Class
 * CSDS132 Spring 2024 - Project 1
 */

public class CreditAccount extends Account {
    // This field holds the Interest rate for the account
    private Double interestRate = 3.0;

    // This field holds the amount that should be paid monthly
    private Double monthlyPayment = 100.0;

    // This field holds the amount credited to the account for the month
    private Double amountPaidThisMonth = 0.0;

    /** Initializes an instance with Account number input and its interest rate
     * @param inputNumber The desired account number as a String
     * @param interestRate The desired interest rate for the user as a Double
     */
    public CreditAccount(String inputAccount, Double interestRate) {
        super(inputAccount);
        this.setInterestRate(interestRate);
    }

    /** Sets the account's interest rate to a desired value. 
     * @param inputRate The desired interest rate to be set for the account, as a Double
     * @return void
     */ 
    public void setInterestRate(Double inputRate) {
        interestRate = inputRate;
    }

    /** Returns the interest rate for the account.
     * @return The students interest rate from the account as a Double 
     */
    public Double getInterestRate() {
        return interestRate;
    }

    /** Returns the monthly payment amount for the student.
     * Note: The amount they owe that prevents interest charges. 
     * @return Amount owed for monthly payment, as a Double
    */
    public Double getMonthlyPayment() {
        return monthlyPayment;
    }

    /** Returns the amount that the student has paid this month.
     * The value paid is equivalent to the amount credited to the account that month.
     * @return The total amount paid this month as a Double
     */
    public Double getAmountPaidThisMonth() {
        return amountPaidThisMonth;
    }

    /** If the Student has not paid their full balance, 
     * Increases the accounts balance by its current balance multiplied with its ANNUAL interest rate.
     * Otherwise, resets the Student's balance and total amount paid.
     * @return void
     */
    public void endOfMonth() {
        if (amountPaidThisMonth < monthlyPayment) {
          super.charge((this.getInterestRate() * super.getBalance()) / 12);  
        }

        amountPaidThisMonth = 0.0;
        monthlyPayment = this.getBalance();
    }

    /** Decreases the balance of both the account and the amount payed this month by the student.
     * @param creditAmount The amount that the two balances listed above should be decreased by.
     * @return void
     */
    @Override 
    public void credit(double creditAmount) {
        super.credit(creditAmount);
        this.amountPaidThisMonth = getAmountPaidThisMonth() + creditAmount;

    }
    // This is the main method
    public static void main(String[] args) {

    }
}
