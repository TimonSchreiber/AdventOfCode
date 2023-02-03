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
        final String filepath = "aoc2022/Day05/test";

        String input = ReadInput.toSingleStringFrom(filepath);

        String[] split = input.split("\n\n");

        List<String> input1 = split[0].lines().toList();
        List<String> input2 = split[1].lines().toList();

        // just for testing:
        // System.out.println("Size of input1:\t" + input1.size());
        // System.out.println("Size of input2:\t" + input2.size());
        // ---------
        System.out.println("Before part 1\n");
        CrateStacks crateStacks1 = CrateParser.parse(input1);
        System.out.println("crateStack1:\t" + crateStacks1.getTopCrates());

        CrateStacks crateStacks2 = CrateParser.parse(input1);
        System.out.println("crateStack2:\t" + crateStacks2.getTopCrates());

        List<Procedure> procedures = ProcedureParser.parse(input2);
        
        // just for testing
        // System.out.println("Size of crateStacks:\t" + crateStacks.map().size());
        // System.out.println("Size of procedures:\t" + procedures.size());

        // crateStacks.map().entrySet().stream().forEachOrdered(System.out::println);
        // ---------

        String part1 = moveAll(crateStacks1, procedures, new CrateMover9000());
        System.out.println("-> Part1: " + part1);

        System.out.println("\nAfter Part 1\n");
        System.out.println("crateStack1:\t" + crateStacks1.getTopCrates());
        System.out.println("crateStack2:\t" + crateStacks2.getTopCrates());

        String part2 = moveAll(crateStacks2, procedures, new CrateMover9001());
        System.out.println("-> Part2: " + part2);

        System.out.println("\nAfter Part 2\n");
        System.out.println("crateStack1:\t" + crateStacks1.getTopCrates());
        System.out.println("crateStack2:\t" + crateStacks2.getTopCrates());
    }

    private static String moveAll(CrateStacks crateStacks, List<Procedure> procedures, CrateMover crane) {

        // this works on the crateStacks List and moves the crates around
        procedures.stream()
            .forEachOrdered(
                procedure -> crane.move(crateStacks, procedure)
            );

        // get the first Crate of each CrateStack and turn it into a String. Join all Strings and return.
        return crateStacks.getTopCrates();
    }

}
