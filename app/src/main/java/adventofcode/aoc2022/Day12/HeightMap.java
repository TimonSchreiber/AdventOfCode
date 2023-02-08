package adventofcode.aoc2022.Day12;

import java.util.Map;

import adventofcode.util.geometry.Point2D;

/**
 * HeightMap
 */
public record HeightMap(Map<Point2D, Height> map, Point2D start, Point2D end, int width, int height) {

    /**
     * Check if the {@code Point2D} is inside this {@code HeightMap} area.
     * @param point the {@code Point2D} to check
     * @return      {@code true} if the {@code Point2D} is inside this
     *              HeightMap, {@code false} otherwise.
     */
    public boolean isInside(Point2D point) {
        return point.x() >= 0
            && point.x() < this.width
            && point.y() >= 0
            && point.y() < this.height;
    }

    public boolean isReachable(Point2D from, Point2D to) {
        return this.get(from).reachable(this.get(to));
    }

    public Height get(Point2D key) {
        return this.map.get(key);
    }

    @Override
    public String toString() {
        String str = "";
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                Point2D point = new Point2D(x, y);
                if (point.equals(this.start)) {
                    str += "S";
                } else if (point.equals(this.end)) {
                    str += "E";
                } else {
                    str += this.get(point).toString();
                }
            }
            str += "\n";
        }
        return str;
    }
}
