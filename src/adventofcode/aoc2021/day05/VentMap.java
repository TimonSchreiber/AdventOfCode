package adventofcode.aoc2021.day05;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class VentMap {

    private final List<LineSegment> lines;

    public VentMap(List<LineSegment> lines) {
        this.lines = lines;
    }

    /**
     * Returns the number of Points which overlapp in this VentMap.
     * Only looking at horizontal/vertical LineSegments or additionally with
     * diagonal LineSegments.
     * @param withDiagonals With or without diagonal LineSegments.
     * @return              Number of overlapping Points.
     */
    public long countOverlapp(boolean withDiagonals) {
        Map<Point, Long> pointFrequency = lines.stream()
                .filter(line -> withDiagonals || horizontalAndVertical(line))
                .flatMap(line -> line.line().stream())
                .collect(
                    Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                    )
                );

        return pointFrequency.values()
                    .stream()
                    .filter(l -> l > 1)
                    .count();
    }

    private boolean horizontalAndVertical(LineSegment line) {
        return line.p1().x() == line.p2().x()
            || line.p1().y() == line.p2().y();
    }

}
