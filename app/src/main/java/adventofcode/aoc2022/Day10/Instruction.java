package adventofcode.aoc2022.Day10;

/**
 * Instruction
 */
public sealed interface Instruction permits NoOp, AddX {
    int getCycles();
}
