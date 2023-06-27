package adventofcode.aoc2021.day09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import adventofcode.util.geometry.Point3D;

public class CaveFloor {

    private static final int MAX_HEIGHT = 9;

    private final int[][] heightMap;
    private final List<Integer> lowPoints;
    private final List<Integer> basinSizes;

    public CaveFloor(List<String> list) {
        this.heightMap  = heightMap(list);
        this.lowPoints  = new ArrayList<>();
        this.basinSizes = new ArrayList<>();

        this.processHeightMap();
    }

    private int xMax() {
        return heightMap.length - 1;
    }

    private int yMax() {
        return heightMap[0].length - 1;
    }

    /**
     * Process the HeightMap and find all the LowPoints aswell as their
     * respective Basin Sizes.
     */
    private void processHeightMap() {
        for (int x = 0; x <= xMax(); x++) {
            for (int y = 0; y <= yMax(); y++) {

                int height = heightMap[x][y];

                // only check towards the direction if not at the respective cave border
                if ((x > 0      && height >= heightMap[x-1][y+0])
                 || (x < xMax() && height >= heightMap[x+1][y+0])
                 || (y > 0      && height >= heightMap[x+0][y-1])
                 || (y < yMax() && height >= heightMap[x+0][y+1]))
                {
                    // one of the adjacent locations is at least as high as this one
                    continue;
                }

                // Add the height of this low point to the list of low points
                lowPoints.add(height);

                // Find the Basin Size (the Area) to this low point and add it to the list of basin sizes
                basinSizes.add(findBasinSize(x, y));
            }
        }
    }

    private int findBasinSize(int x, int y) {
        // A Set of all the Points this Basin is made of
        Set<Point3D> points = new HashSet<>();

        // add current low point to the Set
        points.add(new Point3D(x, y, heightMap[x][y]));

        // explore the cave and add all the Points this basin is made of to the Set
        exploreEachDirection(points, x, y);

        // return the size (area) of this basin
        return points.size();
    }

    /**
     * Explore the cave in each direction and find all the points this basin is made of.
     * @param points    A Set of Points this Basin is made of
     * @param x         Current x coordinate
     * @param y         Current y coordinate
     */
    private void exploreEachDirection(Set<Point3D> points, int x, int y) {

        // explore towards the top if the Cave border has not been reached
        if (x > 0) {
            Point3D up = new Point3D(x-1, y, heightMap[x-1][y]);
            exploreNewLocation(points, up, heightMap[x][y]);
        }

        // explore towards the bottom if the Cave border has not been reached
        if (x < xMax()) {
            Point3D down = new Point3D(x+1, y, heightMap[x+1][y]);
            exploreNewLocation(points, down, heightMap[x][y]);
        }

        // explore towards the left if the Cave border has not been reached
        if (y > 0) {
            Point3D left = new Point3D(x, y-1, heightMap[x][y-1]);
            exploreNewLocation(points, left, heightMap[x][y]);
        }

        // explore towards the right if the Cave border has not been reached
        if (y < yMax()) {
            Point3D right = new Point3D(x, y+1, heightMap[x][y+1]);
            exploreNewLocation(points, right, heightMap[x][y]);
        }
    }

    /**
     * Explore one specific new Location and compare it to the previous Height.
     * @param points    A Set of Points this Basin is made of
     * @param point     A single new Point which might be part of this basin
     * @param z         The old height
     */
    private void exploreNewLocation(Set<Point3D> points, Point3D point, int z) {
        /* if the new Location is not already in the Set
         * AND it's height is below the height limit
         * AND it's height is greater than the old height
         * -> add the new Location to the set of Points and explore from there
         */
        if (!points.contains(point) && point.z() < MAX_HEIGHT && point.z() > z) {
            points.add(point);
            exploreEachDirection(points, point.x(), point.y());
        }
    }

    /**
     * The Risk level is the sum of all the low point heights increased by one
     * @return
     */
    public int riskLevel() {
        return lowPoints.size()
             + lowPoints.stream()
                .mapToInt(Integer::valueOf)
                .sum();
    }

    /**
     * Return the product of the size of the three largest basins
     * @return
     */
    public int productOf3LargestBasins() {
        return basinSizes.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3L)
                .mapToInt(Integer::valueOf)
                .reduce(1, (a,b) -> a*b);
    }

    /**
     * Transfor a List of Strings into a 2D-int-Array.
     * @param list
     * @return
     */
    private static int[][] heightMap(List<String> list) {
        return list.stream()
                .map(CaveFloor::mapToList)
                .toArray(int[][]::new);
    }

    /**
     * Transform a single String of Digits into an Array of ints.
     * @param string
     * @return
     */
    private static int[] mapToList(String string) {
        return Arrays.stream(string.split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
    }


}
