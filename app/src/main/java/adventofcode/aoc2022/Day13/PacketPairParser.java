package adventofcode.aoc2022.Day13;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Packet Pair Parser
 */
public class PacketPairParser {

    private static final String DOUBLE_LINEBREAK = "\n\n";

    /**
     * Use when parsing a String of more or one ListValue pair into PacketPais.
     * @param string
     * @return          An imutable List of PacketPairs.
     */
    public static List<PacketPair> parse(String string) {
        return Arrays.stream(string.split(DOUBLE_LINEBREAK))
                .map(PacketPairParser::parsePacketPair)
                .toList();
    }

    private static PacketPair parsePacketPair(String string) {
        return string.lines()
                .map(ValueParser::parseListValue)
                .collect(
                    Collectors.collectingAndThen(
                        Collectors.toList(),
                        PacketPair::new
                    )
                );
    }

}
