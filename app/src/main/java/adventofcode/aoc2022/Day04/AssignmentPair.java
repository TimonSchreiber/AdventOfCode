package adventofcode.aoc2022.Day04;

/**
 * Assignment Pair
 */
public record AssignmentPair(SectionRange range1, SectionRange range2) {

    public boolean fullyContainOneAnother() {
        return this.range1.contains(this.range2)
            || this.range2.contains(this.range1);
    }

    public boolean overlap() {
        return this.range1.overlap(this.range2);
    }

}
