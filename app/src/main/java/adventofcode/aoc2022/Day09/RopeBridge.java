package adventofcode.aoc2022.Day09;

import java.util.List;

import adventofcode.util.ReadInput;

/**
 * https://adventofcode.com/2022/day/9
 */
public class RopeBridge {
    public static void main(String[] args) {

        System.out.println("\n### Day 9: Rope Bridge ###\n");

        // file path as String
        final String filePath = "aoc2022/Day09/input";

        List<String> input = ReadInput.toListofStringsFrom(filePath);

        List<Motion> motions = processInput(input);

        int part1 = numberOfVisitedPositions(motions, 2);
        System.out.println("-> Part1: " + part1);

        int part2 = numberOfVisitedPositions(motions, 10);
        System.out.println("-> Part2: " + part2);
    }

    private static int numberOfVisitedPositions(List<Motion> motions, int ropeLength) {
        Position start = new Position(0, 0);
        return new Rope(start, ropeLength).numberOfVisitedPositions(motions);
    }

    private static List<Motion> processInput(List<String> input) {

        List<Motion> list = input.stream()
            .map(Motion::new)
            .toList();

        return list;
    }

}
