package adventofcode.aoc2022.Day08;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A 2D-Grid of {@code Tree}s.
 */
public record Forest(List<TreeRow> trees) {

    record Position(int x, int y){}

    /** TODO: clean up this method, it is WAAAAY to long. DRY. Maybe use an enum for Directions.
     * Count from each side of this {@code Forest} which {@code Tree}s can be seen.
     * @return  the number of {@code Tree}s which can be seen from the outside.
     */
    public int countVisibleTrees() {
        final Set<Position> visibleTrees = new HashSet<>();

        // check left to right
        for (int i = 0 ; i < this.height(); i++) {
            int tallestTree = -1;
            for (int j = 0; j < this.trees.get(i).size(); j++) {
                tallestTree = checkVisibility(visibleTrees, i, j, tallestTree);

                if (tallestTree >= Tree.MAX_HEIGHT) {
                    // the max tree height is reached.
                    // No need to look further down this row/column
                    break;
                }
            }
        }

        // check right to left
        for (int i = this.height()-1 ; i >= 0; i--) {
            int tallestTree = -1;
            for (int j = this.trees.get(i).size()-1; j >= 0; j--) {
                tallestTree = checkVisibility(visibleTrees, i, j, tallestTree);

                if (tallestTree >= Tree.MAX_HEIGHT) {
                    // the max tree height is reached.
                    // No need to look further down this row/column
                    break;
                }
            }
        }
        
        // check top to bottom
        for (int i = 0 ; i < this.height(); i++) {
            int tallestTree = -1;
            for (int j = 0; j < this.trees.get(i).size(); j++) {
                tallestTree = checkVisibility(visibleTrees, j, i, tallestTree);

                if (tallestTree >= Tree.MAX_HEIGHT) {
                    // the max tree height is reached.
                    // No need to look further down this row/column
                    break;
                }
            }
        }

        // check bottom to top
        for (int i = this.height()-1 ; i >= 0; i--) {
            int tallestTree = -1;
            for (int j = this.trees.get(i).size()-1; j >= 0; j--) {
                tallestTree = checkVisibility(visibleTrees, j, i, tallestTree);

                if (tallestTree >= Tree.MAX_HEIGHT) {
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
     * Check if the {@code Tree} at i, j is visible compared to the current
     * maxHeight. If it is visible, add it to the {@code visibleTrees}
     * {@code Set}.
     *
     * @param visibleTrees  A Set with the Positions of all visible Trees
     * @param i             The index for the TreeRow in this Forest
     * @param j             The index for the Tree in the TreeRow
     * @param tallestTree   Height of the tallest Tree so far
     * @return              The height of the new tallest Tree
     */
    private int checkVisibility(Set<Position> visibleTrees, int i, int j, int tallestTree) {
        int currentHeight = this.get(i, j).height();

        if (currentHeight > tallestTree) {
            tallestTree = currentHeight;
            visibleTrees.add(new Position(j, i));
        }
        return tallestTree;
    }

    /** TODO: maybe use an enum for Directions
     * Find the highest Scenic Score in this {@code Forest}.
     * @return  The highest Scenic Score in this {@code Forest}.
     */
    public int highestScenicScore() {
        
        int highestScore = -1;

        for (int i = 0; i < this.height(); i++) {
            for (int j = 0; j < this.trees.get(i).size(); j++) {
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
        int newI = oldI + deltaI;
        int newJ = oldJ + deltaJ;
        int res = 0;
        while (isInForest(newI, newJ)) {
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

    /** get a a {@code Tree} by its coordinates */
    private Tree get(int i, int j) {
        return this.trees.get(i).get(j);
    }

    /** check if the the Coordinates are inside this Forest. */
    private boolean isInForest(int newI, int newJ) {
        return (newI >= 0)
          && (newI < this.height())
          && (newJ >= 0)
          && (newJ < this.trees.get(newI).size());
    }

    /**
     * Print a 2D Height-Map of trees.
     */
    public void print() {
        this.trees.stream().forEachOrdered(TreeRow::print);
        System.out.println();
    }
}
