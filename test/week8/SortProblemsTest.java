package week8;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

import adt.ListADT;
import adt.impl.JavaList;

public class SortProblemsTest {

    @Test
    public void testInsertSortedEmpty() {
        ListADT<Integer> empty = new JavaList<>();

        ListADT<Integer> expected = new JavaList<>();
        expected.addBack(7);

        SortProblems.insertSorted(7, empty);
        assertEquals(expected, empty);
    }

    @Test
    public void testInsertSortedFront() {
        ListADT<Integer> actual = new JavaList<>();
        actual.addBack(2);
        actual.addBack(3);

        ListADT<Integer> expected = new JavaList<>();
        expected.addBack(1);
        expected.addBack(2);
        expected.addBack(3);

        SortProblems.insertSorted(1, actual);
        assertEquals(expected, actual);

    }

    @Test
    public void testInsertSortedMiddle() {
        ListADT<Integer> actual = new JavaList<>();
        actual.addBack(1);
        actual.addBack(3);

        ListADT<Integer> expected = new JavaList<>();
        expected.addBack(1);
        expected.addBack(2);
        expected.addBack(3);

        SortProblems.insertSorted(2, actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testInsertSortedBack() {
        ListADT<Integer> actual = new JavaList<>();
        actual.addBack(1);
        actual.addBack(2);

        ListADT<Integer> expected = new JavaList<>();
        expected.addBack(1);
        expected.addBack(2);
        expected.addBack(3);

        SortProblems.insertSorted(3, actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testFindMin() {
        ListADT<Integer> example = new JavaList<>();
        for (int x : new int[] { 8, 6, 7, 0, 3, 5, 9 }) {
            example.addBack(x);
        }
        assertEquals(7, example.size());

        // The minimum in the list is 0, which is at position 3.
        // Any "start" value less than 3 will identify it.
        assertEquals(3, SortProblems.findMinPosition(example, 0));
        assertEquals(3, SortProblems.findMinPosition(example, 1));
        assertEquals(3, SortProblems.findMinPosition(example, 2));
        assertEquals(3, SortProblems.findMinPosition(example, 3));
        // the sequence 3,5,9 are in positions 4,5,6.
        assertEquals(4, SortProblems.findMinPosition(example, 4));
        assertEquals(5, SortProblems.findMinPosition(example, 5));
        assertEquals(6, SortProblems.findMinPosition(example, 6));
    }

    @Test
    public void testInsertionSort() {
        ListADT<Integer> start = new JavaList<>();
        for (int x : new int[] { 8, 6, 7, 5, 7, 3, 0, 9 }) {
            start.addBack(x);
        }
        ListADT<Integer> expected = new JavaList<>();
        for (int x : new int[] { 0, 3, 5, 6, 7, 7, 8, 9 }) {
            expected.addBack(x);
        }
        ListADT<Integer> actual = SortProblems.insertionSort(start);
        assertEquals(expected, actual);
    }

    @Test
    public void testSelectionSort() {
        ListADT<Integer> actual = new JavaList<>();
        for (int x : new int[] { 8, 6, 7, 5, 7, 3, 0, 9 }) {
            actual.addBack(x);
        }
        ListADT<Integer> expected = new JavaList<>();
        for (int x : new int[] { 0, 3, 5, 6, 7, 7, 8, 9 }) {
            expected.addBack(x);
        }
        SortProblems.selectionSort(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testSelectionSortRand() {
        Random rand = new Random(13);

        ListADT<Integer> actual = new JavaList<>();
        List<Integer> expected = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int num = rand.nextInt(1000) - 500;
            actual.addBack(num);
            expected.add(num);
        }
        Collections.sort(expected);
        SortProblems.selectionSort(actual);
        assertEquals(expected, actual.toJava());
    }

    @Test
    public void testIsSorted() {
        ListADT<Integer> actual = new JavaList<>();
        assertTrue("Empty lists are sorted.", SortProblems.isSorted(actual));

        actual.addBack(3);
        assertTrue("Size-1 lists are sorted.", SortProblems.isSorted(actual));

        actual.addBack(4);
        assertTrue("[3,4] is sorted.", SortProblems.isSorted(actual));

        actual.addBack(1);
        assertFalse("[3,4,1] is NOT sorted.", SortProblems.isSorted(actual));
    }
}
