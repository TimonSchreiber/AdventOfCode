package adventofcode.aoc2022.Day16;

import java.util.ArrayList;

public class DFS2 {

    private final Path path = new Path(new ArrayList<>());
    private final int timeLimit;

    public DFS2(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int findBestPath() {

        boolean checkedEverything = false;

        while (checkedEverything) {

            String nextStep;

            if (this.path.size() >= this.timeLimit) {
                // calcutalte the maxPressure

            } else if ((nextStep = this.nextStep()) != null) {
                this.path.add(nextStep);
                continue;
            }

            String removedStep = this.path.removeLast();

            if (removedStep == null) {
                checkedEverything = true;
            }

        }

        return -1;
    }

    private String nextStep() {

        // open Valve


        // next Room

        return "";
    }


}
