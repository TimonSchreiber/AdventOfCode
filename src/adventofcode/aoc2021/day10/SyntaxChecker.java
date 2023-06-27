package adventofcode.aoc2021.day10;

import java.util.ArrayDeque;
import java.util.Deque;

public class SyntaxChecker {
    // private enum POINTS {
    //     ROUND   ('(', ')', 3),
    //     SQUARE  ('[', ']', 57),
    //     CURLY   ('{', '}', 1197),
    //     ANGLE   ('<', '>', 25137);

    //     public final char open;
    //     public final char close;
    //     public final int points;
    //     private POINTS(char open, char close, int points) {
    //         this.open = open;
    //         this.close = close;
    //         this.points = points;
    //     }
    // }

    public static int corrupted(String line) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < line.length(); i++) {
            char bracket = line.charAt(i);
            if (bracket == '(' || bracket == '[' || bracket == '{' || bracket == '<') {
                stack.addFirst(bracket);
            } else {
                char openBracket = stack.removeFirst();
                int diff = bracket - openBracket;
                if (diff == 1 || diff == 2) {
                    continue;
                } else {
                    return switch (bracket) {
                        case ')' -> 3;
                        case ']' -> 57;
                        case '}' -> 1197;
                        case '>' -> 25137;
                        default -> throw new IllegalStateException("Should not be here: " + openBracket + " and " + bracket);
                    };
                }
            }
        }

        return 0;
    }

    public static long incomplete(String line) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < line.length(); i++) {
            char bracket = line.charAt(i);
            if (bracket == '(' || bracket == '[' || bracket == '{' || bracket == '<') {
                stack.addFirst(bracket);
            } else {
                char openBracket = stack.removeFirst();
                int diff = bracket - openBracket;
                if (diff == 1 || diff == 2) {
                    continue;
                } else {
                    return 0;
                }
            }
        }

        return stack.stream()
                .mapToLong(
                    ch -> switch (ch) {
                        case '(' -> 1L;
                        case '[' -> 2L;
                        case '{' -> 3L;
                        case '<' -> 4L;
                        default -> throw new IllegalStateException("Should not be here: " + ch);
                    }
                )
                .reduce(0, (a,b) -> a*5+b);
    }

}
