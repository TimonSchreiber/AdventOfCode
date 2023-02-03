package adventofcode.aoc2022.Day05;

import java.util.ArrayDeque;
import java.util.Deque;

public class CrateMover9001 implements CrateMover {

    @Override
    public void move(CrateStacks crateStacks, Procedure procedure) {

        System.out.println("\nCrateMover9001.move()");

        // the craneStack is needed to keep the order of crates in the second part
        Deque<Crate> craneStack = new ArrayDeque<>();

        CrateStack origin = crateStacks.get(procedure.from());
        CrateStack target = crateStacks.get(procedure.to());

        System.out.println("procedure size:\t" + procedure.count());

        for (int i = 0; i < procedure.count(); i++) {

            Crate crate = origin.pop();

            craneStack.offerFirst(crate);
        }

        System.out.println("craneStack size:\t" + craneStack.size());

        while (!craneStack.isEmpty()) {
            
            Crate crate = craneStack.pop();

            target.offerFirst(crate);
        }

        // for (int i = 0; i < craneStack.size(); i++) {

        //     Crate crate = craneStack.pop();

        //     target.offerFirst(crate);
        // }

        crateStacks.map().entrySet().stream().forEachOrdered(System.out::println);

    }

}
