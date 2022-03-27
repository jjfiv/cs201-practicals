package adt;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * This is our abstract interface to a Set. Items inside are unordered but
 * unique.
 * 
 * @author jfoley
 *
 * @param <ItemType> - the type of the items stored in this Set.
 */
public abstract class SetADT<ItemType> implements Iterable<ItemType> {
	/**
	 * How many items are inside.
	 * 
	 * @return the number of unique items added to the set.
	 */
	public abstract int size();

	/**
	 * Add a element to this set if new/unique.
	 * 
	 * @param value - the value to add.
	 * @return true if the element was new.
	 */
	public abstract boolean insert(ItemType value);

	/**
	 * Check whether a certain value has been seen in this Set.
	 * 
	 * @param value - the value to look for.
	 * @return true if already inside this set.
	 */
	public abstract boolean contains(ItemType value);

	/**
	 * Remove a value from this Set. No error is reported if your remove something
	 * you don't have.
	 * 
	 * @param value - the value to remove.
	 * @return true if the element was found to remove.
	 */
	public abstract boolean remove(ItemType value);

	/**
	 * Convert this SetADT to a list, for equality comparisons and looping.
	 * 
	 * @return a ListADT object.
	 */
	public abstract ListADT<ItemType> toList();

	/**
	 * Supports for-loops.
	 * 
	 * @return an iterator over the items in this set.
	 */
	@Override
	public Iterator<ItemType> iterator() {
		return this.toList().iterator();
	}

	/**
	 * Convert this to a Java data structure; probably useful for unit-test errors.
	 * 
	 * @return - a Java List object.
	 */
	public Set<ItemType> toJava() {
		HashSet<ItemType> output = new HashSet<>();
		for (ItemType x : this.toList()) {
			output.add(x);
		}
		return output;
	}

	/**
	 * Teach java how to compare two ListADT objects.
	 * 
	 * @return true if they have the same contents.
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof SetADT) {
			// We don't really know what kind of list is passed in, so treat it as a list of
			// anything:
			@SuppressWarnings("unchecked")
			SetADT<Object> rhs = (SetADT<Object>) other;

			// if they're not the same size, they're different!
			if (rhs.size() != this.size()) {
				return false;
			}

			// if it doesn't have all the things we do, they're different.
			for (ItemType x : this.toList()) {
				if (!rhs.contains(x)) {
					return false;
				}
			}

			// if we got here, they're the same sets!
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
		output.append("SetADT {{");
		int count = 0;
		for (ItemType x : this.toList()) {
			if (count++ > 0) {
				output.append(", ");
			}
			output.append(x);
		}
		return output.append("}}").toString();
	}

	/**
	 * Don't let people mis-use this class.
	 * 
	 * @return nothing; crash only.
	 */
	@Override
	public int hashCode() {
		throw new IllegalArgumentException("Don't use a SetADT as a key in a hashmap!");
	}
}
