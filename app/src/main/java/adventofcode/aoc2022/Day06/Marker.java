package adventofcode.aoc2022.Day06;

public record Marker(String marker) {
    
    /**
     * Check if every character in the Marker is unique.
     * 
     * @return  {@code true} if the number of unique chars in this Marker is
     *          equal to its length, {@code false} otherwise.
     */
    public boolean allUniqueChars() {
        return this.unique() == this.size();
    }

    /** the size (length) of this {@code Marker}. */
    private int size() {
        return this.marker.length();
    }

    /** Nu,ber of unique chars in this {@code Marker}. */
    private long unique() {
        return this.marker.codePoints().distinct().count();
    }

}
