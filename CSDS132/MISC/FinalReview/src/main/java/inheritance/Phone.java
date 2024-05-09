package inheritance;

import java.util.Comparator;

/**
 * A general phone class to handle all phones.
 * 
 * @author Trevor Swan
 * @version CSDS132 - Final Review
 */
public abstract class Phone {
    // A field to store the price
    private double price;

    // A field to store the OS
    private final String operatingSystem;

    // A field to store the name of the phone
    private final String name;

    // A field to store number of pixels
    private final int numPixels;

    /**
     * Requires all of the fields associated with a phone to be declared upon
     * instantiation.
     * 
     * @param price           a double for the price
     * @param operatingSystem a String that is the OS
     * @param name            the String name of the phone
     * @param numPixels       an int to describe amount of pixels
     */
    public Phone(double price, String operatingSystem, String name, int numPixels) {
        this.setPrice(price);
        this.operatingSystem = operatingSystem;
        this.name = name;
        this.numPixels = numPixels;
    }

    /**
     * Retrieves the price of the phone.
     * 
     * @return the double phone price
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Sets the price of the phone.
     * 
     * @param price the double price to use
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Retrieves the OS of the phone
     * 
     * @return the String OS
     */
    public String getOS() {
        return this.operatingSystem;
    }

    /**
     * Retrieves the name of the phone.
     * 
     * @return the String name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the number of pixels.
     * 
     * @return the int number of pixels
     */
    public int getNumPixels() {
        return this.numPixels;
    }

    /**
     * Overrides the equals method to compare OS and pixels.
     * 
     * @param obj the Object to compare with this
     * @return true if the OS and pixel number are equal
     */
    @Override
    public boolean equals(Object obj) {
        // check instance, typecast if pass
        if (!(obj instanceof Phone)) {
            return false;
        }
        Phone compare = (Phone) obj;

        // compare the OS
        if (!this.getOS().equals(compare.getOS())) {
            return false;
        }

        // compare the pixels
        if (this.getNumPixels() != compare.getNumPixels()) {
            return false;
        }
        return true;
    }
    
    /**
     * Returns a comparator for two phones.
     * 
     * @return a comparator
     */
    public static Comparator<Phone> getComparator() {
        return new PhoneComparison();
    }

    /**
     * Compares two phones based off of just price.
     * 
     * @param phone1 the first phone
     * @param phone2 the second phone to reference
     * @return a negative integer, zero, or a positive integer as the
     *         first argument is less than, equal to, or greater than the
     *         second
     */
    public static Comparator<Phone> compareByPrice() {
        return (phone1, phone2) -> (phone1 == null || phone2 == null) ? 1 :
                Double.compare(phone1.getPrice(), phone2.getPrice());
    }

    /**
     * A private nested class which handles comparisons.
     * 
     * @author Trevor Swan
     * @version CSDS132 - Final Review
     */
    private static class PhoneComparison implements Comparator<Phone> {
        /**
         * Compares two phones based off of pixels and price.
         * 
         * @param phone1 the first phone
         * @param phone2 the second phone to reference
         * @return a negative integer, zero, or a positive integer as the
         *         first argument is less than, equal to, or greater than the
         *         second
         */
        public int compare(Phone phone1, Phone phone2) {
            // reduce verbosity by retrieving fields
            int phone1Pixels = phone1.getNumPixels();
            int phone2Pixels = phone2.getNumPixels();
            double phone1Price = phone1.getPrice();
            double phone2Price = phone1.getPrice();
            
            // first compare pixels
            if (phone1Pixels < phone2Pixels) {
                return phone1Pixels - phone2Pixels;
            } else if (phone1Pixels > phone2Pixels) {
                return phone1Pixels - phone2Pixels;
            }

            // now compare price
            if (phone1Price < phone2Price) {
                return (int) (phone1Price - phone2Price);
            } else if (phone1Price > phone2Price) {
                return (int) (phone1Price - phone2Price);
            }

            // the phones must be equal
            return 0;
        }
    }
}
