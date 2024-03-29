package LabCode10;

import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 * A class to represent a linked list of nodes.
 */
public class LinkedList<T> {
    /** the first node of the list, or null if the list is empty */
    private LLNode<T> firstNode;

    /**
     * Creates an initially empty linked list
     */
    public LinkedList() {
        firstNode = null;
    }

    /**
     * Returns the first node.
     */
    protected LLNode<T> getFirstNode() {
        return firstNode;
    }

    /**
     * Changes the front node.
     * 
     * @param node the node that will be the first node of the new linked list
     */
    protected void setFirstNode(LLNode<T> node) {
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

            setFirstNode(new LLNode<T>(element, getFirstNode()));
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
        LLNode<T> nodeptr = getFirstNode();
        while (nodeptr != null) {
            lengthSoFar++;
            nodeptr = nodeptr.getNext();
        }
        return lengthSoFar;
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
        LLNode<T> thisList = new LLNode<T>(null, this.getFirstNode());
        StringBuilder builder = new StringBuilder("list:");
        if (this.length() != 0) {
            builder.append(" ");
        }
        for (int i = 0; i < this.length(); i++) {
            if (i != this.length() - 1) {
                builder.append(thisList.getNext().getElement() + " ");
            }
            else {
                builder.append(thisList.getNext().getElement());
            }
            thisList.setNext(thisList.getNext().getNext());
        }
        return builder.toString();
    }

    /**
     * Determines whether an element is stored in the list
     * 
     * @param element the element to search for in the list
     * @return true if and only if the parameter element is in the list
     */
    public boolean contains(T element) {
        LLNode<T> thisList = new LLNode<T>(element, this.getFirstNode());
        for (int i = 0; i < this.length(); i++) {
            if (thisList.getNext().getElement().equals(element)) {
                return true;
            }

            thisList.setNext(thisList.getNext().getNext());
        }
        return false;
    }

    /**
     * Deletes the first occurrence of an element in the list.
     * If the element is not in the list, the list is unchanged.
     * 
     * @param element the element to remove
     */
    public void remove(T element) {
        // Do nothing if the list is empty or doesn't contain element
        if (this.isEmpty() || !this.contains(element));

        // If the element to remove is in the first node
        else if (this.getFirstNode().getElement().equals(element)) {
            this.setFirstNode(this.getFirstNode().getNext());
        } 

        // Otherwise search for the element
        else {
            // declare an element checker and LLNode to make use of those methods
            boolean found = false;
            LLNode<T> thisList = getFirstNode();

            // Loop through elements until the element is found 
            while (!found) {
                // Found the element to remove, so indicate and bypass node
                if (thisList.getNext().getElement().equals(element)) {
                    thisList.setNext(thisList.getNext().getNext());
                    found = true;
                }
                thisList = thisList.getNext();
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int test = input.nextInt();
        System.out.println(test);
        input.close();
    }
}
