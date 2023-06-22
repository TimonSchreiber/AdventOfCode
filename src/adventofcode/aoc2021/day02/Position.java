package adventofcode.aoc2021.day02;

import java.util.List;

public class Position {

    protected int x = 0;
    protected int y = 0;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void follow(List<Instruction> instructions) {
        for (Instruction instruction : instructions) {
            switch (instruction.direction()) {
                case "forward"  -> x += instruction.step();
                case "down"     -> y += instruction.step();
                case "up"       -> y -= instruction.step();
            }
        }
    }

    public long calcSolution() {
        return 1L * x * y;
    }

}
