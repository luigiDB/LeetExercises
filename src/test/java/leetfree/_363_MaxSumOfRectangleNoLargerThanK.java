package leetfree;

import java.util.TreeSet;
/*
Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum
is no larger than k.
Example:
Input: matrix = [[1,0,1],[0,-2,3]], k = 2
Output: 2
Explanation: Because the sum of rectangle [[0, 1], [-2, 3]] is 2,
             and 2 is the max number no larger than k (k = 2).
Note:
The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?
 */
public class _363_MaxSumOfRectangleNoLargerThanK {

    /**
     * Complexity rows^2 * columns^2
     * @param matrix
     * @param k
     * @return
     */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows + 1][cols + 1];
        int totalSum = Integer.MIN_VALUE;
        for (int i = 1; i < rows + 1; i++) {
            for (int j = 1; j < cols + 1; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + matrix[i - 1][j - 1];
                if (dp[i][j] <= k)
                    totalSum = Math.max(totalSum, dp[i][j]);
            }
        }

        for (int i = 1; i < rows + 1; i++) {
            for (int j = i; j < rows + 1; j++) {
                for (int x = 1; x < cols + 1; x++) {
                    for (int y = x; y < cols + 1; y++) {
                        int sum = dp[j][y] - dp[i - 1][y] - dp[j][x - 1] + dp[i - 1][x - 1];
                        if (sum <= k)
                            totalSum = Math.max(sum, totalSum);
                    }
                }
            }
        }
        return totalSum;
    }

    /**
     * Complexity rows^2 * columns * log(columns)
     * @param matrix
     * @param k
     * @return
     */
    public int maxSumSubmatrixWithTreeSet(int[][] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows + 1][cols + 1];
        int totalSum = Integer.MIN_VALUE;
        for (int i = 1; i < rows + 1; i++) {
            for (int j = 1; j < cols + 1; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + matrix[i - 1][j - 1];
                if (dp[i][j] <= k)
                    totalSum = Math.max(totalSum, dp[i][j]);
            }
        }

        for (int i = 1; i < rows + 1; i++) {
            for (int j = i; j < rows + 1; j++) {
                TreeSet<Integer> set = new TreeSet();
                set.add(0);
                for (int x = 1; x < cols + 1; x++) {
                    int sum = dp[j][x] - dp[i - 1][x];
                    Integer curr = set.ceiling(sum - k);
                    if (curr != null) {
                        totalSum = Math.max(totalSum, sum - curr);
                    }
                    set.add(sum);
                }
            }
        }
        return totalSum;
    }
}
