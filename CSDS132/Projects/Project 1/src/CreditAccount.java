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

    /** This constructor initializes an instance with Account number input and its interest rate
     * @param inputNumber The desired account number as a String
     * @param interestRate The desired interest rate for the user as a Double
     */
    public CreditAccount(String inputAccount, Double interestRate) {
        super(inputAccount);
        this.setInterestRate(interestRate);
    }

    /** Takes in a Double and sets it as the accounts interest rate. 
     * @param inputRate The desired interest rate to be set for the account
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
     * The amount they owe that prevents interest charges 
     * @return Amount owed for monthly payment as a Double
    */
    public Double getMonthlyPayment() {
        return monthlyPayment;
    }

    /** Returns the amount that the student has paid this month.
     * The value paid is equivalent to the amount credited to the account that month
     * @return The total amount paid this month as a Double
     */
    public Double getAmountPaidThisMonth() {
        return amountPaidThisMonth;
    }

    /** Checks to see if the amount paid this month is less than the monthly payment. If this is true,
     * interest is charged. The amount increased is equal to the only balance increased by the interest on 
     * the old balance, divided by 12. If this is false, interest is not charged. Because this indicates 
     * the end of the month, the monthly payment amount is reset to 0 and the accounts balance is set to the 
     * amount that will need to be paid this month.
     * @return void
     */
    public void endOfMonth() {

    }
    // This is the main method
    public static void main(String[] args) {

    }
}
