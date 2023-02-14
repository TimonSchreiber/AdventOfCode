package adventofcode.aoc2022.Day15;

import java.util.List;
import java.util.Map;

import adventofcode.util.IO.ReadInput;
import adventofcode.util.geometry.Point2D;

public class BeaconExclusionZone {

    public static void main(String[] args) {

        System.out.println("\n### Day 15: Beacon Exclusion Zone ###\n");

        // file path as String
        final String filePath = "aoc2022/Day15/test";

        final List<String> input = ReadInput.toListofStringsFrom(filePath);

        SensorScan scan = SensorScanParser.parse(input);

        Map<Integer, Ranges> map = scan.getAnalysis();

        long part1 = getNoBeacons(scan, map, 2_000_000);
        System.out.println("-> Part1: " + part1);
    }

    private static long getNoBeacons(SensorScan scan, Map<Integer, Ranges> map, int yLevel) {
        // System.out.println("\nBeaconExclusionZone.getNoBeacons()");
        long numberOfBeacons = scan.reports().stream()
                .map(SensorReport::beacon)
                .mapToInt(Point2D::y)
                .filter(y -> y == yLevel)
                .distinct()
                .count();
        int sumOfRanges = map.get(yLevel).ranges().stream()
                .peek(System.out::println) // TODO: delete
                .mapToInt(Range::size)
                .sum();

        // System.out.println(" -> Number of beacons: " + numberOfBeacons);
        // System.out.println(" -> Sum of Ranges: " + sumOfRanges);
        return sumOfRanges - numberOfBeacons;
    }

}
