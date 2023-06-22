package adventofcode.aoc2022.day10;

import java.util.List;

/**
 * Instruction Parser
 */
public class InstructionParser {

    private static final String NOOP = "noop";
    private static final String ADDX = "addx";

    public static List<Instruction> parse(List<String> input) {
        return input.stream()
            .map(InstructionParser::parse)
            .toList();
    }

    private static Instruction parse(String line) {
        String[] arr = line.split(" "); // split at every space

        return switch (arr[0]) {
            case NOOP -> new NoOp();
            case ADDX -> new AddX(Integer.parseInt(arr[1]));
            default     -> throw new IllegalArgumentException("Invalid Instruction " + line);
        };

    }

}
