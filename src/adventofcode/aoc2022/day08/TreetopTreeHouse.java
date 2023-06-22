package adventofcode.aoc2022.day08;

import java.util.List;

import adventofcode.util.io.ReadInput;

/**
 * https://adventofcode.com/2022/day/8
 */
public class TreetopTreeHouse {
    public static void main(String[] args) {
        System.out.println("\n### Day 8: Treetop Tree House ###\n");

        // file path as String
        final String filePath = "resources/aoc2022/day08/input";

        final List<String> input = ReadInput.toListofStringsFrom(filePath);

        Forest forest = ForestParser.parse(input);

        int part1 = forest.countVisibleTrees();
        System.out.println("-> Part1: " + part1);

        int part2 = forest.highestScenicScore();
        System.out.println("-> Part2: " + part2);
    }

}
