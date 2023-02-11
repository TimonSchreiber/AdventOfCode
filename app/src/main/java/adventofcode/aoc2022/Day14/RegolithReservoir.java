package adventofcode.aoc2022.Day14;

import java.util.List;

import adventofcode.util.IO.ReadInput;

/**
 * https://adventofcode.com/2022/day/14
 */
public class RegolithReservoir {
    
    public static void main(String[] args) {

        System.out.println("\n### Day 13: Regolith Reservoir ###\n");

        // file path as String
        final String filePath = "aoc2022/Day14/input";

        final List<String> input = ReadInput.toListofStringsFrom(filePath);

        Cave cave = RockParser.parse(input);

        cave.print();

        // int part1 = correctOrder(packets);
        // System.out.println("-> Part1: " + part1);

        // int part2 = dividerPackets(listValues);
        // System.out.println("-> Part2: " + part2);
    }
}


/**
 * TODO:
 *  1. add sand source (500,0) to Cave.java
 *  2. write Sand.java
 *  3. write loop for falling sand
 */