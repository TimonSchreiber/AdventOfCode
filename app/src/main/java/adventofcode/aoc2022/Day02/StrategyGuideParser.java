package adventofcode.aoc2022.Day02;

import java.util.List;

/**
 * Strategy Guide Parser
 */
public class StrategyGuideParser {

    public static List<Strategy> parse(List<String> list) {
        return list.stream()
                .map(StrategyGuideParser::parseStrategy)
                .toList();
    }

    private static Strategy parseStrategy(String string) {
        // string is of format 'A X'
        int codePoint1 = string.codePointAt(0);
        int codePoint2 = string.codePointAt(2);

        return new Strategy(codePoint1, codePoint2);
    }

}
