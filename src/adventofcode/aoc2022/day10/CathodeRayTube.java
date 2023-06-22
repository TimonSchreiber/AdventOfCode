package adventofcode.aoc2022.day10;

import java.util.List;

import adventofcode.util.io.ReadInput;

/**
 * https://adventofcode.com/2022/day/10
 */
public class CathodeRayTube {

    public static void main(String[] args) {

        System.out.println("\n### Day 10: Cathode-Ray Tube ###\n");

        // file path as String
        final String filePath = "resources/aoc2022/day10/input";

        final List<String> input = ReadInput.toListofStringsFrom(filePath);

        final List<Instruction> instructions = InstructionParser.parse(input);

        CPU cpu = new CPU(instructions);
        cpu.runInstructions();

        int part1 = cpu.signalStrength();
        System.out.println("-> Part1: " + part1);

        Screen screen = new Screen();
        System.out.println("-> Part2:");
        screen.drawScreen(cpu.getRegisterHistory());

    }

}
