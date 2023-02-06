package adventofcode.aoc2022.Day09;

/**
 * Motion
 */
public record Motion(Direction direction, int steps) {

    // a constructor taking in an array of Strings in the format '[D] [S]' with D=Direction and S=Steps'
    public Motion(String[] arr) {
        this(
            Direction.valueOf(arr[0]),
              Integer.parseInt(arr[1])
        );
    }

    // a constructor taking in a single string in the format 'D S' with D=Direction and S=Steps'
    public Motion(String line) {
        this(line.split("[ ]"));
    }

}
