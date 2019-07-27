package leetfree;

import org.junit.Assert;
import org.junit.Test;

/**
 * Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal, vertical
 * , diagonal or anti-diagonal.
 * Example:
 * Input:
 * [[0,1,1,0],
 *  [0,1,1,0],
 *  [0,0,0,1]]
 * Output: 3
 * Hint: The number of elements in the given matrix will not exceed 10,000.
 */
public class _562_LongestLineOfConsecutiveOneInMatrix {
    @Test
    public void given() {
        int[][] matrix = new int[][]{{0,1,1,0},
                {0,1,1,0},
                {0,0,0,1}};
        Assert.assertEquals(3, longestLine(matrix));
    }

    @Test
    public void testAntidiagonal() {
        int[][] matrix = new int[][]{{0,1,1,0},
                {0,1,1,0},
                {1,0,0,0}};
        Assert.assertEquals(3, longestLine(matrix));
    }

    @Test
    public void testComplex() {
        int[][] matrix = new int[][]{{0,1,1,0},
                {0,1,1,0},
                {1,1,1,1}};
        Assert.assertEquals(4, longestLine(matrix));
    }

    private int longestLine(int[][] matrix) {
        int longestCounter = 0;
        /**
         * 0    -   horizontal
         * 1    |   vertical
         * 2    \   diagonal
         * 3    /   antidiagonal
         */
        int [][][] supportMatrix = new int[matrix.length+1][matrix[0].length+2][4];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] == 1) {
                    supportMatrix[i+1][j+1][0] = (supportMatrix[i+1][j][0]>0)?supportMatrix[i+1][j][0]+1:1;
                    longestCounter = Math.max(longestCounter, supportMatrix[i+1][j+1][0]);

                    supportMatrix[i+1][j+1][1] = (supportMatrix[i][j+1][1]>0)?supportMatrix[i][j+1][1]+1:1;
                    longestCounter = Math.max(longestCounter, supportMatrix[i+1][j+1][1]);

                    supportMatrix[i+1][j+1][2] = (supportMatrix[i][j][2]>0)?supportMatrix[i][j][2]+1:1;
                    longestCounter = Math.max(longestCounter, supportMatrix[i+1][j+1][2]);

                    supportMatrix[i+1][j+1][3] = (supportMatrix[i][j+2][3]>0)?supportMatrix[i][j+2][3]+1:1;
                    longestCounter = Math.max(longestCounter, supportMatrix[i+1][j+1][3]);
                }
            }
        }

        return longestCounter;
    }
}
