package adventofcode.aoc2022.day06;

/**
 * Signal
 */
public record Signal(String signal) {

    public int indexOfMarker(int markerSize) {
        // check if the input is large enough to contain a marker
        if (this.length() < markerSize) {
            return 0;
        }

        // check every marker sized substring if it onl y contains unique characters.
        for (int start = 0, end = markerSize; end < this.length(); start++, end++) {

            Marker marker = new Marker(this.substring(start, end));

            if (marker.allUniqueChars()) {
                return end;
            }
        }

        // no marker was found
        return 0;
    }

    private int length() {
        return this.signal.length();
    }

    private String substring(int beginIndex, int endIndex) {
        return this.signal.substring(beginIndex, endIndex);
    }

}
