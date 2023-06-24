package adventofcode.aoc2021.day05;

public record Point(int x, int y) {

    @Override
    public String toString() {
        return x + "," + y;
    }
}
