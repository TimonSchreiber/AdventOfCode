package adventofcode.aoc2022.Day07;

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