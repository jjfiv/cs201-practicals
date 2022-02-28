package week3;

import week3.grid.Actor;

/**
 * This represents one of many fruits to collect.
 */
public class Fruit extends Actor {

    /**
     * These are all the fruits in our small emoji set.
     */
    public static String[] EMOJI_NAMES = { "strawberry", "cherries", "grapes", "green-apple", "kiwi-fruit", "lemon",
            "mango", "pear", "pineapple", "red-apple", "tangerine", "watermelon" };

    /**
     * Construct a new Fruit object.
     */
    public Fruit() {
        // TODO: take an int parameter to this constructor, and look up into the static
        // array for the setEmoji call.
        this.setEmoji(EMOJI_NAMES[0]);
        this.visual.setScale(0.8);
    }

    @Override
    public void act() {
        // TODO Challenge: the amazing shrinking fruit.
        // Use this.visual.getScale() and this.visual.setScale() to shrink this fruit
        // over time, and use this.remove() to remove it when it gets too small.
    }

}
