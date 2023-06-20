package adventofcode.aoc2022.Day16;

import java.util.List;

/**
 * Room
 */
public record Room(Valve valve, List<String> tunnels) {
    public String name() {
        return this.valve.name();
    }
}
