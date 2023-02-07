package adventofcode.aoc2022.Day11;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import adventofcode.util.ReadInput;

/**
 * https://adventofcode.com/2022/day/11
 */
public class MonkeyInTheMiddle {

    public static void main(String[] args) {

        System.out.println("\n### Day 11: Monkey in the Middle ###\n");

        // file path as String
        final String filePath = "aoc2022/Day11/input";

        String input = ReadInput.toSingleStringFrom(filePath);

        final List<Monkey> monkeys = MonkeyParser.parse(input);

        // monkeys.stream()
        //     .forEachOrdered(System.out::println);

        final List<Integer> inspections = numberOfInspectedItems(monkeys);

        int part1 = twoMostActiveMonkeys(inspections);
        System.out.println("-> Part1: " + part1);

        // int part2 = part2(inspections);
        // System.out.println("-> Part2: " + part2);

    }

    private static List<Integer> numberOfInspectedItems(List<Monkey> monkeys) {
        int[] inspections = new int[monkeys.size()];

        final int numberOfRounds = 20;

        for (int i = 0; i < numberOfRounds; i++) {
            int index = 0;
            for (Monkey monkey : monkeys) {
                while (monkey.hasItems()) {
                    ThrowTo throwTo = monkey.inspectAndThrow();
                    monkeys.get(throwTo.target()).recieveItem(throwTo.item());
                    inspections[index]++;
                }
                index++;
            }
        }

        return Arrays.stream(inspections)
            .boxed()
            .toList();
    }

    private static int twoMostActiveMonkeys(List<Integer> inspections) {
        return inspections.stream()
            .sorted(Comparator.reverseOrder())
            .limit(2)
            .reduce(1, (a,b) -> a*b);
    }

}
