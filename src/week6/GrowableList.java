package week6;

import adt.ArrayWrapper;
import adt.ListADT;
import adt.errors.*;

/**
 * A GrowableList (or dynamic array) is also known as an
 * {@link java.util.ArrayList}. It starts at a particular size and grows as
 * needed, replacing its inner array with a larger one when more space is
 * necessary.
 * 
 * @author jfoley
 *
 * @param <T> - the type of item stored in this list.
 */
public class GrowableList<T> extends ListADT<T> {
    /**
     * How big should the initial list be? This is public for use in tests.
     * 
     * Note that real implementations try to make this really small or zero, in case
     * the list is never used.
     */
    public static final int START_SIZE = 10;
    /**
     * This is the current array held by the GrowableList. It may be replaced.
     */
    private ArrayWrapper<T> array;
    /**
     * This is the number of elements in the array that are used.
     */
    private int fill;

    /**
     * Construct a new, empty, GrowableList.
     */
    public GrowableList() {
        this.array = new ArrayWrapper<>(START_SIZE);
        this.fill = 0;
    }

    @Override
    public T removeFront() {
        this.checkNotEmpty();
        return removeIndex(0);
    }

    @Override
    public T removeBack() {
        this.checkNotEmpty();
        return removeIndex(fill - 1);
    }

    @Override
    public T removeIndex(int index) {
        checkNotEmpty();
        checkExclusiveIndex(index);
        T removed = this.getIndex(index);
        fill--;

        throw new TODOErr();
        // Slide all elements to the left.
        // return removed element
    }

    @Override
    public void addFront(T item) {
        addIndex(0, item);
    }

    @Override
    public void addBack(T item) {
        if (fill >= array.length()) {
            this.resizeArray();
        }
        array.setIndex(fill++, item);
    }

    /**
     * This private method is called when we need to make room in our GrowableList.
     */
    private void resizeArray() {
        ArrayWrapper<T> larger = new ArrayWrapper<>(this.fill * 2);
        // copy to larger;
        // hold onto it by replacing this.array
        throw new TODOErr();
    }

    @Override
    public void addIndex(int index, T item) {
        // Add to a specific location is the only list method that accepts the size of
        // the list as a valid argument. Think about why.
        this.checkInclusiveIndex(index);
        // Make space if needed:
        if (fill >= array.length()) {
            this.resizeArray();
        }

        throw new TODOErr();
        // shifting items to the right. (HINT: loop backwards)
        // put this item in the middle
    }

    @Override
    public T getFront() {
        checkNotEmpty();
        return this.getIndex(0);
    }

    @Override
    public T getBack() {
        checkNotEmpty();
        return this.getIndex(this.fill - 1);
    }

    @Override
    public T getIndex(int index) {
        checkNotEmpty();
        checkExclusiveIndex(index);
        return this.array.getIndex(index);
    }

    @Override
    public int size() {
        return this.fill;
    }

    @Override
    public boolean isEmpty() {
        return this.fill == 0;
    }

    @Override
    public void setIndex(int index, T value) {
        checkNotEmpty();
        checkExclusiveIndex(index);
        this.array.setIndex(index, value);
    }

}