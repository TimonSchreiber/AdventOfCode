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

        Cave cave1 = RockParser.parse(input);
        cave1.print();
        cave1.fillSandBottomLess();
        cave1.print();
        int part1 = cave1.sand().size();
        System.out.println("-> Part1: " + part1);

        Cave cave2 = RockParser.parse(input);
        cave2.print();
        cave2.fillSandFloor();
        cave2.print();
        int part2 = cave2.sand().size();
        System.out.println("-> Part2: " + part2);
    }
}


/**
 * TODO:
 *  1. add sand source (500,0) to Cave.java
 *  2. write Sand.java
 *  3. write loop for falling sand
 */