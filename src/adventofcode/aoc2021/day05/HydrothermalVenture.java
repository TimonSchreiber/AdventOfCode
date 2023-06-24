package adventofcode.aoc2021.day05;

import java.util.List;

import adventofcode.util.io.ReadInput;

/**
 * https://adventofcode.com/2021/day/5
 */
public class HydrothermalVenture {

    public static void main(String[] args) {
        System.out.println("\n--- Day 5: Hydrothermal Venture ---\n");

        final String filePath = "aoc2021/day05/input";

        final List<String> input = ReadInput.toListOfStringsFrom(filePath);

        final VentMap map = new VentMap(VentParser.parse(input));

        long part1 = map.countOverlapp(false);
        System.out.println("-> Part1: " + part1);   // 7436

        long part2 = map.countOverlapp(true);
        System.out.println("-> Part2: " + part2);   // 21104
    }

}
