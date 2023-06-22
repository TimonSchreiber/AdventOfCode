package adventofcode.aoc2022.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * CPU
 */
public class CPU {
    private int register = 1;

    private List<Instruction> instructions;
    private List<Integer> registerHistory = new ArrayList<>();

    public CPU(List<Instruction> instructions) {
        this.instructions = List.copyOf(instructions);
    }

    public List<Integer> getRegisterHistory() {
        return List.copyOf(this.registerHistory);
    }

    public void runInstructions() {
        this.instructions.stream()
            .forEachOrdered(
                this::processInstruction
            );
    }

    private void processInstruction(Instruction instruction) {
        for (int i = 0; i < instruction.getCycles(); i++) {
            this.registerHistory.add(this.register);
        }
        if (instruction instanceof AddX addX) {
            this.register += addX.value();
        }
    }

    public int signalStrength() {
        return IntStream.range(0, this.registerHistory.size())
            .filter(cycle -> (cycle == 20) || ((cycle - 20) % 40 == 0))
            .map(cycle -> cycle*this.registerHistory.get(cycle-1)) // clycle starts at 1 not at 0 so cycle correspond to the index cycle-1
            // .peek(System.out::println)
            .sum();
    }

}
