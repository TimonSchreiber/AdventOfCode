package adventofcode.aoc2022.day12;

import java.util.List;

import adventofcode.util.io.ReadInput;

/**
 * https://adventofcode.com/2022/day/12
 */
public class HillClimbingAlgorithm {

    public static void main(String[] args) {

        System.out.println("\n--- Day 12: Hill Climbing Algorithm ---\n");

        // file path as String
        final String filePath = "aoc2022/day12/input";

        final List<String> input = ReadInput.toListOfStringsFrom(filePath);

        HeightMap heightMap = HeightMapParser.parse(input);

        int part1 = new PathFinder(heightMap).findPath();
        System.out.println("-> Part1: " + part1);

        int part2 = new TrailFinder(heightMap).findTrail();
        System.out.println("-> Part2: " + part2);
    }

}
