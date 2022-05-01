package week11;

import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;

import adt.errors.EmptyListError;
import adt.errors.TODOErr;

/**
 * This class represents a 'min' heap over integers.
 * This means that it can always give you the minimum value in O(1) time.
 */
public class IntMinHeap {
    /**
     * The heap is stored in this list.
     * This should be an ArrayList, or any {@linkplain RandomAccess} List.
     */
    List<Integer> heap;

    /**
     * Construct an empty heap.
     */
    public IntMinHeap() {
        this.heap = new ArrayList<>();
    }

    /**
     * This constructor performs the 'heapify' algorithm on the given List to make
     * sure it's a valid heap.
     * 
     * @param unsorted - an unsorted list.
     */
    public IntMinHeap(List<Integer> unsorted) {
        this.heap = unsorted;
        // loop bottom-up, sifting downwards.
        int lowest_parent = this.parent(this.heap.size() - 1);
        for (int i = lowest_parent; i >= 0; i--) {
            this.siftDown(i);
        }
    }

    /**
     * Internal helper -- is position i the root?
     * 
     * @param i - the index in this heap array.
     * @return true if it's the first item in the array.
     */
    private boolean isRoot(int i) {
        return i == 0;
    }

    /**
     * Given an index to a node, compute where its left child would be stored.
     * 
     * @param i - the index of the current node.
     * @return - the index of the left child.
     */
    private int leftChild(int i) {
        return i * 2 + 1;
    }

    /**
     * Given an index to a node, compute where its right child would be stored.
     * 
     * @param i - the index of the current node.
     * @return - the index of the right child.
     */
    private int rightChild(int i) {
        return this.leftChild(i) + 1;
    }

    /**
     * Given an index to a node, compute where its parent would be stored.
     * 
     * @param i - the index of the current node.
     * @return - the index of its parent.
     */
    private int parent(int i) {
        assert i != 0 : "Cannot find parent of root!";
        return (i - 1) / 2;
    }

    /**
     * Helper method to swap two elements inside the heap.
     * 
     * @param i - one index.
     * @param j - the other index.
     */
    private void swap(int i, int j) {
        int tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }

    /**
     * Public method to add to this heap:
     * 
     * @param value - the value to add to the heap.
     */
    public void insert(int value) {
        // 1. add to the back of the this.heap list.
        // 2. sift-up that value.
        throw new TODOErr("IntMinHeap.insert");
    }

    /**
     * Private method to manage the heap after adding.
     * 
     * @param index - the index of the current node.
     */
    private void siftUp(int index) {
        while (!isRoot(index)) {
            int parent = this.parent(index);
            if (heap.get(parent) > heap.get(index)) {
                swap(parent, index);
                index = parent;
                continue;
            } else {
                break;
            }
        }
    }

    /**
     * How many elements are in the heap? Equivalent to the size of the backing
     * list.
     * 
     * @return - the heap size
     */
    public int size() {
        return this.heap.size();
    }

    /**
     * Look at the minimum element of the heap, or crash.
     * 
     * @return the minimum value if the heap is not empty.
     */
    public int peekMin() {
        if (this.heap.size() >= 1) {
            return this.heap.get(0);
        } else {
            throw new EmptyListError();
        }
    }

    /**
     * Remove and return the minimum value of the heap, preserving its heapness.
     * 
     * @return the minimum element.
     */
    public int removeMin() {
        if (this.heap.isEmpty()) {
            throw new EmptyListError();
        }
        int top = this.heap.get(0);

        if (this.heap.size() > 1) {
            // swap the bottom to the top and siftdown:
            int replacement = this.heap.remove(this.heap.size() - 1);
            throw new TODOErr("put " + replacement + " at the top and siftDown from there!");
        } else {
            // delete the last thing, no sifting needed.
            this.heap.remove(0);
        }
        return top;
    }

    /**
     * Private helper to maintain the heap property after a removal.
     * Takes the current node and pushes it down until it is in the correct place,
     * preserving the heap property.
     * 
     * @param index - the index to potentially push downward.
     */
    private void siftDown(int index) {
        // While there's at least one child to compare:
        while (leftChild(index) < heap.size()) {
            int left = leftChild(index);
            int right = rightChild(index);

            // Find max of left, right (if it exists) and parent:
            int localMin = index;
            if (heap.get(left) < heap.get(localMin)) {
                localMin = left;
            }
            if (right < heap.size() && heap.get(right) < heap.get(localMin)) {
                localMin = right;
            }

            if (localMin == index) {
                return;
            } else {
                swap(localMin, index);
                // continue into whatever subtree we just changed.
                index = localMin;
                continue;
            }
        }
    }

    /**
     * Remove all items from this heap by using {@link #removeMin}, in order,
     * placing them into a new list.
     * 
     * @return the sorted items from this heap.
     */
    public List<Integer> drain() {
        List<Integer> output = new ArrayList<>();
        while (this.size() > 0) {
            output.add(this.removeMin());
        }
        return output;
    }

    /**
     * HeapSort:
     * 1. heapify via constructor {@link #IntMinHeap(List)}
     * 2. drain into a new list {@link drain}
     * 
     * Time: O(nlog(n))
     * 
     * @param items - the items to sort.
     * @return a sorted list.
     */
    public static List<Integer> heapSort(List<Integer> items) {
        IntMinHeap heap = new IntMinHeap(items);
        return heap.drain();
    }

}
