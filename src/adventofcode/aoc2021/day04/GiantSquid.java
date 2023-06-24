package adventofcode.aoc2021.day04;

import adventofcode.util.io.ReadInput;

/**
 * https://adventofcode.com/2021/day/3
 */
public class GiantSquid {
    public static void main(String[] args) {
        System.out.println("\n--- Day 4: Giant Squid ---\n");

        final String filePath = "aoc2021/day04/input";

        final String input = ReadInput.toSingleStringFrom(filePath);

        int part1 = BingoParser.parse(input).playForWin();
        System.out.println("-> Part1: " + part1);   // 16716

        long part2 = BingoParser.parse(input).playToLose();
        System.out.println("-> Part2: " + part2);   // 4880
    }

}
