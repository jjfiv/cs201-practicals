package week3.grid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import me.jjfoley.gfx.IntPoint;

public class GridEnv {
    public List<Actor> actors;
    public int width;
    public int height;
    private int insertions;

    public GridEnv(int width, int height) {
        this.width = width;
        this.height = height;
        this.insertions = 0;
        this.actors = new ArrayList<>();
    }

    public Actor insert(Actor actor) {
        if (actor.environment != null) {
            throw new RuntimeException("Actor can only be inserted into one environment at a time.");
        }
        actors.add(actor);
        actor.insertionOrder = ++this.insertions;
        actor.environment = this;
        return actor;
    }

    public boolean inBounds(IntPoint point) {
        if (point.x < 0 || point.x >= this.width || point.y < 0 || point.y >= this.height) {
            return false;
        }
        return true;
    }

    public <T extends Actor> T insertRandomly(T actor) {
        Set<IntPoint> available = new HashSet<>();
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                available.add(new IntPoint(x, y));
            }
        }
        for (Actor existing : this.actors) {
            available.remove(existing.getPoint());
        }
        this.insert(actor);
        List<IntPoint> positions = new ArrayList<>(available);
        if (positions.size() > 0) {
            IntPoint where = positions.get(ThreadLocalRandom.current().nextInt(positions.size()));
            actor.setPosition(where.x, where.y);
        }
        return actor;
    }

    public void remove(Actor actor) {
        if (actor.environment != this) {
            throw new RuntimeException("Actor can only be removed from its own environment.");
        }
        this.actors.remove(actor);
        actor.environment = null;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public List<Actor> find(IntPoint pt) {
        return this.find(pt.x, pt.y);
    }

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

    public void act() {
        for (Actor it : this.actors) {
            it.act();
        }
    }

}
