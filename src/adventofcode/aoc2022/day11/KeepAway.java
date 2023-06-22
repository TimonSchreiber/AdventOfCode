package adventofcode.aoc2022.day11;

import java.util.Comparator;
import java.util.List;
import java.util.function.LongUnaryOperator;

/**
 * Keep Away
 */
public class KeepAway {
    private List<Monkey> monkeys;
    private LongUnaryOperator reliefOperation;

    public KeepAway(List<Monkey> monkeys, LongUnaryOperator reliefOperation) {
        this.monkeys = monkeys;
        this.reliefOperation = reliefOperation;
    }

    public void play(int numberOfRounds) {
        for (int i = 0; i < numberOfRounds; i++) {
            this.playRound();
        }
    }

    private void playRound() {
        for (Monkey monkey : this.monkeys) {
            monkey.turn(reliefOperation, this.monkeys);
        }
    }

    public long twoMostActiveMonkeys() {
        return this.monkeys.stream()
            .map(Monkey::numberOfInspections)
            .mapToLong(Inspections::get)
            .boxed()
            .sorted(Comparator.reverseOrder())
            .limit(2)
            .reduce(1L, (a,b) -> a*b);
    }
}
