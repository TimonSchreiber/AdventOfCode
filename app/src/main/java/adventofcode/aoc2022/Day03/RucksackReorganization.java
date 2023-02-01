package adventofcode.aoc2022.Day03;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import adventofcode.util.ReadInput;

/**
 * https://adventofcode.com/2022/day/3
 */
public class RucksackReorganization {

    public static void main(String[] args) {
        System.out.println("\n### Day 3: Rucksack Reorganisation ###\n");

        // file path as String
        final String filePath = "Day03/files/input";

        List<String> input = ReadInput.toListofStringsFrom(filePath);

        int part1 = sumOfNotDistinctItems(input);
        System.out.println("-> Part1: " + part1);
        
        int part2 = sumOfGroupBadges(input);
        System.out.println("-> Part2: " + part2);

    }

    /** TODO: this can be used in the final solution (main method), or is there a better way?
     * converts a character to its priority value specified by the task.
     * @param letter
     * @return
     */
    private static int calculatePriorityValue(char letter) {

        // form 'letter' substract it's coresponding first letter ('A' or 'a')
        // and add the priority offset to it so that letter 'a' = 1, 'b' = 2, 'A' = 27, 'B' = 28, ...
        return letter + ((letter <= 'Z') ? (27 - 'A') : (1 - 'a'));
    }

    /**
     *
     * @param input
     * @return
     */
    private static int sumOfNotDistinctItems(List<String> input) {
        return input.stream()
            .mapToInt(
                str -> {
                    // divide the String into two parts of equal length
                    int halfLength = str.length() / 2;
                    String firstHalf  = str.substring(0, halfLength);
                    String secondHalf = str.substring(   halfLength);

                    // collect each char of each subString into a Set
                    List<Set<Character>> list =
                        List.of(
                             firstHalf.chars().mapToObj(ch -> (char) ch).collect(Collectors.toSet()),
                            secondHalf.chars().mapToObj(ch -> (char) ch).collect(Collectors.toSet())
                        );

                    // only keep the chars in set 0, who are also in set 1
                    // this will only be one char
                    list.get(0).retainAll(list.get(1));

                    // get the one remaining char form the set 0 and calculate its value
                    return calculatePriorityValue(list.get(0).iterator().next());
                }
            ).sum(); // sum up all the values
    }

    /**
     * TODO: change method name
     * @param input
     * @return
     */
    private static int sumOfGroupBadges(List<String> input) {
        // divide the List into groups of size 3
        return IntStream.range(0, input.size()/3)
            .map(
                i -> {
                    int index = i * 3; // calculate first index of each group of three lines

                    // collect all three Strings in a separate Set of chars
                    List<Set<Character>> badge =
                        List.of(
                            input.get(index + 0).chars().mapToObj(ch -> (char) ch).collect(Collectors.toSet()),
                            input.get(index + 1).chars().mapToObj(ch -> (char) ch).collect(Collectors.toSet()),
                            input.get(index + 2).chars().mapToObj(ch -> (char) ch).collect(Collectors.toSet())
                        );

                    // only keep the (one) common char in all three sets
                    badge.get(0).retainAll(badge.get(1));
                    badge.get(0).retainAll(badge.get(2));

                    // calculate its priority value ( there is onl yone char in the set 0 at the end!)
                    return calculatePriorityValue(badge.get(0).iterator().next());
                }
            ).sum(); // sum up all the values
    }

}
