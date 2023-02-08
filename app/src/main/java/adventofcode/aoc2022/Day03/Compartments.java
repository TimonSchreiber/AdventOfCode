package adventofcode.aoc2022.Day03;

import java.util.Set;

/**
 * Compartments
 */
public record Compartments(Set<Item> comp1, Set<Item> comp2) {

    /**
     * get the common {@code Item} in this {@code Compartment} by removing
     * every {@code Item} in the first {@code Set} that is not also in the
     * second {@code Set}.
     *
     * @return  One common {@code Item} in this {@code Compartment}
     * @throws  NoSuchElementException - if no common {@code Item} is present
     */
    public Item findCommonItem() {
        this.comp1.retainAll(this.comp2);
        return this.comp1.stream().findAny().orElseThrow();
    }

}
