package leetfree;

import org.junit.Assert;
import org.junit.Test;

/*
Two images A and B are given, represented as binary, square matrices of the same size.  (A binary matrix has only 0s
and 1s as values.)
We translate one image however we choose (sliding it left, right, up, or down any number of units), and place it on
top of the other image.  After, the overlap of this translation is the number of positions that have a 1 in both images.
(Note also that a translation does not include any kind of rotation.)
What is the largest possible overlap?
Example 1:
Input: A = [[1,1,0],
            [0,1,0],
            [0,1,0]]
       B = [[0,0,0],
            [0,1,1],
            [0,0,1]]
Output: 3
Explanation: We slide A to right by 1 unit and down by 1 unit.
Notes:
1 <= A.length = A[0].length = B.length = B[0].length <= 30
0 <= A[i][j], B[i][j] <= 1
 */
public class _835_ImageOverlap {

    @Test
    public void baseTest() {
        int[][] A = new int[][]{
                {1, 1, 0},
                {0, 1, 0},
                {0, 1, 0}
        };
        int[][] B = new int[][]{
                {0, 0, 0},
                {0, 1, 1},
                {0, 0, 1}
        };
        Assert.assertEquals(3, largestOverlap(A, B));
    }

    @Test
    public void baseInverted() {
        int[][] A = new int[][]{
                {1, 1, 0},
                {0, 1, 0},
                {0, 1, 0}
        };
        int[][] B = new int[][]{
                {0, 0, 0},
                {0, 1, 1},
                {0, 0, 1}
        };
        Assert.assertEquals(3, largestOverlap(B, A));
    }

    /*TODO: There are no really efficient way to resolve this; apart from knowing the Fourier Transform.
        The best we can obtain is N^4 time complexity like
        for any point in A
            for any point in B
                evaluate the delta and keep track of how many points participate in each delta

        return the delta with the highest number of occurrences.
         */
    public int largestOverlap(int[][] A, int[][] B) {
        int len = A.length;
        int maxOverlap = 0;
        for (int iA = 0; iA < len; iA++) {
            for (int jA = 0; jA < len; jA++) {
                for (int iB = 0; iB < len; iB++) {
                    for (int jB = 0; jB < len; jB++) {
                        if (A[iA][jA] == 1 && B[iB][jB] == 1) {
                            int overlap = 0;
                            for (int iK = 0; iK < len - Math.max(iA, iB); iK++) {
                                for (int jK = 0; jK < len - Math.max(jA, jB); jK++) {
                                    if (A[iA + iK][jA + jK] == 1 && B[iB + iK][jB + jK] == 1)
                                        overlap++;
                                }
                            }
                            maxOverlap = Math.max(maxOverlap, overlap);
                        }
                    }
                }
            }
        }
        return maxOverlap;
    }

}
