package adventofcode.aoc2022.Day12;

import java.util.ArrayList;
import java.util.List;

import adventofcode.util.geometry.Direction;
import adventofcode.util.geometry.Point2D;

/**
 * Path
 */
public record Path(List<Direction> path, Point2D position) {

    /**
     * @see List#size()
     */
    public int size() {
        return this.path.size();
    }

    /**
     * Copy this {@code List} and add the spezified {@code Direction} at the
     * end. Get a new {@code Point2D} by moving this this {@code Point2D}
     * towards the {@code Direction}. Return a new {@code Path} consiting of
     * both new objects.
     * @param direction
     * @return
     */
    public Path step(Direction direction) {
        List<Direction> list = new ArrayList<>();
        for (Direction dir : this.path) {
            list.add(dir);
        }
        list.add(direction);
        Point2D point = this.position.moveTowards(direction);
        return new Path(list, point);
    }

}
