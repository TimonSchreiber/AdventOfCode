package adventofcode.aoc2022.Day05;

/**
 * The CrateMover9001 keeps the order in which the crates are stacked.
 * Moving 3 Crates form Stack 1 to 3 means:
 * 
 *                       [D]
 * [D]                   [N]
 * [N] [C]           [C] [Z]
 * [Z] [M] [P]       [M] [P]
 *  1   2   3     1   2   3
 */
public final class CrateMover9001 implements CrateMover {

    public void offer(CrateStack crane, Crate crate) {
        crane.offerFirst(crate);
    }

}
