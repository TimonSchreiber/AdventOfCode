package adventofcode.aoc2022.Day12;

import java.util.Map;

import adventofcode.util.geometry.Point2D;

public record HeightMap(Map<Point2D, Height> map, Point2D start, Point2D end, int width, int height) {

    public boolean isInside(Point2D point) {
        return point.x() >= 0
            && point.x() < width
            && point.y() >= 0
            && point.y() < height;
    }

    @Override
    public String toString() {
        String str = "";
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Point2D point = new Point2D(x, y);
                if (point.equals(start)) {
                    str += "S";
                } else if (point.equals(end)) {
                    str += "E";
                } else {
                    str += map.get(point).toString();
                }
            }
            str += "\n";
        }
        return str;
    }
}
