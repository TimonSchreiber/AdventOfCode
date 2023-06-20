package adventofcode.aoc2022.day03;

/**
 * Item
 */
public record Item(int codePoint) {

    public int priorityValue() {
        return this.codePoint
                + (Character.isLowerCase(this.codePoint)
                    ?  1 - 'a'
                    : 27 - 'A');
    }

}
