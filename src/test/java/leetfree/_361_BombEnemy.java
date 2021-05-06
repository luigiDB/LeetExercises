package leetfree;

import java.util.Arrays;

public class _361_BombEnemy {
    /*Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
    The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
    Note that you can only put the bomb at an empty cell.
    Example:
    For the given grid
    0 E 0 0
    E 0 W E
    0 E 0 0
    return 3. (Placing a bomb at (1,1) kills 3 enemies)*/

    /**
     * Simple solution that traverse the matrix 2 times. First passage from top-left second from bottom-right counting
     * enemies in view O(2 * N^2) = O(N^2)
     * Another solution could be to propagate from enemies the counting approach that can be more efficient if we know
     * that enemies << empty spaces
     */
    class Solution {
        public int maxKilledEnemies(char[][] grid) {
            if (grid.length == 0) {
                return 0;
            }
            int[][] tmp = new int[grid.length][grid[0].length];
            int[] horizontalEnemiesCounter = new int[grid[0].length];
            for (int column = 0; column < grid.length; column++) {
                int verticalEnemiesCounter = 0;
                for (int row = 0; row < grid[0].length; row++) {
                    if (grid[column][row] == 'W') {
                        horizontalEnemiesCounter[row] = 0;
                        verticalEnemiesCounter = 0;
                    } else if (grid[column][row] == 'E') {
                        horizontalEnemiesCounter[row]++;
                        verticalEnemiesCounter++;
                    } else {
                        tmp[column][row] = verticalEnemiesCounter + horizontalEnemiesCounter[row];
                    }
                }
            }
            Arrays.fill(horizontalEnemiesCounter, 0);
            int ret = 0;
            for (int column = grid.length - 1; column >= 0; column--) {
                int v = 0;
                for (int row = grid[0].length - 1; row >= 0; row--) {
                    if (grid[column][row] == 'W') {
                        v = 0;
                        horizontalEnemiesCounter[row] = 0;
                    } else if (grid[column][row] == 'E') {
                        v++;
                        horizontalEnemiesCounter[row]++;
                    } else {
                        tmp[column][row] += v + horizontalEnemiesCounter[row];
                        ret = Math.max(ret, tmp[column][row]);
                    }
                }
            }
            return ret;
        }
    }
}
