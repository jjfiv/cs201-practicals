package week6;

import java.util.ArrayList;

import adt.StackADT;
import adt.errors.TODOErr;

/**
 * This class represents a stack by adding/removing to the back of a Java list.
 */
public class JavaStack<T> implements StackADT<T> {
    /**
     * This arraylist datastructure does all the real work.
     */
    ArrayList<T> inner;

    public JavaStack() {
        this.inner = new ArrayList<>();
    }

    @Override
    public void push(T item) {
        // TODO: add to the back of the list
        throw new TODOErr();
    }

    @Override
    public T pop() {
        // TODO: remove from the back of the list
        throw new TODOErr();
    }

    @Override
    public T peek() {
        // TODO: look at the most recently-pushed item
        // ... should be null if no such item.
        throw new TODOErr();
    }

    @Override
    public boolean isEmpty() {
        // TODO: return true if this stack is empty;
        throw new TODOErr();
    }

}
