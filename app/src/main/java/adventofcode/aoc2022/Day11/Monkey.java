package adventofcode.aoc2022.Day11;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Monkey
 */
public record Monkey(
    Deque<Integer> items,
    Function<Integer, Integer> operation,
    Predicate<Integer> test,
    Map<Boolean, Integer> throwTo
){

    private static long LIMIT =  (Long.MAX_VALUE / 10) * 9;

    public Monkey(List<String> note) {
        this(
            fillDeque(note.get(1)),
            parseFunction(note.get(2)),
            parsePredicate(note.get(3)),
            fillMap(note.get(4), note.get(5))
        );
    }

    public boolean hasItems() {
        return !this.items.isEmpty();
    }

    public ThrowItemTo inspectAndThrow(int reliefDivisor, int modulo) {
        Integer item = this.items.pollFirst();                      // get first Item
        item = this.operation.apply(item);                          // apply operation

        // rework
        if (item >= LIMIT || item <= 0) {System.out.println("Almost overflowed!");}
        if (modulo != 0) {
            item %= modulo;
        }
        item /= reliefDivisor;                                               // reduce worry level
        // until here
        boolean testResult = this.test.test(item);                  // test where to throw
        return new ThrowItemTo(item, this.throwTo.get(testResult)); // return new Throw with this item and the target monkeys index
    }

    public void recieveItem(Integer item) {
        this.items.addLast(item);
    }

    // -------------------------------------------------------------------------
    // static methods for parsing the input

    private static Deque<Integer> fillDeque(String string) {
        Pattern integerPatter = Pattern.compile("-?\\d+");  // example: Starting items: 79, 98
        Matcher matcher = integerPatter.matcher(string);

        Deque<Integer> deque = new ArrayDeque<>();

        while (matcher.find()) {
            deque.addLast(Integer.parseInt(matcher.group()));
        }

        return deque;
    }

    private static Function<Integer, Integer> parseFunction(String string) {
        String operator = Arrays.asList(string.split("[ ]")).get(4);    // example: Operation: new = old * 19
        String value = Arrays.asList(string.split("[ ]")).get(5);       //      or: Operation: new = old * old

        if ("old".equals(value)) {
            return switch (operator) {
                case "+" -> {
                    Function<Integer, Integer> func = x -> 2*x;
                    yield func;
                }
                case "*" -> {
                    Function<Integer, Integer> func = x -> (int) Math.pow(x, 2);
                    yield func;
                }
                default -> throw new IllegalArgumentException("Invalid Input " + string);
            };
        } else {
            int valueInt = Integer.parseInt(value);
            return switch (operator) {
                case "+" -> {
                    Function<Integer, Integer> func = x -> x + valueInt;
                    yield func;
                }
                case "*" -> {
                    Function<Integer, Integer> func = x -> x * valueInt;
                    yield func;
                }
                default -> throw new IllegalArgumentException("Invalid Input " + string);
            };
        }
    }

    private static Predicate<Integer> parsePredicate(String string) {
        int denominator = Integer.parseInt(Arrays.asList(string.split("[ ]")).get(3)); // example: Test: divisible by 23

        return x -> x % denominator == 0;
    }

    private static Map<Boolean, Integer> fillMap(String stringTrue, String stringFalse) {
        int monkeyTrue  = Integer.parseInt(Arrays.asList( stringTrue.split("[ ]")).get(5)); // example: If true:  throw to monkey 2
        int monkeyFalse = Integer.parseInt(Arrays.asList(stringFalse.split("[ ]")).get(5)); // example: If false: throw to monkey 3

        return Map.of(true, monkeyTrue, false, monkeyFalse);
    }

}
