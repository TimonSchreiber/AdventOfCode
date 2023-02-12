package adventofcode.aoc2022.Day14;

import java.util.List;
import java.util.Set;

import adventofcode.util.geometry.Direction;
import adventofcode.util.geometry.Point2D;

public record Cave(Set<Point2D> rock, Set<Point2D> sand) {

    private static final Point2D SOURCE = new Point2D(500, 0);

    private static final
    List<Direction[]> DIRECTIONS =
        List.of(
            new Direction[]{Direction.D},
            new Direction[]{Direction.L, Direction.D},
            new Direction[]{Direction.R, Direction.D}
        );

    public void fillSandBottomLess() {
        int abyss = rock.stream().mapToInt(Point2D::y).max().orElseThrow();
        boolean sandFallsForever = false;

        while (!sandFallsForever) {
            Point2D grain = SOURCE;
            boolean fellDown = true;

            while (fellDown) {
                Point2D nextStep = fallDown(grain);

                if (nextStep == null) {
                    fellDown = false;
                    break;
                }

                if (grain.y() > abyss) {
                    sandFallsForever = true;
                    return;
                }
                grain = nextStep;
            }

            sand.add(grain);
        }
    }

    private Point2D fallDown(Point2D grain) {
        for (Direction[] dirArr : DIRECTIONS) {
            Point2D nextStep = grain.moveTowards(dirArr);

            if (rock.contains(nextStep) || sand.contains(nextStep)) {
                continue;
            }

            return nextStep;
        }
        return null;
    }

    public void print() {

        // get the smallest and biggest x- and y-values of all Rocks
        int xMin = rock.stream().mapToInt(Point2D::x).min().orElseThrow();
        int xMax = rock.stream().mapToInt(Point2D::x).max().orElseThrow();
        int yMin = rock.stream().mapToInt(Point2D::y).min().orElseThrow();
        int yMax = rock.stream().mapToInt(Point2D::y).max().orElseThrow();


        // check if the source is bigger/smaller
        xMin = Integer.min(xMin, SOURCE.x());
        xMax = Integer.max(xMax, SOURCE.x());
        yMin = Integer.min(yMin, SOURCE.y());
        yMax = Integer.max(yMax, SOURCE.y());

        // loop over the whole range of coordinates these rocks can be in and print
        for (int y = yMin; y <= yMax; y++) {
            for (int x = xMin; x <= xMax; x++) {
                Point2D point = new Point2D(x, y);
                String string = drawSymbol(point);
                System.out.print(string);
            }
            System.out.println();
        }
    }

    private String drawSymbol(Point2D point) {
        if (SOURCE.equals(point)) { return "+"; }
        else if (rock.contains(point)) { return "#"; }
        else if (sand.contains(point)) { return "o"; }
        else { return "."; }
    }

}
