package SummarySession;

/* Problem 25 on the Review WS */

public class Hospital {
    private String name;
    private String address;
    private int numEmployees;
    private static int nextID = 1;

    public Hospital(String name, String address, int numEmployees) {
        this.setName(name);
        this.setAddress(address);
        this.setNumEmployees(numEmployees);
    }

    public static int generateEmployeeID() {
        return nextID++;
    }
    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public int getNumEmployees() {
        return numEmployees;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumEmployees(int numEmployees) {
        this.numEmployees = numEmployees;
    }

    /* Java 8 required Object here, some other versions let you put 'Hospital' */
    @Override
    public boolean equals(Object otherHospital) {
        if (otherHospital instanceof Hospital) {
            Hospital otherHospital2 = (Hospital)otherHospital;
            return otherHospital2.getName().equals(this.getName()) && otherHospital2.getAddress().equals(this.getAddress());
        }

        return false;
    }

    @Override 
    public String toString() {
        return "Hospital" + this.getName() + " is located at " + this.getAddress();
    }
}

