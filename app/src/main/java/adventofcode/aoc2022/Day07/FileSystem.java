package adventofcode.aoc2022.Day07;

public class FileSystem {
    Directory root = new Directory("/");
    Directory current = root;

    void addDirectory(String name) {
        this.current.subDirectories.putIfAbsent(name, new Directory(name, this.current));
    }

    void addFile(File file) {
        this.current.files.add(file);
    }

    void cdRoot() {
        this.current = this.root;
    }

    void cdDown(String name) {
        this.current = this.current.subDirectories.get(name);
    }

    void cdUp() {
        this.current = this.current.parent;
    }

    void print() {
        this.root.print(0);
    }

}