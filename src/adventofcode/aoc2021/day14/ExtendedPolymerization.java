package adventofcode.aoc2021.day14;

import java.util.List;

import adventofcode.util.io.ReadInput;

/**
 * https://adventofcode.com/2021/day/14
 */
public class ExtendedPolymerization {

    public static void main(String[] args) {
        System.out.println("\n--- Day 14: Extended Polymerization ---\n");

        final String filePath = "aoc2021/day14/input";

        final List<String> input = ReadInput.toListOfStringsFrom(filePath);

        final PolymerProcessor processor = InputParser.parse(input);

        long part1 = processor.steps(10);
        System.out.println("-> Part1: " + part1);   // 2621

        long part2 = processor.steps(40-10);
        System.out.println("-> Part2: " + part2);   // 2843834241366
    }
}
