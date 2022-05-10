package week12.maze;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import me.jjfoley.gfx.IntPoint;
import week3.grid.GridEnv;

public class AStar extends Solver {
    IntPoint goal;
    PriorityQueue<Candidate> toVisit = new PriorityQueue<>();
    Set<IntPoint> frontier = new HashSet<>();

    public AStar(IntPoint start, Maze maze, GridEnv visual) {
        super(start, maze, visual);
        this.goal = this.maze.findGoal();
        this.toVisit.add(new Candidate(start));
    }

    @Override
    public boolean explore() {
        // have we exhausted our visit queue?
        if (this.toVisit.size() == 0) {
            return false;
        }
        // explore the best item first:
        IntPoint current = this.toVisit.poll().point;
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
            if (!frontier.contains(pt) && shouldExplore(pt)) {
                backLinks.put(pt, current);
                frontier.add(pt);
                toVisit.add(new Candidate(pt));
            }
        }

        return true;
    }

    class Candidate implements Comparable<Candidate> {
        IntPoint point;
        int distanceToGoal;

        public Candidate(IntPoint pt) {
            this.point = pt;
            this.distanceToGoal = Math.abs(pt.x - goal.x) + Math.abs(pt.y - goal.y);
        }

        @Override
        public int compareTo(Candidate other) {
            return Integer.compare(this.distanceToGoal, other.distanceToGoal);
        }
    }
}
