package week11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class TopK {
    public List<Integer> binaryTreeTopK(List<Integer> candidates, int k) {
        TreeSet<Integer> tree = new TreeSet<>(candidates);
        List<Integer> output = new ArrayList<>();

        Iterator<Integer> it = tree.descendingIterator();
        for (int i = 0; i < k && it.hasNext(); i++) {
            output.add(it.next());
        }
        Collections.reverse(output);

        return output;
    }

    public List<Integer> sortAllTopK(List<Integer> candidates, int k) {
        Collections.sort(candidates);
        return candidates.subList(candidates.size() - k, candidates.size());
    }

    /**
     * Find the k largest items from a list.
     * 
     * Time: O(nlog(n))
     * Space: O(k)
     * 
     * @param candidates - the list to consider.
     * @param k          - the number of elements to choose.
     * @return a list of the k largest elements.
     */
    public static List<Integer> heapTopK(List<Integer> candidates, int k) {
        IntMinHeap heap = new IntMinHeap();
        for (int c : candidates) {
            heap.insert(c);
            // Don't let the heap get too big:
            if (heap.size() > k) {
                heap.removeMin();
            }
        }
        return heap.drain();
    }
}
