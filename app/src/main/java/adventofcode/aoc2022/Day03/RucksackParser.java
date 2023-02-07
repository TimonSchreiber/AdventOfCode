package adventofcode.aoc2022.Day03;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RucksackParser {
    public static List<Compartments> parseToCompartments(List<String> list) {
        return list.stream()
                .map(RucksackParser::parseToCompartments)
                .toList();
    }

    public static List<Rucksack> parseToRucksacks(List<String> list){
        return list.stream()
                .map(RucksackParser::parseToRucksack)
                .toList();
    }

    private static Compartments parseToCompartments(String line) {
        int middle = line.length() / 2;
        String s1 = line.substring(0, middle);
        String s2 = line.substring(middle);
        final Set<Item> set1 = s1.codePoints().mapToObj(Item::new).collect(Collectors.toSet());
        final Set<Item> set2 = s2.codePoints().mapToObj(Item::new).collect(Collectors.toSet());
        return new Compartments(set1, set2);
    }

    private static Rucksack parseToRucksack(String line) {
        final Set<Item> set = line.codePoints().mapToObj(Item::new).collect(Collectors.toSet());
        return new Rucksack(set);
    }

}
