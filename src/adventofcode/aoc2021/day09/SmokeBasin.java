package adventofcode.aoc2021.day09;

import java.util.List;

import adventofcode.util.io.ReadInput;

/**
 * https://adventofcode.com/2021/day/9
 */
public class SmokeBasin {

    public static void main(String[] args) {
        System.out.println("\n--- Day 9: Smoke Basin ---\n");

        final String filePath = "aoc2021/day09/input";

        final List<String> input = ReadInput.toListOfStringsFrom(filePath);

        final CaveFloor cave = new CaveFloor(input);

        long part1 = cave.riskLevel();
        System.out.println("-> Part1: " + part1);   // 600

        long part2 = cave.productOf3LargestBasins();
        System.out.println("-> Part2: " + part2);   // 987840
    }

}
