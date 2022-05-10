package week12.maze;

import java.awt.Color;

import me.jjfoley.gfx.IntPoint;
import week3.grid.Buttons;
import week3.grid.Decoration;
import week3.grid.GridEnv;
import week3.grid.GridView;

public class MazeViewer extends GridView {
    int currentMaze = 0;
    Solver solver = null;
    private static String[] SOLVER_NAMES = { "BFS", "DFS", "A*" };
    int solverId = 0;

    public MazeViewer() {
        super(new GridEnv(10, 10));
        this.setMaze(0);
        this.backgroundColor = new Color(0.9f, 0.9f, 0.9f);
    }

    public void setMaze(int mazeId) {
        this.grid.actors.clear();
        this.currentMaze = mazeId;
        this.solver = null;
        Maze maze = Mazes.definitions.get(this.currentMaze);
        for (int y = 0; y < maze.height; y++) {
            for (int x = 0; x < maze.width; x++) {
                if (maze.isWall(x, y)) {
                    this.grid.insert(new Decoration("black-square")).setPosition(x, y);
                } else if (maze.isGoal(x, y)) {
                    this.grid.insert(new Decoration("star")).setPosition(x, y);
                } else if (maze.isStart(x, y)) {
                    this.grid.insert(new Decoration("dog")).setPosition(x, y);
                    assert this.solver == null : "Only one goal per map";
                    if (this.solverId == 0) {
                        this.solver = new BFS(new IntPoint(x, y), maze, this.grid);
                    } else if (this.solverId == 1) {
                        this.solver = new DFS(new IntPoint(x, y), maze, this.grid);
                    } else {
                        this.solver = new AStar(new IntPoint(x, y), maze, this.grid);
                    }
                }
            }
        }
    }

    @Override
    public String getHeaderText() {
        return String.format("Maze %d/%d (%s)", this.currentMaze + 1, Mazes.definitions.size(),
                SOLVER_NAMES[this.solverId]);
    }

    @Override
    public void buttons(Buttons buttons) {
        if (buttons.right) {
            this.setMaze((this.currentMaze + 1) % Mazes.definitions.size());
        } else if (buttons.left) {
            int maze = this.currentMaze - 1;
            if (maze < 0) {
                maze += Mazes.definitions.size();
            }
            this.setMaze(maze);
        } else if (buttons.up) {
            this.solverId -= 1;
            if (solverId < 0) {
                solverId += SOLVER_NAMES.length;
            }
            this.setMaze(this.currentMaze);
        } else if (buttons.down) {
            this.solverId += 1;
            this.solverId %= SOLVER_NAMES.length;
            this.setMaze(this.currentMaze);
        } else if (buttons.space) {
            if (this.solver != null) {
                this.solver.animate();
            }
        }
    }

    public static void main(String[] args) {
        GridView view = new MazeViewer();
        view.start();
    }
}
