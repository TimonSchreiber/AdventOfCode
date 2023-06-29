package adventofcode.aoc2021.day11;

import java.util.List;

import adventofcode.util.geometry.Point2D;

public class Octopus {

    public static int flashCounter = 0;

    private static final int MAX_ENERGY_LEVEL = 9;
    private static final int ENERGY_DEPLETED  = 0;

    /* private */ boolean hasFlashed;
    /* private */ int energyLevel;
    /* private */ Point2D position;

    public Octopus(Point2D position, int energyLevel) {
        this.position = position;
        this.energyLevel = energyLevel;
    }

    public void increaseEnergyLevel() {
        this.energyLevel++;
    }

    public void maybeFlash(List<Octopus> octopodes) {
        if (this.hasFlashed) { return; }
        if (this.energyLevel > MAX_ENERGY_LEVEL) {
            this.hasFlashed = true;


            for (Octopus o : octopodes) {
                if ((Math.abs(o.position.x() - this.position.x()) <= 1)
                 && (Math.abs(o.position.y() - this.position.y()) <= 1)
                 && !o.position.equals(this.position)) {
                    o.increaseEnergyLevel();
                    o.maybeFlash(octopodes);
                }
            }
        }
    }

    public void maybeReset() {
        if (this.hasFlashed) {
            this.hasFlashed = false;
            this.energyLevel = ENERGY_DEPLETED;
            Octopus.flashCounter++;
        }
    }

}
