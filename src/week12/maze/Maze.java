package week12.maze;

import java.util.List;

import me.jjfoley.gfx.IntPoint;
import week3.grid.GridEnv;

public class Maze {
    int width;
    int height;
    List<String> rows;

    public Maze(List<String> rows) {
        this.rows = rows;
        this.height = rows.size();
        for (String row : rows) {
            if (this.width < row.length()) {
                this.width = row.length();
            }
        }
    }

    public boolean isWall(int x, int y) {
        if (x >= this.width || x < 0 || y >= this.height || y < 0) {
            return true;
        }
        return this.rows.get(y).charAt(x) == '1';
    }

    public boolean isStart(int x, int y) {
        return this.rows.get(y).charAt(x) == 'S';
    }

    public boolean isGoal(int x, int y) {
        return this.rows.get(y).charAt(x) == 'G';
    }

    public IntPoint findGoal() {
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                if (this.isGoal(x, y)) {
                    return new IntPoint(x, y);
                }
            }
        }
        throw new RuntimeException("No Goal in Maze.");
    }

}
