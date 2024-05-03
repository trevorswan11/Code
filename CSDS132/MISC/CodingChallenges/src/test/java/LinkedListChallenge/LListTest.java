package LinkedListChallenge;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.LinkedList;

public class LListTest {
    // The model array and list for conversions
    private Integer[] array = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    private LinkedList<Integer> list = new LinkedList<Integer>();

    // A method to fail with a descriptive message
    private static void sizeFail() {
        fail("The sizes of the arguments are not equal!");
    }

    // A method to fail with a descriptive message
    private static void elementFail(int index) {
        fail("Element mismatch at index " + index);
    }

    /**
     * A helper method to compare a list with another list
     *
     * @param list  the list to analyze
     * @param array the array to analyze
     */
    private static <T> void listListEquals(LinkedList<T> list1, LinkedList<T> list2) {
        // First compare the sizes of the objects
        if (list1.size() != list2.size()) {
            LListTest.sizeFail();
        } else {
            // Compare each individual element in the respective indices
            for (int i = 0; i < list1.size(); i++) {
                if (list1.get(i) != list2.get(i)) {
                    LListTest.elementFail(i);
                }
            }
        }
    }

    // Test the to linked list method
    @Test
    public void toLinkedList() {
        // Compare equal objects
        for (int i = 0; i < 10; i++) list.add(i, i);
        LinkedList<Integer> tester = LList.arrayToList(array);
        LListTest.listListEquals(list, tester);

        // change one element
        list.set(2, 3);
        try {
            LListTest.listListEquals(list, tester);
        } catch (AssertionError e) {}

        // remove one element
        list.remove(2);
        try {
            LListTest.listListEquals(list, tester);
        } catch (AssertionError e) {}
    }

    // Test the to array method
    @Test
    public void toArrayTest() {
        // Compare equal objects
        for (int i = 0; i < 10; i++) list.add(i, i);
        Integer[] tester = LList.listToArray(list);
        assertArrayEquals(array, tester);

        // Change one element
        array[2] = 3;
        try {
            assertArrayEquals(array, tester);
        } catch (AssertionError e) {}

        // Remove one element
        array = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
        try {
            assertArrayEquals(array, tester);
        } catch (AssertionError e) {}
    }

    // Test the equality method
    @Test
    public void equalTest() {
        // Compare equal objects
        for (int i = 0; i < 10; i++) list.add(i, i);
        assertTrue(LList.arrayListEquals(array, list));

        // change one element
        list.set(2, 3);
        assertFalse(LList.arrayListEquals(array, list));

        // remove one element
        list.remove(2);
        assertFalse(LList.arrayListEquals(array, list));
    }
}
