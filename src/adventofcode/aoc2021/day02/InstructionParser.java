package adventofcode.aoc2021.day02;

import java.util.List;

public class InstructionParser {


    public static List<Instruction> parse(List<String> list) {
        return list.stream()
                .map(InstructionParser::parse)
                .toList();
    }

    private static Instruction parse(String string) {
        String[] arr = string.split(" ");
        return new Instruction(
            arr[0],
            Integer.parseInt(arr[1])
        );
    }
}
