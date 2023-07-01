package adventofcode.aoc2021.day13;

import java.util.List;

import adventofcode.util.io.ReadInput;

/**
 * https://adventofcode.com/2021/day/13
 */
public class TransparentOrigami {

    public static void main(String[] args) {
        System.out.println("\n--- Day 13: Transparent Origami ---\n");

        final String filePath = "aoc2021/day13/input";

        final List<String> input = ReadInput.toListOfStringsFrom(filePath);

        final Paper paper = ManualParser.parsePaper(input);

        System.out.println("Initial number of dots: " + paper.size() + "\n");

        // Follow the first instruction
        paper.followInstructions(1);

        long part1 = paper.size();
        System.out.println("-> Part1: " + part1);   // 664

        // Follow the remaining instructions
        paper.followInstructions();

        // result
        System.out.println("-> Part2:");    // EFJKZLBL
        paper.print();
    }

}
