package adventofcode.aoc2022.Day12;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Queue;

import adventofcode.util.geometry.Direction;
import adventofcode.util.geometry.Point2D;

/**
 * Trail Finder
 * Find the shortest Trail from any Point at the lowest elevation to the End Point
 */
public class TrailFinder {

    private static final EnumSet<Direction> DIRECTIONS = EnumSet.allOf(Direction.class);

    private HeightMap heightMap;
    private Queue<Path> pathQueue;
    private List<Point2D> visitedPoints;
    private boolean foundPath;
    private Path solutionPath;

    public TrailFinder(HeightMap heightMap) {
        this.heightMap = heightMap;
        this.pathQueue = new ArrayDeque<>();
        this.visitedPoints = new ArrayList<>();
        this.foundPath = false;
    }

    public int findTrail() {

        Path startingPath = new Path(List.of(), this.heightMap.end());
        this.pathQueue.add(startingPath);


        while (!this.foundPath) {
            Path path = this.pathQueue.poll();
            nextSteps(path);
        }

        return this.solutionPath.size();
    }

    private void nextSteps(Path path) {
        Point2D position = path.position();

        for (Direction dir : DIRECTIONS) {
            Point2D nextPosition = position.moveTowards(dir);

            // is outside area
            if (!this.heightMap.isInside(nextPosition)) {
                continue;
            }

            // already found a shorter Path to this Position
            if (this.visitedPoints.contains(nextPosition)) {
                continue;
            }

            // the Position is not reachable
            if (!this.heightMap.isReachable(nextPosition, position)) {
                continue;
            }

            Path nextPath = path.step(dir);
            this.pathQueue.add(nextPath);
            this.visitedPoints.add(nextPosition);

            if (this.heightMap.get(nextPosition).equals(Height.LOW)) {
                this.foundPath = true;
                this.solutionPath = nextPath;
                return;
            }
        }
    }

}
