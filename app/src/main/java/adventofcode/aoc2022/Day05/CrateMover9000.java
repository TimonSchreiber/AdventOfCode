package adventofcode.aoc2022.Day05;

public class CrateMover9000 implements CrateMover {

    @Override
    public void move(CrateStacks crateStacks, Procedure procedure) {

        CrateStack origin = crateStacks.get(procedure.from());
        CrateStack target = crateStacks.get(procedure.to());

        for (int i = 0; i < procedure.count(); i++) {

            Crate crate = origin.pop();

            target.offerFirst(crate);
        }

    }

}
