package adventofcode.aoc2021.day14;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputParser {

    public static PolymerProcessor parse(List<String> list) {

        final Map<Element, Long> elementCounter = elementCounter(list.get(0));

        final Map<Pair, Long> pairCounter = pairCounter(list.get(0));

        final Map<Pair, Element> insertionRules = insertionRule(list.subList(2, list.size()));

        return new PolymerProcessor(
            elementCounter,
            pairCounter,
            insertionRules
        );
    }

    private static Map<Element, Long> elementCounter(String template) {
        return template.codePoints()
                .mapToObj(Element::new)
                .collect(
                    Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                    )
                );
    }

    private static Map<Pair, Long> pairCounter(String template) {
        return IntStream.range(0, template.length()-1)
                .mapToObj(i -> template.substring(i, i+2))
                .map(Pair::new)
                .collect(
                    Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                    )
                );
    }

    private static Map<Pair, Element> insertionRule(List<String> list) {
        return list.stream()
                .map(InputParser::split)
                .collect(
                    Collectors.toMap(
                        InputParser::parsePair,
                        InputParser::parsePolymer
                    )
                );
    }

    private static String[] split(String string) {
        return string.split(" -> ");
    }

    private static Pair parsePair(String[] arr) {
        return new Pair(arr[0]);
    }

    private static Element parsePolymer(String[] arr) {
        return new Element(arr[1].codePointAt(0));
    }
}
