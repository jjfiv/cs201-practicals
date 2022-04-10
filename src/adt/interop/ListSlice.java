package adt.interop;

import adt.ListADT;

/**
 * Create a view of slice of an existing list.
 * 
 * @author jfoley
 *
 * @param <T> - the type of items in the list.
 */
public class ListSlice<T> extends ListADT<T> {
	/**
	 * The original list.
	 */
	private ListADT<T> fullList;
	/**
	 * The start of this slice.
	 */
	private int begin;
	/**
	 * The end of this slice.
	 */
	private int end;

	/**
	 * Construct a new slice.
	 * 
	 * @param data  - the full list (or slice).
	 * @param begin - the starting element.
	 * @param end   - the exclusive end of the slice.
	 */
	public ListSlice(ListADT<T> data, int begin, int end) {
		this.fullList = data;
		this.fullList.checkInclusiveIndex(begin);
		this.fullList.checkInclusiveIndex(end);
		this.begin = begin;
		this.end = end;
	}

	@Override
	public boolean isEmpty() {
		return begin == end;
	}

	@Override
	public int size() {
		return end - begin;
	}

	@Override
	public void setIndex(int index, T value) {
		checkNotEmpty();
		checkExclusiveIndex(index);
		fullList.setIndex(index + begin, value);
	}

	@Override
	public T getIndex(int index) {
		checkNotEmpty();
		checkExclusiveIndex(index);
		return fullList.getIndex(index + begin);
	}

	@Override
	public T getFront() {
		return getIndex(0);
	}

	@Override
	public T getBack() {
		return getIndex(size() - 1);
	}

	@Override
	public void addIndex(int index, T value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addFront(T value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addBack(T value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T removeIndex(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T removeBack() {
		checkNotEmpty();
		T found = this.getBack();
		this.end--;
		return found;
	}

	@Override
	public T removeFront() {
		checkNotEmpty();
		T found = this.getFront();
		this.begin++;
		return found;
	}

}
