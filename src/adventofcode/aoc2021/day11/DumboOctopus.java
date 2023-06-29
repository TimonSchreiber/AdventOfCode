package adventofcode.aoc2021.day11;

import java.util.List;

import adventofcode.util.io.ReadInput;

/**
 * https://adventofcode.com/2021/day/11
 */
public class DumboOctopus {

    public static void main(String[] args) {
        System.out.println("\n--- Day 11: Dumbo Octopus ---\n");

        final String filePath = "aoc2021/day11/input";

        final List<String> input = ReadInput.toListOfStringsFrom(filePath);

        final Octopodes octopodes = new Octopodes(input);

        long part1 = octopodes.numberOfFlashesAfter(100);
        System.out.println("-> Part1: " + part1);   // 1679

        long part2 = octopodes.stepsUntilSynchronisation();
        System.out.println("-> Part2: " + part2);   // 519
    }

}