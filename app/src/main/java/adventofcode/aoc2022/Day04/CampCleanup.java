package adventofcode.aoc2022.Day04;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import adventofcode.util.ReadInput;

public class CampCleanup {
    
    public static void main(String[] args) {
        System.out.println("\n### Day 4: Camp Cleanup ###\n");

        // file path as String
        final String filePath = "aoc2022/Day04/input";

        List<String> input = ReadInput.toListofStringsFrom(filePath);

        List<Map<Boolean, Set<Integer>>> sectionAssignmentsForEachPair = processInput(input);

        long part1 = numberOfAssignmentsFullyContainingOneAnother(sectionAssignmentsForEachPair);
        System.out.println("-> Part1: " + part1);
        
        long part2 = numberOfAssignmentsOverlapping(sectionAssignmentsForEachPair);
        System.out.println("-> Part2: " + part2);

    }

    private static List<Map<Boolean, Set<Integer>>> processInput(List<String> input) {
        // extract the integers from each string and put them in a List
        List<List<Integer>> tmp =  input.stream()
            .map(
                str ->
                    Arrays.stream(str.split("[,-]"))
                        .mapToInt(Integer::parseInt)
                        .boxed()
                        .toList()
            ).toList();

        // group the range of integers between each pair of indices in the list into sets
        return tmp.stream()
            .map(
                list -> Map.ofEntries(
                    Map.entry(
                        true,
                        IntStream.rangeClosed(
                            list.get(0),
                            list.get(1)
                        ).boxed()
                        .collect(Collectors.toSet())
                    ),
                    Map.entry(
                        false,
                        IntStream.rangeClosed(
                            list.get(2),
                            list.get(3)
                        ).boxed()
                        .collect(Collectors.toSet())
                    )
                )
            ).toList();
    }

   /**
    * Counts in how many assignment pairs one fully contains the other.
    * @param assignments
    * @return
    */
    private static long numberOfAssignmentsFullyContainingOneAnother(List<Map<Boolean, Set<Integer>>> assignments) {

        // either of the two sets contains the other completley (Set1#containsAll(Set2) || Set2#containsAll(Set1)) -> add one to result1
        return assignments.stream()
            .filter(
                a -> a.get(true).containsAll(a.get(false)) || a.get(false).containsAll(a.get(true))
            ).count();
    }

    /**
     * Counts in how many assignment pairs the ranges overlap.
     * @param assignments
     * @return
     */
    private static long numberOfAssignmentsOverlapping(List<Map<Boolean, Set<Integer>>> assignments) {

        // one set has parts of the other set (Set#removeAll(Set)) -> add one to result2
        return assignments.stream()
            .filter(
                a -> a.get(true).removeAll(a.get(false))
            ).count();
    }

}
