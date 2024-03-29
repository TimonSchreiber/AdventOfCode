package adventofcode.aoc2022.day04;

import java.util.List;

import adventofcode.util.parser.Parser;

/**
 * Assignment Parser
 */
public class AssignmentParser {

    public static List<AssignmentPair> parse(List<String> list) {
        return list.stream().map(AssignmentParser::parse).toList();
    }

    public static AssignmentPair parse(String string) {
        final List<Integer> sections = Parser.parseToIntList(string, "[,-]");

        return new AssignmentPair(
            new SectionRange(sections.get(0), sections.get(1)),
            new SectionRange(sections.get(2), sections.get(3))
        );
    }

}
