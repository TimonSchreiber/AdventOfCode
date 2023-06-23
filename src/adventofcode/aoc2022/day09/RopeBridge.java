package adventofcode.aoc2022.day09;

import java.util.List;

import adventofcode.util.io.ReadInput;
import adventofcode.util.geometry.Point2D;

/**
 * https://adventofcode.com/2022/day/9
 */
public class RopeBridge {
    public static void main(String[] args) {

        System.out.println("\n--- Day 9: Rope Bridge ---\n");

        // file path as String
        final String filePath = "aoc2022/day09/input";

        final List<String> input = ReadInput.toListofStringsFrom(filePath);

        final List<Motion> motions = MotionParser.parse(input);

        int part1 = numberOfVisitedPositions(motions, 2);
        System.out.println("-> Part1: " + part1);

        int part2 = numberOfVisitedPositions(motions, 10);
        System.out.println("-> Part2: " + part2);
    }

    private static int numberOfVisitedPositions(List<Motion> motions, int ropeLength) {
        Point2D start = new Point2D(0, 0);
        return new Rope(start, ropeLength).numberOfVisitedPositions(motions);
    }

}
