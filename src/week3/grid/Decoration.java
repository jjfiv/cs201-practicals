package week3.grid;

/**
 * A shorthand class for actors that won't do anything.
 */
public class Decoration extends Actor {

    /**
     * To construct a decoration, merely pick an emoji.
     * 
     * @param emoji - the name of the image to use.
     */
    public Decoration(String emoji) {
        this.setEmoji(emoji);
    }

    @Override
    public void act() {
        // Nope.
    }
}
