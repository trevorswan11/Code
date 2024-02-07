// Use this import to get user input from the command line
import java.util.Scanner;

public class Employee {
    private String name;
    private int number;

    /* A constructor that takes the name and number as input */
    public Employee(String name, int number) {
        this.name = name;
        this.number = number;
    }

    /* A constructor that takes the name as input */
    public Employee(String name) {
        this.name = name;
    }

    /** Returns employee name
     * @return String value out of the employee
     */
    public String getName() {
        return name;
    }

    /** Returns the employees number
     * @return int value out as employee number
     */
    public int getNumber() {
        return number;
    }

    @Override
    /* override the inherited toString method */
    public String toString() {
        return "Employee name: "+this.getName()+", number:" +this.getNumber();  
    }


    public boolean equals(Object o) {
        // This instanceof command is needed because literally ANY object can be passed through this method, which we don't want!
        if (o instanceof Employee) {
            Employee e = (Employee)o; // Typecast the o input to and employee instance
        return this.getNumber() == e.getNumber() && this.getName().equals(e.getName());
        } 
        else {
            return false;
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Employee e1 = new Employee("Trevor");
        Employee e2 = new Employee("Jeffery", 4);
        System.out.println("Please input 1 for e1 and 2 for e2:");
        int i = scan.nextInt();
        if (i == 1) {
            System.out.println(e1.toString());
        }
        else if (i == 2) {
            System.out.println(e2.toString());
        }
        else {
            System.out.println("invalid input");
        }
        scan.close();
    }
}
