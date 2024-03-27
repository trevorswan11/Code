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

    public static void main(String[] args) {
        // Create the list head
        LLNode<String> head = new LLNode<String>("Hello", null);

        // Create the next node and set it as the next one of head
        LLNode<String> node2 = new LLNode<String>("Hi", null);
        head.setNext(node2);

        // Create the next node and have node2 point to it
        LLNode<String> node3 = new LLNode<String>("there", null);
        head.getNext().setNext(node3);

        // Create the final node and have node3 point to it
        LLNode<String> node4 = new LLNode<String>("please", null);
        head.getNext().getNext().setNext(node4);

        // Print testing
        System.out.println(head.getNext().getNext().getElement());
    }
}
