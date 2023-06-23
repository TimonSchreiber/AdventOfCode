package adventofcode.aoc2022.day05;

import java.util.List;

import adventofcode.util.io.ReadInput;

/**
 * https://adventofcode.com/2022/day/5
 */
public class SupplyStacks {

    public static void main(String[] args) {

        System.out.println("\n--- Day 5: Supply Stacks ---\n");

        final String filePath = "aoc2022/day05/input";

        String input = ReadInput.toSingleStringFrom(filePath);

        String[] split = input.split("\n\n");

        // TODO: make this better (maybe a 3rd parser?)
        final List<String> input1 = split[0].lines().toList();
        final List<String> input2 = split[1].lines().toList();

        // TODO: better way of copying the CrateStacks.map() than parsing it twice
        CrateStacks crateStacks1 = CrateParser.parse(input1);
        CrateStacks crateStacks2 = CrateParser.parse(input1);

        final List<Procedure> procedures = ProcedureParser.parse(input2);

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
