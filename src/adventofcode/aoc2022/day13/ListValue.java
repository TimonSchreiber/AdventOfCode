package adventofcode.aoc2022.day13;

import java.util.List;
import java.util.stream.Collectors;

/**
 * List Value
 */
public record ListValue(List<Value> values) implements Value, Comparable<ListValue> {

    @Override
    public int compareTo(ListValue other) {
        int leftSize  = this.values.size();
        int rightSize = other.values.size();
        int minSize   = Integer.min(leftSize, rightSize);
        int index     = 0;
        int result    = 0;

        // increment 'index' as long as it is smaller than minSize and both
        // lists index Value is not decisive.
        while ((index < minSize)
            && ((result = this.values.get(index).compareTo(other.values.get(index))) == 0)) {
            index++;
        }

        // check if the while loop ended because the index got too big
        if ((index >= minSize) && (result == 0)) {
            // if left list is smaller than right list, return a negative number and vice versa
            // this means left is smaller than right
            return leftSize - rightSize;
        } else {
            // compare the Values of each ListValue at index
            return result;
        }

    }

    /**
     * Returns an unmodifiable {@code ListValue} containing an arbitrary number
     * of {@code Values}.
     * @param values    the {@code Values} to be contained in this
     *                  {@code ListValue}
     * @return          a {@code ListValue} containing the specified
     *                  {@code Values}
     */
    public static ListValue of(Value... values) {
        return new ListValue(List.of(values));
    }

    @Override
    public String toString() {
        return this.values.stream().map(Value::toString).collect(Collectors.joining(",", "[", "]"));
    }

}
