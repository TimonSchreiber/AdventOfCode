package adventofcode.aoc2022.Day04;

/**
 * Section Range
 */
public record SectionRange(int start, int end) {

    public boolean contains(SectionRange other) {
        return this.start <= other.start
            && this.end   >= other.end;
    }

    public boolean overlap(SectionRange other) {
        return  (this.start >= other.start &&  this.start <= other.end)
            ||  (this.end   >= other.start &&  this.end   <= other.end)
            || (other.start >=  this.start && other.start <=  this.end)
            || (other.end   >=  this.start && other.end   <=  this.end);
    }

}
