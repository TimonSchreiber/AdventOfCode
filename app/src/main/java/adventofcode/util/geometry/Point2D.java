package adventofcode.util.geometry;

import java.util.ArrayList;
import java.util.List;

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

    public double distance(Point2D other) {
        double deltaXSquared = Math.pow(other.x - this.x, 2);
        double deltaYSquared = Math.pow(other.y - this.y, 2);

        return Math.sqrt(deltaXSquared + deltaYSquared);
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

        Point2D tmp = new Point2D(this.x, this.y);

        for (Direction direction : vec) {
            tmp = tmp.moveTowards(direction);
        }

        return tmp;
    }

    @Override
    public int compareTo(Point2D other) {
        return (this.y == other.y)
            ? Integer.compare(this.x, other.x)
            : Integer.compare(this.y, other.y);
    }

}
