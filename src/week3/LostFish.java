package week3;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import week3.grid.Actor;
import week3.grid.draw.CustomFish;

/**
 * This represents a fish that is 'lost' and needs help finding its way home.
 */
public class LostFish extends Actor {
    /**
     * Is this fish currently missing? If so, it will wander.
     */
    public boolean isMissing = true;

    /**
     * Create a fish from any Java Color object.
     * 
     * @param c - the color object.
     */
    public LostFish(Color c) {
        this.visual = new CustomFish(c);
    }

    @Override
    public void act() {
        if (this.isMissing) {
            // Only 30% of the times we act (so it's catchable)
            if (this.rand.nextDouble() < 0.3) {
                // Take a random step.
                this.takeRandomStep();
            }
        }
    }

    /**
     * What colors are available for these LostFish?
     */
    private static Color[] FISH_COLORS = {
            Color.red,
            Color.green,
            Color.orange,
            // TODO: add more fish colors (see what colors are available via autocomplete!)
    };

    /**
     * We want to create these in a batch, and add them to a list.
     * 
     * @return a list of LostFish (one of each color!).
     */
    public static List<LostFish> createLostFish() {
        List<LostFish> fish = new ArrayList<LostFish>();
        for (Color c : FISH_COLORS) {
            fish.add(new LostFish(c));
        }
        return fish;
    }

}
