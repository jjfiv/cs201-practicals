package week3.grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import me.jjfoley.gfx.IntPoint;
import week3.grid.draw.Drawable;
import week3.grid.draw.NamedEmoji;

/**
 * Any entity that participates in {@link GridEnv} is an Actor.
 */
public abstract class Actor {
    /**
     * Where is this actor located? x-coordinate.
     */
    protected int x;
    /**
     * Where is this actor located? y-coordinate.
     */
    protected int y;
    /**
     * In which environment is this actor located?
     */
    protected GridEnv environment = null;
    /**
     * What does this Actor look like?
     * It's a separate tree of classes so you don't have to worry about graphics.
     * 
     * This design style is called composition (the member inside does all the
     * work where appropriate).
     * 
     * Change the emoji using {@link #setEmoji(String)}
     */
    public Drawable visual = new NamedEmoji("question");
    /**
     * What order was this inserted?
     */
    protected int insertionOrder = -1;
    /**
     * If you want random numbers, every Actor has access already.
     */
    protected Random rand = ThreadLocalRandom.current();

    /**
     * Use one of the predefined images for this Actor's visual representation.
     * 
     * @param name - the name of a 'mini image'.
     */
    protected void setEmoji(String name) {
        this.visual = new NamedEmoji(name);
    }

    /**
     * Remove this actor from the environment.
     */
    public void remove() {
        if (this.environment != null) {
            this.environment.remove(this);
        }
    }

    /**
     * Set the position of this actor.
     * 
     * @param x - the new x coordinate.
     * @param y - the new y coordinate.
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * What does this actor do on its turn?
     */
    public abstract void act();

    /**
     * Where is this actor? In grid units.
     * 
     * @return an (x,y) point.
     */
    public IntPoint getPoint() {
        return new IntPoint(this.x, this.y);
    }

    /**
     * Change the location of htis actor.
     * 
     * @param pt - the new location (x,y)
     */
    public void setPoint(IntPoint pt) {
        this.x = pt.x;
        this.y = pt.y;
    }

    /**
     * Given this actor, create a list of the four adjacent (x,y) coordinates.
     * 
     * @return [(x-1, y), (x+1, y), (x, y-1), (x, y+1)]
     */
    public List<IntPoint> getNeighborPoints() {
        return List.of(new IntPoint(this.x - 1, this.y), new IntPoint(this.x + 1, this.y),
                new IntPoint(this.x, this.y - 1), new IntPoint(this.x, this.y + 1));
    }

    /**
     * Take a random step (if possible).
     */
    public void takeRandomStep() {
        // 1. find all possible steps:
        List<IntPoint> possibleSteps = new ArrayList<>();
        for (IntPoint pt : this.getNeighborPoints()) {
            if (this.environment.inBounds(pt) && this.environment.find(pt.x, pt.y).isEmpty()) {
                possibleSteps.add(pt);
            }
        }
        // 2.a. give up if none:
        if (possibleSteps.isEmpty()) {
            return;
        }
        // 2.b. pick at random:
        int index = this.rand.nextInt(possibleSteps.size());
        this.setPoint(possibleSteps.get(index));
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "@" + this.insertionOrder;
    }
}
