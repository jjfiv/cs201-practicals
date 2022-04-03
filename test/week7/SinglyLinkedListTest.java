package week7;

import org.junit.Assert;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;

import org.junit.Test;

import adt.ListADT;
import adt.errors.BadIndexError;
import adt.errors.EmptyListError;

public class SinglyLinkedListTest {
    private static int EXAMPLE_SIZE = 100;

    /**
     * Make a new empty list.
     * 
     * @return an empty list to be tested.
     */
    private <T> ListADT<T> makeEmptyList() {
        return new SinglyLinkedList<>();
    }

    /**
     * Helper method to make a full list.
     * 
     * @return [a, b, c, d] - a small, predictable list for many tests.
     */
    private ListADT<String> makeABCDList() {
        ListADT<String> data = makeEmptyList();
        data.addFront("d");
        testNoLoops(data);
        data.addFront("c");
        testNoLoops(data);
        data.addFront("b");
        testNoLoops(data);
        data.addFront("a");
        testNoLoops(data);
        return data;
    }

    @Test
    public void testEmpty() {
        ListADT<String> data = makeEmptyList();
        Assert.assertEquals(0, data.size());
        Assert.assertEquals(true, data.isEmpty());
    }

    @Test(expected = EmptyListError.class)
    public void testRemoveFrontCrash() {
        ListADT<String> data = makeEmptyList();
        data.removeFront();
    }

    @Test(expected = EmptyListError.class)
    public void testRemoveBackCrash() {
        ListADT<String> data = makeEmptyList();
        data.removeBack();
    }

    @Test(expected = EmptyListError.class)
    public void testRemoveIndexCrash() {
        ListADT<String> data = makeEmptyList();
        data.removeIndex(3);
    }

    @Test
    public void testAddToFront() {
        ListADT<String> data = makeEmptyList();
        Assert.assertEquals(true, data.isEmpty());
        data.addFront("1");
        testNoLoops(data);
        Assert.assertEquals(1, data.size());
        Assert.assertEquals("1", data.getIndex(0));
        Assert.assertEquals(false, data.isEmpty());
        data.addFront("0");
        testNoLoops(data);
        Assert.assertEquals(2, data.size());
        Assert.assertEquals("0", data.getIndex(0));
        Assert.assertEquals("1", data.getIndex(1));
        Assert.assertEquals(false, data.isEmpty());
        data.addFront("-1");
        testNoLoops(data);
        Assert.assertEquals(3, data.size());
        Assert.assertEquals("-1", data.getIndex(0));
        Assert.assertEquals("0", data.getIndex(1));
        Assert.assertEquals("1", data.getIndex(2));
        Assert.assertEquals(false, data.isEmpty());
        data.addFront("-2");
        testNoLoops(data);
        Assert.assertEquals("-1", data.getIndex(1));
        Assert.assertEquals("-2", data.getIndex(0));
        Assert.assertEquals("0", data.getIndex(2));
        Assert.assertEquals("1", data.getIndex(3));
        Assert.assertEquals(false, data.isEmpty());
    }

    @Test
    public void testAddToBack() {
        ListADT<String> data = makeEmptyList();
        data.addBack("1");
        testNoLoops(data);
        Assert.assertEquals(1, data.size());
        Assert.assertEquals("1", data.getIndex(0));
        data.addBack("0");
        testNoLoops(data);
        Assert.assertEquals(2, data.size());
        Assert.assertEquals("0", data.getIndex(1));
        Assert.assertEquals("1", data.getIndex(0));
        data.addBack("-1");
        testNoLoops(data);
        Assert.assertEquals(3, data.size());
        Assert.assertEquals("-1", data.getIndex(2));
        Assert.assertEquals("0", data.getIndex(1));
        Assert.assertEquals("1", data.getIndex(0));
        data.addBack("-2");
        testNoLoops(data);
        Assert.assertEquals("-2", data.getIndex(3));
        Assert.assertEquals("-1", data.getIndex(2));
        Assert.assertEquals("0", data.getIndex(1));
        Assert.assertEquals("1", data.getIndex(0));
    }

    @Test
    public void testAddBackLong() {
        ListADT<Integer> items = makeEmptyList();
        for (int i = 0; i < EXAMPLE_SIZE; i++) {
            items.addBack((i + 1) * 3);
            testNoLoops(items);
            Assert.assertEquals(i + 1, items.size());
            Assert.assertEquals((i + 1) * 3, (int) items.getBack());
        }
        for (int i = 0; i < EXAMPLE_SIZE; i++) {
            Assert.assertEquals((i + 1) * 3, (int) items.getIndex(i));
        }
    }

