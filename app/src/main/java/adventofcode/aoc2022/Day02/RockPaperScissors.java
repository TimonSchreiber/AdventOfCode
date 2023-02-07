package adventofcode.aoc2022.Day02;

import java.util.List;

import adventofcode.util.IO.ReadInput;

/** TODO: this Day needs some improvement: Maybe make the Shapes an Enum and get the values form there.
 * https://adventofcode.com/2022/day/2
 * 
 * A -> Rock     -> int value of 65
 * B -> Paper    -> int value of 66
 * C -> Scissors -> int value of 67
 * 
 * Part 1:
 * X -> Rock     -> int value of 88
 * Y -> Paper    -> int value of 89
 * Z -> Scissors -> int value of 90
 * 
 * Part 2:
 * X -> lose -> int value of 88
 * Y -> draw -> int value of 89
 * Z -> win  -> int value of 90
 * 
 */
public class RockPaperScissors {

    public static void main(String[] args) {
        System.out.println("\n### Day 2: Rock-Paper-Scissors ###\n");

        final String fielPath = "aoc2022/Day02/input";

        final List<String> input = ReadInput.toListofStringsFrom(fielPath);

        final List<Strategy> strategyGuide = StrategyGuideParser.parse(input);
        
        int part1 = part1(strategyGuide);
        System.out.println(" > Part1: " + part1);

        int part2 = part2(strategyGuide);
        System.out.println(" > Part2: " + part2);
    }
        
    private static int part1(List<Strategy> strategyGuide) {
        return strategyGuide.stream()
                .mapToInt(Strategy::matchPoints1)
                .sum();
    }

    private static int part2(List<Strategy> strategyGuide) {
        return strategyGuide.stream()
                .mapToInt(Strategy::matchPoints2)
                .sum();
    }

}
