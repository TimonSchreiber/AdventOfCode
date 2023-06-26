package adventofcode.util.parser;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        return Parser.parseToIntStream(string, delimiter)
                .boxed()
                .toList();
    }

    /**
     * Parse a String representing an Array of separated Integer.
     * @param string    A String
     * @param delimiter A delimiter
     * @return          An Array of Integer
     */
    public static int[] parseToIntArray(String string, String delimiter) {
        return Parser.parseToIntStream(string, delimiter)
                .toArray();
    }

    /**
     * TODO: look for more places to use this method in older code
     * Parse a String into a modifiable Set of Integer (Character)
     * @param string
     * @return
     */
    public static Set<Integer> parseStringToIntSet(String string) {
        return string.chars()
                .boxed()
                .collect(Collectors.toSet());
    }

    /**
     * Parse a String representing separated Integer to an IntStream.
     * @param string    A String
     * @param delimiter A delimiter
     * @return          An IntStream
     */
    private static IntStream parseToIntStream(String string, String delimiter) {
        return Arrays.stream(string.split(delimiter))
                .mapToInt(Integer::valueOf);
    }

}
