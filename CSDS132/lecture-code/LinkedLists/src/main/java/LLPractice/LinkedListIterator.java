package llpractice;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListIterator<T> implements Iterator<T> {
    private LLNode<T> nodeptr;

    public LLNode<T> getNodeptr() {
        return nodeptr;
    }

    public LinkedListIterator(LLNode<T> firstNode) {
        this.nodeptr = firstNode;
    }

    @Override
    public boolean hasNext() {
        return this.getNodeptr() != null;
    }

    @Override
    public T next() {
        if (hasNext()) {
            T save = nodeptr.getElement();
            nodeptr = nodeptr.getNext();
            return save;
        }

        else {
            throw new NoSuchElementException();
        }
    }
}
