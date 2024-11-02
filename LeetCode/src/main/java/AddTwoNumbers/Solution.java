package AddTwoNumbers;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution {
    /// INCORRECT IMPLEMENTATION
    private long extractValue(ListNode node) {
        Stack<Integer> stack = new Stack<>();
        ListNode current = node;
        while (current != null) {
            stack.push(current.val);
            current = current.next;
        }

        long value = 0;
        while (!stack.isEmpty()) {
            value = value * 10 + stack.pop();
        }
        System.out.println(value);
        return value;
    }

    private ListNode reconstructValue(long value) {
        Queue<Long> queue = new LinkedList<>();
        if (value == 0) {
            return new ListNode(0);
        }
        while (value > 0) {
            queue.add(value % 10);
            value = value / 10;
        }
        ListNode result = new ListNode(queue.poll().intValue());
        ListNode current = result;
        while (!queue.isEmpty()) {
            current.next = new ListNode(queue.poll().intValue());
            current = current.next;
        }
        return result;
    }

    @Deprecated
    public ListNode badAddTwoNumbers(ListNode l1, ListNode l2) {
        long sum = extractValue(l1) + extractValue(l2);
        return reconstructValue(sum);
    }

    /// CORRECT IMPLEMENTATION
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
        }
        return dummy.next;
    }

    public void test(ListNode l1, ListNode l2, int expected) {
        long sum = extractValue(l1) + extractValue(l2);
        System.out.println(sum == expected ? "PASS" : "FAIL");
    }

    public static void main(String[] args) {
        ListNode l1 = ListNode.makeFromArray(new int[] { 9 });
        ListNode l2 = ListNode.makeFromArray(new int[] { 1, 9, 9, 9, 9, 9, 9, 9, 9, 9 });
        System.out.println(l1.toString());
        System.out.println(l2.toString());
        // new Solution().test(l1, l2, 807);
        System.out.println(new Solution().addTwoNumbers(l1, l2).toString());
    }
}
