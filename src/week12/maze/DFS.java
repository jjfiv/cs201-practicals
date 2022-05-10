package week12.maze;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import me.jjfoley.gfx.IntPoint;
import week3.grid.GridEnv;

public class DFS extends Solver {
    Deque<IntPoint> toVisit = new ArrayDeque<>();

    public DFS(IntPoint start, Maze maze, GridEnv visual) {
        super(start, maze, visual);
        this.toVisit.add(start);
    }

    @Override
    public boolean explore() {
        // have we exhausted our visit stack?
        if (this.toVisit.size() == 0) {
            return false;
        }
        // explore the most recent item first:
        IntPoint current = this.toVisit.removeLast();
        if (this.visitPoint(current)) {
            return true;
        }

        // consider all four neighbors:
        List<IntPoint> neighbors = List.of(
                new IntPoint(current.x, current.y + 1),
                new IntPoint(current.x, current.y - 1),
                new IntPoint(current.x + 1, current.y),
                new IntPoint(current.x - 1, current.y));
        // add neighbors that don't lead into a wall and haven't been visited yet
        for (IntPoint pt : neighbors) {
            if (shouldExplore(pt)) {
                backLinks.put(pt, current);
                toVisit.addLast(pt);
            }
        }

        return true;
    }
}
