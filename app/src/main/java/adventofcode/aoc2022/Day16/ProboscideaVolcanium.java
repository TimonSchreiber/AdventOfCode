package adventofcode.aoc2022.Day16;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import adventofcode.util.IO.ReadInput;

/**
 * https://adventofcode.com/2022/day/16
 */
public class ProboscideaVolcanium {

    public static void main(String[] args) {

        System.out.println("\n### Day 16: Proboscidea Volcanium ###\n");

        // file path as String
        final String filePath = "aoc2022/Day16/test";

        final List<String> input = ReadInput.toListofStringsFrom(filePath);

        final Map<String, Room> rooms = RoomParser.parse(input);

        rooms.forEach((k,v) -> System.out.println(k + ":" + v));

        // dfs with /test  and depth 30 needs ~0.471s
        // dfs with /input and depth 22 needs ~7.404s
        // DFS dfs = new DFS(rooms, 10);

        // Instant start = Instant.now();
        // int part1 = dfs.search();
        // Duration time = Duration.between(start, Instant.now());
        // System.out.println("Time: " + time.toMillis());

        // System.out.println("-> Part1: " + part1);

        // long part2 = part2(map);
        // System.out.println("-> Part2: " + part2);
    }

    /*
     * DFS.search(20)
     * Solution:
     * Path2[rooms=[AA, DD, DD_V, AA, BB, BB_V, AA, II, JJ, JJ_V, II, AA, DD, EE, FF, GG, HH, HH_V, GG, HH]]
     * [0, 0, 20, 20, 20, 33, 33, 33, 33, 54, 54, 54, 54, 54, 54, 54, 54, 76, 76, 76]
     * -> Part1: 852
     * 294
     */

    /*
     * DFS.search(30)
     * Solution:
     * Path[rooms=[AA, DD, DD_V, AA, BB, BB_V, AA, II, JJ, JJ_V, II, AA, DD, EE, FF, GG, HH, HH_V, GG, FF, EE, EE_V, DD, CC, CC_V, BB, AA, DD, EE, FF]]
     * [0, 0, 20, 20, 20, 33, 33, 33, 33, 54, 54, 54, 54, 54, 54, 54, 54, 76, 76, 76, 76, 79, 79, 79, 81, 81, 81, 81, 81, 81]
     * -> Part1: 1651
     * 1609
     */

}
