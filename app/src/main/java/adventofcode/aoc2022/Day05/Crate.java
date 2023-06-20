package adventofcode.aoc2022.day05;

/**
 * Crate
 */
public record Crate(int codePoint) {

    @Override
    public String toString() {
        return Character.toString(this.codePoint);
    }

}
