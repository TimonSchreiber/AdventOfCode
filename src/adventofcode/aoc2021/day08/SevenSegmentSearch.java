package adventofcode.aoc2021.day08;

import java.util.Arrays;
import java.util.List;

import adventofcode.util.io.ReadInput;

/**
 * https://adventofcode.com/2021/day/8
 */
public class SevenSegmentSearch {

    public static void main(String[] args) {
        System.out.println("\n--- Day 8: Seven Sigment Search ---\n");

        final String filePath = "aoc2021/day08/input";

        final List<String> input = ReadInput.toListOfStringsFrom(filePath);

        long part1 = OneFourSevenEight(input);
        System.out.println("-> Part1: " + part1);   // 488

        long part2 = decodeOutputValues(input);
        System.out.println("-> Part2: " + part2);   // 1040429
    }

    private static long OneFourSevenEight(List<String> list) {
        return list.stream()
                .map(str -> str.substring(str.indexOf("|")+1).trim())
                .flatMapToInt(str -> Arrays.stream(str.split(" ")).mapToInt(s -> s.length()))
                // Digit       := 1      := 4      := 7      := 8
                .filter(i -> i == 2 || i == 4 || i == 3 || i == 7)
                .count();
    }

    private static long decodeOutputValues(List<String> list) {
        return list.stream()
                .map(SignalPattern::new)
                .mapToInt(SignalPattern::decodeOutput)
                .sum();
    }
}
