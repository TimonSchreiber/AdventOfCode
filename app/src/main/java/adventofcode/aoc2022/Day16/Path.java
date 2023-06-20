package adventofcode.aoc2022.day16;

import java.util.List;

/**
 * Path
 */
public record Path(List<String> rooms) {

    static Path copyOf(Path path) {
        return new Path(List.copyOf(path.rooms));
    }

    boolean add(String e) {
        return this.rooms.add(e);
    }

    boolean contains(Object o) {
        return this.rooms.contains(o);
    }

    boolean isCircle(int fromIndex) {
        return !this.rooms.subList(fromIndex, this.size()).stream().anyMatch(str -> str.endsWith("_V"));
    }

    int size() {
        return this.rooms.size();
    }

    int lastIndexOf(Object o) {
        return this.rooms.lastIndexOf(o);
    }

    String getLast() {
        if (this.size() == 0) {
            return null;
        }
        return this.rooms.get(this.size()-1);
    }

    String removeLast() {
        if (this.size() == 0) {
            return null;
        }
        return this.rooms.remove(this.size()-1);
    }

}

