package adventofcode.aoc2022.Day07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Directory {
    String name;
    Directory parent;
    List<File> files = new ArrayList<>();
    Map<String, Directory> subDirectories = new HashMap<>();

    Directory(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
    }

    Directory(String name) {
        this(name, null);
    }

    long totalSize() {
        return
            this.files.stream()
                .mapToLong(File::size)
                .sum() +
            this.subDirectories.values().stream()
                .mapToLong(Directory::totalSize)
                .sum();
    }

    /**
     * Sum up the sizes of all directories with a total size not bigger than maxSize.
     * @param maxSize   maximum allowed size
     * @return
     */
    long sumOfDirectoriesWithSizeBelow(long maxSize) {
        long size = this.totalSize();
        return
            this.subDirectories.values().stream()
                .mapToLong(dir -> dir.sumOfDirectoriesWithSizeBelow(maxSize))
                .sum() +
            (size <= maxSize ? size : 0L);
    }

    long sizeOfSmallestDirectorieWithSizeBiggerThan(long requiredSpace) {
        long size = this.totalSize();
        long smallestSubDirectory = this.subDirectories.values().stream()
            .mapToLong(dir -> dir.sizeOfSmallestDirectorieWithSizeBiggerThan(requiredSpace))
            .filter(l -> l >= requiredSpace)
            .min()
            .orElse(Long.MAX_VALUE);

        if (size >= requiredSpace) {
            return size < smallestSubDirectory ? size : smallestSubDirectory;
        } else {
            return smallestSubDirectory;
        }
    }

    void print(int depth) {
        System.out.println("  ".repeat(depth) + "- " + name + " (dir)");
        this.files.forEach(file -> file.print(depth+1));
        this.subDirectories.values().stream().forEachOrdered(dir -> dir.print(depth+1));
    }
}
