package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/*
Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.
If there isn't any rectangle, return 0.
Example 1:
Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4
Example 2:
Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
Output: 2
Note:
1 <= points.length <= 500
0 <= points[i][0] <= 40000
0 <= points[i][1] <= 40000
All points are distinct.
 */
public class __939_MinimumAreaRectangle {
    /**
     * out of laziness i've build the solution using the matrix as input in case of you want the solution with array
     * pairs simply add a prestep in which a map<Integer, List<Integer>> to keep track of edge per row and apply the
     * same approach.
     */

    @Test
    public void given1() {
        int[][] input = new int[][]{
                {0, 0, 0, 0},
                {0, 1, 0, 1},
                {0, 0, 1, 0},
                {0, 1, 0, 1}
        };
        Assert.assertEquals(4, minAreaRect(input));
    }

    @Test
    public void given2() {
        int[][] input = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 1, 0, 1, 1},
                {0, 0, 1, 0, 0},
                {0, 1, 0, 1, 1}
        };
        Assert.assertEquals(2, minAreaRect(input));
    }

    public int minAreaRect(int[][] points) {
        Map<Integer, Integer> lastSeen = new HashMap<>();
        int minArea = Integer.MAX_VALUE;
        for (int row = 0; row < points.length; row++) {
            for (int i = 0; i < points[row].length; i++) {
                for (int j = i + 1; j < points[row].length; j++) {
                    if (points[row][i] == 0 | points[row][j] == 0)
                        continue;

                    int id = i * 40001 + j;
                    if (lastSeen.containsKey(id)) {
                        minArea = Math.min(minArea, (j - i) * (row - lastSeen.get(id)));
                    }
                    lastSeen.put(id, row);
                }
            }
        }

        return (minArea == Integer.MAX_VALUE) ? 0 : minArea;
    }
}
