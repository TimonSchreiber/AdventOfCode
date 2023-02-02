package adventofcode.aoc2022.Day05;

import java.util.Deque;

// use forwarding where possible
public record CrateStack(Deque<Crate> stack) {

    public Crate peek() {
        return this.stack.peek();
    }

    public boolean offerLast(Crate e) {
        return this.stack.offerLast(e);
    }
}
