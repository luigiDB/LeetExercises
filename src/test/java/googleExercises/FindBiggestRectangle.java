package googleExercises;

import org.junit.Assert;
import org.junit.Test;

import java.util.PriorityQueue;

public class FindBiggestRectangle {

    @Test
    public void validTest() {
        int[][] matrix = new int[][]{
                {0, 1, 1, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 0, 1}
        };
        Assert.assertEquals(18, maxRectangle(matrix));
    }

    @Test
    public void testSmallCase() {
        int[][] matrix = new int[][]{
                {1, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 1}
        };
        Assert.assertEquals(4, maxRectangle(matrix));
    }

    @Test
    public void testFalseCase() {
        int[][] matrix = new int[][]{
                {1, 0, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 1}
        };
        Assert.assertEquals(0, maxRectangle(matrix));
    }

    private int maxRectangle(int[][] matrix) {
        //1_ Calculate fill right direction
        //O(n*m)
        int[][] righthMatrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            boolean foundOne = false;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    foundOne = true;
                }
                if (foundOne) {
                    righthMatrix[i][j] = 1;
                }
            }
        }

        //2_ Calculate fill left direction
        //O(n*m)
        int[][] leftMatrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            boolean foundOne = false;
            for (int j = matrix[i].length - 1; j >= 0; j--) {
                if (matrix[i][j] == 1) {
                    foundOne = true;
                }
                if (foundOne) {
                    leftMatrix[i][j] = 1;
                }
            }
        }

        //3_ & the two matrices
        //O(n*m)
        int[][] supportMatrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                supportMatrix[i][j] = righthMatrix[i][j] * leftMatrix[i][j];
            }
        }

        //4_ Evaluate histogram height
        //O(n*m)
        int[][] histogramMatrix = new int[matrix.length][matrix[0].length];
        for (int j = 0; j < matrix[0].length; j++) {
            int count = 0;
            for (int i = 0; i < matrix.length; i++) {
                if (supportMatrix[i][j] == 1) {
                    histogramMatrix[i][j] = ++count;
                } else {
                    if (count > 0) {
                        ++count;
                    }
                }
            }
        }

        //5_ Find all the valid rectangles
        //O(n* ( m*m ) )
        PriorityQueue<Integer> rectangles = new PriorityQueue<>((a, b) -> b - a);
        rectangles.add(0);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                for (int k = j + 1; k < matrix[i].length; k++) {
                    if (matrix[i][j] == 1 && matrix[i][k] == 1) {
                        int maxHeight = Math.min(histogramMatrix[i][j], histogramMatrix[i][k]);
                        if (maxHeight == 1)
                            continue;
                        if (matrix[i - maxHeight + 1][j] != 1
                                || matrix[i - maxHeight + 1][k] != 1)
                            continue;
                        rectangles.add(maxHeight * (k - j + 1));
                    }
                }
            }
        }

        return rectangles.poll();
    }
}
