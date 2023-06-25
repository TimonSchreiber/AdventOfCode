package adventofcode.util.parser;

import java.util.Arrays;
import java.util.List;

/**
 * Class for common parsing operations.
 *
 * TODO: look for more classes where these parsing methods can be used instead.
 */
public class Parser {

    /**
     * Parse a String representing a List of separated Integer.
     * @param string    A String
     * @param delimiter A delimiter
     * @return          A List of Integer
     */
    public static List<Integer> parseToIntList(String string, String delimiter) {
        return Arrays.stream(string.split(delimiter))
                .map(Integer::valueOf)
                .toList();
    }

    /**
     * Parse a String representing an Array of separated Integer.
     * @param string    A String
     * @param delimiter A delimiter
     * @return          An Array of Integer
     */
    public static int[] parseToIntArray(String string, String delimiter) {
        return Arrays.stream(string.split(delimiter))
                .mapToInt(Integer::valueOf)
                .toArray();
    }

}
