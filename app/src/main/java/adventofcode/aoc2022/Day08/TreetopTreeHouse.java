package adventofcode.aoc2022.Day08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import adventofcode.util.ReadInput;

/**
 * https://adventofcode.com/2022/day/8
 */
public class TreetopTreeHouse {
    public static void main(String[] args) {
        System.out.println("\n### Day 8: Treetop Tree House ###\n");

        // file path as String
        final String filePath = "aoc2022/Day08/input";

        List<String> input = ReadInput.toListofStringsFrom(filePath);

        List<List<Integer>> treeHeightMap = processInput(input);

        long part1 = countVisibleTrees(treeHeightMap);
        System.out.println("-> Part1: " + part1);

        long part2 = scenicScore(treeHeightMap);
        System.out.println("-> Part2: " + part2);

    }

    /**
     * Convert each String into a List of its digits.
     * @param input
     * @return
     */
    private static List<List<Integer>> processInput(List<String> input) {
        return input.stream()
            .map(
                str -> str.chars()
                    .map(ch  -> Character.digit(ch, 10)) // 10 is the radix, something like the base
                    .boxed()
                    .toList()
            )
            .toList();
    }

    /**
     * Take a height map and check from each side which elemets are visible and
     * mark them as a 1 in the result map.
     * @param grid a 2D-List as a height map
     * @return a 2D-List with 1s and 0s
     */
    private static int countVisibleTrees(List<List<Integer>> grid) {

        int iDim = grid.size();
        int jDim = grid.get(0).size();
        List<List<Integer>> map = IntStream.range(0, iDim)
            .mapToObj(i -> new ArrayList<Integer>(Collections.nCopies(jDim, 0)))
            .collect(Collectors.toList());
            // .toList(); // TODO: why does this not work?

        // check left to right
        for (int i = 0 ; i < iDim; i++) {
            int height = -1;
            for (int j = 0; j < jDim; j++) {
                if (grid.get(i).get(j) > height) {
                    height = grid.get(i).get(j);
                    map.get(i).set(j, 1);
                }
                if (height >= 9) { break; } // the max tree height is reached. No need to look further down this row/column
            }
        }

        // check right to left
        for (int i = iDim-1 ; i >= 0; i--) {
            int height = -1;
            for (int j = jDim-1; j >= 0; j--) {
                if (grid.get(i).get(j) > height) {
                    height = grid.get(i).get(j);
                    map.get(i).set(j, 1);
                }
                if (height >= 9) { break; }
            }
        }
        
        // check top to bottom
        for (int i = 0 ; i < iDim; i++) {
            int height = -1;
            for (int j = 0; j < jDim; j++) {
                if (grid.get(j).get(i) > height) {
                    height = grid.get(j).get(i);
                    map.get(j).set(i, 1);
                }
                if (height >= 9) { break; }
            }
        }

        // check bottom to top
        for (int i = iDim-1 ; i >= 0; i--) {
            int height = -1;
            for (int j = jDim-1; j >= 0; j--) {
                if (grid.get(j).get(i) > height) {
                    height = grid.get(j).get(i);
                    map.get(j).set(i, 1);
                }
                if (height >= 9) { break; }
            }
        }

        // return the sum of all values in the 2D-List (values can be eitehr 0 or 1)
        return map.stream()
            .mapToInt(
                list -> list.stream().mapToInt(Integer::valueOf).sum()
            ).sum();
    }

    private static int scenicScore(List<List<Integer>> grid) {
        
        int iDim = grid.size();
        int jDim = grid.get(0).size();
        List<List<Integer>> map = IntStream.range(0, iDim)
            .mapToObj(i -> new ArrayList<Integer>(Collections.nCopies(jDim, 0)))
            .collect(Collectors.toList());
            // .toList(); // TODO: why does this not work?

        for (int i = 0; i < iDim; i++) {
            for (int j = 0; j < jDim; j++) {
                int left  = checkView(grid, i, j, 0, -1);
                int right = checkView(grid, i, j, 0,  1);
                int up    = checkView(grid, i, j,-1,  0);
                int down  = checkView(grid, i, j, 1,  0);

                map.get(i).set(j, left*right*up*down);
            }
        }

        printTreeMap(map);

        // return the max value in the 2D-List (or 0)
        return map.stream()
            .mapToInt(
                list -> list.stream().max(Comparator.naturalOrder()).orElse(0)
            ).max().orElse(0);
    }

    private static int checkView(List<List<Integer>> grid, int oldI, int oldJ, int deltaI, int deltaJ) {
        int res = 0;
        int height = grid.get(oldI).get(oldJ);
        int newI = oldI + deltaI;
        int newJ = oldJ + deltaJ;
        while ((newI >= 0) && (newI < grid.size()) && (newJ >= 0) && (newJ < grid.get(0).size())) {
            res++;
            if (grid.get(newI).get(newJ) >= height) {
                return res;
            }
            newI += deltaI;
            newJ += deltaJ;
        }
        return res;
    }

    /**
     * Print a 2D-List.
     * @param treeHeightMap
     */
    private static void printTreeMap(List<List<Integer>> treeHeightMap) {
        System.out.println();
        treeHeightMap.stream()
            .forEachOrdered(
                System.out::println
            );
        System.out.println();
    }

}
