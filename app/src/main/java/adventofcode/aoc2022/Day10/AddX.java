package adventofcode.aoc2022.day10;

/**
 * AddX
 */
public record AddX(int value) implements Instruction {

    @Override
    public int getCycles() {
        return 2;
    }

}
