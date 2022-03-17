package week5.twos;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import week3.grid.Actor;

/**
 * This class defines a number in the 2048 game:
 */
public class NumberBlock extends Actor {
    /**
     * This map contains a color for each block:
     */
    private static Map<Integer, Color> BLOCK_COLORS = new HashMap<>();
    // This static block fills in the map:
    static {
        List<Integer> keys = List.of(2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048);
        for (int i = 0; i < keys.size(); i++) {
            BLOCK_COLORS.put(keys.get(i), new Color(0, 0, 0.2f + 0.6f * i / keys.size()));
        }
    }
    /**
     * The actual value inside the NumberBlock.
     */
    public int value;

    /**
     * Construct a NumberBlock with a given number:
     * 
     * @param number - usually 2 or 4.
     */
    public NumberBlock(int number) {
        this.value = number;
        this.visual = new AnimatedText(Integer.toString(number));
        this.setColor();
    }

    /**
     * Figure out what color this should be based on the block #.
     */
    public void setColor() {
        AnimatedText visual = ((AnimatedText) this.visual);
        visual.backgroundColor = BLOCK_COLORS.get(this.value);
    }

    /**
     * Double the size of this block.
     */
    public void grow() {
        this.value *= 2;
        AnimatedText visual = ((AnimatedText) this.visual);
        visual.text.setString(Integer.toString(this.value));
        this.setColor();
    }

    @Override
    public void act() {
        // Nothing
    }
} // NumberBlock
