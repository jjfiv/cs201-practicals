package adt.impl;

import java.util.LinkedList;

import adt.QueueADT;

/**
 * Actual implementation of QueueADT type, based on a
 * {@link java.util.LinkedList}.
 */
public class JavaQueue<T> implements QueueADT<T> {
    /**
     * The inner data structure; doing all the work.
     */
    private LinkedList<T> inner = new LinkedList<>();

    @Override
    public void enqueue(T item) {
        inner.addLast(item);
    }

    @Override
    public T peek() {
        return this.inner.peekFirst();
    }

    @Override
    public T dequeue() {
        return inner.removeFirst();

    }

    @Override
    public boolean isEmpty() {
        return this.peek() == null;
    }
}
