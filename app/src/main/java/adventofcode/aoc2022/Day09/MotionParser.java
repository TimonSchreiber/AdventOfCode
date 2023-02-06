package adventofcode.aoc2022.Day09;

import java.util.List;

/**
 * Motion Parser
 */
public class MotionParser {
    
    public static List<Motion> parse(List<String> list) {
        return list.stream().map(Motion::new).toList();
    }

}
