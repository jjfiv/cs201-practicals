package week3;

import java.util.ArrayList;
import java.util.List;

import me.jjfoley.gfx.IntPoint;
import week3.grid.Actor;
import week3.grid.Buttons;
import week3.grid.GridEnv;
import week3.grid.GridView;

/**
 * The HungryTurtle simulation/game.
 */
public class HungryTurtleMain extends GridView {
    /**
     * Make the grid logically bigger or smaller here (horizontally).
     */
    public static int GRID_WIDTH = 10;
    /**
     * Make the grid logically bigger or smaller here (vertically).
     */
    public static int GRID_HEIGHT = 10;
    /**
     * This is you, the Turtle.
     */
    public Turtle turtle;
    /**
     * This is a list of fruit to eat.
     */
    public ArrayList<Fruit> fruit;
    /**
     * This is how many fruits you've eaten.
     */
    public int fruitsEaten = 0;

    /**
     * This constructs our simulation.
     */
    public HungryTurtleMain() {
        super(new GridEnv(GRID_WIDTH, GRID_HEIGHT));
        // Start the turtle anywhere:
        this.turtle = this.grid.insertRandomly(new Turtle());

        // Create a line of rocks on the side.
        // TODO: wall in the environment with Rocks.
        for (int y = 0; y < GRID_HEIGHT; y++) {
            this.grid.insert(new Rock()).setPosition(0, y);
        }

        // Keep a list of fruit (it's OK for the turtle to step on them)
        this.fruit = new ArrayList<>();

        // TODO: base this loop off of Fruit.EMOJI_NAMES array.
        for (int i = 0; i < 5; i++) {
            // TODO: vary the kind of fruit.
            this.fruit.add(this.grid.insertRandomly(new Fruit()));
        }
    }

    @Override
    public String getHeaderText() {
        // This text shows up at the top of the window.
        // You won't need to mess with this.
        return "Fruits Eaten: " + this.fruitsEaten + " Left: " + this.fruit.size();
    }

    /**
     * Helper method: can the Turtle legally move here?
     * 
     * @param dest - the (x,y) it's trying to move to.
     * @return true if it's OK, false if not.
     */
    public boolean isLegalMove(IntPoint dest) {
        List<Actor> steppedOn = this.grid.find(dest.x, dest.y);
        // Can't actually move if there's something that's not a fruit there...
        for (Actor item : steppedOn) {
            // Is this element not in our fruit list?
            if (!this.fruit.contains(item)) {
                // Then you can't step on it.
                return false;
            }
        }
        // Challenge: You can also return false here if dest is out of bounds.
        return true;
    }

    /**
     * Helper method to process any fruit the Turtle may be standing on top of.
     */
    public void eatFruit() {
        // Find all the things the Turtle is co-located with:
        List<Actor> onTop = this.grid.find(turtle.getPoint());

        // Eat fruit in onTop list
        for (Actor it : onTop) {
            if (this.fruit.contains(it)) {
                // Delete it from the world!
                this.grid.remove(it);
                // Award points:
                this.fruitsEaten++;
                // TODO: remove from this.fruits list.
            }
        }
    }

    @Override
    public void buttons(Buttons pressed) {
        // This method gets called when you press WASD, arrow-keys, or space.

        // The Buttons class can give you the position the turtle's trying to move
        // towards (saving you some if/elses).
        IntPoint turtleNext = pressed.nextPosition(turtle.getPoint());

        // Now determine if it's OK to move in that direction.
        if (this.isLegalMove(turtleNext)) {
            // actually move if we get here:
            turtle.setPoint(turtleNext);
        }

        // Now process any fruit we're standing on...
        this.eatFruit();
    }

    /**
     * Run the HungryTurtle game:
     * 1. create an instance
     * 2. call start
     * 
     * @param args - ignore args.
     */
    public static void main(String[] args) {
        GridView app = new HungryTurtleMain();
        app.start();
    }
}
