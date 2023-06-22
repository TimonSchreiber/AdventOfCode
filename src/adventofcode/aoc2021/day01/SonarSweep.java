package adventofcode.aoc2021.day01;

import java.util.List;
import java.util.stream.IntStream;

import adventofcode.util.io.ReadInput;

/**
 * https://adventofcode.com/2021/day/1
 */
public class SonarSweep {

    public static void main(String[] args) {
        System.out.println("\n### Day 1: Sonar Sweep ###\n");

        final String filePath = "resources/aoc2021/day01/input";

        final List<String> input = ReadInput.toListofStringsFrom(filePath);

        final List<Integer> depthMeasurement = MeasurementParser.parse(input);

        long part1 = countMeasurementIncrease(depthMeasurement, 1);
        System.out.println("-> Part1: " + part1);   // 1139

        long part2 = countMeasurementIncrease(depthMeasurement, 3);
        System.out.println("-> Part2: " + part2);   // 1103
    }

    private static long countMeasurementIncrease(List<Integer> depthMeasurement, int windowSize) {
        return IntStream.range(windowSize, depthMeasurement.size())
                .filter(i -> sumOfWindow(depthMeasurement, windowSize, (i-1)) < sumOfWindow(depthMeasurement, windowSize, i))
                .count();
    }

    private static int sumOfWindow(List<Integer> depthMeasurement, int windowSize, int index) {
        return depthMeasurement.subList(index - windowSize  + 1 , index + 1)
                .stream()
                .mapToInt(Integer::valueOf)
                .sum();
    }

}
