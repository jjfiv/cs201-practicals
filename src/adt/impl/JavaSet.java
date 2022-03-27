package adt.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import adt.ListADT;
import adt.SetADT;

/**
 * Java Set Data Structure wrapped in our SetADT interface.
 * 
 * @author jfoley
 *
 * @param <T> - the type of items in the set.
 */
public class JavaSet<T> extends SetADT<T> {
	/**
	 * The private Java set on the inside; does all the work.
	 */
	LinkedHashSet<T> inner = new LinkedHashSet<>();

	@Override
	public int size() {
		return inner.size();
	}

	@Override
	public boolean insert(T value) {
		return inner.add(value);
	}

	@Override
	public boolean contains(T value) {
		return inner.contains(value);
	}

	@Override
	public boolean remove(T value) {
		return inner.remove(value);
	}

	@Override
	public ListADT<T> toList() {
		return new JavaList<>(new ArrayList<>(inner));
	}
}
