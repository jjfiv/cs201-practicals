package week3.grid;

import java.awt.event.KeyEvent;

import me.jjfoley.gfx.GFX;
import me.jjfoley.gfx.IntPoint;

/**
 * This class represents the state of the keyboard, in-so-far as any
 * {@link GridView} subclass cares.
 */
public class Buttons {
    /**
     * Did they press up?
     */
    public boolean up;
    /**
     * Did they press left?
     */
    public boolean left;
    /**
     * Did they press right?
     */
    public boolean right;
    /**
     * Did they press down?
     */
    public boolean down;
    /**
     * Did they press space?
     */
    public boolean space;

    /**
     * Initialize a Buttons instance from a {@link GFX} app.
     * 
     * @param app - the swing application to read key values from.
     */
    public Buttons(GFX app) {
        this.left = app.processKey(KeyEvent.VK_LEFT) || app.processKey(KeyEvent.VK_A);
        this.up = app.processKey(KeyEvent.VK_UP) || app.processKey(KeyEvent.VK_W);
        this.down = app.processKey(KeyEvent.VK_DOWN) || app.processKey(KeyEvent.VK_S);
        this.right = app.processKey(KeyEvent.VK_RIGHT) || app.processKey(KeyEvent.VK_D);
        this.space = app.processKey(KeyEvent.VK_SPACE);
    }

    /**
     * Is any key being pressed?
     * 
     * @return true if any of (left, right, up, down, or space) is pressed.
     */
    public boolean any() {
        return this.left || this.right || this.up || this.down || this.space;
    }

    @Override
    public String toString() {
        if (this.left) {
            return "LEFT";
        } else if (this.right) {
            return "RIGHT";
        } else if (this.up) {
            return "UP";
        } else if (this.down) {
            return "DOWN";
        } else if (this.space) {
            return "SKIP";
        } else {
            return "N/A";
        }
    }

    /**
     * Given a point, imagine this set of buttons 'moves' that point.
     * NOTE: if only space is pressed, you'll get back the original position.
     * 
     * @param current - the current location.
     * @return the new location this input set would move it to.
     */
    public IntPoint nextPosition(IntPoint current) {
        if (this.left) {
            return new IntPoint(current.x - 1, current.y);
        } else if (this.right) {
            return new IntPoint(current.x + 1, current.y);
        } else if (this.up) {
            return new IntPoint(current.x, current.y - 1);
        } else if (this.down) {
            return new IntPoint(current.x, current.y + 1);
        } else {
            return current;
        }
    }
}
