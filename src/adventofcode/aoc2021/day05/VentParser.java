package adventofcode.aoc2021.day05;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VentParser {

    // example line: "79,490 -> 652,490"
    private static Pattern SEGMENT_PATTERN =
        Pattern.compile("^(?<x1>\\d+),(?<y1>\\d+) -> (?<x2>\\d+),(?<y2>\\d+)$");

    public static List<LineSegment> parse(List<String> list) {
        return list.stream()
                .map(VentParser::parse)
                .toList();
    }

    private static LineSegment parse(String string) {
        Matcher matcher = SEGMENT_PATTERN.matcher(string);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid input:\n" + string);
        }

        return new LineSegment(
            new Point(
                Integer.parseInt(matcher.group("x1")),
                Integer.parseInt(matcher.group("y1"))
            ),
            new Point(
                Integer.parseInt(matcher.group("x2")),
                Integer.parseInt(matcher.group("y2"))
            )
        );
    }

}
