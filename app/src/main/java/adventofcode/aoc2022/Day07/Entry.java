package adventofcode.aoc2022.Day07;

/**
 * Entry
 */
public sealed interface Entry permits Directory, File {

    public long size();

    public void print(int depth);

}
