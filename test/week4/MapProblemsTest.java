package week4;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class MapProblemsTest {
    @Test
    public void testRomanValues() {
        Map<Character, Integer> values = MapProblems.getRomanNumeralValues();
        assertEquals((Integer) 1000, values.get('M'));
        assertEquals((Integer) 500, values.get('D'));
        assertEquals((Integer) 100, values.get('C'));
        assertEquals((Integer) 50, values.get('L'));
        assertEquals((Integer) 10, values.get('X'));
        assertEquals((Integer) 5, values.get('V'));
        assertEquals((Integer) 1, values.get('I'));
    }

    @Test
    public void testCountWordsSimple() {
        // empty list -> empty map
        assertEquals(Map.of(), MapProblems.countWords(List.of()));

        // list of non-repeated words -> map of count=1 words
        Map<String, Integer> expected = Map.of("hello", 1, "world", 1);
        Map<String, Integer> actual = MapProblems.countWords(List.of("hello", "world"));
        assertEquals(expected, actual);
    }

    @Test
    public void testCountWordsMultiple() {
        // list of repeated words -> complex response
        Map<String, Integer> expected = Map.of("a", 3, "b", 2, "c", 1);
        Map<String, Integer> actual = MapProblems.countWords(List.of("a", "b", "c", "a", "b", "a"));
        assertEquals(expected, actual);
    }

    @Test
    public void testCountsLargerThanSimple() {
        Map<String, Integer> input = Map.of("a", 3, "b", 2, "c", 1);

        // empty map never gives us anything:
        assertEquals(Set.of(), MapProblems.countsLargerThan(Map.of(), 0));
        // high n gives us nothing:
        assertEquals(Set.of(), MapProblems.countsLargerThan(input, 10));
        // low n gives us input:
        assertEquals(input.keySet(), MapProblems.countsLargerThan(input, 0));
        // medium n gives us some:
        assertEquals(Set.of("a", "b"), MapProblems.countsLargerThan(input, 1));
        assertEquals(Set.of("a"), MapProblems.countsLargerThan(input, 2));
        assertEquals(Set.of(), MapProblems.countsLargerThan(input, 3));
    }

}