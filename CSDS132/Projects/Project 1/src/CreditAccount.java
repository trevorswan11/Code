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

    // This constructor initializes an instance with Account number input and its interest rate
    public CreditAccount(String inputAccount, Double interestRate) {
        super(inputAccount);
        setInterestRate(interestRate);
    }

    // This method takes in a Double and sets it as the accounts interest rate. It has no output
    public void setInterestRate(Double inputRate) {
        interestRate = inputRate;
    }
    // This is the main method
    public static void main(String[] args) {

    }
}
