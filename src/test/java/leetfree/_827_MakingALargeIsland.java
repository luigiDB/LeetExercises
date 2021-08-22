package leetfree;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
 * Return the size of the largest island in grid after applying this operation.
 * An island is a 4-directionally connected group of 1s.
 */
public class _827_MakingALargeIsland {

    @Test
    public void a() {
        int[][] grid = {
                {1, 0},
                {0, 1}
        };
        assertEquals(3, largestIsland(grid));
    }

    @Test
    public void b() {
        int[][] grid = {
                {1, 1},
                {1, 0}
        };
        assertEquals(4, largestIsland(grid));
    }

    @Test
    public void c() {
        int[][] grid = {
                {1, 1},
                {1, 1}
        };
        assertEquals(4, largestIsland(grid));
    }

    public int largestIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int islandCounter = 2;
        Map<Integer, Integer> size = new HashMap<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    size.put(islandCounter, color(grid, i, j, islandCounter));
                    islandCounter++;
                }
            }
        }

        int maxSize = 0;
        if (islandCounter == 2) // no island
            return 1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> distinctNeighbors = getDistinctNeighbors(grid, i, j);
                    int currSize = 1;
                    for (int island : distinctNeighbors)
                        currSize += size.get(island);
                    maxSize = Math.max(maxSize, currSize);
                }
            }
        }

        if (maxSize == 0)
            return size.values().stream().max(Comparator.comparingInt(a -> a)).get();
        else
            return maxSize;
    }

    private Set<Integer> getDistinctNeighbors(int[][] grid, int row, int column) {
        Set<Integer> neighbors = new HashSet<>();
        int[] dir = {-1, 0, 1, 0, -1};
        for (int i = 0; i < dir.length - 1; i++) {
            int nextRow = row + dir[i];
            int nextCol = column + dir[i + 1];
            if (
                    nextRow >= 0
                            && nextRow < grid.length
                            && nextCol >= 0
                            && nextCol < grid[0].length
                            && grid[nextRow][nextCol] != 0
            )
                neighbors.add(grid[nextRow][nextCol]);

        }
        return neighbors;
    }

    private Integer color(int[][] grid, int row, int column, int id) {
        int size = 0;
        Queue<Pair<Integer, Integer>> visit = new LinkedList<>();
        visit.add(Pair.of(row, column));
        while (!visit.isEmpty()) {
            Pair<Integer, Integer> poll = visit.poll();

            if (grid[poll.getLeft()][poll.getRight()] != 1)
                continue;

            grid[poll.getLeft()][poll.getRight()] = id;
            size += 1;
            int[] dir = {-1, 0, 1, 0, -1};
            for (int i = 0; i < dir.length - 1; i++) {
                int nextRow = poll.getLeft() + dir[i];
                int nextCol = poll.getRight() + dir[i + 1];
                if (
                        nextRow >= 0
                                && nextRow < grid.length
                                && nextCol >= 0
                                && nextCol < grid[0].length
                                && grid[nextRow][nextCol] == 1
                ) {
                    visit.add(Pair.of(nextRow, nextCol));
                }
            }
        }

        return size;
    }
}
