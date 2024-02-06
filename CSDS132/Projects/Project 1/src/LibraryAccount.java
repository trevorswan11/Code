/*
 * Trevor Swan
 * LibraryAccount Class
 * CSDS132 Spring 2024
 */
public class LibraryAccount extends Account {
    // This field holds the book fine information
    private Double bookFine = 20.0;
    
    // This field holds the reserved items fine value
    private Double reserveFine = 10.0;

    // This field holds the number of overdue books in the account
    private int overdueBooks = 0;

    // This field holds the number of overdue reserved items in the account
    private int overdueReserve = 0;

    // This field holds the total fine amount in the account
    private double fineAmount = 0.0;

    // This constructor initializs the account number 
    public LibraryAccount(String inputNumber) {
        super(inputNumber);
    }

    // This construsctor initializes account number, balance limit, book fine, and reserved items
    public LibraryAccount(String inputNumber, int inputlimit, double inputBookFine, double inputReserve) {
        super(inputNumber, inputlimit);
        setBookFine(inputBookFine);
        setReserveFine(inputReserve);
    }

    // This method sets the book fine amount to the size of the fine on the day. It takes a double 
    public void setBookFine(Double bookFineSize) {
        bookFine = bookFineSize;
    }

    // This method returns the book fine. It returns a double
    public Double getBookFine() {
        return bookFine;
    }

    /* This method sets the amount fined for a reserved book to the amount due that day. 
     * It takes a double */
    public void setReserveFine(Double reserveFineSize) {
        reserveFine = reserveFineSize;
    }

    /* This method returns the amount fined for each day overdue. It returns a double */
    public Double getReserveFine() {
        return reserveFine;
    }

    /* This method increases the amount of overdue books in the account by one. 
     * It is a void method */
    public void incrementOverdueBooks() {
        overdueBooks = getNumberOverdueBooks() + 1;
    }

    /* This method decreases the amount of overdue books down to at most 0. 
     * It is a void method */
    public void decrementOverdueBooks() {
        // checks to see if there is 0 overdue books. Reterns 0 if already at 0.
        if (overdueBooks <= 0) {
            overdueBooks = 0;
        }
        // decreases amount by 1 if there is a non-zero amount
        else {
            overdueBooks = getNumberOverdueBooks() - 1;
        }
    }

    /* This method returns the total number of currently overdue books. It takes an int input */
    public int getNumberOverdueBooks() {
        return overdueBooks;
    }

    /* This method increases the amount of reserved overdue items in the account. It is a void method */
    public void incrementOverdueReserve() {
        overdueReserve = getNumberOverdueReserve() + 1;
    } 

    /* This method decreases the amount of reserved overdue items in the account to at most 0. 
     * It is a void method */
    public void decrementOverdueReserve() {
        // checks to see if there are 0 items in overdue reserve, and sets to 0 if this is true
        if (overdueReserve <= 0) {
            overdueReserve = 0;
        }
        // Decreases the amount of overdue books by 1 if the first statement is false
        else {
            overdueReserve = getNumberOverdueReserve() - 1;
        }
    }

    /* This method returns the total number of overdue reserved items. Its output is an int */
    public int getNumberOverdueReserve() {
        return overdueReserve;
    }

    /* This method checks to see if the balance in the account is less than or equal to the balance limit. 
     * returns boolean value */
    public Boolean canBorrow() {
        // Return true if the user has a balance less than or equal to than the limit
        if (getBalance() <= getBalanceLimit()) {
            return true;
        }
        // Return false if the user has a balance above the limit
        else {
            return false;
        }
    }

    /* This method increases the balance by the products of the overdue amount and its fine,
     * Along with the reserved item amount and its fine. It is a void method */
    public void endOfDay() {
        // Calculate the total owed using methods above
        fineAmount = getNumberOverdueBooks() * getBookFine() + getNumberOverdueReserve() * getReserveFine();

        // charge the account with the charge(Double) method from the Account class
        charge(fineAmount);
    }

    // This is the main method
    public static void main(String[] args) {

    }
}