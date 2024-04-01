package llpractice;

/** This is an LLNode class
 * @author Trevor Swan
 */
public class LLNode<T> {
    private T element;
    private LLNode<T> next;

    public LLNode(T element, LLNode<T> next) {
        this.element = element;
        this.next = next;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public T getElement() {
        return this.element;
    }

    public void setNext(LLNode<T> next) {
        this.next = next;
    }

    public LLNode<T> getNext() {
        return this.next;
    }
}