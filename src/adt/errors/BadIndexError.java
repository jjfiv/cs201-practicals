package adt.errors;

/**
 * This class defines our own special error for when an operation is called on
 * an list that doesn't have that index.
 * 
 * @author jfoley
 *
 */
public class BadIndexError extends RuntimeException {
	public BadIndexError(int index) {
		super("BadIndexError at " + index);
	}
}
