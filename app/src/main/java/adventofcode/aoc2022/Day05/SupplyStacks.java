package adventofcode.aoc2022.Day05;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/** FIXME: main method at the top and then calling everthing
 * TODO: maybe more streams?
 * https://adventofcode.com/2022/day/5
 */
public class SupplyStacks {
    
    /** File Path */
    private static final String FILE_PATH = "app/src/main/resources/aoc2022/Day05/input";

    private static final Map<Integer, Deque<Character>> crates1 = new HashMap<>();
    private static final Map<Integer, Deque<Character>> crates2 = new HashMap<>();

    // -------------------------------------------------------------------------

    /**
     * Read the input and calculate the result of each match.
     */
    private static void readInput() {

        try (Scanner scanner = new Scanner(new File(FILE_PATH))) {

            while (scanner.hasNextLine()) {

                // one line of the file
                String line = scanner.nextLine(); // format example: "move 1 from 2 to 1"

                processLine1(line);
                processLine2(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return;
    }

    // method for processing the input for part 1
    private static void processLine1(String line) {

        /*
         * Three different kinds of lines to differentiate:
         * 1. the crates with capital letters and '[]'
         * 2. the move orders starting with 'move'
         * 3. the numbers corresponding to each crate stack -> not relevant to read
         */


        if (line.contains("[")) { // example format: "[Z] [M] [P]"

            // get an array of crates by filtering for letters
            var chars = // TODO: try to make this a char[] or List<Character>
                line.chars()
                    .filter(Character::isLetter)
                    // .mapToObj(i -> (char)i)
                    .toArray();

            // get an array of indices by filtering a range of ints and mapping it to corresponding List-indices
            int[] index =
                IntStream.range(0, line.length())
                    .filter(i -> Character.isLetter(line.charAt(i)))
                    .map(i -> (i-1)/4)
                    .toArray();

            for (int i = 0; i < chars.length; i++) {
                // check if the stack at index[i] already exist. if not, create it.
                crates1.putIfAbsent(index[i], new ArrayDeque<>());

                // insert at the tail of the stack index[i] the value chars[i]   
                crates1.get(index[i]).offerLast((char)chars[i]); // so "var chars...", make this cast obsolete
            }

        } else if (line.startsWith("move")) { // example format: "move 1 from 2 to 1"

            // remove the leading "move " String, split along each non digit subString, map the remaining numbers to Ints and put them into an array
            int[] moveOrder =
                Arrays.stream(
                    line.substring("move ".length())
                        .split("[^0-9]+")
                )
                .mapToInt(Integer::parseInt)
                .toArray();

            // pop the first int from the stack at int[1] and add it to the int[2] stack. Do this int[0] times.
            for (int i = 0; i < moveOrder[0]; i++) {
                crates1.get(moveOrder[2]-1)               // get the correct stack to add to
                    .offerFirst(
                        crates1.get(moveOrder[1]-1).pop() // get the correct stack to pop the first element from
                    ); // moveOrder[]-1 because indexing of crates starts at 1
            }

        } else {
            // do nothing
        }
    }

    // method for processing the input for part 2
    private static void processLine2(String line) {

        /*
         * Three different kinds of lines to differentiate:
         * 1. the crates with capital letters and '[]'
         * 2. the move orders starting with 'move'
         * 3. the numbers corresponding to each crate stack -> not relevant to read
         */


        if (line.contains("[")) { // example format: "[Z] [M] [P]"

            // get an array of crates by filtering for letters
            var chars = // TODO: try to make this a char[] or List<Character>
                line.chars()
                    .filter(Character::isLetter)
                    // .mapToObj(i -> (char)i)
                    .toArray();

            // get an array of indices by filtering a range of ints and mapping it to corresponding List-indices
            int[] index =
                IntStream.range(0, line.length())
                    .filter(i -> Character.isLetter(line.charAt(i)))
                    .map(i -> (i-1)/4)
                    .toArray();

            for (int i = 0; i < chars.length; i++) {
                // check if the stack at index[i] already exist. if not, create it.
                crates2.putIfAbsent(index[i], new ArrayDeque<>());

                // insert at the tail of the stack index[i] the value chars[i]   
                crates2.get(index[i]).offerLast((char)chars[i]); // so "var chars...", make this cast obsolete
            }

        } else if (line.startsWith("move")) { // example format: "move 1 from 2 to 1"

            // remove the leading "move " String, split along each non digit subString, map the remaining numbers to Ints and put them into an array
            int[] moveOrder =
                Arrays.stream(
                    line.substring("move ".length())
                        .split("[^0-9]+")
                )
                .mapToInt(Integer::parseInt)
                .toArray();

            // the tmpStack is needed to keep the order of crates in the second part
            Deque<Character> tmpStack = new ArrayDeque<>();

            // pop the first int from the stack at int[1]-1 and add it to the tmpStack. Do this int[0] times.
            for (int i = 0; i < moveOrder[0]; i++) {
                tmpStack.offerFirst(
                        crates2.get(moveOrder[1]-1).pop() // get the correct stack to pop the first element from
                    ); // moveOrder[]-1 because indexing of crates starts at 1
            }

            // pop the first int from the tmpStack and add it to the int[2]-1 stack. Do this int[0] times.
            for (int i = 0; i < moveOrder[0]; i++) {
                crates2.get(moveOrder[2]-1)               // get the correct stack to add to
                    .offerFirst(
                        tmpStack.pop() // pop the first element from the tmpStack
                    ); // moveOrder[]-1 because indexing of crates starts at 1
            }

        } else {
            // do nothing
        }
    }

    private static String getResult(Map<Integer, Deque<Character>> crates) {

        return crates.values()
            .stream()
            .map(Deque::peek)
            .map(String::valueOf)
            .collect(Collectors.joining());
    }

    public static void main(String[] args) {
        
        System.out.println("\n### Day 5: Supply Stacks ###\n");

        readInput();

        System.out.println(" > Part1: " + getResult(crates1));

        System.out.println(" > Part2: " + getResult(crates2));

        System.out.println();

        return;
    }
}
