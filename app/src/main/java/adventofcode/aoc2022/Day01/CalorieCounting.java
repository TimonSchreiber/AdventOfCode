package adventofcode.aoc2022.Day01;

import java.util.Comparator;
import java.util.List;

import adventofcode.util.ReadInput;

/**
 * https://adventofcode.com/2022/day/1
 */
public class CalorieCounting {
    
    public static void main(String[] args) {
        System.out.println("\n### Day 1: Colorie Counting ###\n");

        // file path suffix as String
        final String filePath = "aoc2022/Day01/input";

        String input = ReadInput.toSingleStringFrom(filePath);

        List<Calorie> caloriesPerElf = CalorieParser.parse(input);

        int part1 = getMostCalories(caloriesPerElf);
        System.out.println("-> Part1: " + part1);
        
        int part2 = sumOfTopN(caloriesPerElf, 3);
        System.out.println("-> Part2: " + part2);

    }

    private static int getMostCalories(List<Calorie> caloriesPerElf) {
        return caloriesPerElf.stream()
                .map(Calorie::calorie)
                .max(Comparator.reverseOrder())
                .orElseThrow();
    }

    private static int sumOfTopN(List<Calorie> caloriesPerElf, int n) {
        return caloriesPerElf.stream()
                .map(Calorie::calorie)
                .sorted(Comparator.reverseOrder())
                .limit(n)
                .mapToInt(Integer::valueOf)
                .sum();
    }

}
