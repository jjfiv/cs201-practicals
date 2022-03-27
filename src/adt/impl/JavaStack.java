package adt.impl;

import java.util.ArrayList;

import adt.StackADT;

/**
 * StackADT implemented on top of {@link ArrayList}.
 */
public class JavaStack<T> implements StackADT<T> {
    /**
     * Inner data structure does all the work.
     */
    ArrayList<T> inner = new ArrayList<>();

    @Override
    public void push(T item) {
        inner.add(item);
    }

    @Override
    public T pop() {
        return inner.remove(inner.size() - 1);
    }

    @Override
    public T peek() {
        if (inner.isEmpty()) {
            return null;
        }
        return inner.get(inner.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return inner.size() == 0;
    }

}
