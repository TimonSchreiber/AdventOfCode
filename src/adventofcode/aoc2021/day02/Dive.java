package adventofcode.aoc2021.day02;

import java.util.List;

import adventofcode.util.io.ReadInput;

/**
 * https://adventofcode.com/2021/day/2
 */
public class Dive {

    public static void main(String[] args) {
        System.out.println("\n### Day 1: Dive! ###\n");

        final String filePath = "aoc2021/day02/input";

        final List<String> input = ReadInput.toListofStringsFrom(filePath);

        final List<Instruction> instructions = InstructionParser.parse(input);

        Submarine submarine = new Submarine(new Position(0, 0));
        submarine.follow(instructions);
        long part1 = submarine.calcSolution();
        System.out.println("-> Part1: " + part1);   // 2187380

        submarine = new Submarine(new PositionWithAim(0, 0, 0));
        submarine.follow(instructions);
        long part2 = submarine.calcSolution();
        System.out.println("-> Part2: " + part2);   // 2086357770
    }

}
