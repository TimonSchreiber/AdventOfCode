package adventofcode.aoc2021.day04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class BingoParser {

    private static String DOUBLE_LINE_BREAK = "\n\n";

    public static BingoGame parse(String string) {
        List<Integer> drawnNumbers = new ArrayList<>();
        List<Board> boards = new ArrayList<>();
        String[] arr = string.split(DOUBLE_LINE_BREAK);

        for (String str : arr) {
            if (str.contains(",")) {
                // System.out.println("String has ','");
                // System.out.println(str);
                drawnNumbers = IntStream.of(parseNumbers(str, ","))
                        .boxed()
                        .toList();
            }
            else {
                // System.out.println("String has no ','");
                // System.out.println(str);
                boards.add(parseBoard(str));
            }
        }

        return new BingoGame(drawnNumbers, boards);
    }

    private static int[] parseNumbers(String string, String regex) {
        return Arrays.stream(string.split(regex))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static Board parseBoard(String string) {
        return new Board(
                string.lines()
                    .map(str -> str.strip())
                    .map(str -> parseNumbers(str, " +"))
                    .toArray(int[][]::new)
            );
    }

}
