package adventofcode.aoc2022.Day14;

import java.util.Set;

import adventofcode.util.geometry.Point2D;

public record Cave(Set<Point2D> rocks) {

    private static final Point2D SOURCE = new Point2D(500, 0);

    public void print() {

        int xMin = rocks.stream().mapToInt(Point2D::x).min().orElseThrow();
        int xMax = rocks.stream().mapToInt(Point2D::x).max().orElseThrow();
        int yMin = rocks.stream().mapToInt(Point2D::y).min().orElseThrow();
        int yMax = rocks.stream().mapToInt(Point2D::y).max().orElseThrow();
    
        Point2D min = new Point2D(xMin, yMin);
        Point2D max = new Point2D(xMax, yMax);

        System.out.println("min: " + min);
        System.out.println("max: " + max);
        for (int y = 0; y <= max.y(); y++) {
            for (int x = min.x(); x <= max.x(); x++) {
                Point2D point = new Point2D(x, y);
                String str = rocks.contains(point) ? "#" : ".";
                if (SOURCE.equals(point)) { str = "+"; }
                System.out.print(str);
            }
            System.out.println();
        }
    }

}
