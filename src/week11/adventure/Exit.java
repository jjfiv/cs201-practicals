package week11.adventure;

import java.util.Objects;

/**
 * This class represents an exit from a Place to another Place.
 * 
 * @author jfoley
 *
 */
public class Exit {
	/**
	 * How do we describe this exit to a user, e.g., "A door with a spiderweb."
	 */
	private String description;
	/**
	 * How do we identify the Place that this is going.
	 */
	private String target;

	/**
	 * Create a new Exit.
	 * 
	 * @param target      - where it goes.
	 * @param description - how it looks.
	 */
	public Exit(String target, String description) {
		this.description = description;
		this.target = target;
	}

	/**
	 * A getter for the description of this exit.
	 * 
	 * @return how it looks.
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * A getter for the target place of this exit.
	 * 
	 * @return where it goes.
	 */
	public String getTarget() {
		return this.target;
	}

	/**
	 * Make this debug-able when we print it for ourselves.
	 */
	public String toString() {
		return "Exit(" + this.description + ", " + this.target + ")";
	}

	/**
	 * Make it so we can put this in a HashMap or HashSet.
	 */
	public int hashCode() {
		return Objects.hash(this.description, this.target);
	}

	/**
	 * This is a useful definition of being the same.
	 * 
	 * @param other - another exit.
	 * @return if they go to the same place.
	 */
	public boolean goesToSamePlace(Exit other) {
		return this.target.equals(other.target);
	}

	/**
	 * The other half of hashCode that lets us put it in a HashMap or HashSet.
	 */
	public boolean equals(Object other) {
		if (other instanceof Exit) {
			Exit rhs = (Exit) other;
			return this.target.equals(rhs.target) && this.description.equals(rhs.description);
		}
		return false;
	}

	/**
	 * This is not a secret exit in any way, but a subclass of exit might be!
	 * 
	 * @return always false for a regular exit!
	 */
	public boolean isSecret() {
		// DO NOT CHANGE THIS METHOD. ONLY OVERRIDE IN A SUBCLASS.
		return false;
	}

	/**
	 * What changes about this Exit if a player searches it thoroughly? Nothing
	 * here.
	 */
	public void search() {
		// DO NOT CHANGE THIS METHOD. ONLY OVERRIDE IN A SUBCLASS.
		// Does nothing.
	}
}
