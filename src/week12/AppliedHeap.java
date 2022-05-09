package week12;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import adt.errors.TODOErr;

/**
 * A short lab exercise on applied heaps.
 */
public class AppliedHeap {

    /**
     * Merge many sorted lists:
     * 
     * @param inputs - the sorted lists.
     * @return all the elements of inputs, sorted in a single list.
     */
    public static List<Integer> mergeSortedLists(List<List<Integer>> inputs) {
        ArrayList<Integer> output = new ArrayList<>();
        PriorityQueue<ListTaker> heap = new PriorityQueue<>();
        for (List<Integer> input : inputs) {
            heap.offer(new ListTaker(input));
        }

        // while there are any "ListTakers" that are not done.
        // find the minimum ListTaker from the priority queue
        // take the next value from it; add to output
        // if that ListTaker isn't finished now, put it back in the heap.
        throw new TODOErr("mergeSortedLists");
        // finally return output
    }

    /**
     * This helper class moves through a list (without deleting from it).
     * It's also comparable (based on the current element) ...
     * so it can be placed in a heap.
     */
    private static class ListTaker implements Comparable<ListTaker> {
        /** How far are we through taking items from 'data'? */
        int position;
        /** The list to serve one item at a time from. */
        List<Integer> data;

        /**
         * Create a new ListTaker object from a list; kind of like an
         * {@linkplain Iterator}.
         * 
         * @param data
         */
        public ListTaker(List<Integer> data) {
            this.data = data;
            this.position = 0;
        }

        /**
         * Return true if there are still more elements in this 'taker'.
         * 
         * @return false if done.
         */
        public boolean hasNext() {
            return position < data.size();
        }

        /**
         * Take a look at the next element without taking it.
         * Not valid if not {@linkplain #hasNext}.
         * 
         * @return the current value in the list.
         */
        public int peek() {
            return data.get(position);
        }

        /**
         * Take the next element (moving past it).
         * 
         * @return the current element (only once!).
         */
        public int take() {
            return data.get(position++);
        }

        @Override
        public int compareTo(ListTaker o) {
            return Integer.compare(this.peek(), o.peek());
        }
    }
}
