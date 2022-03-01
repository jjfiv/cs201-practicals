package week3.grid.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * This class shows a bunch of shapes together that look like a fish.
 * Disadvantage: cuteness...
 * Advantage: any color we want!
 * 
 * @author jfoley;
 * @author originally: Smith College faculty.
 */
public class CustomFish extends Drawable {
    /**
     * The color of fish chosen:
     */
    private Color color;

    /**
     * To construct a CustomFish, give it a color.
     * 
     * @param color try, e.g., {@link Color#red}
     */
    public CustomFish(Color color) {
        this.color = color;
    }

    @Override
    public void draw(Graphics2D g, Rectangle2D destination) {
        // .. don't worry about the code here ...

        // save the transform state:
        AffineTransform beforeState = g.getTransform();

        // Figure out how big it needs to be:
        double size = Math.min(destination.getHeight(), destination.getWidth()) / 1;
        g.translate(destination.getX(), destination.getY());
        g.scale(size, size);
        g.translate(-0.5, -0.5);

        // Fish is constructed of 3 shapes:
        Shape body = new Ellipse2D.Double(.6, .8, .8, .4);
        Shape tail = new Ellipse2D.Double(+1.25, .7, .175, .6);
        Shape eye = new Ellipse2D.Double(.75, .9, .1, .1);

        // Tail is slightly darker:
        Color tailColor = this.color.darker();

        g.setColor(this.color);
        g.fill(body);

        g.setColor(Color.black);
        g.fill(eye);

        // draw tail:
        g.setColor(tailColor);
        g.fill(tail);

        g.setTransform(beforeState);
    }

}
