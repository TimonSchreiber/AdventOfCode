package adventofcode.aoc2022.day04;

import java.util.List;

import adventofcode.util.io.ReadInput;

/**
 * https://adventofcode.com/2022/day/4
 */
public class CampCleanup {

    public static void main(String[] args) {
        System.out.println("\n--- Day 4: Camp Cleanup ---\n");

        // file path as String
        final String filePath = "aoc2022/day04/input";

        final List<String> input = ReadInput.toListOfStringsFrom(filePath);

        final List<AssignmentPair> assignmentPairs = AssignmentParser.parse(input);

        long part1 = numberOfAssignmentsFullyContainingOneAnother(assignmentPairs);
        System.out.println("-> Part1: " + part1);   // 526

        long part2 = numberOfAssignmentsOverlapping(assignmentPairs);
        System.out.println("-> Part2: " + part2);   // 886
    }

   /**
    * Counts in how many assignment pairs one fully contains the other.
    * @param assignmentPairs
    * @return
    */
    private static long numberOfAssignmentsFullyContainingOneAnother(List<AssignmentPair> assignmentPairs) {
        return assignmentPairs.stream()
                .filter(AssignmentPair::fullyContainOneAnother)
                .count();
    }

    /**
     * Counts in how many assignment pairs the ranges overlap.
     * @param assignmentPairs
     * @return
     */
    private static long numberOfAssignmentsOverlapping(List<AssignmentPair> assignmentPairs) {
        return assignmentPairs.stream()
                .filter(AssignmentPair::overlap)
                .count();
    }

}
