package adventofcode.aoc2022.Day09;

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

    // constrcutor with the initial Point2D and the number of elements
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
        final Set<Point2D> uniquePoint2Ds = new HashSet<>();
        uniquePoint2Ds.add(this.tail());
        
        motions.stream()
            .forEachOrdered(
                motion -> {
                    for (int i = 0; i < motion.steps(); i++) {
                        this.move(motion.direction());
                        uniquePoint2Ds.add(this.tail());
                    }
                }
            );

        return uniquePoint2Ds.size();
    }

    // move the head of this Rope to a new Point2D
    private void move(Direction direction) {
        this.rope.set(0, this.head().moveTo(direction));
        moveTail();
    }

    // move every other element of this Rope to a new Point2D
    private void moveTail() {
        for (int i = 1; i < this.size(); i++) {
            Point2D head = this.rope.get(i-1);
            Point2D tail = this.rope.get(i);
            if (head.distance(tail) >= 1.5) {
                final List<Direction> directions = tail.getDirections(head);
                for (Direction dir : directions) {
                    tail = tail.moveTo(dir);
                }
                this.rope.set(i, tail);
            }
        }
    }

    // print the unique Point2Ds
    private void printPoint2Ds(Set<Point2D> uniquePoint2Ds) {
        int maxX = uniquePoint2Ds.stream().mapToInt(Point2D::x).max().orElse(0);
        int maxY = uniquePoint2Ds.stream().mapToInt(Point2D::y).max().orElse(0);

        System.out.println("\nmax X and max Y value: " + maxX + ", " + maxY);

        for (int i = 15; i >= -5; i--) {
            for (int j = -11; j < 15; j++) {
                String str = uniquePoint2Ds.contains(new Point2D(j, i)) ? "#" : ".";
                System.out.print(str);
            }
            System.out.println();
        }
    }

    // print the Rope
    private void print() {
        for (int i = 15; i >= -5; i--) {
            for (int j = -11; j < 15; j++) {

                Point2D pos = new Point2D (j, i);
                int index = this.rope.indexOf(pos);
                System.out.print(index == -1 ? "." : ((index == 0) ? "H" : String.valueOf(index)));
            }
            System.out.println();
        }
        System.out.println();
    }
    
}
