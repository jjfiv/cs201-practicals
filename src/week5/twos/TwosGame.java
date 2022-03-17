package week5.twos;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import me.jjfoley.gfx.IntPoint;
import week3.grid.Actor;
import week3.grid.Buttons;
import week3.grid.GridEnv;
import week3.grid.GridView;

/**
 * A 2048-clone written in our GridView universe:
 */
public class TwosGame extends GridView {

    /**
     * How many blocks to initialize?
     */
    private static final int NUM_START_BLOCKS = 3;
    /**
     * We'll use random numbers a bunch.
     */
    private Random rand = ThreadLocalRandom.current();

    /**
     * Construct a new grid-game:
     */
    public TwosGame() {
        super(new GridEnv(4, 4));
        this.backgroundColor = Color.black;
        this.newGame();
    }

    /**
     * What do we do to create a new game?
     */
    public void newGame() {
        // delete everything:
        this.grid.actors.clear();
        // add a few random values
        for (int i = 0; i < NUM_START_BLOCKS; i++) {
            this.insertRandomValue();
        }
    }

    /**
     * Insert a random value (done whenever the player makes a move).
     */
    public void insertRandomValue() {
        int value = 4;
        if (rand.nextInt(4) < 3) { // 75% of the time:
            value = 2;
        }
        this.grid.insertRandomly(new NumberBlock(value));
    }

    /**
     * Select all NumberBlocks in a column.
     * 
     * @param x - the x-coordinate of the column.
     * @return a list of the NumberBlocks found.
     */
    public List<NumberBlock> getColumn(int x) {
        List<NumberBlock> values = new ArrayList<>();
        for (Actor a : this.grid.getActors()) {
            if (a.getPoint().x == x && a instanceof NumberBlock) {
                values.add((NumberBlock) a);
            }
        }
        return values;
    }

    /**
     * Select all NumberBlocks in a row.
     * 
     * @param x - the y-coordinate of the row.
     * @return a list of the NumberBlocks found.
     */
    public List<NumberBlock> getRow(int y) {
        List<NumberBlock> values = new ArrayList<>();
        for (Actor a : this.grid.getActors()) {
            if (a.getPoint().y == y && a instanceof NumberBlock) {
                values.add((NumberBlock) a);
            }
        }
        return values;
    }

    /**
     * Get a NumberBlock at a specific spot, if possible.
     * 
     * @param where - the (x,y) to check.
     * @return - null or the block.
     */
    public NumberBlock getBlock(IntPoint where) {
        for (Actor a : this.grid.getActors()) {
            if (a.getPoint().equals(where) && a instanceof NumberBlock) {
                return (NumberBlock) a;
            }
        }
        return null;
    }

    public boolean gameOver() {
        // TODO: determine if the game is stuck.
        return false;
    }

    @Override
    public void buttons(Buttons pressed) {
        boolean needsNewBlock = false;
        if (pressed.up) {
            // when moving, e.g., up, we don't need to move anything in row 0.
            moveAll(0, -1, getRow(1));
            moveAll(0, -1, getRow(2));
            moveAll(0, -1, getRow(3));
            needsNewBlock = true;
        } else if (pressed.down) {
            moveAll(0, +1, getRow(2));
            moveAll(0, +1, getRow(1));
            moveAll(0, +1, getRow(0));
            needsNewBlock = true;
        } else if (pressed.left) {
            moveAll(-1, 0, getColumn(1));
            moveAll(-1, 0, getColumn(2));
            moveAll(-1, 0, getColumn(3));
            needsNewBlock = true;
        } else if (pressed.right) {
            moveAll(+1, 0, getColumn(2));
            moveAll(+1, 0, getColumn(1));
            moveAll(+1, 0, getColumn(0));
            needsNewBlock = true;
        } else if (pressed.space) {
            if (this.gameOver()) {
                this.newGame();
            }
        }

        if (needsNewBlock) {
            this.insertRandomValue();
        }
    }

    /**
     * Move all the blocks in a row or a column in a specific direction.
     * 
     * @param dx     - the change in x (encodes direction alongside dy).
     * @param dy     - the change in y (encodes direction alongside dx).
     * @param blocks - the blocks to move in that direction.
     */
    public void moveAll(int dx, int dy, List<NumberBlock> blocks) {
        for (NumberBlock b : blocks) {
            boolean falling = true;
            while (falling) {
                IntPoint current = b.getPoint();
                IntPoint next = new IntPoint(current.x + dx, current.y + dy);
                if (!this.grid.inBounds(next)) {
                    falling = false;
                    break;
                }
                NumberBlock maybeCombine = getBlock(next);
                if (maybeCombine != null) {
                    if (maybeCombine.value == b.value) {
                        // remove this
                        this.grid.remove(b);
                        // make the other one bigger
                        maybeCombine.grow();
                        // stop falling.
                    }
                    // stop falling.
                    falling = false;
                } else {
                    // slide
                    b.setPoint(next);
                    // keep sliding.
                }
            }
        }
    }

    @Override
    public String getHeaderText() {
        if (this.gameOver()) {
            return "2048: Game Over - Space Restarts";
        }
        return "2048";
    }

    public static void main(String[] args) {
        TwosGame.TILE_SIZE = 100;
        TwosGame game = new TwosGame();
        game.start();
    }

}
