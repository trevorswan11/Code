package SummarySession;

/* Problem 26 on the Summary Sheet */

public class Employee {
    private String name;
    private Hospital hospital;
    private double hourlyPay;
    private double hoursWorkedPerWeek;
    private int employeeID;

    public Employee(String name, Hospital hospital, double hourlyPay, double hoursWorkedPerWeek) {
        this.setName(name);
        this.setHospital(hospital);
        this.setHourlyPay(hourlyPay);
        this.setHoursWorkedPerWeek(hoursWorkedPerWeek);
    }

    // Getter and setter for 'name'
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter for 'hospital'
    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    // Getter and setter for 'hourlyPay'
    public double getHourlyPay() {
        return hourlyPay;
    }

    public void setHourlyPay(double hourlyPay) {
        this.hourlyPay = hourlyPay;
    }

    // Getter and setter for 'hoursWorkedPerWeek'
    public double getHoursWorkedPerWeek() {
        return hoursWorkedPerWeek;
    }

    public void setHoursWorkedPerWeek(double hoursWorkedPerWeek) {
        this.hoursWorkedPerWeek = hoursWorkedPerWeek;
    }

    // Getter and setter for 'employeeID'
    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
}
