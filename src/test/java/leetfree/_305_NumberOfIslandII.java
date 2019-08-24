package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which
 * turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of
 * islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands
 * horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 *     Example:
 *
 *     Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
 *     Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
 *
 *     0 0 0
 *     0 0 0
 *     0 0 0
 *     Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
 *
 *     1 0 0
 *     0 0 0   Number of islands = 1
 *     0 0 0
 *     Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
 *
 *     1 1 0
 *     0 0 0   Number of islands = 1
 *     0 0 0
 *     Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
 *
 *     1 1 0
 *     0 0 1   Number of islands = 2
 *     0 0 0
 *     Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
 *
 *     1 1 0
 *     0 0 1   Number of islands = 3
 *     0 1 0
 *     We return the result as an array: [1, 1, 2, 3]
 *
 *     Challenge:
 *
 *     Can you do it in time complexity O(k log mn), where k is the length of the positions?
 */
public class _305_NumberOfIslandII {

    @Test
    public void givenExample() {
        WorldIsland islands = new WorldIsland(3, 3);
        Assert.assertEquals(1, islands.addLand(0,0));
        Assert.assertEquals(1, islands.addLand(0,1));
        Assert.assertEquals(2, islands.addLand(1,2));
        Assert.assertEquals(3, islands.addLand(2,1));
    }

    private class WorldIsland {
        private int rows;
        private int columns;
        private final int[][] world;
        private int addCounter;
        private int islands;

        public WorldIsland(int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
            world = new int[rows][columns];
            addCounter = 0;
            islands = 0;
        }


        public int addLand(int row, int column) {
            world[row][column] = ++addCounter;
            Set<Integer> neighbors = getNeighbor(row, column) ;

            if (neighbors.size() == 0) {
                islands += 1;
            } else {
                int min = Collections.min(neighbors);
                colorIslands(min, row, column);
                islands -= (neighbors.size() - 1);
            }
            return islands;
        }

        private Set<Integer> getNeighbor(int row, int column) {
            Set<Integer> neighbors = new HashSet<>();
            try {
                neighbors.add(world[row - 1][column]);
            } catch (Exception e) {}
            try {
                neighbors.add(world[row][column+1]);
            } catch (Exception e) {}
            try {
                neighbors.add(world[row+1][column]);
            } catch (Exception e) {}
            try {
                neighbors.add(world[row][column-1]);
            } catch (Exception e) {}

            neighbors.remove(0);
            return neighbors;
        }

        private void colorIslands(int referenceValue, int row, int column) {
            try {
                if (world[row][column] != referenceValue && world[row][column] != 0) {
                    world[row][column] = referenceValue;
                    colorIslands(referenceValue, row-1, column);
                    colorIslands(referenceValue, row, column+1);
                    colorIslands(referenceValue, row+1, column);
                    colorIslands(referenceValue, row, column-1);
                }
            } catch (Exception e) {

            }
        }


    }
}
