package week7;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import adt.QueueADT;
import adt.errors.EmptyListError;

public class SLLQueueTest {
    @Test
    public void testEmpty() {
        QueueADT<String> q = new SLLQ<String>();
        assertTrue(q.isEmpty());
    }

    @Test
    public void testEmptyPeek() {
        QueueADT<String> q = new SLLQ<String>();
        assertNull(q.peek());
    }

    @Test(expected = EmptyListError.class)
    public void testEmptyDequeue() {
        QueueADT<String> q = new SLLQ<String>();
        assertNull(q.dequeue());
    }

    @Test
    public void testEnqueue1() {
        QueueADT<String> q = new SLLQ<String>();
        q.enqueue("A");
        assertFalse(q.isEmpty());
        assertEquals("A", q.peek());
    }

    @Test
    public void testEnqueueABCD() {
        QueueADT<String> q = new SLLQ<String>();
        q.enqueue("A");
        assertEquals("A", q.peek());
        q.enqueue("B");
        assertEquals("A", q.peek());
        q.enqueue("C");
        assertEquals("A", q.peek());
        q.enqueue("D");
        assertEquals("A", q.peek());
    }

    @Test
    public void testOneAtATime() {
        QueueADT<String> q = new SLLQ<String>();
        q.enqueue("A");
        assertEquals("A", q.dequeue());
        assertNull(q.peek());
        q.enqueue("B");
        assertEquals("B", q.dequeue());
        assertNull(q.peek());
        q.enqueue("C");
        assertEquals("C", q.dequeue());
        assertNull(q.peek());
        q.enqueue("D");
        assertEquals("D", q.dequeue());
        assertNull(q.peek());
    }

    @Test
    public void testAllAtOnce() {
        QueueADT<String> q = new SLLQ<String>();
        q.enqueue("A");
        q.enqueue("B");
        q.enqueue("C");
        q.enqueue("D");
        assertEquals("A", q.dequeue());
        assertEquals("B", q.dequeue());
        assertEquals("C", q.dequeue());
        assertEquals("D", q.dequeue());
        assertTrue(q.isEmpty());
    }
}
