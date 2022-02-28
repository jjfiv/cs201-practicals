package week3.grid;

import java.awt.event.KeyEvent;

import me.jjfoley.gfx.GFX;
import me.jjfoley.gfx.IntPoint;

public class Buttons {
    public boolean up;
    public boolean left;
    public boolean right;
    public boolean down;
    public boolean space;

    public Buttons(GFX app) {
        this.left = app.processKey(KeyEvent.VK_LEFT) || app.processKey(KeyEvent.VK_A);
        this.up = app.processKey(KeyEvent.VK_UP) || app.processKey(KeyEvent.VK_W);
        this.down = app.processKey(KeyEvent.VK_DOWN) || app.processKey(KeyEvent.VK_S);
        this.right = app.processKey(KeyEvent.VK_RIGHT) || app.processKey(KeyEvent.VK_D);
        this.space = app.processKey(KeyEvent.VK_SPACE);
    }

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
