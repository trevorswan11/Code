package LLPractice;

import java.util.NoSuchElementException;

public class LinkedList<T> {
    private LLNode<T> firstNode;

    public LinkedList() {
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

    public LLNode<T> getFirstNode() {
        return this.firstNode;
    }

    public void setFirstNode(LLNode<T> firstNode) {
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
}