    @Test
    public void testAddFrontLong() {
        ListADT<Integer> items1 = makeEmptyList();
        for (int i = 0; i < EXAMPLE_SIZE; i++) {
            items1.addBack((i + 1) * 3);
            testNoLoops(items1);
            Assert.assertEquals(i + 1, items1.size());
            Assert.assertEquals((i + 1) * 3, (int) items1.getBack());
        }

        int limit = 0;
        ListADT<Integer> items2 = makeEmptyList();
        while (!items1.isEmpty()) {
            if (limit++ > EXAMPLE_SIZE * 2) {
                Assert.fail("Some issue with removeBack or addFront!");
            }
            items2.addFront(items1.removeBack());
            testNoLoops(items1);
            testNoLoops(items2);
        }
        for (int i = 0; i < EXAMPLE_SIZE; i++) {
            Assert.assertEquals((i + 1) * 3, (int) items2.getIndex(i));
        }
    }

    private void insertSorted(ListADT<Integer> items, int num) {
        for (int i = 0; i < items.size(); i++) {
            if (items.getIndex(i) >= num) {
                items.addIndex(i, num);
                return;
            }
        }
        items.addBack(num);
    }

    @Test
    public void testAddIndexMany() {
        ListADT<Integer> items1 = makeEmptyList();
        for (int i = 0; i < EXAMPLE_SIZE; i++) {
            items1.addBack((i + 1) * 3);
            testNoLoops(items1);
            Assert.assertEquals(i + 1, items1.size());
            Assert.assertEquals((i + 1) * 3, (int) items1.getBack());
        }

        Random rand = new Random(13);
        ListADT<Integer> items2 = makeEmptyList();
        int limit = 0;

        // If this test runs forever, make sure removeIndex actually removes things.
        while (!items1.isEmpty()) {
            if (limit++ > EXAMPLE_SIZE * 2) {
                Assert.fail("Some issue with addIndex or removeIndex!");
            }
            int value = items1.removeIndex(rand.nextInt(items1.size()));
            testNoLoops(items1);
            insertSorted(items2, value);
            testNoLoops(items2);
        }

        for (int i = 0; i < EXAMPLE_SIZE; i++) {
            Assert.assertEquals((i + 1) * 3, (int) items2.getIndex(i));
        }
    }

    @Test
    public void testRemoveFront() {
        ListADT<String> data = makeABCDList();
        Assert.assertEquals(4, data.size());
        Assert.assertEquals("a", data.removeFront());
        testNoLoops(data);
        Assert.assertEquals(3, data.size());
        Assert.assertEquals("b", data.removeFront());
        testNoLoops(data);
        Assert.assertEquals(2, data.size());
        Assert.assertEquals("c", data.removeFront());
        testNoLoops(data);
        Assert.assertEquals(1, data.size());
        Assert.assertEquals("d", data.removeFront());
        testNoLoops(data);
        Assert.assertEquals(0, data.size());
        Assert.assertTrue(data.isEmpty());
    }

    @Test
    public void testRemoveBack() {
        ListADT<String> data = makeABCDList();
        Assert.assertEquals(4, data.size());
        Assert.assertEquals("d", data.removeBack());
        testNoLoops(data);
        Assert.assertEquals(3, data.size());
        Assert.assertEquals("c", data.removeBack());
        testNoLoops(data);
        Assert.assertEquals(2, data.size());
        Assert.assertEquals("b", data.removeBack());
        testNoLoops(data);
        Assert.assertEquals(1, data.size());
        Assert.assertEquals("a", data.removeBack());
        testNoLoops(data);
        Assert.assertEquals(0, data.size());
        Assert.assertTrue(data.isEmpty());
    }

    @Test
    public void testRemoveIndex() {
        ListADT<String> data = makeABCDList();
        Assert.assertEquals(4, data.size());
        Assert.assertEquals("c", data.removeIndex(2));
        testNoLoops(data);
        Assert.assertEquals(3, data.size());
        Assert.assertEquals("d", data.removeIndex(2));
        testNoLoops(data);
        Assert.assertEquals(2, data.size());
        Assert.assertEquals("b", data.removeIndex(1));
        testNoLoops(data);
        Assert.assertEquals(1, data.size());
        Assert.assertEquals("a", data.removeIndex(0));
        testNoLoops(data);
        Assert.assertEquals(0, data.size());
    }

    @Test
    public void testAddIndexFront() {
        ListADT<String> data = makeEmptyList();
        data.addBack("A");
        testNoLoops(data);
        Assert.assertEquals(1, data.size());
        Assert.assertEquals("A", data.getFront());
        data.addIndex(0, "B");
        testNoLoops(data);
        Assert.assertEquals(2, data.size());
        Assert.assertEquals("B", data.getFront());
        Assert.assertEquals("A", data.getBack());
    }

    @Test
    public void testAddIndexBack() {
        ListADT<String> data = makeEmptyList();
        data.addBack("A");
        testNoLoops(data);
        Assert.assertEquals(1, data.size());
        Assert.assertEquals("A", data.getFront());
        data.addIndex(1, "B");
        testNoLoops(data);
        Assert.assertEquals(2, data.size());
        Assert.assertEquals("A", data.getFront());
        Assert.assertEquals("B", data.getBack());
    }

    @Test
    public void testAddIndexCenter() {
        ListADT<String> data = makeEmptyList();
        data.addBack("A");
        data.addBack("C");
        data.addBack("D");
        data.addBack("E");
        testNoLoops(data);
        Assert.assertEquals(4, data.size());

        data.addIndex(1, "B");
        testNoLoops(data);
        Assert.assertEquals(5, data.size());
        Assert.assertEquals("B", data.getIndex(1));
    }

