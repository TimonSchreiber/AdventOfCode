package adventofcode.aoc2021.day06;

import adventofcode.util.io.ReadInput;

/**
 * https://adventofcode.com/2021/day/6
 */
public class Lanternfish {

    public static void main(String[] args) {
        System.out.println("\n--- Day 6: Laternfish ---\n");

        final String filePath = "aoc2021/day06/input";

        final String input = ReadInput.toSingleStringFrom(filePath);

        final SchoolOfFish school = new SchoolOfFish(input);

        // simulate for 80 days
        school.play(80);
        long part1 = school.countFish();
        System.out.println("-> Part1: " + part1);   // 380612

        // simulate upto day 256
        school.play(256-80);
        long part2 = school.countFish();
        System.out.println("-> Part2: " + part2);   // 1710166656900
    }
}
