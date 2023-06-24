package adventofcode.aoc2021.day05;

import java.util.List;
import java.util.stream.IntStream;

public record LineSegment(Point p1, Point p2) {

    /**
     * Only returns a correct List of Points when the LineSegment is either
     * strictly vertical, horizontal or diagonal with a 45° angel.
     * @return
     */
    public List<Point> line() {
        int deltaX = p2.x() - p1.x();
        int deltaY = p2.y() - p1.y();
        int increX = Integer.signum(deltaX);
        int increY = Integer.signum(deltaY);
        int length = Integer.max(Math.abs(deltaX), Math.abs(deltaY));

        return IntStream.rangeClosed(0, length)
                    .mapToObj(
                        i -> new Point(
                            p1.x() + (i * increX),
                            p1.y() + (i * increY)
                        )
                    )
                    .toList();
    }

    @Override
    public String toString() {
        return p1 + " -> " + p2;
    }
}
