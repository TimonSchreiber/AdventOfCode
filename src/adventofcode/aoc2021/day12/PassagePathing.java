package adventofcode.aoc2021.day12;

import java.util.List;

import adventofcode.util.io.ReadInput;

/**
 * https://adventofcode.com/2021/day/12
 */
public class PassagePathing {

    public static void main(String[] args) {
        System.out.println("\n--- Day 12: Passage Pathing ---\n");

        // more files than usual: test1, test2, test3, and input
        final String filePath = "aoc2021/day12/input";

        final List<String> input = ReadInput.toListOfStringsFrom(filePath);

        final CaveSystem caveSystem = CaveParser.parse(input);

        // find all paths with no extra time for double visits of small caves
        caveSystem.findAllPaths("start", "end", false);
        long part1 = caveSystem.paths.size();
        System.out.println("-> Part1: " + part1);   // 3679

        caveSystem.paths.clear();

        // find all paths with extra time for a double visit to ONE small cave
        caveSystem.findAllPaths("start", "end", true);
        long part2 = caveSystem.paths.size();
        System.out.println("-> Part2: " + part2);   // 107395
    }
}
