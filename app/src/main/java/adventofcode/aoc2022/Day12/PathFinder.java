package adventofcode.aoc2022.Day12;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Queue;

import adventofcode.util.geometry.Direction;
import adventofcode.util.geometry.Point2D;

public class PathFinder {

    private static final EnumSet<Direction> DIRECTIONS = EnumSet.allOf(Direction.class);

    private HeightMap heightMap;
    private Queue<Path> pathQueue;
    private List<Point2D> visitedPoints;
    private boolean foundPath;
    private Path solutionPath;

    public PathFinder(HeightMap heightMap) {
        this.heightMap = heightMap;
        this.pathQueue = new ArrayDeque<>();
        this.visitedPoints = new ArrayList<>();
        this.foundPath = false;
    }

    public int findPath() {

        Path startingPath = new Path(List.of(), heightMap.start());
        pathQueue.add(startingPath);


        while (!foundPath) {
            Path path = pathQueue.poll();
            nextSteps(path);
        }

        return solutionPath.path().size();
    }

    private void nextSteps(Path path) {
        Point2D position = path.position();

        for (Direction dir : DIRECTIONS) {
            Point2D nextPosition = position.moveTowards(dir);

            // is outside area
            if (!heightMap.isInside(nextPosition)) {
                continue;
            }

            // already found a shorter Path to this Position
            if (visitedPoints.contains(nextPosition)) {
                continue;
            }

            // the Position is not reachable
            if (!heightMap.map().get(position).isReachable(heightMap.map().get(nextPosition))) {
                continue;
            }

            Path nextPath = path.step(dir);
            this.pathQueue.add(nextPath);
            this.visitedPoints.add(nextPosition);

            if (nextPosition.equals(heightMap.end())) {
                this.foundPath = true;
                this.solutionPath = nextPath;
                return;
            }
        }
    }

}
