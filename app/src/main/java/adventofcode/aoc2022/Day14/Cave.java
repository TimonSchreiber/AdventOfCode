package adventofcode.aoc2022.Day14;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public void clearSand() {
        sand.clear();
    }

    /**
     * Part 1: fill this Cave with Sand until the next grain of sand will fall
     * forever into the abyss.
     */
    public void fillSandBottomLess() {
        int abyss = rock.stream().mapToInt(Point2D::y).max().orElseThrow();
        boolean sandFallsForever = false;

        while (!sandFallsForever) {
            Point2D grain = SOURCE;
            boolean fellDown = true;

            while (fellDown) {
                Point2D nextStep = fallDown(grain);

                if (nextStep.equals(grain)) {
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

    /** Part1
     * calculate the next Point2D for a falling grain of sand. If the grain
     * comes to a rest return the Point2D of the grain itself.
     * @param grain
     * @return
     */
    private Point2D fallDown(Point2D grain) {
        for (Direction[] dirArr : DIRECTIONS) {
            Point2D nextStep = grain.moveTowards(dirArr);

            if (rock.contains(nextStep) || sand.contains(nextStep)) {
                continue;
            }

            return nextStep;
        }
        return grain;
    }

    /**
     * Part2: fill this Cave with Sand until the Source is blocked. This Cave
     * now has a floor 2 levels below the lowest Rock (highest y value).
     */
    public void fillSandFloor() {
        int floor = 2 + rock.stream().mapToInt(Point2D::y).max().orElseThrow();
        boolean sourceIsBlocked = false;


        while (!sourceIsBlocked) {
            Point2D grain = SOURCE;
            boolean fellDown = true;

            if (sand.contains(SOURCE)) {
                sourceIsBlocked = true;
                return;
            }

            while (fellDown) {
                Point2D nextStep = fallDown(grain, floor);

                if (nextStep.equals(grain)) {
                    fellDown = false;
                    break;
                }

                grain = nextStep;

            }

            sand.add(grain);
        }
    }

    /** Part2
     * calculate the next Point2D for a falling grain of sand. If the grain
     * comes to a rest return the Point2D of the grain itself.
     * @param grain
     * @return
     */
    private Point2D fallDown(Point2D grain, int floorLevel) {
        for (Direction[] dirArr : DIRECTIONS) {
            Point2D nextStep = grain.moveTowards(dirArr);

            if (rock.contains(nextStep) || sand.contains(nextStep) || nextStep.y() >= floorLevel) {
                continue;
            }

            return nextStep;
        }
        return grain;
    }

    public void print() {
        System.out.println("\nCAVE\n");

        // combine all elements to a single Set
        Set<Point2D> elements = Stream.of(rock, sand, Set.of(SOURCE))
                .flatMap(set -> set.stream())
                .collect(Collectors.toSet());

        // get the smallest and biggest x- and y-values of all elements
        int xMin = elements.stream().mapToInt(Point2D::x).min().orElseThrow();
        int xMax = elements.stream().mapToInt(Point2D::x).max().orElseThrow();
        int yMin = elements.stream().mapToInt(Point2D::y).min().orElseThrow();
        int yMax = elements.stream().mapToInt(Point2D::y).max().orElseThrow();

        // Loop over every
        for (int y = yMin; y <= yMax; y++) {
            for (int x = xMin; x <= xMax; x++) {
                Point2D point = new Point2D(x, y);
                drawSymbol(point);
            }
            System.out.println();
        }
        System.out.println();
    }

    private void drawSymbol(Point2D point) {
        if      (rock.contains(point)) { System.out.print("#"); }
        else if (sand.contains(point)) { System.out.print("o"); }
        else if (SOURCE.equals(point)) { System.out.print("+"); }
        else                           { System.out.print("."); }
    }

}
