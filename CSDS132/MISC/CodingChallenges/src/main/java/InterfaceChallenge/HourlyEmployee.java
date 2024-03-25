package InterfaceChallenge;


public class HourlyEmployee extends Employee {
    private double hourlyRate = 0.;
    private double hoursWorked = 0.;

    public HourlyEmployee(String name, double rate) {
        super(name, rate);
    }

    /* Sets the Hourly Rate */
    public void setHourlyRate(double rate) {
        this.hourlyRate = rate;
    }

    /* Gets the Hourly Rate */
    public double getHourlyRate() {
        return hourlyRate;
    }

    /* Sets the number of hours worked */
    public void setHoursWorked(double hours) {
        this.hoursWorked = hours;
    }

    /* Gets the number of hours worked */
    public double getHoursWorked() {
        return hoursWorked;
    }
    @Override
    /* This is an overridden version of getSalary */
    public double getSalary() {
        return getHoursWorked()*getHourlyRate();
    }

    /* conditional practice */
    public boolean leapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    return true;
                }
                else 
                    return false;
            }
            else
                return false;
        }
        else
            return false;
    }

    /* More conditional practice, but with else if cases */
    public boolean leapYear2(int year) {
        if (year % 4 != 0) {
            return false;
        }
        else if (year % 100 != 0) {
            return true;
        }
        else if (year % 400 != 0) {
            return false;
        }
        else 
            return true;
    }
    public static void main(String[] args) {

    }
}
