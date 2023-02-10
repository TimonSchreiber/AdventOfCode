package adventofcode.aoc2022.Day13;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Packet Pair Parser
 */
public class PacketPairParser {

    private static final String DOUBLE_LINEBREAK = "\n\n";

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
