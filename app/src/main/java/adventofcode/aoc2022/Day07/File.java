package adventofcode.aoc2022.Day07;

/**
 * File
 */
public record File(long size, String name) implements Entry {

    @Override
    public void print(int depth) {
        System.out.println(
            "  ".repeat(depth) + "- " + this.name
            + " (file, size=" + this.size + ")"
        );
    }

}