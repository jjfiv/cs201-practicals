package week10;

import org.junit.Test;

import week10.IntBST.Node;

import java.util.*;

import static org.junit.Assert.*;

public class IntBSTTest {

    @Test
    public void testIsEmpty() {
        IntBST empty = new IntBST();
        assertTrue(empty.isEmpty());
    }

    @Test
    public void testIsNotEmpty() {
        IntBST one = new IntBST();
        one.insert(13);
        assertFalse(one.isEmpty());
    }

    @Test
    public void testTreeEq() {
        IntBST empty1 = new IntBST();
        IntBST empty2 = new IntBST();
        assertEquals(empty1, empty2);

        IntBST item1 = new IntBST();
        item1.insert(7);
        IntBST item2 = new IntBST();
        item2.insert(7);
        assertEquals(item1, item2);
    }

    @Test
    public void testInsert() {
        IntBST tree = new IntBST();
        tree.insert(4);
        tree.insert(2);
        tree.insert(5);

        assertEquals(tree.root, new Node(4, new Node(2), new Node(5)));
    }

    @Test
    public void testMaxMin() {
        IntBST tree = new IntBST(new Node(4, new Node(2), new Node(5)));
        assertEquals(2, tree.root.getMinimum());
        assertEquals(5, tree.root.getMaximum());
    }

    @Test
    public void testHeightLeaf() {
        Node leaf = new Node(7);
        assertEquals(1, leaf.getHeight());
    }

    @Test
    public void testHeightLeft() {
        Node leaf = new Node(7, new Node(7), null);
        assertEquals(2, leaf.getHeight());
    }

    @Test
    public void testHeightRight() {
        Node leaf = new Node(7, null, new Node(7));
        assertEquals(2, leaf.getHeight());
    }

    @Test
    public void testHeightImbalanced() {
        IntBST rightTree = new IntBST();
        IntBST leftTree = new IntBST();
        for (int i = 0; i < 10; i++) {
            rightTree.insert(i);
            leftTree.insert(9 - i);
        }
        assertEquals(rightTree.getHeight(), 10);
        assertEquals(rightTree.getHeight(), leftTree.getHeight());
    }

    @Test
    public void testRemoveL() {
        IntBST tree = new IntBST(new Node(4, new Node(2), new Node(5)));
        tree.remove(2);
        IntBST expected = new IntBST(new Node(4, null, new Node(5)));
        assertEquals(expected, tree);
    }

    @Test
    public void testRemoveR() {
        IntBST tree = new IntBST(new Node(4, new Node(2), new Node(5)));
        tree.remove(5);
        IntBST expected = new IntBST(new Node(4, new Node(2), null));
        assertEquals(expected, tree);
    }

    @Test
    public void testRemoveRoot() {
        IntBST tree = new IntBST(new Node(4, new Node(2), new Node(5)));
        tree.remove(4);
        IntBST expected = new IntBST(new Node(5, new Node(2), null));
        assertEquals(expected, tree);
    }

    @Test
    public void testRemoveAll() {
        IntBST tree = new IntBST();
        HashSet<Integer> knownGood = new HashSet<>();
        List<Integer> dataset = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            knownGood.add(i);
            dataset.add(i);
        }
        Collections.shuffle(dataset, new Random(13));
        for (int x : dataset) {
            tree.insert(x);
        }
        for (int x : dataset) {
            tree.remove(x);
            knownGood.remove(x);
            assertEquals(knownGood, tree.toSet());
        }
    }

    @Test
    public void testEmptyContains() {
        IntBST empty = new IntBST();
        assertFalse(empty.contains(7));
    }

    @Test
    public void testContainsEasy() {
        IntBST justSeven = new IntBST();
        justSeven.insert(7);

        assertTrue(justSeven.contains(7));

        assertFalse(justSeven.contains(6));
        assertFalse(justSeven.contains(8));
    }

    @Test
    public void testContainsMedium() {
        IntBST small = new IntBST();
        small.insert(5);
        small.insert(3);
        small.insert(2);

        small.insert(8);
        small.insert(7);
        small.insert(9);

        assertTrue(small.contains(5));
        assertTrue(small.contains(3));
        assertTrue(small.contains(2));
        assertTrue(small.contains(8));
        assertTrue(small.contains(7));
        assertTrue(small.contains(9));

        assertFalse(small.contains(6));
        assertFalse(small.contains(4));
        assertFalse(small.contains(1));
        assertFalse(small.contains(10));
    }

    @Test
    public void testContainsAll() {
        IntBST tree = new IntBST();
        HashSet<Integer> knownGood = new HashSet<>();
        List<Integer> dataset = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            knownGood.add(i);
            dataset.add(i);
        }
        Collections.shuffle(dataset, new Random(13));
        for (int x : dataset) {
            tree.insert(x);
        }
        for (int x : dataset) {
            assertTrue(tree.contains(x));
            assertEquals(knownGood, tree.toSet());
        }

        for (int i : new int[] { 44, 77, -10, 12 }) {
            assertFalse(tree.contains(i));
        }
    }

    @Test
    public void toSortedListTest() {
        IntBST small = new IntBST();
        small.insert(5);
        small.insert(3);
        small.insert(2);

        small.insert(8);
        small.insert(7);
        small.insert(9);

        assertEquals(Arrays.asList(2, 3, 5, 7, 8, 9), small.toSortedList());
    }
}
