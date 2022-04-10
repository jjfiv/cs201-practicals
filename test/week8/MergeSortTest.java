package week8;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import adt.ListADT;
import adt.impl.JavaList;

public class MergeSortTest {
    @Test
    public void testCombineSortedEven() {
        ListADT<Integer> left = new JavaList<>();
        left.addBack(2);
        left.addBack(4);
        ListADT<Integer> right = new JavaList<>();
        right.addBack(1);
        right.addBack(3);

        ListADT<Integer> expected = new JavaList<>();
        expected.addBack(1);
        expected.addBack(2);
        expected.addBack(3);
        expected.addBack(4);

        assertEquals(expected, MergeSort.combineTwoSortedLists(left, right));
    }

    @Test
    public void testCombineSortedLists() {
        ListADT<Integer> left = new JavaList<>();
        left.addBack(2);
        left.addBack(4);
        ListADT<Integer> right = new JavaList<>();
        right.addBack(1);
        right.addBack(3);
        right.addBack(5);

        ListADT<Integer> expected = new JavaList<>();
        expected.addBack(1);
        expected.addBack(2);
        expected.addBack(3);
        expected.addBack(4);
        expected.addBack(5);

        assertEquals(expected, MergeSort.combineTwoSortedLists(left, right));
    }

    @Test
    public void testCombineSortedListsLong() {
        ListADT<Integer> left = new JavaList<>(List.of(1, 3, 5, 7, 9, 90, 92));
        ListADT<Integer> right = new JavaList<>(List.of(2, 4, 6, 7, 8, 9, 11));
        ListADT<Integer> expected = new JavaList<>(List.of(1, 2, 3, 4, 5, 6, 7, 7, 8, 9, 9, 11, 90, 92));

        assertEquals(expected, MergeSort.combineTwoSortedLists(left, right));
    }

    @Test
    public void testMergeSortRec() {
        ListADT<Integer> start = new JavaList<>(List.of(8, 6, 7, 5, 7, 3, 0, 9));
        ListADT<Integer> expected = new JavaList<>(List.of(0, 3, 5, 6, 7, 7, 8, 9));
        ListADT<Integer> actual = MergeSort.doMergeSortRecursively(start);
        assertEquals(expected, actual);
    }

    @Test
    public void testMergeSortIter() {
        ListADT<Integer> start = new JavaList<>(List.of(8, 6, 7, 5, 7, 3, 0, 9));
        ListADT<Integer> expected = new JavaList<>(List.of(0, 3, 5, 6, 7, 7, 8, 9));
        ListADT<Integer> actual = MergeSort.doMergeSortIteratively(start);
        assertEquals(expected, actual);
    }
}
