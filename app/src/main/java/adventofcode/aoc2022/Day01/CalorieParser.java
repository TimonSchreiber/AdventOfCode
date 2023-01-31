package adventofcode.aoc2022.Day01;

import java.util.Arrays;
import java.util.List;

public class CalorieParser {

    private static final String DOUBLE_LINE_BREAK = "\n\n";

    public static List<Calorie> parse(String input) {
        return Arrays.stream(input.split(DOUBLE_LINE_BREAK))
                .map(
                    str -> str.lines()
                        .mapToInt(Integer::parseInt)
                        .sum()
                )
                .map(Calorie::new)
                .toList();
    }

}
