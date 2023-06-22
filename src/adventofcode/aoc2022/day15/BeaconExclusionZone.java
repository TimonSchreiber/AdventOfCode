package adventofcode.aoc2022.day15;

import java.util.List;
import java.util.Map;
// import java.util.Set;
// import java.util.stream.Collectors;

import adventofcode.util.io.ReadInput;
import adventofcode.util.geometry.Point2D;

/**
 * https://adventofcode.com/2022/day/15
 */
public class BeaconExclusionZone {

    public static void main(String[] args) {

        System.out.println("\n### Day 15: Beacon Exclusion Zone ###\n");

        // file path as String
        final String filePath = "resources/aoc2022/day15/input";

        final List<String> input = ReadInput.toListofStringsFrom(filePath);

        SensorScan scan = SensorScanParser.parse(input);

        Map<Integer, Ranges> map = scan.getAnalysis();

        // print(scan, map);

        long part1 = getNoBeacons(scan, map, 2_000_000);
        System.out.println("-> Part1: " + part1);

        // System.out.println("y=11: " + map.get(11).ranges());

        long part2 = part2(map);
        System.out.println("-> Part2: " + part2);
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
                .mapToInt(Range::size)
                .sum();

        // System.out.println(" -> Number of beacons: " + numberOfBeacons);
        // System.out.println(" -> Sum of Ranges: " + sumOfRanges);
        return sumOfRanges - numberOfBeacons;
    }

    private static long part2(Map<Integer, Ranges> map) {
        return map.entrySet().stream()
                .filter(entry -> entry.getValue().findBeacon())
                .findAny()
                .map(entry -> new Point2D(entry.getValue().ranges().get(0).end()+1, entry.getKey()))
                .stream()
                .peek(point -> System.out.println(" > Part2: " + point))
                .mapToLong(point -> (long)point.x() * 4_000_000L + (long)point.y())
                .sum();
                // .orElseThrow();
    }

    // private static void print(SensorScan scan, Map<Integer, Ranges> map){

    //     Set<Point2D> sensors = scan.reports().stream().map(SensorReport::sensor).collect(Collectors.toSet());
    //     Set<Point2D> beacons = scan.reports().stream().map(SensorReport::beacon).collect(Collectors.toSet());

    //     for (int y = -11; y <= 31; y++){
    //         System.out.print(y + "\t");
    //         for (int x = -10; x <= 30; x++) {
    //             Point2D point = new Point2D(x, y);
    //             String str;
    //             if (sensors.contains(point)) {
    //                 str = "S";
    //             } else if (beacons.contains(point)) {
    //                 str = "B";
    //             } else if (map.get(y) != null && map.get(y).ranges().stream().anyMatch(range -> range.contains(point.x()))) {
    //                 str = "#";
    //             } else {
    //                 str = ".";
    //             }
    //             System.out.print(str);
    //         }
    //         System.out.println();
    //     }
    // }

}
