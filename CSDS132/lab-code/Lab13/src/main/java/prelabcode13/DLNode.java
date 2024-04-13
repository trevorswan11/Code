package prelabcode13;

public class DLNode<T> {
    private T element;
    private DLNode<T> next;
    private DLNode<T> previous;

    /**
     * Creates a new double linked list node.
     * 
     * @param element The desired element for the node
     * @param previous The node directly behind the node
     * @param next The node directly ahead of node
     */
    public DLNode(T element, DLNode<T> previous, DLNode<T> next) {
        // Basic constructor operation
        this.element = element;
        this.setPrevious(previous);
        this.setNext(next);
        
        /*
         * If previous node is non-null, then the previous node should now point towards
         * this.
         */
        if (previous != null) {
            previous.setNext(this);
        }

        /*
         * If the next node is not null, then the next node should point back to this,
         * similarly to the above conditionals logic.
         */
        if (next != null) {
            next.setPrevious(this);
        }
    }

    public T getElement() {
        return element;
    }

    public DLNode<T> getNext() {
        return next;
    }

    public void setNext(DLNode<T> next) {
        this.next = next;
    }

    public DLNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(DLNode<T> previous) {
        this.previous = previous;
    }

    /**
     * Creates a double linked list with the required nodes for part 1. Outputs the
     * result to the screen as requested by instructor.
     */
    public static void printingTest() {
        // Create the Double linked list of integers
        DLNode<Integer> list = new DLNode<>(1, null, 
                new DLNode<>(2, null, 
                new DLNode<>(3, null, null)));

        // Print the first set of tests
        System.out.println("Test One\n--------");
        System.out.println(list.getElement());
        System.out.println(list.getNext().getElement());
        System.out.println(list.getNext().getNext().getElement());
        System.out.println(list.getNext().getNext().getNext());

        // Print the second set of tests
        System.out.println("\nTest Two\n--------");
        System.out.println(list.getPrevious());
        System.out.println(list.getNext().getPrevious().getElement());
        System.out.println(list.getNext().getNext().getPrevious().getElement());
    }

    /**
     * The main method of this script. Runs the needed code for the assignment.
     * Accesses the two test methods, as I do not use the interaction pane in DrJava
     * because of a bug.
     */
    public static void main(String[] args) {
        printingTest();

        /*
         * Command Line Result:
         * 
         * Test One
         * --------
         * 1
         * 2
         * 3
         * null
         * 
         * Test Two
         * --------
         * null
         * 1
         * 2
         */
    }
}