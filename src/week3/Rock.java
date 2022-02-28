package week3;

import week3.grid.Actor;

/**
 * This class does not represent the Rock, but it represents a Rock.
 */
public class Rock extends Actor {

    /**
     * Construct a new Rock.
     */
    public Rock() {
        // Only have to set the emoji name once.
        this.setEmoji("rock");
    }

    @Override
    public void act() {
        // Rocks don't do anything.
    }

}
