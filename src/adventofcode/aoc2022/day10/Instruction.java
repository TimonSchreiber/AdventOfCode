package adventofcode.aoc2022.day10;

/**
 * Instruction
 */
public sealed interface Instruction permits NoOp, AddX {
    int getCycles();
}
