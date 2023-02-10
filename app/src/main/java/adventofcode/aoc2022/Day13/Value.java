package adventofcode.aoc2022.Day13;

/**
 * Value
 */
public sealed interface Value permits IntValue, ListValue {

    public default int compareTo(Value other) {

        if (this instanceof IntValue left && other instanceof IntValue right) {
            return left.compareTo(right);
        }

        if (this instanceof IntValue left && other instanceof ListValue right) {
            ListValue lv = ListValue.of(left);
            return lv.compareTo(right);
        }

        if (this instanceof ListValue left && other instanceof ListValue right) {
            return left.compareTo(right);
        }

        if (this instanceof ListValue left && other instanceof IntValue right) {
            ListValue lv = ListValue.of(right);
            return left.compareTo(lv);
        }

        // if (value instanceof IntValue intValue) {
        //     System.out.println(" -> this instanceof: " + this.getClass());
        //     System.out.println(" -> value instanceof IntValue: " + intValue);
        //     IntValue left = (IntValue) this;
        //     // IntValue right = (IntValue) value;
        //     return left.compareTo(intValue);
        //     // return this.compareTo(ListValue.of(intValue));
        // }

        // if (value instanceof ListValue listValue) {
        //     System.out.println(" -> this instanceof: " + this.getClass());
        //     System.out.println(" -> value instanceof ListValue: " + listValue);
        //     return -listValue.compareTo(this);
        // }

        throw new IllegalArgumentException("Invalid value: " + other);

        // FIXME: this could be a pattern matching with switch. but this is not working with gradle?!
        // return switch (o) {
        //     case IntValue  intValue  -> this.compareTo(ListValue.of(intValue));
        //     case ListValue listValue -> -listValue.compareTo(this);
        //     /* no default, switch is exhaustive */
        // };
    }

}

