package adventofcode.aoc2022.Day08;

import java.util.List;

public record TreeRow(List<Tree> treeRow) {

    public int size() {
        return this.treeRow.size();
    }
    
    public Tree get(int index) {
        return this.treeRow.get(index);
    }

    public Tree set(int index, Tree element) {
        return this.treeRow.set(index, element);
    }

    public void print() {
        this.treeRow.stream()
                .map(Tree::height)
                .forEachOrdered(System.out::print);
        System.out.println();
    }
}
