package adventofcode.aoc2021.day11;

import java.util.ArrayList;
import java.util.List;

import adventofcode.util.geometry.Point2D;
import adventofcode.util.parser.Parser;

public class Octopodes {

    private static long stepCounter  = 0;

    private final List<Octopus> octopodes;

    public Octopodes(List<String> list) {
        this.octopodes = parse(list);
    }

    // part 1
    public long numberOfFlashesAfter(int stepLimit) {
        while (stepCounter < stepLimit) {
            this.step();
        }

        return Octopus.flashCounter;
    }

    // part 2
    public long stepsUntilSynchronisation() {
        int countBefore;

        do {
            countBefore = Octopus.flashCounter;
            this.step();
        } while (Octopus.flashCounter - countBefore != octopodes.size());

        return stepCounter;
    }

    private void step() {
        // First, the energy level of each octopus increases by 1.
        for (Octopus octopus : octopodes) {
            octopus.increaseEnergyLevel();
        }

        // Then, any octopus with an energy level greater than 9 flashes.
        for (Octopus octopus : octopodes) {
            octopus.maybeFlash(octopodes);
        }

        // Finally, any octopus that flashed during this step has its energy level set to 0.
        for (Octopus octopus : octopodes) {
            octopus.maybeReset();
        }
        stepCounter++;
    }


    private static List<Octopus> parse(List<String> list) {
        final List<Octopus> octopodes = new ArrayList<>();

        final int[][] grid = list.stream()
                .map(str -> Parser.parseToIntArray(str, ""))
                .toArray(int[][]::new);

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                octopodes.add(new Octopus(new Point2D(x, y), grid[y][x]));
            }
        }

        return octopodes;
    }
}
