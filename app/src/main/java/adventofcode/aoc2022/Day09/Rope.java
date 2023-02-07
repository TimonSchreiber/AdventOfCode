package adventofcode.aoc2022.Day09;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Rope
 */
public class Rope {
    private List<Position> rope;

    // constrcutor with the initial Position and the number of elements
    public Rope(Position start, int size) {
        this.rope = new ArrayList<>();
        IntStream.range(0, size)
            .forEach(i -> this.rope.add(start));
    }

    // the size of this List of Positions
    private int size() {
        return this.rope.size();
    }

    // the last element of this list
    private Position tail() {
        return this.rope.get(this.size()-1);
    }

    // the first element of this List
    private Position head() {
        return this.rope.get(0);
    }

    // run through a lost of Motions and track all the unique positions the Tail visits.
    // return the number of unique Positions
    public int numberOfVisitedPositions(List<Motion> motions) {
        final Set<Position> uniquePositions = new HashSet<>();
        uniquePositions.add(this.tail());
        
        motions.stream()
            .forEachOrdered(
                motion -> {
                    for (int i = 0; i < motion.steps(); i++) {
                        this.move(motion.direction());
                        uniquePositions.add(this.tail());
                    }
                }
            );

        return uniquePositions.size();
    }

    // move the head of this Rope to a new Position
    private void move(Direction direction) {
        this.rope.set(0, this.head().moveTo(direction));
        moveTail();
    }

    // move every other element of this Rope to a new Position
    private void moveTail() {
        for (int i = 1; i < this.size(); i++) {
            Position head = this.rope.get(i-1);
            Position tail = this.rope.get(i);
            if (head.distance(tail) >= 1.5) {
                final List<Direction> directions = tail.getDirections(head);
                for (Direction dir : directions) {
                    tail = tail.moveTo(dir);
                }
                this.rope.set(i, tail);
            }
        }
    }

    // print the unique Positions
    private void printPositions(Set<Position> uniquePositions) {
        int maxX = uniquePositions.stream().mapToInt(Position::x).max().orElse(0);
        int maxY = uniquePositions.stream().mapToInt(Position::y).max().orElse(0);

        System.out.println("\nmax X and max Y value: " + maxX + ", " + maxY);

        for (int i = 15; i >= -5; i--) {
            for (int j = -11; j < 15; j++) {
                String str = uniquePositions.contains(new Position(j, i)) ? "#" : ".";
                System.out.print(str);
            }
            System.out.println();
        }
    }

    // print the Rope
    private void print() {
        for (int i = 15; i >= -5; i--) {
            for (int j = -11; j < 15; j++) {

                Position pos = new Position (j, i);
                int index = this.rope.indexOf(pos);
                System.out.print(index == -1 ? "." : ((index == 0) ? "H" : String.valueOf(index)));
            }
            System.out.println();
        }
        System.out.println();
    }
    
}
