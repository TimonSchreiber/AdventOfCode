package adventofcode.aoc2021.day12;

public record Cave(String name, boolean isBig) {
    public Cave(String name) {
        this(name, name.equals(name.toUpperCase()));
    }
}
