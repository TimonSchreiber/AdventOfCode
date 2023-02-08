package adventofcode.aoc2022.Day09;

import java.util.List;

import adventofcode.util.geometry.Direction;

/**
 * Motion Parser
 */
public class MotionParser {
    
    public static List<Motion> parse(List<String> list) {
        return list.stream().map(MotionParser::parseMotion).toList();
    }

    private static Motion parseMotion(String string) {
        String[] arr = string.split(" ");

        Direction direction = Direction.valueOf(arr[0]);
        int steps = Integer.parseInt(arr[1]);

        return new Motion(direction, steps);
    }

}
