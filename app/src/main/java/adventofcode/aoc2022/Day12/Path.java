package adventofcode.aoc2022.Day12;

import java.util.ArrayList;
import java.util.List;

import adventofcode.util.geometry.Direction;
import adventofcode.util.geometry.Point2D;

public record Path(List<Direction> path, Point2D position) {
    
    public Path step(Direction direction) {
        List<Direction> list = new ArrayList<>();
        for (Direction dir : path) {
            list.add(dir);
        }
        list.add(direction);
        Point2D point = position.moveTowards(direction);
        return new Path(list, point);
    }

}
