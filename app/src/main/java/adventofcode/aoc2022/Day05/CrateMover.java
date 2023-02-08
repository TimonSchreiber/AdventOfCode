package adventofcode.aoc2022.Day05;

import java.util.ArrayDeque;

/**
 * Crate Mover
 */
public sealed interface CrateMover permits CrateMover9000, CrateMover9001 {

    public void offer(CrateStack crane, Crate crate);

    /**
     * Moves {@code Crate}s from one {@code CrateStack} to another depending on
     * the {@code CrateMover} implementation.
     *
     * @param crateStacks   The {@code CrateStacks} to work on
     * @param procedure     The {@code Procedure} to execute
     */
    public default void move(CrateStacks crateStacks, Procedure procedure) {

        // this CrateStack represents the Crates the crane lifts at once
        CrateStack crane = new CrateStack(new ArrayDeque<>());

        CrateStack origin = crateStacks.get(procedure.from());
        CrateStack target = crateStacks.get(procedure.to());

        for (int i = 0; i < procedure.count(); i++) {

            Crate crate = origin.pop();

            this.offer(crane, crate);
        }

        while (!crane.isEmpty()) {
            
            Crate crate = crane.pop();

            target.offerFirst(crate);
        }
    }

}
