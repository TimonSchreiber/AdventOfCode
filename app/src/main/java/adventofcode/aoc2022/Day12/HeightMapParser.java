package adventofcode.aoc2022.Day12;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import adventofcode.util.geometry.Point2D;

/**
 * Height Map Parser
 */
public class HeightMapParser {

    private static Map<Point2D, Height> heightMap;
    
    public static HeightMap parse(List<String> list) {
        heightMap = new TreeMap<>();

        IntStream.range(0, list.size())
            .forEach(i -> parseHeights(i, list.get(i)));

        Entry<Point2D, Height> start = heightMap.entrySet().stream().filter(entry -> entry.getValue().height() == 'S').findAny().orElseThrow();
        Entry<Point2D, Height> end   = heightMap.entrySet().stream().filter(entry -> entry.getValue().height() == 'E').findAny().orElseThrow();

        heightMap.replace(start.getKey(), new Height("a".codePointAt(0)));
        heightMap.replace(  end.getKey(), new Height("z".codePointAt(0)));

        return new HeightMap(heightMap, start.getKey(), end.getKey(), list.size(), list.get(0).length());
    }

    private static void parseHeights(int index, String string) {
        // convert the String to a List of Heights
        List<Height> heights = string.codePoints()
                .mapToObj(Height::new)
                .toList();

        // Create a List of 2D Points for all heights
        List<Point2D> points = IntStream.range(0, string.length())
                .mapToObj(i -> new Point2D(index, i))
                .toList();

        // zip both Lists together
        Map<Point2D, Height> map = IntStream.range(0, heights.size())
                .boxed()
                .collect(Collectors.toMap(points::get, heights::get));

        // add the map to the HeightMap
        heightMap.putAll(map);
    }

}
