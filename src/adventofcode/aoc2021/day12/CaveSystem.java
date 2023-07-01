package adventofcode.aoc2021.day12;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CaveSystem {

    private final Map<Cave, List<Cave>> adjCaves = new HashMap<>();
    public final List<String> paths = new ArrayList<>();

    public void addCave(String name) {
        Cave cave = new Cave(name);
        adjCaves.putIfAbsent(cave, new ArrayList<>());
    }

    public void addConnection(String name1, String name2) {
        Cave cave1 = new Cave(name1);
        Cave cave2 = new Cave(name2);
        adjCaves.get(cave1).add(cave2);
        adjCaves.get(cave2).add(cave1);
    }

    public List<Cave> getAdjCaves(String name) {
        return adjCaves.get(new Cave(name));
    }

    // TODO: not needed
    public Set<String> depthFirstTraversal(String start) {
        Set<String> visited = new LinkedHashSet<>();
        Deque<String> stack = new ArrayDeque<>();

        stack.addFirst(start);

        while (!stack.isEmpty()) {
            String name = stack.removeFirst();
            if (!visited.contains(name)) {
                visited.add(name);
                for (Cave cave : this.getAdjCaves(name)) {
                    stack.addFirst(cave.name());
                }
            }
        }
        return visited;
    }

    // TODO: not needed
    public Set<String> breadthFirstTraversal(String start) {
        Set<String> visited = new LinkedHashSet<>();
        Deque<String> queue = new ArrayDeque<>();

        queue.addLast(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String name = queue.pollFirst();
            for (Cave cave : this.getAdjCaves(name)) {
                if (!visited.contains(cave.name())) {
                    visited.add(cave.name());
                    queue.addLast(cave.name());
                }
            }
        }
        return visited;
    }

    public void findAllPaths(String start, String end, boolean allowExtraVisit) {
        List<String> caveList = new ArrayList<>();

        caveList.add("start");

        findAllPathsHelper(start, end, caveList, allowExtraVisit);
    }

    private void findAllPathsHelper(String start, String end, List<String> caveList, boolean allowExtraVisit) {
        if (start.equals(end)) {
            this.paths.add(caveList.toString());
            return;
        }

        for (Cave cave : this.getAdjCaves(start)) {
            if (!caveList.contains(cave.name()) || cave.isBig() || (allowExtraVisit && noExtraVisitToSmallCaveYet(cave.name(), caveList))) {
                caveList.add(cave.name());

                findAllPathsHelper(cave.name(), end,  caveList, allowExtraVisit);

                caveList.remove(cave.name());
            }
        }
    }

    private boolean noExtraVisitToSmallCaveYet(String name, List<String> caveList) {
        // the 'start' and 'end' cave can not be visited twice
        if ("start".equals(name) || "end".equals(name)) {
            return false;
        }

        int listSize = caveList.stream()
                .filter(str -> str.equals(str.toLowerCase()))
                .toList().size();
        int setSize  = caveList.stream()
                .filter(str -> str.equals(str.toLowerCase()))
                .collect(Collectors.toSet()).size();

        return listSize == setSize;
    }
}
