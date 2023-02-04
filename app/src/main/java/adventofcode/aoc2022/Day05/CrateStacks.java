package adventofcode.aoc2022.Day05;

import java.util.Map;
import java.util.stream.Collectors;

public record CrateStacks(Map<Integer, CrateStack> map) {

    public String getTopCrates() {
        return this.map.values()
                .stream()
                .map(CrateStack::peek)
                .map(Crate::toString)
                .collect(Collectors.joining());
    }

    public CrateStack putIfAbsent(Integer key, CrateStack value ) {
        return this.map.putIfAbsent(key, value);
    }

    public CrateStack get(Object key) {
        return this.map.get(key);
    }

}
