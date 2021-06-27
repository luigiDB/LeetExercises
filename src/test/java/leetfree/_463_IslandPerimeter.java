package leetfree;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0
 * represents water.
 * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and
 * there is exactly one island (i.e., one or more connected land cells).
 * The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. One cell
 * is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter
 * of the island.
 */
public class _463_IslandPerimeter {

    @Test
    public void a() {
        int[][] map = new int[][]{
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0},
        };
        assertEquals(16, islandPerimeter(map));
    }

    public int islandPerimeter(int[][] grid) {
        int startX = 0, startY = 0;
        int len = grid.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    startX = i;
                    startY = j;
                }
            }
        }

        int[] dir = {-1, 0, 1, 0, -1};
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startX * len + startY);
        int perimeter = 0;

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            int x = poll / len;
            int y = poll % len;

            visited.add(poll);
            perimeter += 4;

            for (int i = 0; i < dir.length - 1; i++) {
                int nx = x + dir[i];
                int ny = y + dir[i + 1];
                int nextIndex = nx * len + ny;
                if (nx >= 0 && nx < len
                        && ny >= 0 && ny < grid[0].length
                        && grid[nx][ny] == 1
                ) {
                    perimeter--;
                    if(!visited.contains(nextIndex))
                        queue.add(nextIndex);
                }
            }
        }
        return perimeter;
    }
}