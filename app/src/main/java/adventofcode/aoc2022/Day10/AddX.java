package adventofcode.aoc2022.Day10;

/**
 * AddX
 */
public record AddX(int value) implements Instruction {

    @Override
    public int getCycles() {
        return 2;
    }

}
