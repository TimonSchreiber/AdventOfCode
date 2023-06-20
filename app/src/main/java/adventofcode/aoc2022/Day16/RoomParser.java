package adventofcode.aoc2022.Day16;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Room Parser
 */
public class RoomParser {

    // example String to match: 'Valve AA has flow rate=0; tunnels lead to valves DD, II, BB'
    // where 'tunnel', 'lead', and 'valve' can have a 's' at the end
    private static final Pattern VALVE_PATTERN =
        Pattern.compile("Valve ([A-Z]{2}) has flow rate=(-?\\d+); tunnels? leads? to valves? (.+)");

    public static Map<String, Room> parse(List<String> list) {
        return list.stream()
                .map(RoomParser::parseRoom)
                .collect(Collectors.toMap(Room::name, Function.identity()));
    }

    private static Room parseRoom(String string) {
        Matcher matcher = VALVE_PATTERN.matcher(string);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid input:\n" + string);
        }

        String valveName = matcher.group(1);
        int rate = Integer.parseInt(matcher.group(2));
        List<String> tunnels = Arrays.stream(matcher.group(3).split(", ")).toList();

        return new Room(new Valve(valveName, rate), tunnels);
    }

}
