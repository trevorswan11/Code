package AddTwoNumbers;

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    @Override
    public String toString() {
        ListNode current = this;
        StringBuilder sb = new StringBuilder();
        while (current != null) {
            sb.append(current.val).append(current.next == null ? "" : " -> ");
            current = current.next;
        }
        return sb.toString();
    }

    public static ListNode makeFromArray(int[] arr) {
        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3, null)));
        System.out.println(l1.toString());
    }
}
