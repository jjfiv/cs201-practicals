package week12.maze;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import me.jjfoley.gfx.IntPoint;
import week3.grid.Decoration;
import week3.grid.GridEnv;

public abstract class Solver {
    /**
     * Keep track of all the nodes we've already explored.
     */
    Set<IntPoint> visited = new HashSet<>();
    /**
     * Map every point to the point the generated it.
     * We can loop backwards from the solution to find the path.
     */
    Map<IntPoint, IntPoint> backLinks = new HashMap<>();
    /**
     * Did we solve it?
     */
    boolean solved = false;
    /**
     * Did we finish, but fail to solve it?
     */
    boolean exhausted = false;
    /**
     * When the solver reaches the goal, set this to be a the point it was found.
     */
    IntPoint solution = null;
    /**
     * When we create a solver, it needs a 'start' point.
     */
    IntPoint start;
    /**
     * When we create a solver, it also needs the 'maze'.
     */
    Maze maze;
    /**
     * When we create a solver, we also want access to the 'grid'.
     */
    GridEnv visual;

    /**
     * All solvers need to be setup with the same problem statement.
     * 
     * @param start  - where to start.
     * @param maze   - the maze to solve.
     * @param visual - the GridEnv to animate the solution over.
     */
    public Solver(IntPoint start, Maze maze, GridEnv visual) {
        this.start = start;
        this.maze = maze;
        this.visual = visual;
    }

    /**
     * The main method called by {@linkplain MazeViewer}; does animation work shared
     * between all solvers.
     */
    public void animate() {
        if (this.exhausted) {
            // slowly mark all visited nodes as errorful.
            if (this.visited.size() > 0) {
                IntPoint any = this.visited.iterator().next();
                this.visited.remove(any);
                if (!any.equals(start)) {
                    visual.insert(new Decoration("red-square")).setPoint(any);
                }
            }
            return;
        }
        if (this.solved) {
            // slowly mark the solution path with green circles.
            if (!solution.equals(start)) {
                visual.insert(new Decoration("green-circle")).setPoint(solution);
                solution = backLinks.get(solution);
            }
            return;
        }
        // abstract exploration step for each solver.
        if (!this.explore()) {
            this.exhausted = true;
        }

    }

    /**
     * Visit a point with the algorithm.
     * 1. colors the square (if not the start node).
     * 2. if it's the goal, sets solved & goal & returns true.
     * 
     * @param pt - the point to visit.
     * @return true if it's the goal.
     */
    public boolean visitPoint(IntPoint pt) {
        visited.add(pt);
        if (!pt.equals(start)) {
            visual.insert(new Decoration("blue-square")).setPoint(pt);
        }
        if (maze.isGoal(pt.x, pt.y)) {
            this.solution = pt;
            this.solved = true;
            return true;
        } else {
            return false;
        }
    }

    public boolean shouldExplore(IntPoint pt) {
        if (visited.contains(pt) || maze.isWall(pt.x, pt.y)) {
            return false;
        }
        return true;
    }

    /**
     * Visit one new position (if possible).
     * 
     * @return false if nothing more to explore.
     */
    protected abstract boolean explore();
}
