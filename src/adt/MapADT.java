package adt;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * An abstract view of a Map or dictionary object for CSC212.
 * 
 * @author jfoley
 *
 * @param <KeyType>   - the type of objects stored as keys; kept unique.
 * @param <ValueType> - the type of objects mapped to by keys; not necessarily
 *                    unique.
 */
public abstract class MapADT<KeyType, ValueType> implements Iterable<Pair<KeyType, ValueType>> {
	/**
	 * Put a new entry in this table.
	 * 
	 * @param k - the first column; kept unique.
	 * @param v - the second column; what the key k maps to.
	 */
	public abstract void put(KeyType k, @Nonnull ValueType v);

	/**
	 * Get the value stored for key k; or return null.
	 * 
	 * @param keyType - the key to lookup in the table.
	 * @return the value found or null if k is missing.
	 */
	@Nullable
	public abstract ValueType get(KeyType keyType);

	/**
	 * How many key-value mappings are in this data structure?
	 * 
	 * @return the size of this map.
	 */
	public abstract int size();

	/**
	 * Remove the mapping from this table based on the key k.
	 * 
	 * @param k - the key to remove from the table.
	 * @return what it was mapped to, if you care.
	 */
	@Nullable
	public abstract ValueType remove(KeyType k);

	/**
	 * Get a list of all the keys in this table (order may be random).
	 * 
	 * @return the list of keys.
	 */
	public abstract ListADT<KeyType> getKeys();

	/**
	 * Get the entries from this mapping table as a {@linkplain ListADT} of
	 * {@linkplain Pair} objects.
	 * 
	 * @return the entries of this table.
	 */
	public abstract ListADT<Pair<KeyType, ValueType>> getEntries();

	/**
	 * Supports for-loops.
	 * 
	 * @return an iterator over the entries in this set.
	 */
	@Override
	public Iterator<Pair<KeyType, ValueType>> iterator() {
		return this.getEntries().iterator();
	}

	/**
	 * Convert this to a Java data structure; probably useful for unit-test errors.
	 * 
	 * @return - a Java List object.
	 */
	public Map<KeyType, ValueType> toJava() {
		HashMap<KeyType, ValueType> output = new HashMap<>();
		for (Pair<KeyType, ValueType> row : this.getEntries()) {
			output.put(row.getKey(), row.getValue());
		}
		return output;
	}

	/**
	 * Teach java how to compare two MapADT objects.
	 * 
	 * @return true if they have the same contents.
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof MapADT) {
			// We don't really know what kind of map is passed in, so treat it as a map of
			// anything to anything:
			@SuppressWarnings("unchecked")
			MapADT<Object, Object> rhs = (MapADT<Object, Object>) other;

			// if they're not the same size, they're different!
			if (rhs.size() != this.size()) {
				return false;
			}

			// if they're not the same elements, they're different!
			for (Pair<KeyType, ValueType> row : this.getEntries()) {
				Object rhsValue = rhs.get(row.getKey());
				if (rhsValue == null) {
					return false;
				}
				if (!rhsValue.equals(row.getValue())) {
					return false;
				}
			}
			// if we got here, they're probably the same MapADT!
			return true;
		}

		return false;
	}

	/**
	 * Teach java how to print this kind of object.
	 * 
	 * @return a string representation of the data.
	 */
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("MapADT {");
		int count = 0;
		for (Pair<KeyType, ValueType> row : this.getEntries()) {
			if (count++ > 0) {
				output.append(", ");
			}
			output.append(row.getKey()).append(":").append(row.getValue());
		}
		return output.append("}").toString();
	}

	/**
	 * Don't let people mis-use this class.
	 * 
	 * @return nothing; crash only.
	 */
	@Override
	public int hashCode() {
		throw new IllegalArgumentException("Don't use a MapADT as a key in a hashmap!");
	}
}
