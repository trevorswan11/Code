/* Trevor Swan
* Curtis Li
* Simon Eskin
* Lab 6
* 2/22/24
*/

/**
 * A collection of methods related to int[].
 */
public class ArrayLab {

    /**
     * Return whether k is in array[start..end].
     * Precondition: the length of array is at least end+1
     * @param array the array of int values.
     * @param start the index of the start of the range to be searched.
     * @param end the index of the end of the range to be searched.
     * @param k the number to search for.
     * @return true if k is in array[start..end]
     */
    public static boolean contains(int[] array, int start, int end, int k) {
        // initialize a result to false so that false is returned if conditions not met
        boolean inArray = false;

        // If the indices are valid, then go through the array until the value is found
        while (start <= end && inArray == false) {
            if (array[start] == k) {
                inArray = true;
            }
            else {
                // increment the index if k isn't found
                start = start + 1;
            }
        }
        
        // Return the boolean result
        return inArray;
    }

    /**
     * Create a String that has the contents of list[0..n-1] on one line,
     * separated by spaces and surrounded by { and }.
     * 
     * @param list the sorted array.
     * @param n    the number of items in list to put in the string.
     * @return the first n elements of the array, separated by spaces, and
     *         surrounded by { and }.
     */
    public static String arrayToString(int[] list, int n) {
        // Initialize an index to keep track of array indices
        int idx = 0;

        // If n > the length of the array, return null
        if (n > list.length) {
            n = list.length;
        }

        // Create string builder with { at the start
        StringBuilder arrayString = new StringBuilder("{");

        // Loop through the array and append n-1 characters to the string
        while (idx < n) {
            arrayString.append(list[idx]);
            if (idx != n - 1) {
                // Append a space as long as you aren't at the last value in the array
                arrayString.append(' ');
            }

            // Increment the index
            idx++;
        }

        // Append a closing } and return the array as a string
        arrayString.append('}');
        return arrayString.toString();
    }

    /**
     * Remove k from list[0..n-1].
     * Precondition: list[0..n-1] is sorted, and k is an element of list[0..n-1].
     * Postcondition: list[0..n-2] is sorted.
     * 
     * @param list the sorted array.
     * @param n    the number of items in list.
     * @param k    the number to remove from list.
     */
    public static void remove(int[] list, int n, int k) {
        // Create indices for the loop
        int i = 0;
        int j = 0;
        while (i < n) {
            if (list[i] != k) {
                list[j] = list[i];
                j++;
            }
            i++;
        }
    }

    /**
     * Insert k into list where it belongs.
     * Precondition: list[0..n-1] is sorted, and list[n] is unused.
     * Postcondition: list[0..n] is sorted.
     * 
     * @param list the sorted array.
     * @param n    the number of items in list.
     * @param k    the number to add to list.
     */
    public static void insert(int[] list, int n, int k) {
        /* Didn't get to this :( */
    }

    // This is the main method
    public static void main(String[] args) {

    }
}
