package adventofcode.aoc2022.day03;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Rucksack Parser
 */
public class RucksackParser {

    public static List<Compartments> parseCompartments(List<String> list) {
        return list.stream()
                .map(RucksackParser::parseCompartments)
                .toList();
    }

    public static List<Rucksack> parseRucksacks(List<String> list){
        return list.stream()
                .map(RucksackParser::parseRucksack)
                .toList();
    }

    private static Compartments parseCompartments(String string) {
        int middle = string.length() / 2;
        String s1 = string.substring(0, middle);
        String s2 = string.substring(middle);

        final Set<Item> set1 = parseSetOfItems(s1);
        final Set<Item> set2 = parseSetOfItems(s2);

        return new Compartments(set1, set2);
    }

    private static Rucksack parseRucksack(String string) {
        final Set<Item> set = parseSetOfItems(string);

        return new Rucksack(set);
    }

    private static Set<Item> parseSetOfItems(String string) {
        return string.codePoints()
                .mapToObj(Item::new)
                .collect(Collectors.toSet());
    }

}
