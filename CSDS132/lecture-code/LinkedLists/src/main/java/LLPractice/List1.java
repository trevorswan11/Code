package llpractice;

import java.util.NoSuchElementException;

/**
 * A class to represent a linked list of nodes.
 * The nodes are a static nested class.
 */
public class List1<T> implements Iterable<T> {
    /** the first node of the list, or null if the list is empty */
    private Node<T> firstNode;

    /**
     * Creates an initially empty linked list
     */
    public List1() {
        firstNode = null;
    }

    /**
     * Returns the first node.
     */
    protected Node<T> getFirstNode() {
        return firstNode;
    }

    /**
     * Changes the front node.
     * 
     * @param node the node that will be the first node of the new linked list
     */
    protected void setFirstNode(Node<T> node) {
        this.firstNode = node;
    }

    /**
     * Return whether the list is empty
     * 
     * @return true if the list is empty
     */
    public boolean isEmpty() {
        return (getFirstNode() == null);
    }

    /**
     * Add an element to the front of the linked list
     */
    public void addToFront(T element) {
        setFirstNode(new Node<T>(element, getFirstNode()));
    }

    /**
     * Removes and returns the element at the front of the linked list
     * 
     * @return the element removed from the front of the linked list
     * @throws NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (isEmpty())
            throw new NoSuchElementException();
        else {
            T save = getFirstNode().getElement();
            setFirstNode(getFirstNode().getNext());
            return save;
        }
    }

    /**
     * Returns the length of the linked list
     * 
     * @return the number of nodes in the list
     */
    public int length() {
        int lengthSoFar = 0;
        Node<T> nodeptr = getFirstNode();
        while (nodeptr != null) {
            lengthSoFar++;
            nodeptr = nodeptr.getNext();
        }
        return lengthSoFar;
    }

    /**
     * Add an element to the end of a list.
     * 
     * @param element the element to add
     */
    public void addToEnd(T element) {
        if (isEmpty())
            addToFront(element);
        else {
            Node<T> nodeptr = getFirstNode();
            while (nodeptr.getNext() != null)
                nodeptr = nodeptr.getNext();
            nodeptr.setNext(new Node<T>(element, null));
        }
    }

    /**
     * Returns an iterator for the list
     * 
     * @return an iterator starting at the first element of the list
     */
    public LinkedListIterator<T> iterator() {
        // return new LinkedListIterator<T>(getFirstNode());
        return null;
    }

    /**
     * Print the contents of a list.
     * An example of declaring a generic for a method.
     * 
     * @param list the list to print
     */
    public static <T> void printList(List1<T> list) {
        Node<T> nodeptr = list.getFirstNode();
        while (nodeptr != null) {
            System.out.println(nodeptr.getElement());
            nodeptr = nodeptr.getNext();
        }
    }

    /**
     * Prints the contents of a linked list.
     * An example of using the ? generic type wildcard
     * 
     * @param list the list to print
     */
    public static void printList2(List1<?> list) {
        Node<?> nodeptr = list.getFirstNode();
        while (nodeptr != null) {
            System.out.println(nodeptr.getElement());
            nodeptr = nodeptr.getNext();
        }
    }

    /*-------------------------------------------*/
    /* THE NEXT METHODS WILL BE COMPLETED IN LAB */
    /*-------------------------------------------*/

    /**
     * Returns a String representation of the list
     * 
     * @return a String representing the list
     */
    public String toString() {
        return null;
    }

    /**
     * Determines whether an element is stored in the list
     * 
     * @param element the element to search for in the list
     * @return true if and only if the parameter element is in the list
     */
    public boolean contains(T element) {
        return false;
    }

    /**
     * Deletes the first occurrence of an element in the list.
     * If the element is not in the list, the list is unchanged.
     * 
     * @param element the element to remove
     */
    public void remove(T element) {
    }

    /**
     * The node of a linked list.
     * As a static nested class, it does not get the generic of the containing
     * class.
     * We must declare our own.
     */
    public static class Node<T> {
        /** the element stored in the node */
        private T element;

        /** a reference to the next node of the list */
        private Node<T> next;

        /**
         * the constructor
         * 
         * @param element the element to store in the node
         * @param next    a reference to the next node of the list
         */
        public Node(T element, Node<T> next) {
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
        public Node<T> getNext() {
            return next;
        }

        /**
         * Changes the element stored in this node
         * 
         * @param element the new element to store
         */
        public void setElement(T element) {
            this.element = element;
        }

        /**
         * Changes the node that comes after this node in the list
         * 
         * @param next the node that should come after this node in the list. It can be
         *             null.
         */
        public void setNext(Node<T> next) {
            this.next = next;
        }

        /**
         * Returns the length after this node to the end of the list.
         * An example of structural recursion
         * 
         * @return the length of the list, after this node and not including this node
         */
        public int lengthFromHere() {
            if (getNext() == null) // this is the end of the list so
                return 0; // there are no more nodes
            else // this is not the end of the list so
                return 1 + getNext().lengthFromHere(); // the length is 1 more than next's length
        }

        /*-------------------------------------------*/
        /* THE NEXT METHODS WILL BE COMPLETED IN LAB */
        /*-------------------------------------------*/

        /**
         * Insert a new node after this node in the list.
         * 
         * @param element the value to store in the new node
         */
        public void insertAfter(T element) {
        }

        /**
         * Remove the node that occurs immediately after this node in the list.
         */
        public void deleteNext() {
        }

    }
}
