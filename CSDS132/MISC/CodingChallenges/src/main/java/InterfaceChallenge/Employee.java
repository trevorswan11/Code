package InterfaceChallenge;

/* This class represents employees */
public class Employee extends Object implements Friendly {
  
  // a field to store the employee name
  private String name;
  
  // a field to store the employee salary
  private double salary;
  
  // a field to store the employee number
  private final int number;
  
  // a field to store the last employee number used
  private static int lastEmployeeNumber = 0;
  
  /* A constructor to make an employee with the given name and salary */
  public Employee(String name, double salary) {
    this(Employee.lastEmployeeNumber + 1, name, salary);
  }
  
  /* A constructor to make an employee with the given name, number, and salary */
  public Employee(int number, String name, double salary) {
    super();
    this.number = number;
    this.name = name;
    this.salary = salary;
    if (this.number > Employee.lastEmployeeNumber)
      Employee.lastEmployeeNumber = number;
  }
  
  /* get the name of the employee */
  public String getName() {
    return name;
  }
  
  /* set the name of the employee */
  public void setName(String name) {
    this.name = name;
  }
  
  /* get the salary of the employee */
  public double getSalary() {
    return this.salary;
  }
  
  /* set the salary of the employee */
  public void setSalary(double salary) {
    this.salary = salary;
  }
  
  /* get the employee number */
  public int getNumber() {
    return number;
  }
  
  /* change the behavior of the inherited toString */
  public String toString() {
    return getNumber() + ": " + getName();
  }
  
  /* change the behavior of the inherited equals method.  Two employee instances are the same
   * if they have the same number and name 
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof Employee) {
      Employee e = (Employee)o;
      return (this.getNumber() == e.getNumber() && this.getName().equals(e.getName()));
    }
    return false;
  }
  
  /* Returns true if this employee earns more than the input employee */
  public boolean earnsMoreThan(Employee e) {
    return this.getSalary() > e.getSalary();
  }
  
  /* A friendly greeting */
  public String greeting() {
    return "Hello, I am " + getName();
  }
}