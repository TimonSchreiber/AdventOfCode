package adventofcode.aoc2022.Day06;

import adventofcode.util.ReadInput;

/**
 * https://adventofcode.com/2022/day/6
 */
public class TuningTrouble {

    public static void main(String[] args) {
        System.out.println("\n### Day 6: Tuning Trouble ###\n");

        // file path as String
        final String filePath = "aoc2022/Day05/input";

        String input = ReadInput.toSingleStringFrom(filePath);

        int part1 = numberOfCharsBeforePacket(input, 4);
        System.out.println("-> Part1: " + part1);

        int part2 = numberOfCharsBeforePacket(input, 14);
        System.out.println("-> Part2: " + part2);

    }

    private static int numberOfCharsBeforePacket(String input, int markerLength) {
        
        // check if the input is large enough to contain a marker
        if (input.length() < markerLength) { return 0; }

        // check every marker sized substring if it onl y contains unique characters.
        for (int i = markerLength; i < input.length(); i++) {

            // index i not inclusive, but the number of characters at that point is equal to i
            String potentialMarker = input.substring(i-markerLength, i);

            // if all the characters are in the set, all characters are unique
            if (allUniqueCharsIn(potentialMarker)) {
                return i;
            }
        }

        // no marker was found
        return 0;
    }

    /**
     * check if every character in the String is unique.
     * @param marker
     * @return
     */
    private static boolean allUniqueCharsIn(String marker) {
        return marker.chars().distinct().count() == marker.length();
    }

}
