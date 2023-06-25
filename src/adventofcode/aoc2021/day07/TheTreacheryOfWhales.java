package adventofcode.aoc2021.day07;

import adventofcode.util.io.ReadInput;

/**
 * https://adventofcode.com/2021/day/7
 */
public class TheTreacheryOfWhales {

    public static void main(String[] args) {
        System.out.println("\n--- Day 7: The Treachery of Whales ---\n");

        final String filePath = "aoc2021/day07/input";

        final String input = ReadInput.toSingleStringFrom(filePath);

        final CrabSwarm crabs = new CrabSwarm(input);

        long part1 = crabs.minimumFuelConsumption(false);
        System.out.println();
        System.out.println("-> Part1: " + part1);   // 344535

        long part2 = crabs.minimumFuelConsumption(true);
        System.out.println("-> Part2: " + part2);   // 95581659
    }
}
