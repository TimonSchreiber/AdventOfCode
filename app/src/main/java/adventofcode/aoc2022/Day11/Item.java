package adventofcode.aoc2022.Day11;

import java.util.function.LongUnaryOperator;

/**
 * Item
 */
public record Item(long worryLevel) {

    public Item changeWorryLevel(LongUnaryOperator operation) {
        return new Item(operation.applyAsLong(worryLevel));
    }

}
