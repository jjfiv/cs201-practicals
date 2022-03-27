package adt;

import javax.annotation.Nullable;

/**
 * An interface that describes "Stack" behavior.
 * 
 * @author jfoley
 *
 * @param <T> - the type of items in the stack.
 */
public interface StackADT<T> {
	/**
	 * Add an item to this stack.
	 * 
	 * @param item - the item to add onto this stack.
	 */
	public void push(T item);

	/**
	 * Remove the top item from the stack and return it.
	 * 
	 * @return the previous top of the stack.
	 */
	public T pop();

	/**
	 * Look at the top item on this stack without removing it.
	 * 
	 * @return the top of the stack.
	 */
	public @Nullable T peek();

	/**
	 * Determine whether there is more data on this stack.
	 * 
	 * @return true if this stack is empty.
	 */
	public boolean isEmpty();
}
