package adventofcode.aoc2022.day09;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import adventofcode.util.geometry.Direction;
import adventofcode.util.geometry.Point2D;

/**
 * Rope
 */
public class Rope {
    private List<Point2D> rope;

    // constructor with the initial Point2D and the number of elements
    public Rope(Point2D start, int size) {
        this.rope = new ArrayList<>();
        IntStream.range(0, size)
            .forEach(i -> this.rope.add(start));
    }

    // the size of this List of Point2Ds
    private int size() {
        return this.rope.size();
    }

    // the last element of this list
    private Point2D tail() {
        return this.rope.get(this.size()-1);
    }

    // the first element of this List
    private Point2D head() {
        return this.rope.get(0);
    }

    // run through a lost of Motions and track all the unique Point2Ds the Tail visits.
    // return the number of unique Point2Ds
    public int numberOfVisitedPositions(List<Motion> motions) {
        final Set<Point2D> uniquePoints = new HashSet<>();
        uniquePoints.add(this.tail());

        motions.stream()
            .forEachOrdered(
                motion -> makeMoves(motion, uniquePoints)
            );

        return uniquePoints.size();
    }

    private void makeMoves(Motion motion, Set<Point2D> uniquePoints) {
        for (int i = 0; i < motion.steps(); i++) {
            this.move(motion.direction());
            uniquePoints.add(this.tail());
        }
    }

    // move the head of this Rope to a new Point2D
    private void move(Direction direction) {
        this.rope.set(0, this.head().moveTowards(direction));
        moveTail();
    }

    // move every other element of this Rope to a new Point2D
    private void moveTail() {
        for (int i = 1; i < this.size(); i++) {
            Point2D head = this.rope.get(i-1);
            Point2D tail = this.rope.get(i);
            if (head.distance(tail) >= 1.5) {
                tail = tail.moveClose(head);
                this.rope.set(i, tail);
            }
        }
    }

}
