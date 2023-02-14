package adventofcode.aoc2022.Day15;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import adventofcode.util.geometry.Point2D;

/**
 * Sensor Scan Parser
 */
public class SensorScanParser {

    private static final Pattern INTEGER_PATTERN = Pattern.compile("-?\\d+");

    public static SensorScan parse(List<String> list) {
        // System.out.println("\nSensorScanParser.parse()");
        List<SensorReport> reports = list.stream()
                .map(SensorScanParser::parseSensorReport)
                .toList();

        return new SensorScan(reports);
    }

    private static SensorReport parseSensorReport(String string) {
        Matcher matcher = INTEGER_PATTERN.matcher(string);

        List<Integer> integerList = new ArrayList<>();

        while (matcher.find()) {
            String str = matcher.group();
            int i = Integer.parseInt(str);
            integerList.add(i);
        }

        Point2D sensor = new Point2D(integerList.get(0), integerList.get(1));
        Point2D beacon = new Point2D(integerList.get(2), integerList.get(3));

        return new SensorReport(sensor, beacon);
    }

}
