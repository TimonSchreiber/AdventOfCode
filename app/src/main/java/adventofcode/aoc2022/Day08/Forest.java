package adventofcode.aoc2022.Day08;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A 2D-Grid of {@code Tree}s.
 */
public record Forest(List<TreeRow> trees) {

    record Position(int x, int y){}

    /**
     * Count from each side of this {@code Forest} which {@code Tree}s can be seen.
     * @return  the number of {@code Tree}s which can be seen from the outside.
     */
    public int countVisibleTrees() {

        int iDim = this.height();
        int jDim = this.width();
        Set<Position> visibleTrees = new HashSet<>();

        // check left to right
        for (int i = 0 ; i < iDim; i++) {
            int maxHeight = -1;
            for (int j = 0; j < jDim; j++) {
                int currentHeight = this.get(i, j).height();

                if (currentHeight > maxHeight) {
                    maxHeight = currentHeight;
                    visibleTrees.add(new Position(j, i));
                }

                if (maxHeight >= Tree.MAX_HEIGHT) {
                    // the max tree height is reached.
                    // No need to look further down this row/column
                    break;
                }
            }
        }

        // check right to left
        for (int i = iDim-1 ; i >= 0; i--) {
            int maxHeight = -1;
            for (int j = jDim-1; j >= 0; j--) {
                int currentHeight = this.get(i, j).height();

                if (currentHeight > maxHeight) {
                    maxHeight = currentHeight;
                    visibleTrees.add(new Position(j, i));
                }

                if (maxHeight >= Tree.MAX_HEIGHT) {
                    // the max tree height is reached.
                    // No need to look further down this row/column
                    break;
                }
            }
        }
        
        // check top to bottom
        for (int i = 0 ; i < iDim; i++) {
            int maxHeight = -1;
            for (int j = 0; j < jDim; j++) {
                int currentHeight = this.get(j, i).height();

                if (currentHeight > maxHeight) {
                    maxHeight = currentHeight;
                    visibleTrees.add(new Position(i, j));
                }

                if (maxHeight >= Tree.MAX_HEIGHT) {
                    // the max tree height is reached.
                    // No need to look further down this row/column
                    break;
                }
            }
        }

        // check bottom to top
        for (int i = iDim-1 ; i >= 0; i--) {
            int maxHeight = -1;
            for (int j = jDim-1; j >= 0; j--) {
                int currentHeight = this.get(j, i).height();

                if (currentHeight > maxHeight) {
                    maxHeight = currentHeight;
                    visibleTrees.add(new Position(i, j));
                }

                if (maxHeight >= Tree.MAX_HEIGHT) {
                    // the max tree height is reached.
                    // No need to look further down this row/column
                    break;
                }
            }
        }

        // return the number of (unique) Positions in the Set.
        return visibleTrees.size();
    }

    /**
     * Find the highest Scenic Score in this {@code Forest}.
     * @return  The highest Scenic Score in this {@code Forest}.
     */
    public int highestScenicScore() {
        
        int iDim = this.height();
        int jDim = this.width();
        int highestScore = -1;

        for (int i = 0; i < iDim; i++) {
            for (int j = 0; j < jDim; j++) {
                int left  = checkView(i, j, 0, -1);
                int right = checkView(i, j, 0,  1);
                int up    = checkView(i, j,-1,  0);
                int down  = checkView(i, j, 1,  0);

                highestScore = Integer.max(highestScore, left*right*up*down);
            }
        }

        return highestScore;
    }

    /** calculate the Scenic Score of this {@code Tree} */
    private int checkView(int oldI, int oldJ, int deltaI, int deltaJ) {
        int height = this.get(oldI, oldJ).height();
        int iDim = this.height();
        int jDim = this.width();
        int newI = oldI + deltaI;
        int newJ = oldJ + deltaJ;
        int res = 0;
        while ((newI >= 0) && (newI < iDim) && (newJ >= 0) && (newJ < jDim)) {
            res++;
            if (this.get(newI, newJ).height() >= height) {
                return res;
            }
            newI += deltaI;
            newJ += deltaJ;
        }
        return res;
    }

    /** the number of {@code TreeRow}s in this {@code Forest} */
    private int height() {
        return this.trees.size();
    }

    /** the max number of {@code Tree}s in a {@code TreeRow} in this {@code Forest} */
    private int width() {
        return this.trees.stream().mapToInt(TreeRow::size).max().orElseThrow();
    }

    /** get a a {@code Tree} by its coordinates */
    private Tree get(int i, int j) {
        return this.trees.get(i).get(j);
    }

    /**
     * Print a 2D Height-Map of trees.
     */
    public void print() {
        this.trees.stream().forEachOrdered(TreeRow::print);
        System.out.println();
    }
}
