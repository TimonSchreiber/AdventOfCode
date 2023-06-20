package adventofcode.aoc2021.day01;

import java.util.List;

public class MeasurementParser {

    public static List<Integer> parse(List<String> list) {
        return list.stream()
                .map(Integer::valueOf)
                .toList();
    }

}
