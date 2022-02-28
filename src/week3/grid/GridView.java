package week3.grid;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

import me.jjfoley.gfx.GFX;
import me.jjfoley.gfx.IntPoint;
import me.jjfoley.gfx.TextBox;

/**
 * This class is responsible for the graphical representation of the GridEnv.
 * The details of this class are not as relevant for you to understand.
 */
public class GridView extends GFX {
    /**
     * How big should each grid cell be?
     */
    public static int TILE_SIZE = 72;
    /**
     * How much vertical space should the header at the top reserve.
     */
    public static int HEADER_HEIGHT = 50;
    /**
     * Which grid should we draw?
     */
    public GridEnv grid;
    /**
     * What color should the grid-lines be?
     */
    public Color gridColor = new Color(200, 200, 200);
    /**
     * What color should the background of the grid be?
     */
    public Color backgroundColor = new Color(100, 200, 255);
    /**
     * What should the header say? Default.
     */
    public TextBox header = new TextBox("GridView");
    /**
     * A rectangle representation of the header space so we can center the text.
     */
    public Rectangle2D headerRect;

    /**
     * To construct a GridView, we need a GridEnv!
     * 
     * @param grid - the GridEnviroment
     */
    public GridView(GridEnv grid) {
        // The GFX class accepts the (width, height) of the window we want.
        super(grid.width * TILE_SIZE, (grid.height) * TILE_SIZE + HEADER_HEIGHT);
        this.headerRect = new Rectangle2D.Double(0, 0, this.getWidth(), HEADER_HEIGHT);
        // choose a font size and color for the header:
        this.header.setFontSize(HEADER_HEIGHT * 0.75);
        this.header.setColor(Color.white);
        // hold onto the grid object.
        this.grid = grid;
    }

    @Override
    public void draw(Graphics2D input) {
        // Draw header text:
        this.header.setString(this.getHeaderText());
        this.header.centerInside(this.headerRect);
        this.header.draw(input);

        // If no grid, stop here.
        if (this.grid == null) {
            return;
        }

        // Draw the grid background:
        Rectangle2D destination = new Rectangle2D.Double(0, HEADER_HEIGHT, TILE_SIZE * this.grid.width,
                TILE_SIZE * this.grid.height);
        input.setColor(this.backgroundColor);
        input.fill(destination);

        // Decide how big each cell should be:
        double tileW = destination.getWidth() / this.grid.width;
        double tileH = destination.getHeight() / this.grid.height;

        // shift below the header:
        Graphics2D g = (Graphics2D) input.create();
        g.translate(destination.getX(), destination.getY());

        // Determine where the mouse location is:
        IntPoint mouse = this.getMouseLocation();
        IntPoint hover = null;
        if (mouse != null) {
            int tx = (int) ((mouse.x - destination.getX()) / tileW);
            int ty = (int) ((mouse.y - destination.getY()) / tileH);
            hover = new IntPoint(tx, ty);
        }

        // Draw each grid cell frame:
        Rectangle2D cell = new Rectangle2D.Double();
        if (this.gridColor != null) {
            g.setColor(this.gridColor);
            for (int x = 0; x < this.grid.width; x++) {
                for (int y = 0; y < this.grid.height; y++) {
                    cell.setFrame(x * tileW, y * tileH, tileW, tileH);
                    g.draw(cell);
                }
            }
        }

        // Draw each actor:
        for (Actor actor : this.grid.getActors()) {
            cell.setFrame(actor.x * tileW, actor.y * tileH, tileW, tileH);
            if (actor.visual != null) {
                // at whatever size they want:
                double scale = actor.visual.getScale();
                // unless you're hovering your mouse
                if (hover != null && actor.x == hover.x && actor.y == hover.y) {
                    // then we zoom in
                    scale *= 1.2;
                }
                // if scale is not 1.0, adjust our bounding box
                if (scale != 1.0) {
                    double pad = (1.0 - scale) / 2.0;
                    cell.setFrame(cell.getX() + tileW * pad, cell.getY() + tileH * pad, tileW * scale, tileH * scale);
                }
                // trust the draw method otherwise.
                actor.visual.draw(g, cell);
            } else {
                System.err.println("Warning: Actor " + actor + " has no drawable.");
            }
        }

        // Have they clicked?
        IntPoint click = this.processClick();
        if (click != null) {
            int tx = (int) ((click.x - destination.getX()) / tileW);
            int ty = (int) ((click.y - destination.getY()) / tileH);
            this.click(tx, ty);
            // If so, everyone gets an update:
            this.grid.act();
        } else {
            // otherwise, have they pressed any buttons? WASD/arrow keys?
            Buttons current = new Buttons(this);
            if (current.any()) {
                this.buttons(current);
                this.grid.act();
            }
        }

        // get rid of our translated graphics space.
        g.dispose();
    }

    /**
     * Override this method to customize the header.
     * 
     * @return a text representation.
     */
    public String getHeaderText() {
        return this.getClass().getSimpleName();
    }

    /**
     * This method is called whenever buttons are pressed.
     * Override this method to have your simulation react to button presses.
     * 
     * @param current - an object containing directional button information.
     */
    public void buttons(Buttons current) {
        System.out.println("Buttons: " + current);
    }

    /**
     * This method is called whenever a grid cell is clicked.
     * 
     * @param x - the x coordinate of the grid cell.
     * @param y - the y coordinate of the grid cell.
     */
    public void click(int x, int y) {
        List<Actor> clickedOn = this.grid.find(x, y);
        System.out.println("click: (" + x + "," + y + "): " + clickedOn);
    }
}
