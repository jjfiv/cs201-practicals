package week3;

import week3.grid.Actor;

/**
 * This is our Turtle class.
 */
public class Turtle extends Actor {

    /**
     * Construct a new Turtle.
     */
    public Turtle() {
        this.setEmoji("turtle");
    }

    @Override
    public void act() {
        // Turtle isn't going to take steps on its own.
        // We'll use the controls pressed to move.
    }

}
