package adt.impl;

import java.util.ArrayList;
import java.util.List;

import adt.ListADT;

/**
 * JavaList is a wrapper around Java's list as a ListADT and it gets specific if
 * you ask it silly questions.
 * 
 * @author jfoley
 *
 * @param <T> - the type of the item stored in this list.
 */
public class JavaList<T> extends ListADT<T> {
	/**
	 * The Java object doing most of the work.
	 */
	private List<T> inner;

	/**
	 * Construct an empty JavaList.
	 */
	public JavaList() {
		this.inner = new ArrayList<T>();
	}

	/**
	 * Construct this list from existing data.
	 * 
	 * @param toCopy - the elements to copy.
	 */
	public JavaList(List<T> toCopy) {
		this.inner = new ArrayList<T>(toCopy);
	}

	@Override
	public boolean isEmpty() {
		return inner.isEmpty();
	}

	@Override
	public int size() {
		return inner.size();
	}

	@Override
	public void setIndex(int index, T value) {
		checkNotEmpty();
		checkExclusiveIndex(index);
		inner.set(index, value);
	}

	@Override
	public T getIndex(int index) {
		checkNotEmpty();
		checkExclusiveIndex(index);
		return inner.get(index);
	}

	@Override
	public T getFront() {
		checkNotEmpty();
		return inner.get(0);
	}

	@Override
	public T getBack() {
		checkNotEmpty();
		return inner.get(inner.size() - 1);
	}

	@Override
	public void addIndex(int index, T value) {
		checkInclusiveIndex(index);
		inner.add(index, value);
	}

	@Override
	public void addFront(T value) {
		inner.add(0, value);
	}

	@Override
	public void addBack(T value) {
		inner.add(inner.size(), value);
	}

	@Override
	public T removeIndex(int index) {
		checkNotEmpty();
		checkExclusiveIndex(index);
		return inner.remove(index);
	}

	@Override
	public T removeBack() {
		checkNotEmpty();
		return inner.remove(inner.size() - 1);
	}

	@Override
	public T removeFront() {
		checkNotEmpty();
		return inner.remove(0);
	}

}
