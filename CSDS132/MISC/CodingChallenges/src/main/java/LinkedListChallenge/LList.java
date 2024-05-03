package LinkedListChallenge;

import java.lang.reflect.Array;
import java.util.LinkedList;

public class LList {
    /**
     * A method to convert an array to a linked list.
     * 
     * @param array The array to convert
     * @return The converted array as a list
     */
    public static <T> LinkedList<T> arrayToList(T[] array) {
        LinkedList<T> conversion = new LinkedList<T>();
        for (int i = 0;  i < array.length; i++) {
            conversion.add(i, array[i]);
        }
        return conversion;
    }

    /**
     * A method to convert a linked list to an array.
     * 
     * @param list The list to convert
     * @return The converted list as an array
     */
    public static <T> T[] listToArray(LinkedList<T> list) {
        @SuppressWarnings("unchecked")
        T[] conversion = (T[]) Array.newInstance(list.get(0).getClass(), list.size());
        return list.toArray(conversion);
    }

    /**
     * A method to compare a list to an array
     * 
     * @param array the array to analyze
     * @param list the list to analyze
     * @return true if the list and array are the same size and have equivalent indices
     */
    public static <T> boolean arrayListEquals(T[] array, LinkedList<T> list) {
        // First compare the sizes of the objects
        if (list.size() != array.length) {
            return false;
        } else {
            // Compare each individual element in the respective indices
            for (int i = 0; i < array.length; i++) {
                if (list.get(i) != array[i]) {
                    return false;
                }
            }
        }

        return true;
    }
}
