package SessionSolutions;

public class Nurse extends Employee {
  private double overtimePay;
  private double overtimeLimit;

  public Nurse(String name, Hospital hospital, double hourlyPay,
      double hoursWorkedPerWeek, double overtimePay, double overtimeLimit) {
    super(name, hospital, hourlyPay, hoursWorkedPerWeek);
    this.overtimePay = overtimePay;
    this.overtimeLimit = overtimeLimit;
  }

  public Nurse(String name, Hospital hospital, double hourlyPay,
      double hoursWorkedPerWeek) {
    super(name, hospital, hourlyPay, hoursWorkedPerWeek);
    this.overtimePay = 1.5 * this.getHourlyPay();
    this.overtimeLimit = 40;
  }

  public double getOvertimePay() {
    return overtimePay;
  }

  public double getOvertimeLimit() {
    return overtimeLimit;
  }

  public void setOvertimePay(double overtimePay) {
    this.overtimePay = overtimePay;
  }

  public void setOvertimeLimit(double overtimeLimit) {
    this.overtimeLimit = overtimeLimit;
  }

  @Override
  public String toString() {
      return super.toString() + " as a nurse";
  }

  @Override
  public double getWeeklyPay() {
    if (getHoursWorkedPerWeek() <= getOvertimeLimit()) {
      return super.getWeeklyPay();
    }
    double overtimeHours = getHoursWorkedPerWeek() - getOvertimeLimit();
    return overtimeLimit * getHourlyPay() + overtimeHours * getOvertimePay();
  }
}