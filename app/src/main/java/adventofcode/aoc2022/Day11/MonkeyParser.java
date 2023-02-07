package adventofcode.aoc2022.Day11;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Monkey Parser
 */
public class MonkeyParser {

    private static final String DOUBLE_LINEBREAK = "\n\n";

    public static List<Monkey> parse(String list) {
        return Arrays.stream(list.split(DOUBLE_LINEBREAK))
                .map(MonkeyParser::parseToMonkey)
                .toList();
    }

    private static Monkey parseToMonkey(String string) {
        return string.lines()
            .map(String::trim)
            .collect(
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    Monkey::new
                )
            );
    }

}
