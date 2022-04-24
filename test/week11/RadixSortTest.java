package week11;

import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import adt.ListADT;
import adt.impl.JavaList;
import week8.SortProblems;

public class RadixSortTest {

    /**
     * Bubble Sort is done! And it is easy to do in-place (without creating a new
     * list!)
     * 
     * @param input - the list to be sorted.
     */
    public static void bubbleSort(ListADT<Integer> input) {
        int N = input.size();

        while (true) {
            boolean sorted = true;
            for (int i = 0; i < N - 1; i++) {
                if (input.getIndex(i) > input.getIndex(i + 1)) {
                    input.swap(i, i + 1);
                    sorted = false;
                }
            }
            if (sorted) {
                break;
            }
            N = N - 1;
        }
    }

    @Test
    public void testRadixSortFixed() {
        ListADT<Integer> input = new JavaList<Integer>();
        ListADT<Integer> bubbled = new JavaList<Integer>();

        for (int x : List.of(9, 8, 4, 7, 6, 5, 4, 3, 2, 1)) {
            input.addBack(x);
            bubbled.addBack(x);
        }

        // sort one of them with known-good:
        bubbleSort(bubbled);
        // sort with maybe-broken radix-sort:
        ListADT<Integer> output = RadixSort.sort(input);

        // For debugging:
        // System.out.println(input);
        // System.out.println(output);
        // System.out.println(bubbled);

        Assert.assertEquals(bubbled, output);
    }

    @Test
    public void stressTestRadixSort() {
        int N = 1000;
        Random rand = new Random();

        // This test generates random data, and makes sure that
        // RadixSort can sort as well as BubbleSort.
        for (int trial = 0; trial < 100; trial++) {
            ListADT<Integer> input = new JavaList<Integer>();
            ListADT<Integer> bubbled = new JavaList<Integer>();

            for (int i = 0; i < N; i++) {
                // very important we use huge numbers here to test all of radix sorts' passes.
                int next = rand.nextInt();
                input.addBack(next);
                bubbled.addBack(next);
            }

            // sort one of them with known-good:
            bubbleSort(bubbled);
            // sort with maybe-broken radix-sort:
            ListADT<Integer> output = RadixSort.sort(input);

            // For debugging:
            // System.out.println(output);
            // System.out.println(bubbled);

            // Both sorts should yield the same lists:
            Assert.assertEquals(bubbled, output);
        }
    }
}
