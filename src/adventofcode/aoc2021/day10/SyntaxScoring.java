package adventofcode.aoc2021.day10;

import java.util.List;

import adventofcode.util.io.ReadInput;

/**
 * https://adventofcode.com/2021/day/10
 */
public class SyntaxScoring {

    public static void main(String[] args) {
        System.out.println("\n--- Day 10: Syntax Scoring ---\n");

        final String filePath = "aoc2021/day10/input";

        final List<String> input = ReadInput.toListOfStringsFrom(filePath);

        long part1 = totalOfCorruptedLines(input);
        System.out.println("-> Part1: " + part1);   // 464991

        long part2 = medianOfIncompleteLines(input);
        System.out.println("-> Part2: " + part2);   // 3662008566
    }

    private static int totalOfCorruptedLines(List<String> list) {
        return list.stream()
                .mapToInt(SyntaxChecker::corrupted)
                .sum();
    }

    private static long medianOfIncompleteLines(List<String> list) {
        var tmp = list.stream()
                .mapToLong(SyntaxChecker::incomplete)
                .filter(i -> i != 0)
                .sorted()
                .toArray();
        return tmp[tmp.length/2];
    }

}
