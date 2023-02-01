package adventofcode.aoc2022.Day02;

import java.util.List;

public class StrategyGuideParser {

    public static List<Strategy> parse(List<String> input) {
        return input.stream()
                .map(Strategy::new)
                .toList();
    }

}
