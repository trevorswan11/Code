package projectOne;

/* Trevor Swan
 * CreditAccount Class
 * Represents an account that has a balance that needs to be payed off. 
 *  Paying off a credit account also pays off the total balance of the account.
 *  Does all of the heavy lifting regarding interest rates and monthly dues
 * CSDS132 Spring 2024 - Project 1
 */

public class CreditAccount extends Account {
    // This field holds the Interest rate for the account
    private double interestRate = 3.0;

    // This field holds the amount that should be paid monthly
    private double monthlyPayment = 100.0;

    // This field holds the amount credited to the account for the month
    private double amountPaidThisMonth = 0.0;

    /** Initializes an instance with Account number input and its interest rate
     * 
     * @param inputNumber The desired account number as a String
     * @param interestRate The desired interest rate for the user as a double
     */
    public CreditAccount(String inputAccount, double interestRate) {
        super(inputAccount);
        this.setInterestRate(interestRate);
    }

    /** Sets the account's interest rate to a desired value. 
     * 
     * @param inputRate The desired interest rate to be set for the account, as a double
     * @return void
     */ 
    public void setInterestRate(double inputRate) {
        interestRate = inputRate;
    }

    /** Returns the interest rate for the account.
     * 
     * @return The students interest rate from the account as a double 
     */
    public double getInterestRate() {
        return interestRate;
    }

    /** Sets the users monthly payment amount to a desired value.
     * 
     * @param setMonthly Desired monthly payment amount as a double
     * @return void
     */
    public void setMonthlyPayment(double setMonthly) {
        monthlyPayment = setMonthly;
    }

    /** Returns the monthly payment amount for the student.
     * Note: The amount they owe that prevents interest charges. 
     * 
     * @return Amount owed for monthly payment, as a double
    */
    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    /** Sets the amount the user has paid this month to a desired value. 
     * 
     * @param setPaid Desired amount to set the total amount the user has paid this month as a double
     * @return void
    */
    public void setAmountPaidThisMonth(double setPaid) {
        amountPaidThisMonth = setPaid;
    }

    /** Returns the amount that the student has paid this month.
     * The value paid is equivalent to the amount credited to the account that month.
     * 
     * @return The total amount paid this month as a double
     */
    public double getAmountPaidThisMonth() {
        return amountPaidThisMonth;
    }

    /** If the Student has not paid their full balance, 
     * Increases the accounts balance by its current balance multiplied with its ANNUAL interest rate.
     * Otherwise, resets the Student's balance and total amount paid.
     * 
     * @return void
     */
    public void endOfMonth() {
        if (getAmountPaidThisMonth() < getMonthlyPayment()) {
          super.charge((this.getInterestRate() * super.getBalance()) / 12);  
        }

        this.setAmountPaidThisMonth(0.0);
        this.setMonthlyPayment(super.getBalance());
    }

    /** Decreases the balance of both the account and the amount payed this month by the student.
     * 
     * @param creditAmount The amount that the two balances listed above should be decreased by.
     * @return void
     */
    @Override 
    public void credit(double creditAmount) {
        super.credit(creditAmount);
        this.setAmountPaidThisMonth(getAmountPaidThisMonth() + creditAmount);
    }
    // This is the main method
    public static void main(String[] args) {

    }
}
