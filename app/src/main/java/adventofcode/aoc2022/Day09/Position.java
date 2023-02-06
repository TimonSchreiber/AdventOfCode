package adventofcode.aoc2022.Day09;

import java.util.ArrayList;
import java.util.List;

/**
 * Position
 */
public record Position(int x, int y) implements Comparable<Position> {

    public Position moveTo(Direction direction) {
        return switch (direction) {
            case U -> new Position(this.x + 0, this.y + 1);   
            case D -> new Position(this.x + 0, this.y - 1);     
            case L -> new Position(this.x - 1, this.y + 0);     
            case R -> new Position(this.x + 1, this.y + 0);
            // no default, switch is exhaustive
        };
    }

    public double distance(Position other) {
        double deltaXSquared = Math.pow(other.x - this.x, 2);
        double deltaYSquared = Math.pow(other.y - this.y, 2);

        return Math.sqrt(deltaXSquared + deltaYSquared);
    }

    public List<Direction> getDirections(Position other) {
        List<Direction> vec = new ArrayList<>();
        int deltaX = other.x - this.x;
        int deltaY = other.y - this.y;

        if (deltaX != 0) {
            vec.add((deltaX > 0) ? Direction.R : Direction.L);
        }
        if (deltaY != 0) {
            vec.add((deltaY > 0) ? Direction.U : Direction.D);
        }

        return vec;
    }

    @Override
    public int compareTo(Position other) {
        return (this.y == other.y)
            ? Integer.compare(this.x, other.x)
            : Integer.compare(this.y, other.y);
    }

}
