package adventofcode.util.geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Point in 2D.
 */
public record Point2D(int x, int y) implements Comparable<Point2D> {

    public Point2D moveTowards(Direction direction) {
        return switch (direction) {
            case U -> new Point2D(this.x + 0, this.y - 1);
            case D -> new Point2D(this.x + 0, this.y + 1);
            case L -> new Point2D(this.x - 1, this.y + 0);
            case R -> new Point2D(this.x + 1, this.y + 0);
            // no default, switch is exhaustive
        };
    }

    public Point2D moveTowards(Direction... directions) {
        Point2D tmp = this;
        for (Direction dir : directions) {
            tmp = tmp.moveTowards(dir);
        }
        return tmp;
    }

    /**
     * Calculate the euclidian distance between two Points.
     * @param other
     * @return
     */
    public double distance(Point2D other) {
        double deltaXSquared = Math.pow(other.x - this.x, 2);
        double deltaYSquared = Math.pow(other.y - this.y, 2);

        return Math.sqrt(deltaXSquared + deltaYSquared);
    }

    /**
     * Calculate the manhattan distance between two Points.
     * @param other
     * @return
     */
    public int manhattanDistance(Point2D other) {
        int deltaX = Math.abs(other.x - this.x);
        int deltaY = Math.abs(other.y - this.y);

        return deltaX + deltaY;
    }

    public Point2D moveClose(Point2D other) {
        final List<Direction> vec = new ArrayList<>();
        int deltaX = other.x - this.x;
        int deltaY = other.y - this.y;

        if (deltaX != 0) {
            vec.add((deltaX > 0) ? Direction.R : Direction.L);
        }
        if (deltaY != 0) {
            vec.add((deltaY > 0) ? Direction.D : Direction.U);
        }

        Point2D newPoint = new Point2D(this.x, this.y);

        for (Direction direction : vec) {
            newPoint = newPoint.moveTowards(direction);
        }

        return newPoint;
    }

    /**
     * Get a List of all Points between this Point and another Point. Both
     * Points must be either vertically or horizontally alligned.
     * @param other
     * @return
     */
    public List<Point2D> getPointsOnLine(Point2D other) {
        if ((this.x - other.x) != 0) {
            // the two points have different x values
            // -> draw a horizontal line from this Point to the other
            int start = Integer.min(this.x, other.x);
            int end   = Integer.max(this.x, other.x);
            return IntStream.rangeClosed(start, end)
                    .mapToObj(x -> new Point2D(x, this.y))
                    .toList();
        } else if ((this.y - other.y) != 0) {
            // the two points have different y values
            // -> draw a vertical line from this Point to the other
            int start = Integer.min(this.y, other.y);
            int end   = Integer.max(this.y, other.y);
            return IntStream.rangeClosed(start, end)
                    .mapToObj(y -> new Point2D(this.x, y))
                    .toList();
        } else if (this.equals(other)) {
            // the two points are the same
            // -> return a List of a single Point (this)
            return List.of(this);
        } else {
            // something went wrong!
            throw new IllegalStateException();
        }
    }

    /**
     * Get a List of all Points with a fixed distance to this Point in taxicab
     * geometry. In taxicab geometry this 'circle' looks like a square rotated
     * by 45°.
     * @param radius    the radius of this square
     * @return          A List of all {@code Point2Ds} with the same distance to
     *                  the center
     * @see             https://en.wikipedia.org/wiki/Taxicab_geometry
     */
    public List<Point2D> getManhattanSquare(int radius) {
        System.out.println(" > (" + x + "," + y + ").getManhattanSquare(" + radius + ")");
        List<Point2D> points = new ArrayList<>();

        for (int deltaY = -radius; deltaY <= radius; deltaY++) {
            int deltaX = Math.abs(radius - Math.abs(deltaY));

            Point2D point1 = new Point2D(this.x - deltaX, this.y + deltaY);
            Point2D point2 = new Point2D(this.x + deltaX, this.y + deltaY);

            // List<Point2D> list = point1.getPointsOnLine(point2);
            // points.addAll(list);
            points.add(point1);
            points.add(point2);
        }

        return points;
    }

    @Override
    public int compareTo(Point2D other) {
        return (this.y == other.y)
            ? Integer.compare(this.x, other.x)
            : Integer.compare(this.y, other.y);
    }

}
