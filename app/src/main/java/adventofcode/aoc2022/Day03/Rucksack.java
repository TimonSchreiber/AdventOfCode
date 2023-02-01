package adventofcode.aoc2022.Day03;

import java.util.Set;

public record Rucksack(Set<Item> rucksack) {

    public Item findBadge(Rucksack rucksack1, Rucksack rucksack2) {
        this.rucksack.retainAll(rucksack1.rucksack);
        this.rucksack.retainAll(rucksack2.rucksack);
        return this.rucksack.stream().findAny().orElseThrow();
    }
}
