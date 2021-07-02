package leetfree;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

/**
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally
 * (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 * The area of an island is the number of cells with a value 1 in the island.
 * Return the maximum area of an island in grid. If there is no island, return 0.
 */
public class _695_MaxAreaofIsland {

    @Test
    public void a() {
        int[][] grd = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };
        assertEquals(6, maxAreaOfIsland(grd));
    }

    public int maxAreaOfIsland(int[][] grid) {
        int maxIsland = Integer.MIN_VALUE;
        int islandCounter = 2;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    int size = colorIsland(i, j, grid, islandCounter++);
                    maxIsland = Math.max(maxIsland, size);
                }
            }
        }
        return maxIsland;
    }

    private int colorIsland(int row, int col, int[][] grid, int islandNumber) {
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(Pair.of(row, col));
        int size = 0;

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> poll = queue.poll();

            //the cell can be already visited from another neighbor
            if(grid[poll.getLeft()][poll.getRight()] != 1)
                continue;

            grid[poll.getLeft()][poll.getRight()] = islandNumber;
            size++;

            int[] dir = {-1, 0, 1, 0, -1};
            for (int i = 0; i < dir.length - 1; i++) {
                int x = poll.getLeft() + dir[i];
                int y = poll.getRight() + dir[i + 1];
                if (0 <= x && x < grid.length && 0 <= y && y < grid[0].length) {
                    if (grid[x][y] == 1)
                        queue.offer(Pair.of(x, y));
                }
            }
        }
        return size;
    }

}
