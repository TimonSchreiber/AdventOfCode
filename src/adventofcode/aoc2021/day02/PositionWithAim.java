package adventofcode.aoc2021.day02;

import java.util.List;

public class PositionWithAim extends Position {

    private int aim = 0;

    public PositionWithAim(int x, int y, int aim) {
        super(x, y);
        this.aim = aim;
    }

    @Override
    public void follow(List<Instruction> instructions) {
        for (Instruction instruction : instructions) {
            switch (instruction.direction()) {
                case "forward"  -> {
                    x += instruction.step();
                    y += aim * instruction.step();
                }
                case "down"     -> aim += instruction.step();
                case "up"       -> aim -= instruction.step();
            }
        }
    }

}
