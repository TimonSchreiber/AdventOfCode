package adventofcode.aoc2022.Day11;

/**
 * Inspections
 */
public class Inspections {
    private long inspections = 0;

    public void addOne() {
        this.inspections++;
    }

    public long get() {
        return this.inspections;
    }
}
