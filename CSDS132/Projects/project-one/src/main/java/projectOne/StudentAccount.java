package projectOne;

/* Trevor Swan
 * StudentAccount Class
 * Represents all of the financial information and accounts of a student
 *  Handles all the front end operations regarding created and relevant accounts
 * CSDS132 Spring 2024 - Project 1
 */

public class StudentAccount extends Account {
    // This field contains the student's name
    private String studentName = "John Doe";
    
    // This field contains the students Address
    private String studentAddress = "10900 Euclid Ave, Cleveland, OH, 44106";

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
        /* The following variables are used to keep track of values in this method only:
         * balanceSum: total balance from all existing accounts. 
         * libraryAmt: balance of the library account, if it exists
         * tuitionAmt: balance of the tuition account, if it exists
         * diningAmt: balance of the dining account, if it exists 
         * They are all stored in their respective conditionals to not take up too much balance
        */

        // This conditional performs the appropriate action if and only if there is an existing account
        double balanceSum = 0;
        if (LibraryAccount.getAccountNumber() != null) {
            double libraryAmt = LibraryAccount.getBalance();
            balanceSum = balanceSum + libraryAmt;
        }
        
        // This conditional performs the appropriate action if and only if there is an existing account
        if (TuitionAccount.getAccountNumber() != null) {
            double tuitionAmt = TuitionAccount.getBalance();
            balanceSum = balanceSum + tuitionAmt; 
        }

        // This conditional performs the appropriate action if and only if there is an existing account
        if (DiningAccount.getAccountNumber() != null) {
            double diningAmt = DiningAccount.getBalance();
            balanceSum = balanceSum + diningAmt;
        }

        return (balanceSum - super.getBalance());
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
        // This variable contains the accounts difference between their current balance and the input
        double difference = this.getBalance() - chargeAmount;

        // This conditional only does the nested code if the above difference is positive and a tuition account exists for the student
        if (difference > 0 && TuitionAccount.getAccountNumber() != null) {
            TuitionAccount.charge(difference);
        }

        // Otherwise, the actual accounts balance is set to the negation of difference
        else {
            super.setBalance(-1 * difference);
        }
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
        // This variable holds the remaining balance available to be paid
        double remainingCredit = creditAmount; 

        // Ensures that the code will only run if a correct value is inputted, this is for robust reasons
        if (remainingCredit < 0) {
            System.err.println("Please pay with a positive value!");
        }

        // If the user inputted the right type of value, then the code proceeds as usual
        else {
            // Checks to see if the tuition account exists 
            if (TuitionAccount.getAccountNumber() != null) {
                // these variables holds the monthly payment amount and amount paid this month for the tuition account
                double tuitionMonthlyAmt = TuitionAccount.getMonthlyPayment();
                double tuitionMonthlyPaid = TuitionAccount.getAmountPaidThisMonth();
                
                // this variable holds the amount owed by the account
                double tuitionOwed = tuitionMonthlyAmt - tuitionMonthlyPaid;

                // The following only proceeds if the student has tuition to pay
                if (tuitionOwed > 0) {
                    // This behavior should only be exhibited if the amount remaining to credit is greater than the amount owed
                    if (remainingCredit > tuitionOwed) {
                        TuitionAccount.credit(tuitionOwed);
                        remainingCredit = remainingCredit - tuitionOwed;
                    } 

                    // This behavior should only be exhibited if the remaining credit is less or equal to than the amount owed
                    else if (remainingCredit <= tuitionOwed) {
                        TuitionAccount.credit(remainingCredit);
                        remainingCredit = 0;
                    }
                }
            }

            // Checks to see if the dining account exists and there is still available credit
            if (DiningAccount.getAccountNumber() != null && remainingCredit > 0) {
                // these variables holds the monthly payment amount and amount paid this month for the dining account
                double diningMonthlyAmt = DiningAccount.getMonthlyPayment();
                double diningMonthlyPaid = DiningAccount.getAmountPaidThisMonth();
                
                // this variable holds the amount owed by the account
                double diningOwed = diningMonthlyAmt - diningMonthlyPaid;

                // The following only proceeds if the student has dining fees to pay
                if (diningOwed > 0) {
                    // This behavior should only be exhibited if the amount remaining to credit is greater than the amount owed
                    if (remainingCredit > diningOwed) {
                        DiningAccount.credit(diningOwed);
                        remainingCredit = remainingCredit - diningOwed;
                    } 

                    // This behavior should only be exhibited if the remaining credit is less or equal to than the amount owed
                    else if (remainingCredit <= diningOwed) {
                        DiningAccount.credit(remainingCredit);
                        remainingCredit = 0;
                    }
                }
            }

            // Checks to see if a library account exists for this student and that they still have credit available
            if (LibraryAccount.getAccountNumber() != null && remainingCredit > 0) {
                // This variable holds the amount that is owed to the library
                double libraryOwed = LibraryAccount.getBalance();

                // The following proceeds only if there are actual fees to pay
                if (libraryOwed > 0) {
                    // This behavior should only be exhibited if the amount in credit is greater than the amount owed 
                    if (remainingCredit > libraryOwed) {
                        LibraryAccount.credit(libraryOwed);
                        remainingCredit = remainingCredit - libraryOwed;
                    }

                    // This behavior should only be exhibited if the amount in credit is less than the amount owed
                    else if (remainingCredit < libraryOwed) {
                        LibraryAccount.credit(remainingCredit);
                        remainingCredit = 0;
                    }
                }
            }
        }

        // This final conditional adds any left over credit to the accounts balance if it is greater than 0
        if (remainingCredit > 0) {
            this.setBalance(this.getBalance() + remainingCredit);
        }
    }
    // This is the main method
    public static void main(String[] args) {
        
    }
}
