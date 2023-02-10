package adventofcode.aoc2022.Day13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Packet Pair Parser
 */
public class PacketPairParser {

    private static final String DOUBLE_LINEBREAK = "\n\n";

    public static List<PacketPair> parse(String string) {
        return Arrays.stream(string.split(DOUBLE_LINEBREAK))
                .map(PacketPairParser::parsePacketPair)
                .toList();
    }

    private static PacketPair parsePacketPair(String string) {
        return string.lines()
                .map(PacketPairParser::parseListValue)
                .collect(
                    Collectors.collectingAndThen(
                        Collectors.toList(),
                        PacketPair::new
                    )
                );
    }

    private static ListValue parseListValue(String string) {

        // empty list
        if (string.length() <= 2) { return ListValue.of(); }

        // List for accumulating values
        List<Value> values = new ArrayList<>();

        // end of List index
        int closingBracket = indexOfClosingBracket(string);

        // iterate over every char between the pair of Brackets initializing
        // this List
        for (int i = 1; i < closingBracket; i++) {

            // char at index
            char ch = string.charAt(i);

            // if the char is a digit, safe the index and increment i until the
            // char is no longer a digit (, or []). Parse the subString between
            // start and i to an IntValue
            if (Character.isDigit(ch)) {
                int start = i;

                while (Character.isDigit(string.charAt(++i)));

                String intValueStr = string.substring(start, i);
                IntValue intValue = parseIntValue(intValueStr);
                values.add(intValue);
            }
            // if the char is an opening bracket take the subString starting
            // from i and parse it to a ListValue. at the end jump to the index
            // of the matching closing bracket.
            else if (ch == '[') {
                String listValueStr = string.substring(i);
                ListValue listValue = parseListValue(listValueStr);
                values.add(listValue);
                i += indexOfClosingBracket(listValueStr);
            }

        }

        return new ListValue(values);
    }

    // parse an IntValue
    private static IntValue parseIntValue(String string) {
        return new IntValue(Integer.parseInt(string));
    }

    // return the index of the matching closing bracket when this string starts
    // with an open bracket 
    private static int indexOfClosingBracket(String string) {
        // assume string starts with a '[', otherwise this methid shouidl not be called!
        int bracketCounter = 0;
        int index = 0;
        do {
            if (index >= string.length()) {
                System.err.println("No closing bracket found in String: " + string);
                return 0;
            }
            char ch = string.charAt(index++);
            if (ch == '[') { bracketCounter++; }
            if (ch == ']') { bracketCounter--; }
        } while (bracketCounter != 0);

        return index-1;
    }

}
