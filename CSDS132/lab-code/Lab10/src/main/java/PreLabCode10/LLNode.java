package PreLabCode10;

/**
 * The node of a linked list
 */
public class LLNode<T> {
    /** the element stored in the node */
    private T element;

    /** a reference to the next node of the list */
    private LLNode<T> next;

    /**
     * the constructor
     * 
     * @param element the element to store in the node
     * @param next    a reference to the next node of the list
     */
    public LLNode(T element, LLNode<T> next) {
        this.element = element;
        this.next = next;
    }

    /**
     * Returns the element stored in the node
     * 
     * @return the element stored in the node
     */
    public T getElement() {
        return element;
    }

    /**
     * Returns the next node of the list
     * 
     * @return the next node of the list
     */
    public LLNode<T> getNext() {
        return next;
    }

    /**
     * Changes the node that comes after this node in the list
     * 
     * @param next the node that should come after this node in the list. It can be
     *             null.
     */
    public void setNext(LLNode<T> next) {
        this.next = next;
    }

    /**
     * Step one of the prelab assignment
     */
    public static void step1() {
        // Create a linked list in 3 lines
        System.out.println("Three line linked list:");
        LLNode<Integer> listHead1 = new LLNode<>(1, null);
        listHead1.setNext(new LLNode<>(2, null));
        listHead1.getNext().setNext(new LLNode<>(3, null));
        System.out.println(listHead1.getElement());
        System.out.println(listHead1.getNext().getElement());
        System.out.println(listHead1.getNext().getNext().getElement());
        System.out.println(listHead1.getNext().getNext().getNext());

        // Create a linked list in one line
        System.out.println("Single line linked list:");
        LLNode<Integer> listHead2 = new LLNode<>(1, new LLNode<>(2, new LLNode<>(3, null)));
        System.out.println(listHead2.getElement());
        System.out.println(listHead2.getNext().getElement());
        System.out.println(listHead2.getNext().getNext().getElement());
        System.out.println(listHead2.getNext().getNext().getNext());
    }

    /**
     * Step Two of the prelab assignment using a for loop
     */
    public static void step2() {
        // Create a linked list using a for loop
        System.out.println("For loop linked list:");
        LLNode<Integer> listHead3 = null;
        for (int i = 3; i > 0; i--) {
            listHead3 = new LLNode<>(i, listHead3);
        }

        System.out.println(listHead3.getElement());
        System.out.println(listHead3.getNext().getElement());
        System.out.println(listHead3.getNext().getNext().getElement());
        System.out.println(listHead3.getNext().getNext().getNext());
    }

    // Main method to do PreLab Assignment
    public static void main(String[] args) {
        // Outputs should be 1, 2, 3, and null in that order for all 3 lists
        step1();
        step2();
    }
}