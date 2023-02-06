package adventofcode.aoc2022.Day08;

import java.util.List;
import java.util.stream.Collectors;

public class ForestParser {
    
    public static Forest parse(List<String> list) {
        return list.stream()
                .map(ForestParser::parse)
                .collect(
                    Collectors.collectingAndThen(
                        Collectors.toList(),
                        Forest::new
                    )
                );
    }

    private static TreeRow parse(String string) {
        return string.codePoints()
                .map(ForestParser::toDigit)
                .mapToObj(Tree::new)
                .collect(
                    Collectors.collectingAndThen(
                        Collectors.toList(),
                        TreeRow::new
                    )
                );
    }

    private static int toDigit(int codePoint) {
        // 10 is the radix, something like base 10
        return Character.digit(codePoint, 10);
    }
}
