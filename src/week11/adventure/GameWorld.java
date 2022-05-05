package week11.adventure;

/**
 * This is the interface through which our main method in
 * {@link Adventure} interacts with different games.
 * 
 * @author jfoley
 *
 */
public interface GameWorld {
	/**
	 * What is the id of the starting location for this game?
	 * 
	 * @return the id of a Place.
	 */
	String getStart();

	/**
	 * What is the Place for a given id in this game?
	 * 
	 * @param id - the internal name of the Place.
	 * @return the place object.
	 */
	Place getPlace(String id);
}
