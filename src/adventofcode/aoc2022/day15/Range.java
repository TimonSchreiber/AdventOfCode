package adventofcode.aoc2022.day15;

import java.util.List;

/**
 * Range
 */
public record Range(int start, int end) /* implements Comparable<Range> */ {

    public Range {
        if (end < start) {
            throw new IllegalStateException("End should be greater than Start!");
        }
    }

    public Range merge(Range other) {
        int newStart = Integer.min(this.start, other.start);
        int newEnd   = Integer.max(this.end, other.end);
        return new Range(newStart, newEnd);
    }

    public boolean contains(Range other) {
        return this.start <= other.start
            && this.end   >= other.end;
    }

    public boolean overlap(Range other) {
        return  (this.start >= other.start &&  this.start <= other.end)
            ||  (this.end   >= other.start &&  this.end   <= other.end)
            || (other.start >=  this.start && other.start <=  this.end)
            || (other.end   >=  this.start && other.end   <=  this.end);
    }

    public int size() {
        return this.end - this.start + 1;
    }

    public static Range of(List<Integer> list) {
        return switch (list.size()) {
            case 1 -> new Range(list.get(0), list.get(0));
            case 2 -> new Range(list.get(0), list.get(1));
            default -> throw new IllegalStateException("Invalid number of elements!");
        };
    }

    // TODO: delete later
    public boolean contains(int i) {
        return (this.start <= i) && (i <= this.end);
    }

    // @Override
    // public int compareTo(Range other) {
    //     return (this.start != other.end)
    //             ? Integer.compare(this.start, other.start)
    //             : Integer.compare(this.size(), other.size());
    // }

}
