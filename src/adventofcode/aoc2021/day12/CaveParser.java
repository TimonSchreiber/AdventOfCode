package adventofcode.aoc2021.day12;

import java.util.List;

public class CaveParser {

    public static CaveSystem parse(List<String> list) {
        final CaveSystem system = new CaveSystem();

        list.forEach(str -> fill(str, system));

        return system;
    }

    private static void fill(String name, CaveSystem system) {
        String[] arr = name.split("-");
        system.addCave(arr[0]);
        system.addCave(arr[1]);
        system.addConnection(arr[0], arr[1]);
    }

}
