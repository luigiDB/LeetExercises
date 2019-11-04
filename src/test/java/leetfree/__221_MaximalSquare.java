package leetfree;

import org.junit.Assert;
import org.junit.Test;

/*
Given a 2D binary matrix filled with '0''s and '1''s, find the largest square containing only '1''s and return its area.
Example:
Input:
'1' '0' '1' '0' '0'
'1' '0' '1' '1' '1'
'1' '1' '1' '1' '1'
'1' '0' '0' '1' '0'
Output: 4
 */
public class __221_MaximalSquare {
    @Test
    public void given() {
        char[][] inputMatrix = new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        Assert.assertEquals(4, maximalSquare(inputMatrix));
    }

    @Test
    public void test2() {
        char[][] inputMatrix = new char[][]{
                {'1', '0', '1', '0'},
                {'1', '0', '1', '1'},
                {'1', '0', '1', '1'},
                {'1', '1', '1', '1'}
        };
        Assert.assertEquals(4, maximalSquare(inputMatrix));
    }

    @Test
    public void test3() {
        char[][] inputMatrix = new char[][]{
                {'0', '0', '0', '1'},
                {'1', '1', '0', '1'},
                {'1', '1', '1', '1'},
                {'0', '1', '1', '1'},
                {'0', '1', '1', '1'}
        };
        Assert.assertEquals(9, maximalSquare(inputMatrix));
    }

    @Test
    public void extremeCases() {
        char[][] inputMatrix = new char[][]{
                {'1'}
        };
        Assert.assertEquals(1, maximalSquare(inputMatrix));
        inputMatrix = new char[][]{
                {'0'}
        };
        Assert.assertEquals(0, maximalSquare(inputMatrix));

    }

    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return 0;
        int[][] counterMatrix = new int[matrix.length + 1][matrix[0].length + 1];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '1') {
                    counterMatrix[i + 1][j + 1] = Math.min(Math.min(counterMatrix[i + 1][j], counterMatrix[i][j + 1]), counterMatrix[i][j]) + 1;
                    max = Math.max(max, counterMatrix[i + 1][j + 1]);
                }
            }
        }
        return (max == Integer.MIN_VALUE) ? 0 : max * max;
    }
}