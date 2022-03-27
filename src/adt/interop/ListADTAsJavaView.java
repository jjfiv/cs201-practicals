package adt.interop;

import java.util.AbstractList;
import java.util.Collection;

import adt.ListADT;

/**
 * This class wraps a ListADT in Java's List interface.
 * 
 * @author jfoley
 *
 * @param <T> the type of elements in this list.
 */
public class ListADTAsJavaView<T> extends AbstractList<T> {

	/**
	 * The list to modify through Java's API.
	 */
	ListADT<T> inner;

	/**
	 * Construct a view of a {@link ListADT} that implements {@link java.util.List}
	 * for using nice Collections methods.
	 * 
	 * @param listADT - the inner list.
	 */
	public ListADTAsJavaView(ListADT<T> listADT) {
		this.inner = listADT;
	}

	@Override
	public T get(int index) {
		return this.inner.getIndex(index);
	}

	@Override
	public int size() {
		return this.inner.size();
	}

	@Override
	public boolean add(T e) {
		this.inner.addBack(e);
		return true;
	}

	@Override
	public T set(int index, T element) {
		T prev = this.inner.getIndex(index);
		this.inner.setIndex(index, element);
		return prev;
	}

	@Override
	public void add(int index, T element) {
		this.inner.addIndex(index, element);
	}

	@Override
	public T remove(int index) {
		return this.inner.removeIndex(index);
	}

	@Override
	public void clear() {
		while (this.inner.size() > 0) {
			this.inner.removeBack();
		}
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		for (T e : c) {
			this.inner.addBack(e);
		}
		return true;
	}

}
