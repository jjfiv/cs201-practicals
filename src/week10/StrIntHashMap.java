package week10;

import adt.ArrayWrapper;
import adt.ListADT;
import adt.MapADT;
import adt.Pair;
import adt.errors.TODOErr;
import adt.impl.JavaList;

public class StrIntHashMap extends MapADT<String, Integer> {
    /**
     * This is our array of buckets; each cell is the "start" of a
     * Singly-Linked-List!
     */
    ArrayWrapper<EntryNode> buckets;

    /**
     * Let's keep track of size separately so it can be O(1).
     */
    int size;

    /**
     * Let's keep track of numUsedBuckets so we know when to resize.
     */
    int numUsedBuckets;

    /**
     * StrIntHashMap:
     * 
     */
    public StrIntHashMap() {
        // We just use an array of buckets, since growing is not the same as for
        // GrowableList/ArrayList.
        this.buckets = new ArrayWrapper<>(7);
        // We start off with no elements!
        this.size = 0;
        // We start off with no used buckets!
        this.numUsedBuckets = 0;
    }

    /**
     * Private helper method: into which bucket does this key belong?
     * 
     * @param key - a string key.
     * @return - the bucket index it would be in if we had it.
     */
    private int whichBucket(String key) {
        return Math.abs(key.hashCode()) % this.buckets.length();
    }

    /**
     * What percentage of the buckets in this HashMap are used?
     * 
     * @return numUsedBuckets / numBuckets.
     */
    private double loadFactor() {
        return this.numUsedBuckets / (double) this.buckets.length();
    }

    /**
     * Resize method goals: - create new array of buckets (bigger or smaller!) -
     * re-hash all elements into new array - make sure size/numUsedBuckets are still
     * consistent.
     *
     * @param newNumBuckets - tells us whether we're growing or shrinking.
     */
    private void resize(int newNumBuckets) {
        // hold onto oldBuckets;
        ArrayWrapper<EntryNode> oldBuckets = this.buckets;
        // make this.buckets be the new array.
        this.buckets = new ArrayWrapper<>(newNumBuckets);

        // need to reset this value as we copy to a larger or smaller array of buckets.
        this.numUsedBuckets = 0;

        // for all old buckets:
        for (int i = 0; i < oldBuckets.length(); i++) {
            // for all entries in those buckets:
            for (EntryNode n = oldBuckets.getIndex(i); n != null; n = n.next) {
                // find-new-bucket:
                int newBucket = whichBucket(n.key);
                // add-to-front:
                EntryNode start = this.buckets.getIndex(newBucket);
                if (start == null) {
                    this.numUsedBuckets++;
                }
                this.buckets.setIndex(newBucket, new EntryNode(n.key, n.value, start));
            }
        }

    }

    /**
     * Call this method every time we remove items.
     */
    private void maybeShrink() {
        // Enforce a minimum bucket-size.
        if (this.buckets.length() > 7 && loadFactor() < 0.25) {
            // Shrink by roughly half.
            int newBuckets = this.buckets.length() / 2;
            // Try to keep the number of buckets odd:
            if (newBuckets % 2 == 0) {
                newBuckets -= 1;
            }
            // Always at least 7 buckets:
            this.resize(Math.max(7, newBuckets));
        }
    }

    /**
     * If we're more than 75% full, try to grow this HashMap.
     */
    private void maybeGrow() {
        // check whether we should resize or not:
        if (loadFactor() > 0.75) {
            this.resize(this.buckets.length() * 2 - 1);
        }
    }

    @Override
    public void put(String key, Integer value) {
        // 1. Calculate which bucket contains our key.
        int bucket = whichBucket(key);
        // 2. Get the list of entries in that bucket:
        EntryNode start = this.buckets.getIndex(bucket);
        // 3. Search the list for the pair we want:
        for (EntryNode current = start; current != null; current = current.next) {
            // 3.a. If found, update the node and leave this method early.
            throw new TODOErr("StrIntHashMap.put");
        }
        // 3.b. If not found, add our key, value to the front of this list! O(1).
        EntryNode addFront = new EntryNode(key, value, start);
        this.buckets.setIndex(bucket, addFront);

        // 4. Need to update our sizes manually! (and call maybeGrow)
        this.size += 1;
        if (start == null) {
            this.numUsedBuckets += 1;
        }
        this.maybeGrow();
    }

