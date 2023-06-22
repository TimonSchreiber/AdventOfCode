package adventofcode.aoc2022.day15;

import adventofcode.util.geometry.Point2D;

/**
 * Sensor Report
 */
public record SensorReport(Point2D sensor, Point2D beacon, int distance) {

    public SensorReport(Point2D sensor, Point2D beacon) {
        this(sensor, beacon, sensor.manhattanDistance(beacon));
    }

}
