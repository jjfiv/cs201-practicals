package week9;

import static org.junit.Assert.assertEquals;
import static week9.SortedSearch.*;

import org.junit.Test;

import adt.ListADT;
import adt.impl.JavaList;

public class SortedSearchTest {
    @Test
    public void testLinearSearch() {
        ListADT<Integer> data = new JavaList<>();
        for (int i = 0; i < 100; i++) {
            data.addBack(i);
        }

        for (int i = 0; i < 100; i++) {
            assertEquals("Item " + i + " should be at position " + i, i, linearSearch(i, data));
        }

        assertEquals(-1, linearSearch(-100, data));
        assertEquals(-1, linearSearch(1000, data));
    }

    @Test
    public void testBinarySearchIter() {
        ListADT<Integer> data = new JavaList<>();
        for (int i = 0; i < 100; i++) {
            data.addBack(i);
        }

        for (int i = 0; i < 100; i++) {
            assertEquals("Item " + i + " should be at position " + i, i,
                    binarySearchIterative(i, data));
        }

        assertEquals(-1, binarySearchIterative(-100, data));
        assertEquals(-1, binarySearchIterative(1000, data));
    }

    @Test
    public void testBinarySearchRec() {
        ListADT<Integer> data = new JavaList<>();
        for (int i = 0; i < 100; i++) {
            data.addBack(i);
        }

        for (int i = 0; i < 100; i++) {
            assertEquals("Item " + i + " should be at position " + i, i,
                    binarySearchRecursive(i, data));
        }

        assertEquals(-1, binarySearchRecursive(-100, data));
        assertEquals(-1, binarySearchRecursive(1000, data));
    }
}
