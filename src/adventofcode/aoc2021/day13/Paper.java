package adventofcode.aoc2021.day13;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import adventofcode.util.geometry.Point2D;

public class Paper {

    private final Set<Point2D> dots;
    private final List<FoldInstruction> instructions;

    public Paper(Set<Point2D> dots, List<FoldInstruction> instructions) {
        this.dots = dots;
        this.instructions = instructions;
    }

    /** get the number of points/dots on this paper (part1) */
    public int size() {
        return this.dots.size();
    }

    /**
     * Follow a certain amount of instructions.
     * @param number
     */
    public void followInstructions(int number) {
        for (int i = 0; i < number; i++) {
            this.fold(this.instructions.remove(0));
        }
    }

    /**
     * Follow all the remaining instructions.
     */
    public void followInstructions() {
        this.followInstructions(this.instructions.size());
    }

    private void fold(FoldInstruction instruction) {
        // save all the new Points after folding the paper in a List
        List<Point2D> list = switch (instruction.line()) {
            case 'x' ->
                this.dots.stream()
                    .map(
                        dot -> {
                            if (dot.x() > instruction.level()) {
                                int x = instruction.level() - Math.abs(dot.x() - instruction.level());
                                return new Point2D(x, dot.y());
                            } else {
                                return dot;
                            }
                        }
                    )
                    .toList();
            case 'y' ->
                this.dots.stream()
                    .map(
                        dot -> {
                            if (dot.y() > instruction.level()) {
                                int y = instruction.level() - Math.abs(dot.y() - instruction.level());
                                return new Point2D(dot.x(), y);
                            } else {
                                return dot;
                            }
                        }
                    )
                    .toList();
            default  -> Collections.emptyList();
        };
        // clear the initial Set of Points
        this.dots.clear();
        // add all the new Points form the List to this Set
        this.dots.addAll(list);
    }

    /** TODO: don't use 'magic numbers' for the dimensions of x and y
     * Print the remaining (folded) paper on the screen (part2)
     */
    public void print() {
        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 40; x++) {
                String str = this.dots.contains(new Point2D(x, y)) ? "#" : " ";
                System.out.print(str);
            }
            System.out.println();
        }
    }

}
