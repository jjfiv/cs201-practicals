package week11.adventure;

import java.util.ArrayList;
import java.util.List;

/**
 * This represents a place in our text adventure; this is a Node in our graph.
 * 
 * @author jfoley
 */
public class Place {
	/**
	 * This is a list of places we can get to from this place. These are the
	 * directed edges in our graph.
	 */
	private List<Exit> exits;
	/**
	 * This is the identifier of the place.
	 */
	private String id;
	/**
	 * What to tell the user about this place.
	 */
	private String description;
	/**
	 * Whether reaching this place ends the game.
	 */
	private boolean terminal;

	/**
	 * Internal only constructor for Place. Use {@link #create(String, String)} or
	 * {@link #terminal(String, String)} instead.
	 * 
	 * @param id          - the internal id of this place.
	 * @param description - the user-facing description of the place.
	 * @param terminal    - whether this place ends the game.
	 */
	protected Place(String id, String description, boolean terminal) {
		this.id = id;
		this.description = description;
		this.exits = new ArrayList<>();
		this.terminal = terminal;
	}

	/**
	 * Create an exit for the user to navigate to another Place.
	 * 
	 * @param exit - the description and target of the other Place.
	 */
	public void addExit(Exit exit) {
		this.exits.add(exit);
	}

	/**
	 * For gameplay, whether this place ends the game.
	 * 
	 * @return true if this is the end.
	 */
	public boolean isTerminalState() {
		return this.terminal;
	}

	/**
	 * The internal id of this place, for referring to it in {@link Exit} objects.
	 * 
	 * @return the id.
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * The narrative description of this place.
	 * 
	 * @return what we show to a player about this place.
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Get a view of the exits from this Place, for navigation.
	 * 
	 * @return all the exits from this place.
	 */
	public List<Exit> getVisibleExits() {
		List<Exit> visible = new ArrayList<>();
		for (Exit e : this.exits) {
			if (!e.isSecret()) {
				visible.add(e);
			}
		}
		return visible;
	}

	/**
	 * This is a terminal location (good or bad).
	 * 
	 * @param id          - this is the id of the place (for creating {@link Exit}
	 *                    objects that go here).
	 * @param description - this is the description of the place.
	 * @return the Place object.
	 */
	public static Place terminal(String id, String description) {
		return new Place(id, description, true);
	}

	/**
	 * Create a place with an id and description.
	 * 
	 * @param id          - this is the id of the place (for creating {@link Exit}
	 *                    objects that go here).
	 * @param description - this is what we show to the user.
	 * @return the new Place object (add exits to it).
	 */
	public static Place create(String id, String description) {
		return new Place(id, description, false);
	}

	/**
	 * Implements what we need to put Place in a HashSet or HashMap.
	 */
	public int hashCode() {
		return this.id.hashCode();
	}

	/**
	 * Give a string for debugging what place is what.
	 */
	public String toString() {
		return "Place(" + this.id + " with " + this.exits.size() + " exits.)";
	}

	/**
	 * Whether this is the same place as another.
	 */
	public boolean equals(Object other) {
		if (other instanceof Place) {
			return this.id.equals(((Place) other).id);
		}
		return false;
	}

	/**
	 * Create a list of all exits, without filtering for just the 'visible' ones.
	 * 
	 * @return a copy of this {@link #exits}
	 */
	public List<Exit> getAllExits() {
		return new ArrayList<>(this.exits);
	}

}
