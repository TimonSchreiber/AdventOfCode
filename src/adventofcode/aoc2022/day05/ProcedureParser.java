package adventofcode.aoc2022.day05;

import java.util.Arrays;
import java.util.List;

/**
 * Procedure Parser
 *
 * example:
 * move 3 from 1 to 3
 */
public class ProcedureParser {

    public static List<Procedure> parse(List<String> list) {
        return list.stream().map(ProcedureParser::parse).toList();
    }

    private static Procedure parse(String string) {
        final List<Integer> procedureElements = Arrays.stream(string.split(" "))
            .filter(i -> ProcedureParser.isNumeric(i))
            .mapToInt(Integer::parseInt)
            .boxed()
            .toList();

        return new Procedure(
            procedureElements.get(0),
            procedureElements.get(1),
            procedureElements.get(2)
        );
    }

    private static boolean isNumeric(String string) {
        // this matches any string that start with a '+' or '-' (optional)
        // followed by one or more digits
        return string.matches("^[-]?\\d+$");
    }

}
