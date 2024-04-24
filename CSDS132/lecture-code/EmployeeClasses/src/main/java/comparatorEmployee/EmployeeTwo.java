package comparatorEmployee;

import java.util.Comparator;

/* A class that represents employees */

public class EmployeeTwo implements Comparable<EmployeeTwo> {

    // store the employee name
    private String name;

    // store the employee number
    private int number;

    // store the employee salary
    private double salary;

    // store the last employee number used
    private static int lastEmployeeTwoNumber = 0;

    /* A constructor that takes the name and number as input */
    public EmployeeTwo(String name, int number) {
        super();
        this.name = name;
        this.number = number;
        if (number > EmployeeTwo.lastEmployeeTwoNumber)
            EmployeeTwo.lastEmployeeTwoNumber = number;
    }

    /* A constructor that takes the name as input */
    public EmployeeTwo(String name) {
        this(name, EmployeeTwo.lastEmployeeTwoNumber + 1);
    }

    /* retrieve the employee salary */
    public double getSalary() {
        return this.salary;
    }

    /* change a salary */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /* get the name of the employee */
    public String getName() {
        return this.name;
    }

    /* set the name of the employee */
    public void setName(String name) {
        this.name = name;
    }

    /* get the number of the employee */
    public int getNumber() {
        return this.number;
    }

    /* Override the inherited toString so that it prints something useful */
    public String toString() {
        return this.getNumber() + ": " + this.getName();
    }

    /*
     * Override the inherited equals method so that two employees are equal
     * if they have the same name and same number
     */
    public boolean equals(Object o) {
        if (o instanceof EmployeeTwo) {
            EmployeeTwo e = (EmployeeTwo) o;
            return e.getNumber() == this.getNumber() &&
                    e.getName().equals(this.getName());
        } else
            return false;
    }

    /* return true if this employee makes more money than the input e */
    public boolean earnsMoreThan(EmployeeTwo e) {
        return this.getSalary() > e.getSalary();
    }

    /*
     * The default ordering for employees
     * The default is to order by number.
     */
    @Override
    public int compareTo(EmployeeTwo e) {
        return this.getNumber() - e.getNumber();
    }

    /*
     * Returns a comparator that compares employees by salary
     */
    public static Comparator<EmployeeTwo> getCompareBySalary() {
        return new CompareBySalary();
    }

    /*
     * Returns a comparator that compares employees by name.
     * This is an example of an anonymous class.
     */
    /*
     * public static Comparator<EmployeeTwo> getCompareByName() {
     * return new Comparator<EmployeeTwo>() {
     * public int compare(EmployeeTwo e1, EmployeeTwo e2) {
     * return e1.getName().compareTo(e2.getName());
     * }
     * };
     * }
     */

    public static Comparator<EmployeeTwo> getCompareByName() {
        return (e1, e2) -> e1.getName().compareTo(e2.getName());
    }

    /*
     * An example of a static nested class creates a comparator that
     * orders employees by salary.
     */
    public static class CompareBySalary implements Comparator<EmployeeTwo> {

        public int compare(EmployeeTwo e1, EmployeeTwo e2) {
            return (int) ((e1.getSalary() - e2.getSalary()) * 100);
        }
    }

    /*
     * An example of a non-static nested class.
     * Order employees by how close the employee is to "this" employee
     */
    public class CompareSalaryToThisEmployeeTwo implements Comparator<EmployeeTwo> {

        public int compare(EmployeeTwo e1, EmployeeTwo e2) {
            return (int) ((Math.abs(e1.getSalary() - EmployeeTwo.this.getSalary()) -
                    Math.abs(e2.getSalary() - EmployeeTwo.this.getSalary())) * 100);
        }
    }
}

