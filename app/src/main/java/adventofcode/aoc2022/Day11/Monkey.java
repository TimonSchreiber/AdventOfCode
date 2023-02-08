package adventofcode.aoc2022.Day11;

import java.util.Deque;
import java.util.List;
import java.util.function.LongUnaryOperator;

/**
 * Monkey
 */
public record Monkey(
    int id,
    Deque<Item> items,
    LongUnaryOperator operation,
    int testDivisor,
    int monkeyIdtrue,
    int monkeyIdFalse,
    Inspections numberOfInspections
){

    public void turn(LongUnaryOperator reliefOperation, List<Monkey> monkeys) {
        while(!this.items.isEmpty()) {
            Item item = this.items.pollFirst();
            this.inspect(item, reliefOperation, monkeys);
        }
    }

    private void inspect(Item item, LongUnaryOperator reliefOperation, List<Monkey> monkeys) {
        item = item.changeWorryLevel(this.operation);
        item = item.changeWorryLevel(reliefOperation);

        if (item.worryLevel() % this.testDivisor == 0) {
            monkeys.get(monkeyIdtrue).addItem(item);
        } else {
            monkeys.get(monkeyIdFalse).addItem(item);
        }

        this.numberOfInspections.addOne();
    }

    private void addItem(Item item) {
        this.items.addLast(item);
    }

}
