package SessionSolutions;

public class Hospital {
  private String name;
  
  private String address;
  
  private int numEmployees;
  
  private static int nextID = 1; // static makes this variable the same for all instances of Hospital
  
  public Hospital(String name, String address, int numEmployees) {
    this.name = name;
    this.address = address;
    this.numEmployees = numEmployees;
  }
  
  /**
   * Static since this method does not depend on a specific instance of Hospital
   */
  public static int generateEmployeeID() {
    int tempID = nextID;
    nextID = nextID + 1; // increment nextID before returning, since return terminates the method execution
    return tempID;
  }
  
  public String getName() {
    return name;
  }
  
  public String getAddress() {
    return address;
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

  /**
   * Overridden methods need to have the same method signature as their parent class
   */
  @Override
  public boolean equals(Object otherHospital) {
    if (otherHospital instanceof Hospital) {
      Hospital otherHos = (Hospital) otherHospital;
      return otherHos.getName().equals(this.getName()) && otherHos.getAddress().equals(this.getAddress());
    }
    return false;
  }
  
  @Override
  public String toString() {
    return "Hospital " + getName() + " is located at " + getAddress();
  }
}