    @Test
    public void testGetFront() {
        ListADT<String> data = makeABCDList();
        assertEquals("a", data.getFront());
    }

    @Test
    public void testGetBack() {
        ListADT<String> data = makeABCDList();
        assertEquals("d", data.getBack());
    }

    @Test(expected = EmptyListError.class)
    public void testGetFrontCrash() {
        ListADT<String> data = makeEmptyList();
        data.getFront();
    }

    @Test(expected = EmptyListError.class)
    public void testGetBackCrash() {
        ListADT<String> data = makeEmptyList();
        data.getBack();
    }

    @Test(expected = BadIndexError.class)
    public void testGetIndexLow() {
        ListADT<String> data = makeABCDList();
        data.getIndex(-2);
    }

    @Test(expected = BadIndexError.class)
    public void testGetIndexHigh() {
        ListADT<String> data = makeABCDList();
        data.getIndex(data.size());
    }

    @Test(expected = BadIndexError.class)
    public void testGetIndexHighEasy() {
        ListADT<String> data = makeABCDList();
        data.getIndex(data.size() * 2);
    }

    @Test(expected = BadIndexError.class)
    public void testAddIndexHighEasy() {
        ListADT<String> data = makeABCDList();
        data.addIndex(data.size() * 2, "the");
    }

    @Test(expected = BadIndexError.class)
    public void testAddIndexHigh() {
        ListADT<String> data = makeABCDList();
        data.addIndex(data.size() + 1, "the");
    }

    @Test(expected = BadIndexError.class)
    public void testAddIndexLow() {
        ListADT<String> data = makeABCDList();
        data.addIndex(-1, "the");
    }

    @Test(expected = BadIndexError.class)
    public void testSetIndexHighEasy() {
        ListADT<String> data = makeABCDList();
        data.setIndex(data.size() * 2, "the");
    }

    @Test(expected = BadIndexError.class)
    public void testSetIndexHigh() {
        ListADT<String> data = makeABCDList();
        data.setIndex(data.size(), "the");
    }

    @Test(expected = BadIndexError.class)
    public void testSetIndexLow() {
        ListADT<String> data = makeABCDList();
        data.setIndex(-1, "the");
    }

    @Test
    public void testSetIndexEasy() {
        ListADT<String> data = makeABCDList();
        data.setIndex(0, "z");
        assertEquals("z", data.getIndex(0));
        assertEquals("b", data.getIndex(1));
        assertEquals("c", data.getIndex(2));
        assertEquals("d", data.getIndex(3));

        data.setIndex(1, "y");
        assertEquals("z", data.getIndex(0));
        assertEquals("y", data.getIndex(1));
        assertEquals("c", data.getIndex(2));
        assertEquals("d", data.getIndex(3));

        data.setIndex(2, "x");
        assertEquals("z", data.getIndex(0));
        assertEquals("y", data.getIndex(1));
        assertEquals("x", data.getIndex(2));
        assertEquals("d", data.getIndex(3));

        data.setIndex(3, "w");
        assertEquals("z", data.getIndex(0));
        assertEquals("y", data.getIndex(1));
        assertEquals("x", data.getIndex(2));
        assertEquals("w", data.getIndex(3));
    }

    /**
     * This method looks into the insides of the SinglyLinkedList so it can crash if
     * you've introduced a loop.
     * 
     * @param <T>  - the type of items in the list; irrelevant.
     * @param list - the list to check for loops.
     */
    private <T> void testNoLoops(ListADT<T> list) {
        if (list.isEmpty()) {
            return;
        }
        // Cast list to SLL.
        assertTrue(list instanceof SinglyLinkedList);
        SinglyLinkedList<T> sll = (SinglyLinkedList<T>) list;

        // Create two pointers into the list:
        SinglyLinkedList.Node<T> step1 = sll.start;
        SinglyLinkedList.Node<T> step2 = sll.start;

        // Here's the strategy:
        // Move step1 by one, move step2 by two
        // If we reach the end of the list, we don't have any loops.
        // If we don't -- there is a loop -- how?
        // Well, step1 and step2 will land on the same node eventually.
        // Consider a loop of length L (A -> B -> C -> A) would have loop length L=3.
        // After 2*L steps, step2 would be at position L and step1 will also be at
        // position L.
        while (true) {
            // move step1 by one node forward.
            step1 = step1.next;
            // move step2 by two nodes forward.
            step2 = step2.next;
            if (step2 == null) {
                break; // getting to the end means no loops.
            }
            step2 = step2.next;

            // Getting to the end is good.
            if (step1 == null || step2 == null) {
                break;
            }

            // If we're in the same place, crash.
            if (step1 == step2) {
                throw new AssertionError("Loop found in SinglyLinkedList!");
            }
        }
    }

    @Test
    public void testToJava() {
        assertEquals(makeABCDList().toJava(), List.of("a", "b", "c", "d"));
    }
}
