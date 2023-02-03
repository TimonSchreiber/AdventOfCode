package adventofcode.aoc2022.Day05;

import java.util.List;

import adventofcode.util.ReadInput;

/**
 * https://adventofcode.com/2022/day/5
 */
public class SupplyStacks {

    public static void main(String[] args) {

        System.out.println("\n### Day 5: Supply Stacks ###\n");

        // file path as String
        final String filepath = "aoc2022/Day05/input";

        String input = ReadInput.toSingleStringFrom(filepath);

        String[] split = input.split("\n\n");

        List<String> input1 = split[0].lines().toList();
        List<String> input2 = split[1].lines().toList();

        CrateStacks crateStacks1 = CrateParser.parse(input1);

        CrateStacks crateStacks2 = CrateParser.parse(input1);

        List<Procedure> procedures = ProcedureParser.parse(input2);

        String part1 = moveAll(crateStacks1, procedures, new CrateMover9000());
        System.out.println("-> Part1: " + part1);

        String part2 = moveAll(crateStacks2, procedures, new CrateMover9001());
        System.out.println("-> Part2: " + part2);

    }

    private static String moveAll(CrateStacks crateStacks, List<Procedure> procedures, CrateMover crane) {

        procedures.stream()
            .forEachOrdered(
                procedure -> crane.move(crateStacks, procedure)
            );

        return crateStacks.getTopCrates();
    }

}
