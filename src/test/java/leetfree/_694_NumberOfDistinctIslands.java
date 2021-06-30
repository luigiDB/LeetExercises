package leetfree;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class _694_NumberOfDistinctIslands {

    @Test
    public void a() {
        assertEquals(
                3,
                new Solution().numDistinctIslands(new int[][]{
                        {1, 1, 0, 1, 1},
                        {1, 0, 0, 0, 0},
                        {0, 0, 0, 0, 1},
                        {1, 1, 0, 1, 1}
                })
        );
    }

    class Solution {

        private int[][] grid;
        private boolean[][] seen;
        private Set<Pair<Integer, Integer>> currentIsland;
        private int currRowOrigin;
        private int currColOrigin;

        private void dfs(int row, int col) {
            if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length)
                return;
            if (grid[row][col] == 0 || seen[row][col])
                return;

            seen[row][col] = true;
            currentIsland.add(Pair.of(row - currRowOrigin, col - currColOrigin));
            dfs(row + 1, col);
            dfs(row - 1, col);
            dfs(row, col + 1);
            dfs(row, col - 1);
        }

        public int numDistinctIslands(int[][] grid) {
            this.grid = grid;
            this.seen = new boolean[grid.length][grid[0].length];
            Set<Set<Pair<Integer, Integer>>> islands = new HashSet<>();

            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    this.currentIsland = new HashSet<>();
                    this.currRowOrigin = row;
                    this.currColOrigin = col;
                    dfs(row, col);
                    if (!currentIsland.isEmpty())
                        islands.add(currentIsland);
                }
            }
            return islands.size();
        }
    }
}
