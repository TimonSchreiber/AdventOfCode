package adventofcode.aoc2022.day13;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import adventofcode.util.io.ReadInput;

/**
 * https://adventofcode.com/2022/day/13
 */
public class DistressSignal {

    private static final ListValue DIVIDER_1 = ListValue.of(ListValue.of(new IntValue(2)));
    private static final ListValue DIVIDER_2 = ListValue.of(ListValue.of(new IntValue(6)));

    public static final List<ListValue> dividerPackets = List.of(DIVIDER_1, DIVIDER_2);

    public static void main(String[] args) {

        System.out.println("\n### Day 13: Distress Signal ###\n");

        // file path as String
        final String filePath = "aoc2022/day13/input";

        final String input = ReadInput.toSingleStringFrom(filePath);

        List<PacketPair> packets = PacketPairParser.parse(input);
        int part1 = correctOrder(packets);
        System.out.println("-> Part1: " + part1);

        List<ListValue> listValues = ValueParser.parse(input);
        addDividersAndSOrt(listValues);
        int part2 = dividerPackets(listValues);
        System.out.println("-> Part2: " + part2);
    }

    // sum up indices of correctly ordered PacketPairs
    private static int correctOrder(List<PacketPair> packets) {
        return IntStream.range(0, packets.size())
                .filter(i -> packets.get(i).checkOrder())
                .map(i -> i+1)
                .sum();
    }

    // multiply the indices of the divider Packets
    private static int dividerPackets(List<ListValue> listValues) {
        return IntStream.range(0, listValues.size())
        .filter(i -> DIVIDER_1.equals(listValues.get(i)) || DIVIDER_2.equals(listValues.get(i)))
        .map(i -> i+1)
        .reduce((a,b) -> a*b)
        .orElseThrow();
    }

    // all two divider ListValues and sort the List
    private static void addDividersAndSOrt(List<ListValue> listValues) {
        listValues.add(DIVIDER_1);
        listValues.add(DIVIDER_2);
        Collections.sort(listValues);
    }

}
