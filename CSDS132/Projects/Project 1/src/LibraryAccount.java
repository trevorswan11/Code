/*
 * Trevor Swan
 * LibraryAccount Class
 * CSDS132 Spring 2024 - Project 1
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

    /** This constructor initializes an account with a desired account number
     * @param inputNumber The desired account number as a String
     */
    public LibraryAccount(String inputNumber) {
        super(inputNumber);
    }

    /** This constructor initializes an account with a desired account number, balance limit, book fine, and reserved items.
     * @param inputNumber The desired account number as a String
     * @param inputLimit The desired balance limit of the account as an int
     * @param inputBookFine The desired amount to fine the student per book overdue as a Double
     * @param inputReserveFine The desired amount to fine the student per overdue reserve item as a Double
     */
    public LibraryAccount(String inputNumber, int inputLimit, double inputBookFine, double inputReserve) {
        super(inputNumber, inputLimit);
        this.setBookFine(inputBookFine);
        this.setReserveFine(inputReserve);
    }

    /** Sets the book fine amount to the size of the fine on the day.
     * @param bookFineSize The desired amount to charge the student per overdue book as a Double
     * @return void
    */
    public void setBookFine(Double bookFineSize) {
        this.bookFine = bookFineSize;
    }

    /** Returns the book fine.
     * @return The account's book fine value, the amount owed per overdue book, as a Double
     */
    public Double getBookFine() {
        return bookFine;
    }

    /** Sets the amount fined for a reserved book to the amount due that day. 
     * @param reserveFineSize Represents the amount that the student owes per day of not returning an overdue item 
     */
    public void setReserveFine(Double reserveFineSize) {
        this.reserveFine = reserveFineSize;
    }

    /** Returns the amount fined for each day overdue.
     * @return The account's reserved items fine value, the amount owed per overdue reserved item, as a Double
    */
    public Double getReserveFine() {
        return reserveFine;
    }

    /** Increases the amount of overdue books in the account by one. 
     * @return void
     */
    public void incrementOverdueBooks() {
        this.overdueBooks = getNumberOverdueBooks() + 1;
    }

    /** This method decreases the amount of overdue books down to at most 0. 
     * The amount remains at zero if this method is called when there are no overdue books.
     * The amount is decreased by 1 otherwise
     * @return void
     */
    public void decrementOverdueBooks() {
        // checks to see if there is 0 overdue books. Returns 0 if already at 0.
        if (overdueBooks <= 0) {
            this.overdueBooks = 0;
        }
        // decreases amount by 1 if there is a non-zero amount
        else {
            this.overdueBooks = getNumberOverdueBooks() - 1;
        }
    }

    /** Returns the total number of currently overdue books.
     * @return The number of overdue books in the account
     */
    public int getNumberOverdueBooks() {
        return overdueBooks;
    }

    /** Increases the amount of overdue books in the account by one. 
     * @return void
     */
    public void incrementOverdueReserve() {
        this.overdueReserve = getNumberOverdueReserve() + 1;
    } 

    /** This method decreases the amount of overdue books down to at most 0. 
     * The amount remains at zero if this method is called when there are no overdue reserved items.
     * The amount is decreased by 1 otherwise.
     * @return void
     */
    public void decrementOverdueReserve() {
        // checks to see if there are 0 items in overdue reserve, and sets to 0 if this is true
        if (overdueReserve <= 0) {
            this.overdueReserve = 0;
        }
        // Decreases the amount of overdue books by 1 if the first statement is false
        else {
            this.overdueReserve = getNumberOverdueReserve() - 1;
        }
    }

    /** Returns the total number of currently overdue books.
     * @return The number of overdue reserved items in the account
     */    
    public int getNumberOverdueReserve() {
        return overdueReserve;
    }

    /** Checks to see if the balance in the account is less than or equal to the balance limit. 
     * @return Boolean value, true indicates the user can borrow, false indicates they cannot. 
     */
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

    /** Increases the balance by the products of the overdue amount times its fine,
     * along with the reserved item amount times its fine. 
     * @return void 
     */
    public void endOfDay() {
        // Calculate the total owed using methods above
        this.fineAmount = getNumberOverdueBooks() * getBookFine() + getNumberOverdueReserve() * getReserveFine();

        // charge the account with the charge(Double) method from the Account class
        charge(fineAmount);
    }

    // This is the main method
    public static void main(String[] args) {

    }
}