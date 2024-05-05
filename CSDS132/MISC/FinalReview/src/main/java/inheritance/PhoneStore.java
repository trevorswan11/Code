package inheritance;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A general phone class to handle the phone store.
 * 
 * @author Trevor Swan
 * @version CSDS132 - Final Review
 */
public class PhoneStore<T extends Phone> implements Iterable<Phone> {
    // A field to store the stock of the store
    private T[] stock;

    // A field to store the number of phones in the store
    private int numPhones;

    /**
     * Instantiates a store with given stock (array) size.
     * 
     * @param numPhones the total number of phones to sell
     */
    @SuppressWarnings("unchecked")
    public PhoneStore(int numPhones) {
        this.numPhones = numPhones;
        this.stock = (T[]) new Phone[numPhones];
    }

    /**
     * Creates a store for testing.
     * 
     * @param price the double price to set
     * @param numPhones the total number of phones
     * @param multiplier indicates whether index should be multiplied by price
     * @return a PhoneStore with filled in phones
     */
    @SuppressWarnings("unchecked")
    public static <T> PhoneStore<Phone> createStore(double price, int numPhones, boolean multiplier) {
        T[] arr = (T[]) new Phone[numPhones];
        for (int i = 0; i < numPhones; i++) {
            arr[i] = (T) (multiplier ? new IPhone(price * i, "12." + i, "iPhone " + (numPhones - i), 9000) :
                    new IPhone(price, "12." + i, "iPhone " + 2 * i, 9000));
        }
        PhoneStore<Phone> store = new PhoneStore<>(numPhones);
        for (T phone : arr) {
            store.add((Phone) phone);
        }
        return store;
    }

    /**
     * Returns the stock of the store.
     * 
     * @return a generic array for the held phones
     */
    public T[] getStock() {
        return this.stock;
    }

    /**
     * Returns the number of phones possible in the store.
     * 
     * @return an int that is the total number of phones
     */
    public int getNumPhones() {
        return this.numPhones;
    }

    /**
     * Attempts to remove a phone from the array and set it to null.
     * 
     * @param index and int that is the index in the array
     * @return a generic of type T which is the removed phone
     * @throws NoSuchElementException if element at the index is null or the index
     *                                is out of bounds
     */
    public T remove(int index) throws NoSuchElementException {
        try {
            // throw exception iff null is in the index
            if (this.getStock()[index] == null) {
                throw new NoSuchElementException("Element at index is null!");
            }

            // otherwise try to grab and remove the element and return it
            T indexElement = this.getStock()[index]; 
            this.getStock()[index] = null;
            return indexElement;
        } 
        
        // Out of bounds indices should throw no such element, not out of bounds
        catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchElementException("Index out of bounds!");
        }
    }

    /**
     * Attempts to add a phone to the next open position in stock.
     * 
     * @param phone a T element that is the phone to add
     * @throws IllegalStateException if all array indices are occupied
     */
    public void add(T phone) throws IllegalStateException {
        // Find the next open position
        int addIndex;
        for (addIndex = 0; addIndex <= this.getNumPhones() 
                && this.getStock()[addIndex] != null; addIndex++);
        
        // if the index is the number of phones then throw exception
        if (addIndex == this.getNumPhones()) {
            throw new IllegalStateException("All indices occupied!");
        }

        // otherwise set the element in the array to the input
        this.getStock()[addIndex] = phone;
    }

    /**
     * Creates an iterator for the store.
     * 
     * @return a usable iterator of the nested class
     */
    @Override
    public Iterator<Phone> iterator() {
        return new PhoneStoreIterator();
    }

    /**
     * Adds the specified phone to the phone store until the store is “full”.
     * 
     * @param phone the phone type to be added
     */
    public void replenishStock(T phone) {
        // Loop through the array
        for (int i = 0; i < this.getNumPhones() && this.iterator().hasNext(); i++) {
            if (this.getStock()[i] == null) {
                this.getStock()[i] = phone;
            }
        }
    }

    /**
     * A nested class to allow iterations through the phone store.
     * 
     * @author Trevor Swan
     * @version CSDS132 - Final Review
     */
    private class PhoneStoreIterator implements Iterator<Phone> {
        // A field for the index 
        private int index = 0;
        
        /**
         * Returns the current index.
         * 
         * @return the index the iterator is on
         */
        public int getIndex() {
            return this.index;
        }

        /**
         * Checks to see if there is a non-null element in the array.
         * 
         * @return true if the index is in range and non-null exists after index
         */
        @Override
        public boolean hasNext() {
            // Check the remaining values in the array
            for (int i = this.getIndex(); i < PhoneStore.this.getNumPhones(); i++) {
                if (PhoneStore.this.getStock()[i] != null) {
                    return true;
                }
            }
            return false;
        }

         /**
          * Returns the next element in the stock.
          * 
          * @return the element in the next non-null spot.
          * @throws NoSuchElementException if there is no non-null element in the array
          */
        @Override
        public Phone next() throws NoSuchElementException {
            // Throw exception if there are no non-null elements
            if (!this.hasNext()) {
                throw new NoSuchElementException("All elements are null!");
            }

            // return the element in the next position and increment index
            return PhoneStore.this.getStock()[this.index++];
        }
    }
}
