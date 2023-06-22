package adventofcode.aoc2022.day13;

/**
 * Integer Value
 */
public record IntValue(int value) implements Value {

    public int compareTo(IntValue other) {
        return Integer.compare(this.value, other.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
