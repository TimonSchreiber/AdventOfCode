package adventofcode.aoc2022.day07;

/**
 * File System
 * Only for helping the FileSystemParser
 */
public class FileSystem {
    Directory root = new Directory("/", null);
    Directory current = root;

    void add(Entry entry) {
        this.current.add(entry);
    }

    void cdRoot() {
        this.current = this.root;
    }

    void cdDown(String name) {
        this.current = this.current.findDirectory(name).orElseThrow();
    }

    void cdUp() {
        if (this.root.equals(this.current)) { return; }
        this.current = this.current.parent();
    }

}