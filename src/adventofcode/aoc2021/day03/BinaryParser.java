package adventofcode.aoc2021.day03;

import java.util.List;

public class BinaryParser {

    public static List<Integer> parse(List<String> list) {
        return list.stream()
                .map(BinaryParser::parse)
                .toList();
    }

    private static Integer parse(String string) {
        return Integer.parseUnsignedInt(string, 2);
    }

}
