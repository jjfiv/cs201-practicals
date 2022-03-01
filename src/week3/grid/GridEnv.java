package week3.grid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import me.jjfoley.gfx.IntPoint;

/**
 * This class represents a world simulation.
 */
public class GridEnv {
    /**
     * This list contains the actors (turtles, rocks, etc.) that have been added to
     * the grid.
     */
    public List<Actor> actors;

    /**
     * How big the grid environment is, in terms of width (number of cells).
     */
    public int width;
    /**
     * How big the grid environment is, in therms of height (number of cells).
     */
    public int height;
    /**
     * How many actors have been live in this simulation? This ensures they each get
     * a unique {@link Actor#insertionOrder}.
     */
    private int insertions;

    /**
     * To construct a GridEnv, pick a width and height (in cells).
     * 
     * @param width  - the size (horizontally).
     * @param height - the size (vertically).
     */
    public GridEnv(int width, int height) {
        this.width = width;
        this.height = height;
        this.insertions = 0;
        this.actors = new ArrayList<>();
    }

    /**
     * Add an actor to this GridEnv.
     * 
     * @param <T>   - the subclass of Actor involved.
     * @param actor - any subclass of Actor is allowed.
     * @return the actor you added.
     */
    public <T extends Actor> T insert(T actor) {
        if (actor.environment != null) {
            throw new RuntimeException("Actor can only be inserted into one environment at a time.");
        }
        actors.add(actor);
        actor.insertionOrder = ++this.insertions;
        actor.environment = this;
        return actor;
    }

    /**
     * Check whether a point fits within this GridEnv.
     * 
     * @param point - (x,y)
     * @return true if (x,y) are positive and less than width and height.
     */
    public boolean inBounds(IntPoint point) {
        if (point.x < 0 || point.x >= this.width || point.y < 0 || point.y >= this.height) {
            return false;
        }
        return true;
    }

    /**
     * Insert an actor randomly.
     * 
     * @param <T>   the subclass of actor.
     * @param actor the actor itself.
     * @return the actor after insertion.
     */
    public <T extends Actor> T insertRandomly(T actor) {
        // Make a list of width * height points available:
        Set<IntPoint> available = new HashSet<>();
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                available.add(new IntPoint(x, y));
            }
        }
        // Loop over existing actors, removing any points they touch.
        for (Actor existing : this.actors) {
            available.remove(existing.getPoint());
        }
        // add the new thing, no matter what.
        this.insert(actor);
        // Make the set of points into a list.
        List<IntPoint> positions = new ArrayList<>(available);
        // if there's no points, leave it whereever.
        if (positions.size() > 0) {
            // otherwise, choose a randomly-available point.
            IntPoint where = positions.get(ThreadLocalRandom.current().nextInt(positions.size()));
            actor.setPosition(where.x, where.y);
        }
        // return the actor so it can be used.
        return actor;
    }

    /**
     * Remove an actor from this grid/world.
     * 
     * @param actor - the actor to remove (must be inserted!)
     */
    public void remove(Actor actor) {
        if (actor.environment != this) {
            throw new RuntimeException("Actor can only be removed from its own environment.");
        }
        this.actors.remove(actor);
        actor.environment = null;
    }

    /**
     * The size of this environment (horizontally) in cells.
     * 
     * @return the width.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * The size of this environment (vertically) in cells.
     * 
     * @return the height.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Find all the actors present in a particular (x,y) cell.
     * 
     * @param pt - the (x,y) coordinates.
     * @return the actors in the cell.
     */
    public List<Actor> find(IntPoint pt) {
        return this.find(pt.x, pt.y);
    }

    /**
     * Find all the actors present in a particular (x,y) cell.
     * 
     * @param x the x-coordinate.
     * @param y the y-coordinate.
     * @return the actors in the cell.
     */
    public List<Actor> find(int x, int y) {
        List<Actor> output = new ArrayList<>();
        for (Actor actor : this.actors) {
            if (actor.x == x && actor.y == y) {
                output.add(actor);
            }
        }
        return output;
    }

    /**
     * Get a read-only view {@link Collections#unmodifiableList} of all the actors
     * in this GridEnv.
     * Mostly used for graphics in {@link GridView}.
     * 
     * @return a list of all Actors registered in this environment.
     */
    public List<Actor> getActors() {
        return Collections.unmodifiableList(this.actors);
    }

    /**
     * Tell all the actors in this GridEnv to take their {@link Actor#act} method
     * step once.
     */
    public void act() {
        // Update: by copying the list here, we make it possible for actors to remove
        // themselves without concurrent modification of this.actors.
        for (Actor it : new ArrayList<>(this.actors)) {
            it.act();
        }
    }

}
