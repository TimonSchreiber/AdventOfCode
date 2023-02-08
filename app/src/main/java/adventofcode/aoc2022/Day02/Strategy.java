package adventofcode.aoc2022.Day02;

/**
 * Strategy
 */
public record Strategy(int shape1, int shape2) {

    // TODO: extract at least the 3 methods at the bottom to their own class (StrategyGuide, Hand(s), ... maybe?)

    // 21 and 24 result in a win, 23 is always a draw, and 22 and 25 a loss
    public int matchPoints1() {
        return switch (this.shape2 - this.shape1) {
            case 21, 24 -> 6 + handPoints(this.shape2); // win
            case 23     -> 3 + handPoints(this.shape2); // draw
            case 22, 25 -> 0 + handPoints(this.shape2); // loss
            default     -> throw new IllegalArgumentException("Invalid result: " + Character.toString(this.shape1) + " " + Character.toString(this.shape2));
        };
    }

    // TODO: not horrible, but could be better. Try to get rid of the 'magic numbers'
    // figure out, what move player 2 has to make, to lose, draw, or win the game
    public int matchPoints2() {
        return switch (this.shape2) {
            case 'X' -> 0 + ((this.shape1 == 'A') ? handPoints(this.shape1+25) : handPoints(this.shape1+22));
            case 'Y' -> 3 + handPoints(this.shape1 + 23);
            case 'Z' -> 6 + ((this.shape1 == 'C') ? handPoints(this.shape1+21) : handPoints(this.shape1+24));
            default  -> throw new IllegalArgumentException("Invalid result: " + Character.toString(this.shape1) + " " + Character.toString(this.shape2));
        };
    }

    // this will translate 'X', 'Y', and 'Z' to 1, 2, and 3 respectively.
    // TODO: maybe change to "shape - 'Y'" but might not be clearer.
    private static int handPoints(int shape) {
        return shape - 'X' + 1;
    }

}
