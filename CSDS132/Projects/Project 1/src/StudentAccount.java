public class StudentAccount extends Account {
    // This field contains the student's name
    private String studentName = "John Doe";
    
    // This field contains the students Address
    private String studentAddress = "10900 Euclid Ave, Cleveland, OH, 44106";

    // this field contains the StudentAccount's refund balance
    private double refundBalance = 0.;

    // This field contains a uninitialized libraryAccount
    private LibraryAccount LibraryAccount;
    
    // This field contains a uninitialized tuitionAccount
    private CreditAccount TuitionAccount;

    // This field contains an uninitialized diningAccount
    private CreditAccount DiningAccount;

    /** Creates a student account with a desired number and name.
     * 
     * @param accountNumber The desired account number for the student as a String.
     * @param studentName The desired name to be associated with the account number as a String.
      */
    public StudentAccount(String accountNumber, String studentName) {
        super(accountNumber);
        this.setName(studentName);

        // Initializes the fields to null values for the account number, and 0 for interest rate
        this.LibraryAccount = new LibraryAccount(null);
        this.TuitionAccount = new CreditAccount(null, 0);
        this.DiningAccount = new CreditAccount(null, 0);
    }

    /** Sets the name of the student to the inputted value.
     * 
     * @param studentName The desired name to be associated with the account number as a String.
     * @return void
     */
    public void setName(String studentName) {
        this.studentName = studentName;
    }
    
    /** Returns the name of the student
     * 
     * @return The name of the student as a String.
     */
    public String getName() {
        return this.studentName;
    }

    /** Sets the address of the student to an inputted value
     * 
     * @param studentAddress The desired address for the student as a String
     * @return void
     */
    public void setAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    /** Returns the address of the student
     * 
     * @return The address of the student as a String 
     */
    public String getAddress() {
        return this.studentAddress;
    }


    /** Sets the students LibraryAccount to a desired, non-null LibraryAccount class
     * 
     * @param libraryAccount The students associated Library Account as type LibraryAccount
     * @return void
     */
    public void setLibraryAccount(LibraryAccount libraryAccount) {
        this.LibraryAccount = libraryAccount;
    }

    /** Returns the students LibraryAccount 
     * 
     * @return The associated LibraryAccount for the student, of the same type
     */
    public LibraryAccount getLibraryAccount() {
        return this.LibraryAccount;
    }

    /** Sets the students tuition account to a desired, non-null instance of the CreditAccount class
     * 
     * @param tuitionAccount The students associated Tuition Account as type CreditAccount
     * @return void
     */
    public void setTuitionAccount(CreditAccount tuitionAccount) {
        this.TuitionAccount = tuitionAccount;
    }

    /** Returns the students tuition account
     * 
     * @return The associated TuitionAccount for the Student, of the CreditAccount type
      */
    public CreditAccount getTuitionAccount() {
        return this.TuitionAccount;
    }

    /** Sets the student's dining account to a desired, non-null instance of the CreditAccount class 
     * 
     * @param diningAccount The students associated Dining Account as a type CreditAccount
     * @return void
      */
    public void setDiningAccount(CreditAccount diningAccount) {
        this.DiningAccount = diningAccount;
    }

    /** Returns the students dining Account
     * 
     * @return The associated DiningAccount for the student, of the CreditAccount type
      */
    public CreditAccount getDiningAccount() {
        return this.DiningAccount;
    }

    @Override
    /** Changes the getBalance method of the Account class to subtract
     *  the StudentAccount class balance from the total balances of the students accounts  
     * 
     * @return The difference of the accounts balances explained above as a double
     */
    public double getBalance() {

    }

    @Override
    /** Changes the charge method of the Account class to compare the inputted value 
     * to the current balance in the StudentAccount class. It then checks to see if a TuitionAccount
     * exists for the student and takes the appropriate action. The negation of the comparison 
     * becomes the new refund amount if their is no associated tuition account, otherwise, the charge method
     * acts directly on the TuitionAccount.
     * 
     * @param chargeAmount The amount to be compared and acted with in the method, as a double
     * @return void  
    */
    public void charge(double chargeAmount) {

    }

    @Override
    /** Changes the credit methods of the Account class to process a payment across the 
     * existing students accounts in the following order: Tuition, Dining, and Library. Omits 
     * null accounts, and refunds any extra money after all Accounts have been processed.
     * For the accounts with an amount paid this month value, that will be the maximum amount 
     * credited. For the Library Account, the balance must remain either positive or zero.
     * 
     * @param creditAmount the amount to be fed through the method description listed above
     * @return void
    */
    public void credit(double creditAmount) {

    }
    // This is the main method
    public static void main(String[] args) {

    }
}
