package adventofcode.aoc2022.day14;

import java.util.List;

import adventofcode.util.io.ReadInput;

/**
 * https://adventofcode.com/2022/day/14
 */
public class RegolithReservoir {

    public static void main(String[] args) {

        System.out.println("\n### Day 14: Regolith Reservoir ###\n");

        // file path as String
        final String filePath = "resources/aoc2022/day14/input";

        final List<String> input = ReadInput.toListofStringsFrom(filePath);

        Cave cave = RockParser.parse(input);

        cave.print();

        cave.fillSandBottomLess();
        cave.print();
        int part1 = cave.sand().size();
        System.out.println("-> Part1: " + part1);

        cave.clearSand();

        cave.fillSandFloor();
        cave.print();
        int part2 = cave.sand().size();
        System.out.println("-> Part2: " + part2);
    }

}
