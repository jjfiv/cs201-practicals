package week10;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class StrIntHashMapTest {
    @Test
    public void testPutDifferent() {
        StrIntHashMap table = new StrIntHashMap();
        assertEquals(0, table.size());
        table.put("a", 1);
        assertEquals(1, table.size());
        assertEquals((Integer) 1, table.get("a"));
        table.put("b", 2);
        assertEquals(2, table.size());
        assertEquals((Integer) 1, table.get("a"));
        assertEquals((Integer) 2, table.get("b"));
    }

    @Test
    public void testPutSame() {
        StrIntHashMap table = new StrIntHashMap();
        assertEquals(0, table.size());
        table.put("a", 1);
        assertEquals(1, table.size());
        assertEquals((Integer) 1, table.get("a"));
        table.put("a", 2);
        assertEquals(1, table.size());
        assertEquals((Integer) 2, table.get("a"));
    }

    @Test
    public void testGetMissing() {
        StrIntHashMap table = new StrIntHashMap();
        assertEquals(0, table.size());
        table.put("a", 1);
        assertEquals(1, table.size());
        assertEquals((Integer) 1, table.get("a"));

        for (char c : "bcdefghijklmnopqrstuvwxyz".toCharArray()) {
            assertNull("Table should not have: " + c, table.get("" + c));
        }
    }

    @Test
    public void testRemoveSmall() {
        StrIntHashMap table = new StrIntHashMap();
        table.put("a", 1);
        table.put("b", 2);
        table.put("c", 3);
        table.put("d", 4);
        table.put("e", 5);
        table.put("f", 6);
        table.put("g", 7);
        assertEquals(7, table.size());

        assertTrue(table.contains("c"));
        assertEquals((Integer) 3, table.remove("c"));
        assertFalse(table.contains("c"));

        assertEquals((Integer) 2, table.remove("b"));
        assertEquals((Integer) 1, table.remove("a"));
        assertEquals((Integer) 4, table.remove("d"));
        assertEquals((Integer) 5, table.remove("e"));
        assertEquals(2, table.size());
        assertEquals((Integer) 7, table.remove("g"));
        assertEquals((Integer) 6, table.remove("f"));
    }

    @Test
    public void testRemoveMissing() {
        StrIntHashMap table = new StrIntHashMap();
        table.put("a", 1);
        table.put("b", 2);
        table.put("c", 3);
        table.put("d", 4);
        table.put("e", 5);
        table.put("f", 6);
        table.put("g", 7);
        assertEquals(7, table.size());

        table.remove("NotFound");
        assertEquals(7, table.size());
    }

    @Test
    public void testAddRemoveLong() {
        StrIntHashMap table = new StrIntHashMap();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            table.put(Integer.toString(i), i);
            numbers.add(i);
        }
        Collections.shuffle(numbers, new Random(13));

        int size = 100;
        for (int x : numbers) {
            String key = Integer.toString(x);
            assertEquals(size, table.size());
            assertTrue(table.contains(key));
            assertEquals((Integer) x, table.remove(key));
            assertEquals(--size, table.size());
        }
        assertEquals(0, table.size());
    }

}