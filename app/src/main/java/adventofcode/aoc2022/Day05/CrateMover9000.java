package adventofcode.aoc2022.Day05;

/**
 * The CrateMover9000 reverses the order in which the crates are stacked.
 * Moving 3 Crates form Stack 1 to 3 means:
 * 
 *                       [Z]
 * [D]                   [N]
 * [N] [C]           [C] [D]
 * [Z] [M] [P]       [M] [P]
 *  1   2   3     1   2   3
 */
public final class CrateMover9000 implements CrateMover {

    @Override
    public void offer(CrateStack crane, Crate crate) {
        crane.offerLast(crate);
    }

}
