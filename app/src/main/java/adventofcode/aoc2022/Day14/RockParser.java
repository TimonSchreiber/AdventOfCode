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

        return new Cave(set, new HashSet<>());
    }

    private static void parseRock(String string) {
        List<Point2D> points = Arrays.stream(string.split(" -> "))
                .map(RockParser::parsePoint2D)
                .toList();

        for (int i = 0; i < points.size()-1; i++) {
            Point2D current = points.get(i);
            Point2D target = points.get(i+1);

            // Definitley make the class Point2D have a method for returning a List (?) of points forming a line like this
            // maybe use y = m*x + b and figure out all the points -> set.addAll(Collection<Point2D>)
            while (!current.equals(target)) {
                set.add(current);
                current = current.moveClose(target);
            }

            set.add(target);
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
