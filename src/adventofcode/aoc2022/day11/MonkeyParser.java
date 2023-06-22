package adventofcode.aoc2022.day11;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.function.LongUnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Monkey Parser
 */
public class MonkeyParser {

    private static final String DOUBLE_LINEBREAK = "\n\n";
    public static final Pattern MONKEY_PATTERN =
        Pattern.compile("""
                Monkey (\\d+):\\s+\
                Starting items: (.+)\\s+\
                Operation: new = old (.+)\\s+\
                Test: divisible by (\\d+)\\s+\
                If true: throw to monkey (.+)\\s+\
                If false: throw to monkey (.+)\
                """
        );

    public static List<Monkey> parse(String input) {
        return Arrays.stream(input.split(DOUBLE_LINEBREAK))
                .map(MonkeyParser::parseMonkey)
                .toList();
    }

    private static Monkey parseMonkey(String string) {
        Matcher matcher = MONKEY_PATTERN.matcher(string);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid input:\n" + string);
        }

        int id = Integer.parseInt(matcher.group(1));
        List<Item> startingItems = parseItems(matcher.group(2));
        LongUnaryOperator operation = parseOperation(matcher.group(3));
        int divisor = Integer.parseInt(matcher.group(4));
        int monkeyIdTrue = Integer.parseInt(matcher.group(5));
        int monkeyIdFalse = Integer.parseInt(matcher.group(6));

        return new Monkey(
            id,
            new ArrayDeque<>(startingItems),
            operation,
            divisor,
            monkeyIdTrue,
            monkeyIdFalse,
            new Inspections()
        );
    }

    private static List<Item> parseItems(String string) {
        return Arrays.stream(string.split(", "))
                .mapToLong(Long::parseLong)
                .mapToObj(Item::new)
                .toList();
    }

    private static LongUnaryOperator parseOperation(String string) {
        String[] arr = string.split(" ");

        if ("* old".equals(string)) {
            return old -> old * old;
        } else if ("*".equals(arr[0])) {
            return old -> old * Integer.parseInt(arr[1]);
        } else if ("+".equals(arr[0])) {
            return old -> old + Integer.parseInt(arr[1]);
        } else {
            throw new IllegalArgumentException("Invalid Operation: " + string);
        }
    }

}
