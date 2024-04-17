package llpractice;

import java.lang.Iterable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListBase<T> implements Iterable<T> {
    private LLNode<T> firstNode;

    public LinkedListBase() {
        this.firstNode = null;
    }

    public boolean isEmpty() {
        return this.getFirstNode() == null;
    }

    public void addToFront(T element) {
        this.setFirstNode(new LLNode<T>(element, firstNode));
    }

    public T removeFromFront() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T save = this.getFirstNode().getElement();
        this.setFirstNode(this.getFirstNode().getNext());
        return save;
    }

    /*
     * You can use T or K here, but K is stated to illustrate
     * that it works this way. You can declare the generic type
     * in the method.
     * 
     * IN STATIC METHODS: you must declare a generic because the method
     * will not use the one specified in the class declaration.
     */
    public static <K> void print(LinkedListBase<K> list) {
        LLNode<K> nodeptr = list.getFirstNode();
        while (nodeptr != null) {
            System.out.println(nodeptr.getElement());
            nodeptr = nodeptr.getNext();
        }
    }

    /*
     * Pretty similar to the above method, but do we really care
     * what generic type is used? NO!
     * 
     * You can use the wildcard in the declaration. This is 
     * not declared in the static part, and is represented by '?'
      */
    public static void print2(LinkedListBase<?> list) {
        LLNode<?> nodeptr = list.getFirstNode();
        while (nodeptr != null) {
            System.out.println(nodeptr.getElement());
            nodeptr = nodeptr.getNext();
        }
    }

    protected LLNode<T> getFirstNode() {
        return this.firstNode;
    }

    protected void setFirstNode(LLNode<T> firstNode) {
        this.firstNode = firstNode;
    }

    public int length() {
        int count = 0;

        // Create a point to not disturb the actual 'this'
        LLNode<T> nodeptr = this.getFirstNode();

        // Loop through list to find last node
        while (nodeptr != null) {
            count++;
            nodeptr = nodeptr.getNext();
        }

        // Now that we're at the end of the LL, return count
        return count;
    }

    public void addToEnd(T element) {
        // Create a point to not disturb the actual 'this'
        LLNode<T> nodeptr = this.getFirstNode();

        // Loop through list to find last node
        while (nodeptr.getNext() != null) {
            nodeptr = nodeptr.getNext();
        }

        // Now that we're at the end of the LL, add element
        nodeptr.setNext(new LLNode<T>(element, null));
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<T>(this.getFirstNode());   
    }

    public static void main(String[] args) {
        LinkedListBase<String> list = new LinkedListBase<String>();
        list.addToFront("Cleveland");
        list.addToFront("Columbus");
        list.addToFront("Toledo");
        // java.util.Iterator<String> it = list.iterator();
        for (String s:list) System.out.println(s);
    }
}
