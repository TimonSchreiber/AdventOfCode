package adventofcode.aoc2022.Day12;

import java.util.List;

import adventofcode.util.IO.ReadInput;

/**
 * https://adventofcode.com/2022/day/12
 */
public class HillClimbingAlgorithm {
    
    public static void main(String[] args) {

        System.out.println("\n### Day 12: Hill Climbing Algorithm ###\n");

        // file path as String
        final String filePath = "aoc2022/Day12/input";

        final List<String> input = ReadInput.toListofStringsFrom(filePath);

        HeightMap heightMap = HeightMapParser.parse(input);

        PathFinder pathFinder = new PathFinder(heightMap);

        int part1 = pathFinder.findPath();
        System.out.println("-> Part1: " + part1);

        // long part2 = game2.twoMostActiveMonkeys();
        // System.out.println("-> Part2: " + part2);
    }

}
