package dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a grid where each entry is only 0 or 1, find the number of corner rectangles.
 * <p>
 * A corner rectangle is 4 distinct 1s on the grid that form an axis-aligned rectangle. Note that only the corners need to have the value 1. Also, all four 1s used must be distinct.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: grid =
 * [[1, 0, 0, 1, 0],
 * [0, 0, 1, 0, 1],
 * [0, 0, 0, 1, 0],
 * [1, 0, 1, 0, 1]]
 * Output: 1
 * Explanation: There is only one corner rectangle, with corners grid[1][2], grid[1][4], grid[3][2], grid[3][4].
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: grid =
 * [[1, 1, 1],
 * [1, 1, 1],
 * [1, 1, 1]]
 * Output: 9
 * Explanation: There are four 2x2 rectangles, four 2x3 and 3x2 rectangles, and one 3x3 rectangle.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * Input: grid =
 * [[1, 1, 1, 1]]
 * Output: 0
 * Explanation: Rectangles must have four distinct corners.
 * <p>
 * <p>
 * Note:
 * <p>
 * The number of rows and columns of grid will each be in the range [1, 200].
 * Each grid[i][j] will be either 0 or 1.
 * The number of 1s in the grid will be at most 6000.
 */
public class _750_NumberOfCornerRectangles {

    /**
     * Let's maintain a count count[i, j], the number of times we saw row[i] = row[j] = 1. When we process a new row,
     * for every pair new_row[i] = new_row[j] = 1, we add count[i, j] to the answer, then we increment count[i, j].
     */
    public int countCornerRectangles(int[][] grid) {
        Map<Integer, Integer> count = new HashMap();
        int ans = 0;
        for (int[] row : grid) {
            for (int c1 = 0; c1 < row.length; ++c1)
                if (row[c1] == 1) {
                    for (int c2 = c1 + 1; c2 < row.length; ++c2)
                        if (row[c2] == 1) {
                            int pos = c1 * 200 + c2;
                            int c = count.getOrDefault(pos, 0);
                            ans += c;
                            count.put(pos, c + 1);
                        }
                }
        }
        return ans;
    }

}
