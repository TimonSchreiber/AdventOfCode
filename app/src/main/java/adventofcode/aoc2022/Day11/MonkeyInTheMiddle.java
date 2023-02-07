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
        final String filePath = "aoc2022/Day11/test";
        int reduce = (13*17*19*23);         // test input
        // int reduce = (2*3*5*7*11*13*17*19);  // real input

        final String input = ReadInput.toSingleStringFrom(filePath);

        List<Monkey> monkeys;
        int reliefDivisor;
        List<Long> inspections;

        // Part 1
        monkeys = MonkeyParser.parse(input);
        reliefDivisor = 3;
        inspections = numberOfInspectedItems(monkeys, 20, reliefDivisor, 0);
        long part1 = twoMostActiveMonkeys(inspections);
        System.out.println("-> Part1: " + part1);

        // Part 2
        monkeys = MonkeyParser.parse(input);
        reliefDivisor = reduce;
        inspections = numberOfInspectedItems(monkeys, 10_000, 1, reduce);
        long part2 = twoMostActiveMonkeys(inspections);
        System.out.println("-> Part2: " + part2);
    }

    private static List<Long> numberOfInspectedItems(List<Monkey> monkeys, int numberOfRounds, int reliefDivisor, int modulo) {
        long[] inspections = new long[monkeys.size()];

        for (int i = 1; i <= numberOfRounds; i++) {
            int index = 0;
            for (Monkey monkey : monkeys) {
                while (monkey.hasItems()) {
                    ThrowItemTo throwItemTo = monkey.inspectAndThrow(reliefDivisor, modulo);
                    monkeys.get(throwItemTo.target()).recieveItem(throwItemTo.item());
                    inspections[index]++;
                }
                index++;
            }
            if (i == 1 || i == 20 || (i%1_000) == 0) {
                System.out.println("\n== After round " + (i) + " ==");
                for (int j = 0; j < inspections.length; j++) {
                    System.out.println("Monkey " + j + " inspected items " + inspections[j] + "\ttimes.");
                }
            }
        }

        return Arrays.stream(inspections)
            .boxed()
            .toList();
    }

    private static long twoMostActiveMonkeys(List<Long> inspections) {
        return inspections.stream()
            .sorted(Comparator.reverseOrder())
            .limit(2)
            .reduce(1L, (a,b) -> a*b);
    }

}
