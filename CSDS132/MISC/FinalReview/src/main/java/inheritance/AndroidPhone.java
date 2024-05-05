package inheritance;

/**
 * A general phone class to handle Android phones.
 * 
 * @author Trevor Swan
 * @version CSDS132 - Final Review
 */
public class AndroidPhone extends Phone {
    // A field to store the android's company
    private final String company;

    /**
     * Creates an iPhone with parent constructor.
     * 
     * @param price the double price of the phone
     * @param iOSVersion the version of the OS
     * @param name the name of the phone
     * @param numPixels the int number of pixels
     */
    public AndroidPhone(double price, String company, String name, int numPixels) {
        super(price, "AndroidOS", name, numPixels);
        this.company = company;
    }

    /**
     * Retrieves the company for the android
     * 
     * @return a String that is the company
     */
    public String getCompany() {
        return this.company;
    }

    /**
     * Compares two Androids and checks their ios versions.
     * 
     * @param obj an object to compare to the instance
     * @return true if super call is true and companies are equal
     */
    @Override
    public boolean equals(Object obj) {
        // Check super implementation
        if (!super.equals(obj)) {
            return false;
        }
        AndroidPhone comparison = (AndroidPhone) obj;

        // Check the iOS versions
        return this.getCompany().equals(comparison.getCompany());
    }

     /**
     * Formats the Android with specified company and name
     * 
     * @return the String representation of the Android
     */
    @Override
    public String toString() {
        return this.getCompany() + " " + this.getName();
    }
}
