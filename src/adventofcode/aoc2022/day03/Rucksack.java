package adventofcode.aoc2022.day03;

import java.util.Set;

/**
 * Rucksack
 */
public record Rucksack(Set<Item> rucksack) {

    /**
     * Find one common {@code Item} between all three {@code Rucksack}s by
     * removing every {@code Item} from this {@code Rucksack} that is not in
     * either of the other two {@code Ruckack}s.
     *
     * @param rucksack1 {@code Rucksack} 1
     * @param rucksack2 {@code Rucksack} 2
     * @return          One {@code Item} thta is common between all three
     *                  {@code Rucksacks}
     * @throws          NoSuckElementException - if no common {@code Item} is
     *                  present
     */
    public Item findBadge(Rucksack rucksack1, Rucksack rucksack2) {
        this.rucksack.retainAll(rucksack1.rucksack);
        this.rucksack.retainAll(rucksack2.rucksack);

        return this.rucksack.stream().findAny().orElseThrow();
    }

}
