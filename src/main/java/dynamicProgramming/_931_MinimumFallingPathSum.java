package dynamicProgramming;

import java.util.Arrays;

/**
 * Given a square array of integers A, we want the minimum sum of a falling path through A.
 * <p>
 * A falling path starts at any element in the first row, and chooses one element from each row.  The next row's choice
 * must be in a column that is different from the previous row's column by at most one.
 * Note:
 * 1 <= A.length == A[0].length <= 100
 * -100 <= A[i][j] <= 100
 */
public class _931_MinimumFallingPathSum {

    public int minPath(int[][] input) {
        int[] partialPath = Arrays.copyOf(input[0], input.length);

        for (int row = 1; row < input.length; row++) {
            int[] support = new int[input.length];
            for (int i = 0; i < input.length; i++) {
                int minPreviousRow = Integer.MAX_VALUE;
                if (i > 0)
                    minPreviousRow = Math.min(minPreviousRow, partialPath[i - 1]);
                minPreviousRow = Math.min(minPreviousRow, partialPath[i]);
                if (i < input.length - 1)
                    minPreviousRow = Math.min(minPreviousRow, partialPath[i + 1]);
                support[i] = input[row][i] + minPreviousRow;
            }
            partialPath = support;
        }
        int min = partialPath[0];
        for (int i : partialPath) {
            min = Math.min(min, i);
        }
        return min;
    }
}
