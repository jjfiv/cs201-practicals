package adt.errors;

/**
 * I've defined this class for all starter code methods you need to implement.
 * 
 * @author jfoley
 */
public class TODOErr extends RuntimeException {
	/**
	 * Construct a need-to-do-this-later error with no particular message.
	 */
	public TODOErr() {
		super("TODO-Error");
	}

	/**
	 * Construct a TODOErr with some provenance.
	 * 
	 * @param source - where the error came from.
	 */
	public TODOErr(String source) {
		super("TODO: " + source);
	}
}
