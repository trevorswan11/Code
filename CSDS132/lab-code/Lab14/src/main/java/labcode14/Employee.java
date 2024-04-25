package labcode14;

/** A class representing an employee. */

public class Employee {
    /** employee's first name */
    private String firstName;

    /** employee's last name */
    private String lastName;

    /** employee's middle name or middle initial */
    private String middleName;

    /** employee's street address */
    private String streetAddress;

    /** employee's home city */
    private String city;

    /** employee's home state */
    private String state;

    /** employee's home zip code */
    private String zipcode;

    /** employee's salary */
    private double salary;

    /** employee's employee number */
    private int number;

    /** the declared dependents, for tax purposes */
    private int numberDependents;

    /** the employee number for the next created employee */
    private static int nextEmployeeNumber = 1;

    /**
     * A constructor that sets the employee name
     * 
     * @param firstName  the employee's first name
     * @param middleName the employee's middle name or initial
     * @param lastName   the employee's last name
     */
    public Employee(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        number = nextEmployeeNumber++;
    }

    /**
     * Sets the employee's first name
     * 
     * @param name the employee's first name
     */
    public void setFirstName(String name) {
        firstName = name;
    }

    /**
     * Returns the employee's first name
     * 
     * @return the employee's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the employee's last name
     * 
     * @param name the employee's last name
     */
    public void setLastName(String name) {
        lastName = name;
    }

    /**
     * Returns the employee's last name
     * 
     * @return the employee's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the employee's middle name
     * 
     * @param name the employee's middle name or initial
     */
    public void setMiddleName(String name) {
        middleName = name;
    }

    /**
     * Returns the employee's middle name
     * 
     * @return the employee's middle name
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Gets the employee's name
     * 
     * @return the full name of the employee
     */
    public String getName() {
        return lastName + ", " + firstName + " " + middleName;
    }

    /**
     * Sets the name of the employee
     * 
     * @param firstName  the new first name for the employee
     * @param middleName the new middle name for the employee
     * @param lastName   the new last name for the employee
     */
    public void setName(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    /**
     * Sets the street address for the employee
     * 
     * @param address the employee's street address
     */
    public void setAddress(String address) {
        streetAddress = address;
    }

    /**
     * Returns the employee's street address
     * 
     * @return the street address for the employee
     */
    public String getAddress() {
        return streetAddress;
    }

    /**
     * Sets the city for the employee's home address
     * 
     * @param city the employee's home city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns the city for the employee's home address
     * 
     * @return the employee's home city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the state for the employee's home address
     * 
     * @param state the employee's home state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Returns the state for the employee's home address
     * 
     * @return the employee's home state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the zip code for the employee's home address
     * 
     * @param code the employee's home zip code
     */
    public void setZipCode(String code) {
        zipcode = code;
    }

    /**
     * Returns the zip code for the employee's home address
     * 
     * @return the employee's home zip code
     */
    public String getZipCode() {
        return zipcode;
    }

    /**
     * Sets the employee's salary
     * 
     * @param salary the employee's salary
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Returns the employee's salary
     * 
     * @return the salary of the employee
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Sets the number of dependents for the employee's tax forms
     * 
     * @param the number of dependents the employee is declaring
     */
    public void setNumberDependents(int numberDependents) {
        this.numberDependents = numberDependents;
    }

    /**
     * Returns the number of dependents the employee has claimed
     * 
     * @return the number of the employee's dependents
     */
    public int getNumberDependents() {
        return numberDependents;
    }

    /**
     * Returns the employee's employee number
     * 
     * @return the employee number of the employee
     */
    public int getNumber() {
        return number;
    }

    /**
     * gets the next employee number as a static (class) method
     * 
     * @return the value to be used for the number of the next Employee instance
     */
    public static int getNextEmployeeNumber() {
        return nextEmployeeNumber;
    }

    /**
     * Returns true if this employee makes more than e does
     * 
     * @param e an Employee to compare against this Employee
     * @return true if this employee earns more than e does
     */
    public boolean earnsMoreThan(Employee e) {
        return (this.getSalary() > e.getSalary());
    }

    /**
     * Overriding Object's toString method to give the name, number and salary of
     * the employee
     * 
     * @return a String representing the employee
     */
    @Override
    public String toString() {
        return this.getName() + " " + this.getNumber() + ":" + this.getSalary();
    }

    /**
     * Overriding Object's equals method. Two employees are equal if their employee
     * numbers
     * match.
     * 
     * @param o The employee to compare against this employee
     * @return if this Employee's employee number matches o's employee number
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Employee) {
            Employee e = (Employee) o;
            return this.getNumber() == e.getNumber();
        } else
            return false;
    }

    /**
     * Create and return an example employee for testing
     * 
     * @return an example Employee object
     */
    public static Employee testEmployee() {
        Employee e = new Employee("Eric", "W.", "Kaler");
        e.setAddress("2163 Harcourt Road");
        e.setCity("Cleveland");
        e.setState("OH");
        e.setZipCode("44106");
        e.setSalary(1000000.00);
        e.setNumberDependents(2);
        return e;
    }
}