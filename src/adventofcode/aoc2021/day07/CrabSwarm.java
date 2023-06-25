package adventofcode.aoc2021.day07;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import adventofcode.util.parser.Parser;

public class CrabSwarm {

    private final int minPos;
    private final int maxPos;
    private final List<Integer> crabs;

    public CrabSwarm(String string) {
        this.crabs   = Parser.parseToIntList(string, ",");
        this.minPos  = Collections.min(crabs);
        this.maxPos  = Collections.max(crabs);
    }

    public int minimumFuelConsumption(boolean increase) {
        return IntStream.rangeClosed(minPos, maxPos)
                .map(i -> totalFuelConsumption(i, increase))
                .min()
                .orElseThrow();
    }

    private int totalFuelConsumption(int i, boolean increase) {
        return crabs.stream()
                .mapToInt(crab -> Math.abs(crab - i))
                .map(n -> calcFuel(n, increase))
                .sum();
    }

    private int calcFuel(int n, boolean increase) {
        return increase ? n*(n+1)/2 : n;
    }
}
