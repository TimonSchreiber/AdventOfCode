package adventofcode.aoc2022.Day12;

public record Height(int height) {

    public boolean isReachable(Height other) {
        return (other.height - this.height) <= 1;
    }

    @Override
    public String toString() {
        return Character.toString(this.height);
    }
}
