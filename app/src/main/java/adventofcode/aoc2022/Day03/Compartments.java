package adventofcode.aoc2022.Day03;

import java.util.Set;

public record Compartments(Set<Item> comp1, Set<Item> comp2) {

    // this method changes the contend of comp1 !!!
    public Item findCommonItem() {
        this.comp1.retainAll(this.comp2);
        return this.comp1.stream().findAny().orElseThrow();
    }

}
