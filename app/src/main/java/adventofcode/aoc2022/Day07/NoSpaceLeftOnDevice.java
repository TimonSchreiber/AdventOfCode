package adventofcode.aoc2022.Day07;

import java.util.List;

import adventofcode.util.ReadInput;

/**
 * https://adventofcode.com/2022/day/7
 */
public class NoSpaceLeftOnDevice {

    private static final int DISC_SPACE = 70_000_000;
    private static final int NEEDED_SPACE = 30_000_000;

    public static void main(String[] args) {
        System.out.println("\n### Day 7: No Space Left On Device ###\n");

        // file path as String
        final String filePath = "aoc2022/Day07/input";

        final List<String> input = ReadInput.toListofStringsFrom(filePath);

        FileSystem fileSystem = FileSystemParser.parse(input);

        System.out.println("\nTotal size: " + fileSystem.root.totalSize());

        long usedSpace = fileSystem.root.totalSize();

        long requiredSpace = NEEDED_SPACE - (DISC_SPACE - usedSpace);

        System.out.println("\nRequired Space: " + requiredSpace + "\n");

        fileSystem.print();

        long part1 = fileSystem.root.sumOfDirectoriesWithSizeBelow(100000L);
        System.out.println("-> Part1: " + part1);
        
        long part2 = fileSystem.root.sizeOfSmallestDirectorieWithSizeBiggerThan(requiredSpace);
        System.out.println("-> Part2: " + part2);
    }

}
