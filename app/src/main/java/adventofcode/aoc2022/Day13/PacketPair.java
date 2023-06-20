package adventofcode.aoc2022.day13;

import java.util.List;

public record PacketPair(ListValue left, ListValue right) {

    public PacketPair(List<ListValue> listValues) {
        this(listValues.get(0), listValues.get(1));
    }

    public boolean checkOrder() {
        return this.left.compareTo(this.right) < 0;
    }

    @Override
    public String toString() {
        return this.left.toString() + "\n" + this.right.toString() + "\n";
    }

}
