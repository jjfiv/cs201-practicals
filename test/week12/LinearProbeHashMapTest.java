package week12;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class LinearProbeHashMapTest {

    @Test
    public void testSingleElement() {
        LinearProbeHashMap<String, String> dict = new LinearProbeHashMap<>(10);
        dict.put("a", "alpha");
        assertEquals(1, dict.size());
        assertEquals("alpha", dict.get("a"));
        assertEquals("alpha", dict.remove("a"));
        assertNull(dict.get("a"));
    }

    // TODO: create enough tests that you believe this is going to work!

    /**
     * I strongly recommend using BrokenInt; which hard-codes a hash value.
     * This way you can create as many collisions as you want.
     */
    static class BrokenInt {
        int value;

        public BrokenInt(int value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return 7;
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof BrokenInt) {
                return value == ((BrokenInt) other).value;
            }
            return false;
        }
    }
}
