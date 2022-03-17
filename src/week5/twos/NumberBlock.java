package week5.twos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.jjfoley.gfx.TextBox;
import week3.grid.Actor;
import week3.grid.draw.Drawable;

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

    /**
     * Drawable instance to handle the graphics part of NumberBlock.
     */
    public static class AnimatedText extends Drawable {
        /**
         * The largest possible font size.
         */
        private static final double MAX_FONT_SIZE = 26.0;
        /**
         * The smallest possible font size.
         */
        private static final double MIN_FONT_SIZE = 4.0;
        /**
         * What color to fill the rounded-rect with:
         */
        public Color backgroundColor;
        private TextBox text;
        private double fontSize;
        private double fontStep;

        public AnimatedText(String message) {
            this.text = new TextBox(message);
            this.fontSize = 4.0;
            this.fontStep = 0.5;
            this.text.setFontSize(this.fontSize);
            this.backgroundColor = Color.black;
            this.text.setColor(Color.white);
            this.text.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        }

        public void setColor(Color color) {
            this.text.setColor(color);
        }

        @Override
        public void draw(Graphics2D g, Rectangle2D destination) {
            Rectangle2D textSize = this.text.getBoundingBox();
            double textLargerDim = Math.max(textSize.getWidth(), textSize.getHeight());
            double gridSmallerDim = Math.min(destination.getWidth(), destination.getHeight());

            // color square:
            g.setColor(this.backgroundColor);
            int pad = 4;
            g.fillRoundRect((int) destination.getX() + pad, (int) destination.getY() + pad,
                    (int) destination.getWidth() - pad * 2,
                    (int) destination.getHeight() - pad * 2, 10, 10);

            // grow text if-need-be:
            if (this.fontSize < MAX_FONT_SIZE && textLargerDim < gridSmallerDim * 0.85) {
                this.fontSize += this.fontStep;
                this.text.setFontSize(this.fontSize);
            } else if (this.fontSize > MIN_FONT_SIZE && textLargerDim > gridSmallerDim * 0.95) {
                this.fontSize -= this.fontStep;
                this.text.setFontSize(this.fontSize);
            }

            // center and draw text:
            this.text.centerInside(destination);
            this.text.draw(g);
        } // draw
    } // AnimatedText
} // NumberBlock
