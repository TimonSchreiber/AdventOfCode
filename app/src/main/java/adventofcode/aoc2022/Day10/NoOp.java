package adventofcode.aoc2022.day10;

/**
 * NoOp
 */
public record NoOp() implements Instruction {

    @Override
    public int getCycles() {
        return 1;
    }

}
