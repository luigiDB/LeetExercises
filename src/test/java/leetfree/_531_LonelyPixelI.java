package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class _531_LonelyPixelI {
    /**
     * Given a picture consisting of black and white pixels, find the number of black lonely pixels.
     *
     * The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.
     *
     * A black lonely pixel is character 'B' that located at a specific position where the same row and same column don't have any other black pixels.
     *
     * Example:
     * Input:
     * [['W', 'W', 'B'],
     *  ['W', 'B', 'W'],
     *  ['B', 'W', 'W']]
     *
     * Output: 3
     * Explanation: All the three 'B's are black lonely pixels.
     * Note:
     * The range of width and height of the input 2D array is [1,500].
     */

    @Test
    public void testAllPossibleCaseScenario() {
        char[][] matrix = new char[][]{
                {'W','W','W','W','W'},
                {'W','B','W','W','W'},
                {'W','W','B','B','W'},
                {'W','W','W','W','B'},
                {'W','W','W','W','B'}
        };
        Assert.assertEquals(1, countLonely(matrix));
    }

    @Test
    public void testProvidedScenario() {
        char[][] matrix = new char[][]{
                {'W','W','B'},
                {'W','B','W'},
                {'B','W','W'}
        };
        Assert.assertEquals(3, countLonely(matrix));
    }

    private int countLonely(char[][] matrix) {
        int[] rowCount = new int[matrix.length];
        int[] columnCount = new int[matrix[0].length];
        int[] lastIndex = new int[matrix.length];
        Arrays.fill(lastIndex, -1);


        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j]=='B') {
                    rowCount[i]++;
                    columnCount[j]++;
                    lastIndex[i] = j;
                }
            }
        }

        int lonelyCounter = 0;

        for (int i = 0; i < matrix.length; i++) {
            if(rowCount[i]==1) {
                if(columnCount[lastIndex[i]] ==1) {
                    lonelyCounter++;
                }
            }
        }

        return lonelyCounter;
    }
}
