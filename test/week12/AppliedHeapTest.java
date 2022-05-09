package week12;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

import week5.ComplexityEx;

public class AppliedHeapTest {
    @Test
    public void testCombineSortedEven() {
        List<Integer> left = List.of(2, 4);
        List<Integer> right = List.of(1, 3);
        List<Integer> expected = List.of(1, 2, 3, 4);

        assertEquals(expected, AppliedHeap.mergeSortedLists(List.of(left, right)));
    }

    @Test
    public void testCombineSortedLists() {
        List<Integer> left = List.of(2, 4);
        List<Integer> right = List.of(1, 3, 5);
        List<Integer> expected = List.of(1, 2, 3, 4, 5);

        assertEquals(expected, AppliedHeap.mergeSortedLists(List.of(left, right)));
    }

    @Test
    public void testCombineSortedListsLong() {
        List<Integer> left = List.of(1, 3, 5, 7, 9, 90, 92);
        List<Integer> right = List.of(2, 4, 6, 7, 8, 9, 11);
        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6, 7, 7, 8, 9, 9, 11, 90, 92);

        assertEquals(expected, AppliedHeap.mergeSortedLists(List.of(left, right)));
    }

    @Test
    public void testManyLists() {
        Random rand = ThreadLocalRandom.current();
        List<List<Integer>> data = new ArrayList<>();
        for (int i = 0; i < rand.nextInt(5) + 5; i++) {
            data.add(new ArrayList<>());
        }
        List<Integer> whichList = ComplexityEx.makeRandomInts(1000, 0, data.size());
        List<Integer> randomData = ComplexityEx.makeRandomInts(1000, 0, 50);
        for (int i = 0; i < whichList.size(); i++) {
            data.get(whichList.get(i)).add(randomData.get(i));
        }

        for (int i = 0; i < data.size(); i++) {
            Collections.sort(data.get(i));
        }
        Collections.sort(randomData);
        assertEquals(randomData, AppliedHeap.mergeSortedLists(data));
    }

}
