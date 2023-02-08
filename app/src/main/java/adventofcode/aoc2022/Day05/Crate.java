package adventofcode.aoc2022.Day05;

/**
 * Crate
 */
public record Crate(int codePoint) {

    @Override
    public String toString() {
        return Character.toString(this.codePoint);
    }

}
