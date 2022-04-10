package week8;

import adt.ListADT;
import adt.errors.TODOErr;
import adt.impl.JavaList;

public class SortProblems {

    /**
     * Read through the list in data and return true only if it's sorted.
     * 
     * @param data - a list of numbers.
     * @return true if they are sorted, false if not.
     */
    public static boolean isSorted(ListADT<Integer> data) {
        throw new TODOErr("isSorted");
    }

    /**
     * Insert the value x in to the sorted list "target" in the correct position.
     * Helper for {@link #insertionSort}; complexity O(n).
     * 
     * @param x      - the new number to insert.
     * @param target - the sorted list to modify (might be empty!)
     */
    public static void insertSorted(int x, ListADT<Integer> target) {
        throw new TODOErr("insertSorted");
    }

    /**
     * Find the position of the minimum element of list starting at start. Helper
     * for selectionSort; complexity: O(n).
     * 
     * @param list  - the full list (NOT sorted)
     * @param start - where to start in list (don't look to the left).
     * @return the position (int greater than start) of the minimum element.
     */
    public static int findMinPosition(ListADT<Integer> list, int start) {
        assert (start < list.size()) : "There should be stuff in the list to the right of start!";
        throw new TODOErr("findMinPosition");
    }

    /**
     * InsertionSort: Create a new output list that contians all elements of input
     * but in sorted order. This is very short if you call {@link #insertSorted}.
     * 
     * @param input - the list to sort.
     * @return a new sorted list -- just use JavaList<>().
     */
    public static ListADT<Integer> insertionSort(ListADT<Integer> input) {
        ListADT<Integer> output = new JavaList<>();
        throw new TODOErr("insertionSort");
    }

    /**
     * SelectionSort: Move through the input list left-to-right and swap the minimum
     * element of the list to the current position until you reach the end.
     * 
     * Helpful: - {@link #findMinPosition(ListADT, int)} -
     * {@link ListADT#swap(int, int)} is also really helpful :)
     * 
     * @param fixMe - the input and output of this method -- it modifies a list
     *              in-place.
     */
    public static void selectionSort(ListADT<Integer> fixMe) {
        throw new TODOErr("selectionSort");
    }

}
