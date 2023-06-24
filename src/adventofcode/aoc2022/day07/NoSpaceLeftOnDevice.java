package adventofcode.aoc2022.day07;

import java.util.List;

import adventofcode.util.io.ReadInput;

/**
 * https://adventofcode.com/2022/day/7
 */
public class NoSpaceLeftOnDevice {

    private static final int DISC_SPACE   = 70_000_000;
    private static final int NEEDED_SPACE = 30_000_000;

    public static void main(String[] args) {
        System.out.println("\n--- Day 7: No Space Left On Device ---\n");

        // file path as String
        final String filePath = "aoc2022/day07/input";

        final List<String> input = ReadInput.toListOfStringsFrom(filePath);

        Directory root = FileSystemParser.parse(input);

        root.print(0);

        long usedSpace = root.size();
        System.out.println("\nUsed space: " + usedSpace);

        long requiredSpace = NEEDED_SPACE - (DISC_SPACE - usedSpace);
        System.out.println("\nRequired Space: " + requiredSpace + "\n");

        long part1 = root.sumOfDirectoriesWithSizeBelow(100_000);
        System.out.println("-> Part1: " + part1);

        long part2 = root.sizeOfSmallestDirectorieWithSizeBiggerThan(requiredSpace);
        System.out.println("-> Part2: " + part2);
    }

}
