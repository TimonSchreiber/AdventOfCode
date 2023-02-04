package adventofcode.aoc2022.Day05;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Example input:
 *     [D]
 * [N] [C]
 * [Z] [M] [P]
 *  1   2   3
 */

public class CrateParser {

    private static CrateStacks stacks;

    public static CrateStacks parse(List<String> list) {

        stacks = new CrateStacks(new HashMap<>());

        list.stream()
            .filter(CrateParser::isCrateString)
            .forEachOrdered(CrateParser::parse);

        return stacks;
    }

    private static void parse(String string) {

        // get a List of Crates by filtering for Letters in the String
        final List<Crate> crates = string.codePoints()
                .filter(Character::isLetter)
                .mapToObj(Crate::new)
                .toList();

        // get a List of indices corresponding to the index of the Letter representing a Crate
        final List<Integer> indices = IntStream.range(0, string.length())
                .filter(i -> isLetterAtIndex(string, i))
                .map(CrateParser::stringIndexToStackIndex)
                .boxed()
                .toList();

        // check if crates and indices have the same size
        if (crates.size() != indices.size()) { throw new IllegalStateException(); }

        // zip both Lists into a map
        Map<Integer, Crate> indicesToCrates = IntStream.range(0, crates.size())
            .boxed()
            .collect(Collectors.toMap(indices::get, crates::get));

        // for each entry in the map:
        indicesToCrates.entrySet()
            .stream()
            .forEachOrdered(CrateParser::addCrateToStack);
    }

    private static boolean isCrateString(String str) {
        return str.contains("[");
    }

    private static boolean isLetterAtIndex(String string, int i) {
        return Character.isLetter(string.codePointAt(i));
    }

    private static int stringIndexToStackIndex(int index) {
        return 1 + (index / 4);
    }

    private static void addCrateToStack(Entry<Integer, Crate> entry) {
        // check if the CrateStack at indices[i] already exists. If not create a new CrateStack
        stacks.putIfAbsent(entry.getKey(), new CrateStack(new ArrayDeque<>()));
        // insert at the end of the CrateStack indices[i] the Crate from crates[i]
        stacks.get(entry.getKey()).offerLast(entry.getValue());
    }
}
