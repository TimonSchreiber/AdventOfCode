package adventofcode.aoc2022.Day12;

/**
 * Height
 */
public record Height(int elevation) {

    public static Height LOW  = new Height("a".codePointAt(0));
    public static Height HIGH = new Height("z".codePointAt(0));

    /**
     * check if one can get from this Height to the next without climbing more
     * than one level upwards.
     * @param other The Height to get up to.
     * @return
     */
    public boolean reachable(Height other) {
        return (other.elevation - this.elevation) <= 1;
    }

    @Override
    public String toString() {
        return Character.toString(this.elevation);
    }
}
