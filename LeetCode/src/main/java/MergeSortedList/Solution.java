package MergeSortedList;

import java.util.LinkedList;
import java.util.List;

/**
 * You are given the heads of two sorted linked lists list1 and list2.
 * 
 * Merge the two lists into one sorted list. The list should be made by splicing
 * together the nodes of the first two lists.
 * 
 * !Return the head of the merged linked list.
 */
@SuppressWarnings("unused")
public class Solution {
    // Lists to be tested
    static ListNode list1;
    static ListNode list2;
    static ListNode list;

    /**
     * Given definition for a singly-linked list.
     */
    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * Solution for leetcode question
     * 
     * !O(n + m) time
     * !O(n + m) space due to recursion
     */
    public static ListNode mergeTwoListsRecursively(ListNode list1, ListNode list2) {
        // Only need to merge lists that are not null
        if (list1 != null && list2 != null) {
            // recursively call the function with the lesser one being list1
            if (list1.val < list2.val) {
                list1.next = mergeTwoListsRecursively(list1.next, list2);
                return list1;
            } 
            
            // reverse case of the above conditional
            else {
                list2.next = mergeTwoListsRecursively(list1, list2.next);
                return list2;
            }
        }

        // Return the other node if one is null
        if (list1 == null) {
            return list2;
        }
        return list1;
    }

    /**
     * Solution for leetcode question
     * 
     * !O(n + m) time
     * !O(1) space - no recursion
     */
    public static ListNode mergeTwoListsIteratively(ListNode list1, ListNode list2) {
        // Create a dummy node with a traversal pointer
        ListNode result = new ListNode();
        ListNode trav = result;

        // traverse both lists until one is null
        while (list1 != null && list2 != null) {
            // append the lesser one to trav and move forward
            if (list1.val < list2.val) {
                trav.next = list1;
                list1 = list1.next;
            } 
            
            // greater or equal should append the other list
            else {
                trav.next = list2;
                list2 = list2.next;
            }
            trav = trav.next; 
        }

        // append the remaining list if the first one is null as lists are given to be sorted
        trav.next = (list1 != null) ? list1 : list2;
        return result.next;
    }
    
    /**
     * Reverses the linked list in constant space with linear time
     * 
     * @param head The head of the ListNode list to reverse
     * @return The head of the reversed list
     */
    public static ListNode reverseList(ListNode head) {
        ListNode previous = null;
        ListNode current = head;
        ListNode next = null;

        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }

    /**
     * Prints the contents of the linked list in order
     * 
     * @param list The head of the ListNode list to print 
     */
    private static void printList(ListNode list) {
        while (list != null) {
            System.out.print(list.val + " ");
            list = list.next;
        }
        System.out.println();
    }

    // Case 1
    private static void testOne() {
        // Test recursively
        list1 = new ListNode(1, new ListNode(2, new ListNode(4, null)));
        list2 = new ListNode(1, new ListNode(2, new ListNode(4, null)));
        printList(mergeTwoListsRecursively(list1, list2));

        // Test iteratively
        list1 = new ListNode(1, new ListNode(2, new ListNode(4, null)));
        list2 = new ListNode(1, new ListNode(2, new ListNode(4, null)));
        printList(mergeTwoListsIteratively(list1, list2));

        // Test reverse
        list = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        printList(reverseList(list));
    }

    // Case 2
    private static void testTwo() {
        // Test recursively
        list1 = null;
        list2 = null;
        printList(mergeTwoListsRecursively(list1, list2));

        // Test iteratively
        list1 = null;
        list2 = null;
        printList(mergeTwoListsIteratively(list1, list2));
    }

    // Case 3
    private static void testThree() {
        // Test recursively
        list1 = null;
        list2 = new ListNode(0, null);
        printList(mergeTwoListsRecursively(list1, list2));

        // Test iteratively
        list1 = null;
        list2 = new ListNode(0, null);
        printList(mergeTwoListsIteratively(list1, list2));
    }

    /**
     * Test the provided leetcode cases
     */
    public static void main(String[] args) {
        // Case 1
        testOne();
        
        // Case 2
        testTwo();

        // Case 3
        testThree();
    }
}
