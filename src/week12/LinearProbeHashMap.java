package week12;

import adt.ArrayWrapper;
import adt.ListADT;
import adt.MapADT;
import adt.Pair;
import adt.errors.RanOutOfSpaceError;
import adt.impl.JavaList;

public class LinearProbeHashMap<K, V> extends MapADT<K, V> {
    /**
     * Magic value to signify DELETED slots; we may not be able to erase a slot,
     * because it may break a collision chain.
     */
    private final Pair<K, V> DELETED = new Pair<>(null, null);

    /**
     * Linear probing hash table entries.
     */
    ArrayWrapper<Pair<K, V>> entries;
    /**
     * Keep track of the number of entries.
     */
    int size;

    /**
     * Create a new LinearProbeHashMap with a fixed capacity.
     * This is to avoid having to clutter it up with resize logic.
     * 
     * @param capacity - the capacity of the backing array.
     */
    public LinearProbeHashMap(int capacity) {
        this.size = 0;
        this.entries = new ArrayWrapper<>(capacity);
    }

    @Override
    public void put(K key, V value) {
        int hash = Math.abs(key.hashCode());

        int empty = -1;
        for (int i = 0; i < entries.length(); i++) {
            int position = (hash + i) % entries.length();
            Pair<K, V> here = entries.getIndex(position);
            if (here == null) {
                empty = position;
                break;
            } else if (here == DELETED) {
                if (empty != -1) {
                    empty = position;
                }
                continue; // keep going past deletions
            } else if (here.getKey().equals(key)) {
                here.setValue(value);
                return;
            }
        }
        // did we go all the way around before failing?
        if (empty == -1) {
            // TODO: figure out how to resize.
            throw new RanOutOfSpaceError();
        }
        // put it in empty location:
        entries.setIndex(empty, new Pair<>(key, value));
        this.size++;
    }

    @Override
    public V get(K key) {
        int hash = Math.abs(key.hashCode());

        for (int i = 0; i < entries.length(); i++) {
            int position = (hash + i) % entries.length();
            Pair<K, V> here = entries.getIndex(position);
            if (here == null) {
                // didn't find it.
                return null;
            } else if (here.getKey().equals(key)) {
                return here.getValue();
            }
        }
        // didn't find it, also it's full...
        return null;
    }

    @Override
    public V remove(K key) {
        int hash = Math.abs(key.hashCode());

        for (int i = 0; i < entries.length(); i++) {
            int position = (hash + i) % entries.length();
            Pair<K, V> here = entries.getIndex(position);
            if (here == null) {
                // didn't find it.
                return null;
            } else if (here.getKey().equals(key)) {
                if (entries.getIndex((position + 1) % entries.length()) == null) {
                    entries.setIndex(position, null);
                } else {
                    entries.setIndex(position, DELETED);
                }
                this.size--;
                return here.getValue();
            }
        }
        // didn't find it, also it's full...
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ListADT<K> getKeys() {
        ListADT<K> keys = new JavaList<>();
        for (int i = 0; i < entries.length(); i++) {
            Pair<K, V> here = entries.getIndex(i);
            if (here != null && here != DELETED) {
                keys.addBack(here.getKey());
            }
        }
        return keys;
    }

    @Override
    public ListADT<Pair<K, V>> getEntries() {
        ListADT<Pair<K, V>> pairs = new JavaList<>();
        for (int i = 0; i < entries.length(); i++) {
            Pair<K, V> here = entries.getIndex(i);
            if (here != null && here != DELETED) {
                pairs.addBack(here);
            }
        }
        return pairs;
    }

}
