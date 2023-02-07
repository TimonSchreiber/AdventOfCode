package adventofcode.aoc2022.Day11;

import java.util.List;
import java.util.function.LongUnaryOperator;

import adventofcode.util.ReadInput;

/**
 * https://adventofcode.com/2022/day/11
 */
public class MonkeyInTheMiddle {

    public static void main(String[] args) {

        System.out.println("\n### Day 11: Monkey in the Middle ###\n");

        // file path as String
        final String filePath = "aoc2022/Day11/input";

        final String input = ReadInput.toSingleStringFrom(filePath);

        List<Monkey> monkeys;

        // // Part 1
        monkeys = MonkeyParser.parse(input);
        KeepAway game1 = new KeepAway(monkeys, x -> x/3);
        game1.play(20);
        long part1 = game1.twoMostActiveMonkeys();
        System.out.println("-> Part1: " + part1);

        // Part 2
        monkeys = MonkeyParser.parse(input);
        LongUnaryOperator reliefOperation = productOfTestDivisors(monkeys);
        KeepAway game2 = new KeepAway(monkeys, reliefOperation);
        game2.play(10_000);
        long part2 = game2.twoMostActiveMonkeys();
        System.out.println("-> Part2: " + part2);
    }

    private static LongUnaryOperator productOfTestDivisors(List<Monkey> monkeys) {
        int product = monkeys.stream().mapToInt(Monkey::testDivisor).reduce(1, (a,b) -> a*b);
        return x -> x % product;
    }

}
