package adventofcode.aoc2022.Day15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import adventofcode.util.geometry.Point2D;

/**
 * SensorScan
 */
public record SensorScan(List<SensorReport> reports) {

    public Map<Integer, Ranges> getAnalysis() {
        Map<Integer, Ranges> rangesPerYLevel = new HashMap<>();

        this.reports.stream()
            .forEach(
                report -> {
                    // get all Points of the circle
                    List<Point2D> square = report.sensor().getManhattanSquare(report.distance());

                    // Map each Point to its y-value
                    Map<Integer, List<Point2D>> yToPoint = square.stream()
                        .collect(
                            Collectors.groupingBy(Point2D::y)
                        );

                    // map each value to a Range
                    Map<Integer, Range> yToRange = yToPoint.entrySet().stream()
                        .collect(
                            Collectors.toMap(
                                Map.Entry::getKey,
                                e -> Range.of(e.getValue().stream().map(Point2D::x).toList())
                            )
                        );

                    // add each Entry to the 'rangesPerYLevel' Map
                    yToRange.entrySet().stream()
                        .forEach(
                            entry -> {
                                rangesPerYLevel.putIfAbsent(entry.getKey(), new Ranges(new ArrayList<>()));
                                rangesPerYLevel.get(entry.getKey()).add(entry.getValue());
                            }
                        );
                }
            );

        return rangesPerYLevel;
    }

}
