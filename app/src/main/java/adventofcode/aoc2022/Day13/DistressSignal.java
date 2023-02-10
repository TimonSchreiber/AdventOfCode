package adventofcode.aoc2022.Day13;

import java.util.List;
import java.util.stream.IntStream;

import adventofcode.util.IO.ReadInput;

/**
 * https://adventofcode.com/2022/day/13
 */
public class DistressSignal {

    public static void main(String[] args) {

        System.out.println("\n### Day 13: Distress Signal ###\n");

        // file path as String
        final String filePath = "aoc2022/Day13/input";

        final String input = ReadInput.toSingleStringFrom(filePath);

        List<PacketPair> packets = PacketPairParser.parse(input);

        packets.forEach(System.out::println);

        int part1 = correctOrder(packets);
        System.out.println("-> Part1: " + part1);

        // int part2 = new TrailFinder(heightMap).findTrail();
        // System.out.println("-> Part2: " + part2);
    }

    private static int correctOrder(List<PacketPair> packets) {
        return IntStream.range(0, packets.size())
                .filter(i -> packets.get(i).checkOrder())
                .map(i -> i+1)
                .sum();
    }

}
