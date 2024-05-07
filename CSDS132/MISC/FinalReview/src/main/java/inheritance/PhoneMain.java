package inheritance;

import java.util.Arrays;
import java.util.Iterator;

public class PhoneMain {
    /**
     * Prints the names of all the phones in a PhoneStore
     * 
     * @param store a PhoneStore to iterate through
     */
    public static void printPhones(PhoneStore<?> store) {
        // create an iterator and move through the list to print
        Iterator<Phone> it = store.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    /**
     * Calculates the total price of the phones being sold.
     * 
     * @param store a PhoneStore to iterate through
     * @return a double value that is the summed price
     */
    public static double totalPrice(PhoneStore<?> store) {
        // store the sum
        double sum = 0;
        // create an iterator and move through the list to print
        Iterator<Phone> it = store.iterator();
        while (it.hasNext()) {
            sum += it.next().getPrice();
        }
        return sum;
    }

    /**
     * Prints the phones in order of increasing price.
     * 
     * @param store a PhoneStore to iterate through
     */
    public static void printByPrice(PhoneStore<?> phoneStore) {
        Phone[] phones = phoneStore.getStock();
        Arrays.sort(phones, Phone.compareByPrice());
        Arrays.stream(phones).forEach(System.out::println);
    }

    public static <T> void main(String[] args) {
        PhoneStore<Phone> store = PhoneStore.createStore(900, 11, true);
        PhoneMain.printByPrice(store);
        System.out.println(PhoneMain.totalPrice(store));
    }
}