    /**
     * Return true if the key is present in this HashMap.
     * We do not just call "this.get(key) != null" because we want to allow folks to
     * store 'null' values in their HashMap if they want.
     * 
     * @param key - the key to search for.
     * @return true if it was found, false if not.
     */
    public boolean contains(String key) {
        // 1. Calculate which bucket contains our key.
        int bucket = whichBucket(key);
        // 2. Get the list of entries in that bucket:
        EntryNode start = this.buckets.getIndex(bucket);
        // 3. Search the list for the pair we want:
        for (EntryNode current = start; current != null; current = current.next) {
            if (current.matches(key)) {
                // 3.a. If found, return true.
                return true;
            }
        }
        // 3.b. It wasn't found.
        return false;
    }

    /**
     * Consider {@linkplain #contains} as a very similar method.
     */
    @Override
    public Integer get(String key) {
        // 1. Calculate which bucket contains our key.
        // 2. Get the list of entries in that bucket:
        // 3. Search the list for the pair we want:
        // 3.a. If we find it, return the value being stored!
        // 3.b. If we don't find it, return null!
        throw new TODOErr("StrIntHashMap.get");
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Integer remove(String key) {
        // 1. Calculate which bucket contains our key.
        int bucket = whichBucket(key);
        // 2. Get the list of entries in that bucket:
        EntryNode start = this.buckets.getIndex(bucket);

        // 3. Search the list for the pair we want, and delete it.
        // (Remember: Singly-Linked-List removeIndex).
        EntryNode previous = null;
        for (EntryNode current = start; current != null; current = current.next) {
            if (current.matches(key)) {
                // 3.a. If we find it, remove the node and return the current value.
                if (previous == null) {
                    // 3.a.1. it was the first node: (removeFront)
                    // HINT: you'll need buckets.setIndex
                    throw new TODOErr("HashMap.SLL.removeFront");
                } else {
                    // 3.a.2. it wasn't the first node:
                    previous = current.next;
                }

                // 3.a.3. Update sizes & maybe shrink buckets...
                if (this.buckets.getIndex(bucket) == null) {
                    this.numUsedBuckets -= 1;
                }
                this.size -= 1;
                this.maybeShrink();

                // 3.a.4. Return value:
                return current.value;
            }
            // Update previous as we go around...
            previous = current;
        }
        // 3.b. If we don't find it, return null!
        return null;
    }

    @Override
    public ListADT<String> getKeys() {
        ListADT<String> keys = new JavaList<>();
        for (int i = 0; i < buckets.length(); i++) {
            for (EntryNode n = buckets.getIndex(i); n != null; n = n.next) {
                keys.addBack(n.key);
            }
        }
        return keys;
    }

    @Override
    public ListADT<Pair<String, Integer>> getEntries() {
        ListADT<Pair<String, Integer>> entries = new JavaList<>();
        for (int i = 0; i < buckets.length(); i++) {
            for (EntryNode n = buckets.getIndex(i); n != null; n = n.next) {
                entries.addBack(n.getPair());
            }
        }
        return entries;
    }

    /**
     * This is a "singly-linked-list" node of HashTable entries.
     */
    public static class EntryNode {
        /**
         * The key from put, remove, get, etc.
         */
        String key;
        /**
         * The value stored for this key.
         */
        int value;
        /**
         * The next item after this one in the same bucket.
         */
        EntryNode next;

        /**
         * Construct a new node in our bucket linked-lists:
         * 
         * @param key   - the key in our map.
         * @param value - the value associated with it.
         * @param next  - the 'next' element in this linked list.
         */
        public EntryNode(String key, int value, EntryNode next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        /**
         * Helper method for comparing keys when searching.
         * 
         * @param key - the key we're looking for.
         * @return true if it matches the key in this EntryNode.
         */
        public boolean matches(String key) {
            return this.key.equals(key);
        }

        /**
         * Helper method for getEntries(): turn this into a Pair object.
         * 
         * @return (key, value)
         */
        public Pair<String, Integer> getPair() {
            return new Pair<>(this.key, this.value);
        }
    }
}
