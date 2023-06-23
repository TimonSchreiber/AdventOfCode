package adventofcode.aoc2022.day06;

import adventofcode.util.io.ReadInput;

/**
 * https://adventofcode.com/2022/day/6
 */
public class TuningTrouble {

    private static final int START_OF_PACKET_SIZE  = 4;
    private static final int START_OF_MESSAGE_SIZE = 14;

    public static void main(String[] args) {
        System.out.println("\n--- Day 6: Tuning Trouble ---\n");

        final String filePath = "aoc2022/day06/input";

        String input = ReadInput.toSingleStringFrom(filePath);

        Signal signal = new Signal(input);

        int part1 = signal.indexOfMarker(START_OF_PACKET_SIZE);
        System.out.println("-> Part1: " + part1);

        int part2 = signal.indexOfMarker(START_OF_MESSAGE_SIZE);
        System.out.println("-> Part2: " + part2);
    }

}
