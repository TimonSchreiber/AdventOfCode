package adventofcode.aoc2022.Day02;

import java.util.List;

import adventofcode.util.ReadInput;

/**
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

    public static void solve() {
        System.out.println("\n### Day 2: Rock-Paper-Scissors ###\n");

        final String fielPath = "aoc2022/Day02/input";

        List<String> input = ReadInput.toListofStringsFrom(fielPath);

        List<List<Integer>> strategyGuide = parse(input);
        
        int part1 = part1(strategyGuide);
        System.out.println(" > Part1: " + part1);

        int part2 = part2(strategyGuide);
        System.out.println(" > Part2: " + part2);

        return;
    }

    // TODO: write a parser class for this    
    private static List<List<Integer>> parse(List<String> input) {
        return input.stream()
        .map(
            line -> line.chars()
            .filter(Character::isAlphabetic)
            .boxed()
            .toList()
            )
            .toList();
        }
        
        private static int part1(List<List<Integer>> strategyGuide) {
            
            return strategyGuide.stream()
            .map(List<Integer>::toArray)
            .map(int[].class::cast)
            .mapToInt(guide -> matchPoints1(guide) + handPoints(guide[1]))
            .sum();
    }

    private static int part2(List<List<Integer>> strategyGuide) {
        
        return strategyGuide.stream()
        .map(List<Integer>::toArray)
        .map(int[].class::cast)
        .mapToInt(RockPaperScissors::matchPoints2)
        .sum();
    }
    
    // TODO: extract at least the 3 methods at the bottom to their own class (StrategyGuide, Hand(s), ... maybe?)

    // 21 and 24 result in a win, 23 is always a draw, and 22 and 25 a loss
    private static int matchPoints1(int[] rows) {
        
        return switch (rows[1] - rows[0]) {
            case 21, 24 -> 6; // win
            case 23     -> 3; // draw
            case 22, 25 -> 0; // loss
            default     -> throw new IllegalStateException("Invalid result: " + (char)rows[0] + " " + (char)rows[1]);
        };
    }

    // TODO: not horrible, but could be better
    // figure out, what move player 2 has to make, to lose, draw, or win the game
    private static int matchPoints2(int[] rows) {

        return switch (rows[1]) {
            case 'X' -> 0 + ((rows[0] == 'A') ? handPoints(rows[0]+25) : handPoints(rows[0]+22));
            case 'Y' -> 3 + handPoints(rows[0] + 23);
            case 'Z' -> 6 + ((rows[0] == 'C') ? handPoints(rows[0]+21) : handPoints(rows[0]+24));
            default  -> throw new IllegalStateException("Invalid result: " + (char)rows[0] + " " + (char)rows[1]);
        };
    }

    // this will translate 'X', 'Y', and 'Z' to 1, 2, and 3 respectively.
    // TODO: maybe change to "row2 - 'Y'" but might not be clearer.
    private static int handPoints(int row2) {
        return row2 - 'X' + 1;
    }

}
