package adventofcode.aoc2022.Day07;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record Directory(
    String name,
    Directory parent,
    List<Entry> entries
) implements Entry {

    public Directory(String name, Directory parent) {
        this(name, parent, new ArrayList<>());
    }

    public long size() {
        return this.entries.stream().mapToLong(Entry::size).sum();
    }

    public boolean add(Entry entry) {
        if (entry instanceof Directory dir && !this.equals(dir.parent)) {
            System.out.println("New Directory is not a child of this directory!");
            return false;
        }
        return this.entries.add(entry);
    }

    public Optional<Directory> findDirectory(String name) {
        return this.subDirectories().stream()
                .filter(dir -> name.equals(dir.name))
                .findAny();
    }

    private List<Directory> subDirectories() {
        return this.entries.stream()
                .filter(Directory.class::isInstance)
                .map(Directory.class::cast)
                .toList();
    }

    /** TODO: make this a 'one' line method
     * Sum up the sizes of all directories with a total size not bigger than
     * maxSize.
     * @param maxSize   maximum allowed size
     * @return          the sum of all subdirectories with a size smaller than
     *                  maxSize.
     */
    public long sumOfDirectoriesWithSizeBelow(long maxSize) {
        long size = this.size();
        size = (size <= maxSize ? size : 0L);

        return this.subDirectories().stream()
                .mapToLong(dir -> dir.sumOfDirectoriesWithSizeBelow(maxSize))
                .sum()
            + size;
    }

    /** TODO: make this a 'one' line method
     * Size of smallest sub directory with a size biggern than {@code requiredSize}.
     * @param requiredSpace minimum required size
     * @return              the smallest sub directory bigger than requiredSize.
     */
    public long sizeOfSmallestDirectorieWithSizeBiggerThan(long requiredSpace) {
        if (requiredSpace <= 0) { return 0; }

        long size = this.size();
        long smallestSubDirectory = this.subDirectories().stream()
            .mapToLong(dir -> dir.sizeOfSmallestDirectorieWithSizeBiggerThan(requiredSpace))
            .filter(l -> l >= requiredSpace)
            .min()
            .orElse(Long.MAX_VALUE);

        if ((size >= requiredSpace) && (size < smallestSubDirectory)) {
            return size;
        } else {
            return smallestSubDirectory;
        }
    }

    @Override
    public void print(int depth) {
        System.out.println("  ".repeat(depth) + "- " + this.name + " (dir)");
        this.entries.stream().forEachOrdered(e -> e.print(depth+1));
    }

}
