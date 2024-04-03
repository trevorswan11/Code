package projectOne;

/* Trevor Swan
 * LibraryAccount Class
 * Represents the financial information for a library account holder 
 *  This information is related to their overdue items and total fines
 * CSDS132 Spring 2024 - Project 1
 */

public class LibraryAccount extends Account {
    // This field holds the book fine information
    private double bookFine = 20.0;
    
    // This field holds the reserved items fine value
    private double reserveFine = 10.0;

    // This field holds the number of overdue books in the account
    private int overdueBooks = 0;

    // This field holds the number of overdue reserved items in the account
    private int overdueReserve = 0;

    // This field holds the total fine amount in the account
    private double fineAmount = 0.0;

    /** Creates an account with a desired account number
     * 
     * @param inputAccount The desired account number as a String
     */
    public LibraryAccount(String inputAccount) {
        super(inputAccount);
    }

    /** Creates an account with a desired account number, balance limit, book fine, and reserved items.
     * 
     * @param inputAccount The desired account number as a String
     * @param inputLimit The desired balance limit of the account as an int
     * @param inputBookFine The desired amount to fine the student per book overdue as a double
     * @param inputReserveFine The desired amount to fine the student per overdue reserve item as a double
     */
    public LibraryAccount(String inputAccount, int inputLimit, double inputBookFine, double inputReserve) {
        super(inputAccount, inputLimit);
        this.setBookFine(inputBookFine);
        this.setReserveFine(inputReserve);
    }

    /** Sets the book fine amount to the amount the student will be charged per overdue day.
     * 
     * @param bookFineSize The desired amount to charge the student per overdue book as a double
     * @return void
    */
    public void setBookFine(double bookFineSize) {
        this.bookFine = bookFineSize;
    }

    /** Returns the book fine/day for the Student.
     * 
     * @return The account's book fine value, the amount owed per overdue book, as a double
     */
    public double getBookFine() {
        return bookFine;
    }

    /** Sets the amount fined for a reserved book to the amount due per overdue reserved item/day.
     *  
     * @param reserveFineSize Represents the amount that the student owes per day of not returning an overdue item, as a double
     * @return void
     */
    public void setReserveFine(double reserveFineSize) {
        this.reserveFine = reserveFineSize;
    }

    /** Returns the amount fined for each day overdue.
     * 
     * @return The account's reserved items fine value, the amount owed per overdue reserved item, as a double
    */
    public double getReserveFine() {
        return reserveFine;
    }

    /** Increases the amount of overdue books in the account by one. 
     * 
     * @return void
     */
    public void incrementOverdueBooks() {
        this.overdueBooks = getNumberOverdueBooks() + 1;
    }

    /** This method decreases the amount of overdue books down to at most 0. 
     * The amount remains at zero if there are no overdue books. The amount is decreased by 1 otherwise
     * 
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

    /** Sets the number of overdue books in the account to a desired amount
     * 
     * @param setOverdueAmount Desired amount of Overdue books to be in the account as an int
     * @return void
     */
    public void setNumberOverdue(int setOverdueAmount) {
        this.overdueBooks = setOverdueAmount;
    }

    /** Returns the total number of currently overdue books.
     * 
     * @return The number of overdue books in the account, as an int
     */
    public int getNumberOverdueBooks() {
        return overdueBooks;
    }

    /** Increases the amount of overdue books in the account by one. 
     * 
     * @return void
     */
    public void incrementOverdueReserve() {
        this.overdueReserve = getNumberOverdueReserve() + 1;
    } 

    /** This method decreases the amount of overdue books down to at most 0. 
     * The amount remains at zero if there are no overdue reserved items. The amount is decreased by 1 otherwise.
     * 
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

    /** Sets the Number of overdue books in the account to a desired value.
     * 
     * @param setReserveAmount Desired amount of Overdue Reserved items to be in the account as an int
     * @return void
     */
    public void setNumberOverdueReserve(int setReserveAmount) {
        this.overdueReserve = setReserveAmount;
    }

    /** Returns the total number of currently overdue reserved items for the student.
     * 
     * @return The number of overdue reserved items in the account, as an int
     */    
    public int getNumberOverdueReserve() {
        return overdueReserve;
    }

    /** Checks to see if the student can borrow an item. True if the balance doesn't exceed the limit. 
     * 
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

    /** Increases the student's balance by the amount owed per item times their respective fines.
     * 
     * @return void 
     */
    public void endOfDay() {
        // Calculate the total owed using methods above, I used a variable here to avoid too long of a statement
        this.fineAmount = getNumberOverdueBooks() * getBookFine() + getNumberOverdueReserve() * getReserveFine();

        // charge the account with the charge(double) method from the Account class
        charge(fineAmount);
    }

    // This is the main method
    public static void main(String[] args) {

    }
}