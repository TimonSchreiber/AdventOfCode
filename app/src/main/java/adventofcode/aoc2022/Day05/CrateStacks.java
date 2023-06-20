package adventofcode.aoc2022.day05;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Crate Stacks
 */
public record CrateStacks(Map<Integer, CrateStack> map) {

    /**
     * Return a {@codee String} created by getting the {@code Crate} at the Top
     * of each {@code CrateStack}.
     * @return  A {@code String} representation of the Top of each
     *          {@code CrateStack}
     */
    public String getTopCrates() {
        return this.map.values()
                .stream()
                .map(CrateStack::peek)
                .map(Crate::toString)
                .collect(Collectors.joining());
    }

    // Forwarding
    public CrateStack putIfAbsent(Integer key, CrateStack value ) {
        return this.map.putIfAbsent(key, value);
    }

    // Forwarding
    public CrateStack get(Object key) {
        return this.map.get(key);
    }

}
