package adventofcode.aoc2021.day02;

import java.util.List;

public class Submarine {

    private Position position;

    public Submarine(Position position) {
        this.position = position;
    }

    public void follow(List<Instruction> instructions) {
        this.position.follow(instructions);
    }

    public long calcSolution() {
        return this.position.calcSolution();
    }
}
