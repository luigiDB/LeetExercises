package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can
 * only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
 * <p>
 * Each 0 marks an empty land which you can pass by freely.
 * Each 1 marks a building which you cannot pass through.
 * Each 2 marks an obstacle which you cannot pass through.
 * For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):
 * <p>
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal.
 * So return 7.
 * <p>
 * Note:
 * There will be at least one building. If it is not possible to build such house according to the above rules,
 * return -1.
 */
public class _317_ShortestDistancefromAllBuildings {

    @Test
    public void simpleTest() {
        int[][] map = new int[][]{
                {1, 0},
                {0, 0}
        };
        Assert.assertEquals(1, optimalDistance(map));
    }


    @Test
    public void givenTest() {
        int[][] map = new int[][]{
                {1, 0, 2, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0}
        };
        Assert.assertEquals(7, optimalDistance(map));
    }

    private int optimalDistance(int[][] map) {
        List<Pair> buildings = findBuildings(map);

        int[][] distanceMap = new int[map.length][map[0].length];
        for (Pair building : buildings) {
            int[][] toBeSummed = bfs(building, map);
            sum(distanceMap, toBeSummed);
        }

        return findMin(distanceMap, map);

    }

    private void sum(int[][] distanceMap, int[][] toBeSummed) {
        for (int i = 0; i < distanceMap.length; i++) {
            for (int j = 0; j < distanceMap[i].length; j++) {
                distanceMap[i][j] += toBeSummed[i][j];
            }
        }
    }

    private List<Pair> findBuildings(int[][] map) {
        List<Pair> buildngs = new LinkedList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 1) {
                    buildngs.add(new Pair(i, j));
                }
            }
        }
        return buildngs;
    }

    private int findMin(int[][] distanceMap, int[][] map) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < distanceMap.length; i++) {
            for (int j = 0; j < distanceMap[i].length; j++) {
                if (map[i][j] == 0)
                    min = Math.min(min, distanceMap[i][j]);
            }
        }
        return min;
    }

    private int[][] bfs(Pair building, int[][] map) {
        Queue<Pair> queue = new LinkedList();
        int[][] distanceMap = new int[map.length][map[0].length];

        queue.offer(building);
        while (!queue.isEmpty()) {
            Pair poll = queue.poll();
            if (poll == null)
                continue;
            queue.offer(addIfValid(new Pair(poll.row - 1, poll.column), distanceMap, poll, map));
            queue.offer(addIfValid(new Pair(poll.row, poll.column + 1), distanceMap, poll, map));
            queue.offer(addIfValid(new Pair(poll.row + 1, poll.column), distanceMap, poll, map));
            queue.offer(addIfValid(new Pair(poll.row, poll.column - 1), distanceMap, poll, map));
        }
        return distanceMap;
        /**
         * This method does't check if every spot is reachable this means that a case like that
         *  1  0  0  1  0
         *  0  0  0  1  0
         *  Will detect 0, 4 as correct solution
         *  To avoid check that no valid spot are still at 0 at the end of bfs
         */
    }

    private Pair addIfValid(Pair newPoint, int[][] distanceMap, Pair origin, int[][] map) {
        if (newPoint.row >= 0 && newPoint.row < distanceMap.length && newPoint.column >= 0 && newPoint.column < distanceMap[0].length
                && map[newPoint.row][newPoint.column] == 0
                && distanceMap[newPoint.row][newPoint.column] == 0 ) {
            distanceMap[newPoint.row][newPoint.column] = distanceMap[origin.row][origin.column] + 1;
            return newPoint;
        }
        return null;
    }

    class Pair {
        int row;
        int column;

        public Pair(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }

        @Override
        public boolean equals(Object obj) {
            return this.row == ((Pair) obj).row && this.column == ((Pair) obj).column;
        }

        @Override
        public int hashCode() {
            return row * 100 + column;
        }
    }
}