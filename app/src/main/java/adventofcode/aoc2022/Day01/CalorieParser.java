package adventofcode.aoc2022.Day01;

import java.util.Arrays;
import java.util.List;

public class CalorieParser {

    private static final String DOUBLE_LINE_BREAK = "\n\n";

    public static List<Calorie> parse(String input) {
        return Arrays.stream(input.split(DOUBLE_LINE_BREAK))
                .map(CalorieParser::sumOfGroup)
                .map(Calorie::new)
                .toList();
    }

    private static int sumOfGroup(String string) {
        return string.lines()
                .mapToInt(Integer::parseInt)
                .sum();
    }

}
