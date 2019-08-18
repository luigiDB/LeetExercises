package leetfree;

import org.junit.Assert;
import org.junit.Test;

/**
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner
 * (row1, col1) and lower right corner (row2, col2).
 * <p>
 * Range Sum Query 2D
 * The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3),
 * which contains sum = 8.
 * <p>
 * Example:
 * Given matrix = [
 * [3, 0, 1, 4, 2],
 * [5, 6, 3, 2, 1],
 * [1, 2, 0, 1, 5],
 * [4, 1, 0, 1, 7],
 * [1, 0, 3, 0, 5]
 * ]
 * <p>
 * sumRegion(2, 1, 4, 3) -> 8
 * update(3, 2, 2)
 * sumRegion(2, 1, 4, 3) -> 10
 * Note:
 * The matrix is only modifiable by the update function.
 * You may assume the number of calls to update and sumRegion function is distributed evenly.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.
 */
public class _308_RangeSumQuery2D_Mutable {

    @Test
    public void givenTest() {
        RangeSum rs = new RangeSum(new int[][]{
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}});
        Assert.assertEquals(14, rs.sumRegion(0, 0, 4, 0));
        Assert.assertEquals(8, rs.sumRegion(2, 1, 4, 3));
        rs.update(3, 2, 2);
        Assert.assertEquals(10, rs.sumRegion(2, 1, 4, 3));
    }

    private class RangeSum {
        private int[][] indexedMatrix;
        private int size;

        public RangeSum(int[][] matrix) {
            size = matrix.length;
            indexedMatrix = new int[size + 1][size + 1];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    update(i, j, matrix[i][j]);
                }
            }
        }

        public int sumRegion(int startRow, int startColumn1, int endRow, int endColumn) {
            return sumRegion(endRow, endColumn)
                    - sumRegion(startRow - 1, endColumn)
                    - sumRegion(endRow, startColumn1 - 1)
                    + sumRegion(startRow - 1, startColumn1 - 1);
        }

        private int sumRegion(int row, int column) {
            int sum = 0;

            for (int i = row + 1; i > 0; i -= (i & (-i))) {
                for (int j = column + 1; j > 0; j -= (j & (-j))) {
                    sum += indexedMatrix[i][j];
                }
            }

            return sum;
        }

        public void update(int row, int column, int additiveElement) {
            for (int i = row + 1; i <= size; i += (i & (-i))) {
                for (int j = column + 1; j <= size; j += (j & (-j))) {
                    indexedMatrix[i][j] += additiveElement;
                }
            }
        }
    }
}
