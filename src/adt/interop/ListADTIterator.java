package adt.interop;

import java.util.Iterator;

import adt.ListADT;

/**
 * This is an object that walks/loops/iterates/traverses through a ListADT type.
 * 
 * @author jfoley
 *
 * @param <ItemType> the type of the elements in the list.
 */
public class ListADTIterator<ItemType> implements Iterator<ItemType> {
	/**
	 * Which list are we walking through?
	 */
	ListADT<ItemType> source;
	/**
	 * How far are we?
	 */
	int i = 0;

	/**
	 * Construct this kind of object from a ListADT object.
	 * 
	 * @param list - the list to loop/traverse/iterate over.
	 */
	public ListADTIterator(ListADT<ItemType> list) {
		this.source = list;
	}

	/**
	 * Does this iterator have more data?
	 * 
	 * @return true if there are more items, false if not.
	 */
	@Override
	public boolean hasNext() {
		return i < source.size();
	}

	/**
	 * Get me the next item to use in my loop.
	 * 
	 * @return the next item in the list.
	 */
	@Override
	public ItemType next() {
		return source.getIndex(i++);
	}
}