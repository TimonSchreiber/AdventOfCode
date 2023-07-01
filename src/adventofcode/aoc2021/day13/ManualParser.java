package adventofcode.aoc2021.day13;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import adventofcode.util.geometry.Point2D;

/**
 * TODO: parsing twice is kinda stupid...
 */
public class ManualParser {

    /**
     * Only parse the first half of the List (before the empty line)
     * @param list
     * @return
     */
    public static Paper parsePaper(List<String> list) {
        // a mutable set of Points
        Set<Point2D> points =
            list.subList(0, list.indexOf(""))
                .stream()
                .map(ManualParser::parsePoint)
                .collect(Collectors.toSet());

                // a mutable List of instructions
        List<FoldInstruction> instructions =
            list.subList(list.indexOf("")+1, list.size())
                .stream()
                .map(ManualParser::parseInstruction)
                .collect(Collectors.toList());

        return new Paper(points, instructions);
    }

    private static Point2D parsePoint(String string) {
        String[] arr = string.split(",");
        return new Point2D(
            Integer.parseInt(arr[0]),
            Integer.parseInt(arr[1])
        );
    }

    private static FoldInstruction parseInstruction(String string) {
        String[] arr = string.substring("fold along ".length()).split("=");
        return new FoldInstruction(
            arr[0].charAt(0),
            Integer.parseInt(arr[1])
        );
    }

}
