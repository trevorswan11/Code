package SessionSolutions;

public class Employee {
  private String name;
  private Hospital hospital;
  private double hourlyPay;
  private double hoursWorkedPerWeek;
  private int employeeID;
  
  public Employee(String name, Hospital hospital, double hourlyPay, double hoursWorkedPerWeek) {
    this.name = name;
    this.hospital = hospital;
    this.hourlyPay = hourlyPay;
    this.hoursWorkedPerWeek = hoursWorkedPerWeek;
    this.employeeID = Hospital.generateEmployeeID();
  }
  
  public String getName() {
    return name;
  }

  public Hospital getHospital() {
    return hospital;
  }

  public double getHourlyPay() {
    return hourlyPay;
  }
  
  public double getHoursWorkedPerWeek() {
    return hoursWorkedPerWeek;
  }

  public int getEmployeeID() {
    return employeeID;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setHospital(Hospital hospital) {
    this.hospital = hospital;
  }

  public void setHourlyPay(double hourlyPay) {
    this.hourlyPay = hourlyPay;
  }

  public void setHoursWorkedPerWeek(double hoursWorkedPerWeek) {
    this.hoursWorkedPerWeek = hoursWorkedPerWeek;
  }

  public double getWeeklyPay() {
    return getHoursWorkedPerWeek() * getHourlyPay();
  }

  @Override
  public String toString() {
    return getName() + "(" + getEmployeeID() + ") works at " + getHospital().getName();
  }
}