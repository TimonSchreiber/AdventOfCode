package adventofcode.aoc2022.Day05;

import java.util.ArrayDeque;

public sealed interface CrateMover permits CrateMover9000, CrateMover9001 {

    public void offer(CrateStack crane, Crate crate);

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
