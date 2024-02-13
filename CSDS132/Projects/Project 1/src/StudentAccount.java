public class StudentAccount extends Account {
    // This field contains the student's name
    private String studentName = "John Doe";
    
    // This field contains the students Address
    private String studentAddress = "10900 Euclid Ave, Cleveland, OH, 44106";

    // This field contains a libraryAccount
    private LibraryAccount LibraryAccount = new LibraryAccount(null);
    
    // This field contains a tuitionAccount
    private CreditAccount TuitionAccount = new CreditAccount(null,0) ;

    // This field contains a diningAccount
    private double diningAccount = 0.;

    /** Creates a student account with a desired number and name.
     * 
     * @param accountNumber The desired account number for the student as a String.
     * @param studentName The desired name to be associated with the account number as a String.
      */
    public StudentAccount(String accountNumber, String studentName) {
        super(accountNumber);
        this.setName(studentName);
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


    public void setLibraryAccount(LibraryAccount libraryAccount) {
        libraryAccount;
    }


    public double getLibraryAccount() {
        return this.libraryAccount;
    }


    public void setTuitionAccount(double tuitionAccount) {
        this.tuitionAccount = tuitionAccount;
    }


    public double getTuitionAccount() {
        return this.tuitionAccount;
    }


    public void setDiningAccount(double diningAccount) {
        this.diningAccount = diningAccount;
    }

    
    public double getDiningAccount() {
        return diningAccount;
    }

    // This is the main method
    public static void main(String[] args) {

    }
}
