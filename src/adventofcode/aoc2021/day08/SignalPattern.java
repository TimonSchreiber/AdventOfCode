package adventofcode.aoc2021.day08;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import adventofcode.util.parser.Parser;

public class SignalPattern {

    private final List<String> input;
    private final List<String> output;

    public SignalPattern(String string) {
        // escape the '|' (Pipe) character as it has a special meaning in regex
        String[] arr = string.split(" \\| ");

        this.input = SignalPattern.parseString(arr[0]);
        this.output = SignalPattern.parseString(arr[1]);
    }

    public int decodeOutput() {
        final List<String> digitStringByIndex = this.decodeInput();

        return Integer.parseInt(
            this.output.stream()
                .map(digitStringByIndex::indexOf)
                .map(String::valueOf)
                .collect(Collectors.joining())
        );
    }

    private List<String> decodeInput() {
        // useing an Array for stroing Strings at specific indices
        String[] digit = new String[10];

        // known digits with unique size
        digit[1] = findDigit(2).get(0);
        digit[4] = findDigit(4).get(0);
        digit[7] = findDigit(3).get(0);
        digit[8] = findDigit(7).get(0);

        // digit groups of same size
        List<String> twoThreeFive = findDigit(5);
        List<String> zeroSixNine  = findDigit(6);

        // '3' is the only digit out of '2,3,5' which contains the digit '1'
        digit[3] = filterSpecificString(twoThreeFive, str -> contains(str, digit[1]));

        // '9' is the only digit out of '0,6,9' which contains the digit '4'
        digit[9] = filterSpecificString(zeroSixNine, str -> contains(str, digit[4]));

        // '5' is the only digit out of '2,3,5' which is contained in the digit '9' and is not '3'
        digit[5] = filterSpecificString(twoThreeFive, str -> contains(digit[9], str) && !str.equals(digit[3]));

        // '6' is the only digit out of '0,6,9' which contains the digit '5' and is not '9'
        digit[6] = filterSpecificString(zeroSixNine, str -> contains(str, digit[5]) && !str.equals(digit[9]));

        // '2' is the only digit out of '2,3,5' which is left over
        digit[2] = filterSpecificString(twoThreeFive, str -> !str.equals(digit[3]) && !str.equals(digit[5]));

        // '0' is the only digit out of '0,6,9' which is left over
        digit[0] = filterSpecificString(zeroSixNine, str -> !str.equals(digit[6]) && !str.equals(digit[9]));


        // wrap in a List so finding the digit to a given String is easy (List#indexOf())
        return List.of(digit);
    }

    private List<String> findDigit(int size) {
        return input.stream()
                .filter(str -> str.length() == size)
                .toList();
    }

    private boolean contains(String str1, String str2) {
        Set<Integer> set1 = Parser.parseStringToIntSet(str1);
        Set<Integer> set2 = Parser.parseStringToIntSet(str2);
        return set1.containsAll(set2);
    }

    private String filterSpecificString(List<String> strings, Predicate<? super String> predicate) {
        return strings.stream()
                .filter(predicate)
                .findAny()
                .orElseThrow();
    }

    private static List<String> parseString(String string) {
        return Arrays.stream(string.split(" "))
                .map(SignalPattern::sort)
                .toList();
    }

    private static String sort(String string) {
        return string.chars()
                .sorted()
                .mapToObj(Character::toString)
                .collect(Collectors.joining());
    }

}
