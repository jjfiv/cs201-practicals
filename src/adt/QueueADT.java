package adt;

import javax.annotation.Nullable;

/**
 * An interface that describes "Queue" behavior.
 * 
 * @author jfoley
 *
 * @param <T> the type of items in the queue.
 */
public interface QueueADT<T> {
	/**
	 * Add an item to this queue.
	 * 
	 * @param item - the item to add.
	 */
	public void enqueue(T item);

	/**
	 * Take a look at the next item, if any.
	 * 
	 * @return the next item, or null.
	 */
	public @Nullable T peek();

	/**
	 * Remove an item from this queue.
	 */
	public T dequeue();

	/**
	 * Check whether there are any items in the queue.
	 * 
	 * @return true if there are no more items.
	 */
	public boolean isEmpty();
}
