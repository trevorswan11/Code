package inheritance;

/**
 * A general phone class to handle iPhones.
 * 
 * @author Trevor Swan
 * @version CSDS132 - Final Review
 */
public class IPhone extends Phone {
    // A field to store the version of ios
    private String iOSVersion;

    /**
     * Creates an iPhone with parent constructor.
     * 
     * @param price the double price of the phone
     * @param iOSVersion the version of the OS
     * @param name the name of the phone
     * @param numPixels the int number of pixels
     */
    public IPhone(double price, String iOSVersion, String name, int numPixels) {
        super(price, "iOS", name, numPixels);
        this.setIOSVersion(iOSVersion);
    }

    /**
     * Gets the OS version
     * 
     * @return the OS version as a String
     */
    public String getIOSVersion() {
        return this.iOSVersion; 
    }

    /**
     * Sets the OS version
     * 
     * @param iOSVersion the desired version as a String
     */
    public void setIOSVersion(String iOSVersion) {
        this.iOSVersion = iOSVersion;
    }
    
    /**
     * Compares two iPhones and checks their ios versions.
     * 
     * @param obj an object to compare to the instance
     * @return true if super call is true and versions are equal
     */
    @Override
    public boolean equals(Object obj) {
        // Check super implementation
        if (!super.equals(obj)) {
            return false;
        }
        IPhone comparison = (IPhone) obj;

        // Check the iOS versions
        return this.getIOSVersion().equals(comparison.getIOSVersion());
    }

    /**
     * Formats the iPhone with specified name and version
     * 
     * @return the String representation of the iPhone
     */
    @Override
    public String toString() {
        return this.getName() + ", iOS " + this.getIOSVersion();
    }
}
