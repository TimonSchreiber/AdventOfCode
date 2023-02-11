package adventofcode.aoc2022.Day14;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import adventofcode.util.geometry.Point2D;

public class RockParser {

    private static Set<Point2D> set;
    
    public static Cave parse(List<String> list) {
        set = new HashSet<>();

        list.stream().forEach(RockParser::parseRock);

        Cave cave = new Cave(set);

        return cave;
    }

    private static void parseRock(String string) {
        List<Point2D> points = Arrays.stream(string.split(" -> "))
                .map(RockParser::parsePoint2D)
                .toList();

        for (int i = 0; i < points.size()-1; i++) {
            Point2D first = points.get(i);
            Point2D second = points.get(i+1);

            // TODO: try to find the actual Direction to move towards
            while (!first.equals(second)) {
                set.add(first);
                first = first.moveClose(second);
                set.add(first);
                // TODO: or at least not add twice like an idiot
            }

        }
    }

    private static Point2D parsePoint2D(String string) {
        List<Integer> coordinates = Arrays.stream(string.split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();

        return new Point2D(coordinates.get(0), coordinates.get(1));
    }

}
