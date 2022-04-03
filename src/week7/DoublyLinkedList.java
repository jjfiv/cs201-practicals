package week7;

import adt.ListADT;
import adt.errors.BadIndexError;
import adt.errors.TODOErr;

/**
 * A Doubly-Linked List is a list based on nodes that know of their successor
 * and predecessor.
 * 
 * @author jfoley
 *
 * @param <T>
 */
public class DoublyLinkedList<T> extends ListADT<T> {
    /**
     * This is a reference to the first node in this list.
     */
    Node<T> start;
    /**
     * This is a reference to the last node in this list.
     */
    Node<T> end;

    /**
     * A doubly-linked list starts empty.
     */
    public DoublyLinkedList() {
        this.start = null;
        this.end = null;
    }

    @Override
    public T removeFront() {
        checkNotEmpty();
        T deleted = start.value;
        Node<T> next = start.after;
        if (next == null) {
            end = start = null;
        } else {
            next.before = null;
        }
        start = next;
        return deleted;
    }

    @Override
    public T removeBack() {
        throw new TODOErr("DLL.removeBack");
    }

    @Override
    public T removeIndex(int index) {
        checkNotEmpty();
        int at = 0;
        for (Node<T> n = this.start; n != null; n = n.after) {
            if (at++ == index) {
                if (n == end) {
                    return removeBack();
                } else if (n == start) {
                    return removeFront();
                }
                Node<T> before = n.before;
                Node<T> after = n.after;

                before.after = after;
                after.before = before;
                return n.value;
            }
        }
        throw new BadIndexError(index);
    }

    @Override
    public void addFront(T item) {
        if (start == null) {
            start = end = new Node<T>(item);
        } else {
            Node<T> second = start;
            start = new Node<T>(item);
            start.after = second;
            second.before = start;
        }
    }

    @Override
    public void addBack(T item) {
        throw new TODOErr("DLL.addBack");
    }

    @Override
    public void addIndex(int index, T item) {
        checkNotEmpty();
        if (index == 0) {
            addFront(item);
            return;
        }
        int at = 0;
        for (Node<T> n = this.start; n != null; n = n.after, at++) {
            if (at == index - 1) {
                if (n == end) {
                    addBack(item);
                    return;
                }

                throw new TODOErr("DLL.addIndex(loop-body)");
            }
        }
        throw new BadIndexError(index);
    }

    @Override
    public T getFront() {
        checkNotEmpty();
        return start.value;
    }

    @Override
    public T getBack() {
        checkNotEmpty();
        return end.value;
    }

    @Override
    public T getIndex(int index) {
        checkNotEmpty();
        int at = 0;
        for (Node<T> n = this.start; n != null; n = n.after) {
            if (at++ == index) {
                return n.value;
            }
        }
        throw new BadIndexError(index);
    }

    public void setIndex(int index, T value) {
        checkNotEmpty();
        int at = 0;
        for (Node<T> n = this.start; n != null; n = n.after) {
            if (at++ == index) {
                n.value = value;
                return;
            }
        }
        throw new BadIndexError(index);
    }

    @Override
    public int size() {
        int count = 0;
        for (Node<T> n = this.start; n != null; n = n.after) {
            count++;
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        return start == null;
    }

    /**
     * The node on any linked list should not be exposed. Static means we don't need
     * a "this" of DoublyLinkedList to make a node.
     * 
     * @param <T> the type of the values stored.
     */
    static class Node<T> {
        /**
         * What node comes before me?
         */
        public Node<T> before;
        /**
         * What node comes after me?
         */
        public Node<T> after;
        /**
         * What value is stored in this node?
         */
        public T value;

        /**
         * Create a node with no friends.
         * 
         * @param value - the value to put in it.
         */
        public Node(T value) {
            this.value = value;
            this.before = null;
            this.after = null;
        }
    }
}