package week7;

import adt.QueueADT;
import adt.errors.EmptyListError;
// we can use the same node class as SinglyLinkedList.
import week7.SinglyLinkedList.Node;

/**
 * The Singly-Linked-List Queue implements a Queue with O(1) enqueue/dequeue by
 * having both start/end pointers.
 * 
 * {@link #enqueue} is SLL.addBack which becomes O(1) by tracking end
 * {@link #dequeue} is SLL.removeFront which has always been O(1)
 */
public class SLLQ<T> implements QueueADT<T> {
    /**
     * The start of the list; or null if empty.
     */
    Node<T> start;
    /**
     * The end of the list; or null if empty. May point to the same value as start
     * if there's only one item in the queue.
     */
    Node<T> end;

    @Override
    public void enqueue(T item) {
        if (this.end == null) {
            assert this.start == null;
            this.start = new Node<>(item);
            this.end = this.start;
        } else {
            this.end.next = new Node<>(item);
            this.end = this.end.next;
        }

    }

    @Override
    public T peek() {
        if (this.start == null) {
            return null;
        } else {
            return this.start.value;
        }
    }

    @Override
    public T dequeue() {
        if (this.start == null) {
            throw new EmptyListError();
        }
        T first = this.start.value;
        this.start = this.start.next;
        if (this.start == null) {
            this.end = null;
        }
        return first;
    }

    @Override
    public boolean isEmpty() {
        return this.start == null;
    }

}
