package week3.grid.draw;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * Generic class describing anything visual in a {@link GridEnv} via a
 * subclass of {@link GridView}.
 */
public abstract class Drawable {
    /**
     * How big is this? 1.0 = 100% size, etc.
     */
    protected double scale = 1.0;

    /**
     * Subclasses must implement this draw method, showing their visual component
     * within the bounds of the cell given.
     * 
     * @param g           - the Java Graphics object.
     * @param destination - the rectangular GridEnv cell to draw inside.
     */
    public abstract void draw(Graphics2D g, Rectangle2D destination);

    /**
     * What is the current scale of this visual? default = 1.0
     * 
     * @return a floating-point number representing the size relative to 1 grid
     *         cell.
     */
    public double getScale() {
        return this.scale;
    }

    /**
     * Set the current scale of this visual as a ratio of a grid cell.
     * For example, 0.5 creates a 1/2 size visual centered in the grid cell.
     * 
     * @param scale the new scale to use.
     */
    public void setScale(double scale) {
        this.scale = scale;
    }
}
