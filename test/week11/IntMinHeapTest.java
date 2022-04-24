package week11;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import week5.ComplexityEx;

public class IntMinHeapTest {

    @Test
    public void testHeapInsert() {
        IntMinHeap heap = new IntMinHeap();
        heap.insert(5);
        assertEquals(heap.peekMin(), 5);
        heap.insert(4);
        assertEquals(heap.peekMin(), 4);
        heap.insert(3);
        assertEquals(heap.peekMin(), 3);
        heap.insert(2);
        assertEquals(heap.peekMin(), 2);
        heap.insert(1);
        assertEquals(heap.peekMin(), 1);
        heap.insert(0);
        assertEquals(heap.peekMin(), 0);
    }

    @Test
    public void testHeapRemove() {
        IntMinHeap heap = new IntMinHeap();
        for (int i = 0; i < 100; i++) {
            heap.insert(i);
            assertEquals(heap.peekMin(), 0);
        }
        for (int i = 0; i < 100; i++) {
            assertEquals(heap.removeMin(), i);
        }
    }

    @Test
    public void testHeapSortRand() {
        for (int i = 0; i < 10; i++) {
            List<Integer> random = ComplexityEx.makeRandomInts(100, 0, 100);
            ArrayList<Integer> sorted = new ArrayList<>(random);
            Collections.sort(sorted);
            assertEquals(IntMinHeap.heapSort(random), sorted);
        }
    }

    @Test
    public void testFindTopK() {
        List<Integer> items = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        assertEquals(List.of(10), TopK.heapTopK(items, 1));
        assertEquals(List.of(9, 10), TopK.heapTopK(items, 2));
        assertEquals(List.of(8, 9, 10), TopK.heapTopK(items, 3));
        assertEquals(List.of(7, 8, 9, 10), TopK.heapTopK(items, 4));
    }

    @Test
    public void testFindTopKRand() {
        int k = 20;
        for (int i = 0; i < 10; i++) {
            List<Integer> random = ComplexityEx.makeRandomInts(100, 0, 100);
            ArrayList<Integer> sorted = new ArrayList<>(random);
            Collections.sort(sorted);
            List<Integer> bestK = sorted.subList(sorted.size() - k, sorted.size());
            assertEquals(TopK.heapTopK(random, k), bestK);
        }
    }

}
