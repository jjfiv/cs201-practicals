package week6;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import adt.StackADT;
import adt.errors.EmptyListError;

public class JavaStackTest {
    @Test
    public void testConstruction() {
        StackADT<String> stack = new JavaStack<>();
        assertNotNull(stack);
        assertTrue(stack.isEmpty());
    }

    @Test(expected = EmptyListError.class)
    public void testEmptyPop() {
        StackADT<String> stack = new JavaStack<>();
        assertTrue(stack.isEmpty());
        stack.pop();
    }

    @Test
    public void testEmptyPeek() {
        StackADT<String> stack = new JavaStack<>();
        assertNull(stack.peek());
    }

    @Test
    public void testPushOnce() {
        StackADT<String> stack = new JavaStack<>();
        stack.push("A");
        assertEquals("A", stack.peek());
        String popped = stack.pop();
        assertEquals("A", popped);
        assertNull(stack.peek());
    }

    @Test
    public void testPushMany() {
        StackADT<String> stack = new JavaStack<>();
        assertTrue(stack.isEmpty());

        // add four things:
        stack.push("A");
        assertEquals("A", stack.peek());
        stack.push("B");
        assertEquals("B", stack.peek());
        stack.push("C");
        assertEquals("C", stack.peek());
        stack.push("D");
        assertEquals("D", stack.peek());
        // now remove them all:
        List<String> popped = new ArrayList<>();
        popped.add(stack.pop());
        popped.add(stack.pop());
        popped.add(stack.pop());
        popped.add(stack.pop());

        assertEquals(List.of("D", "C", "B", "A"), popped);
        assertTrue(stack.isEmpty());
    }
}
