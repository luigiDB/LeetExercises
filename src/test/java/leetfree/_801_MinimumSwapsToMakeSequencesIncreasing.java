package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/*
We have two integer sequences A and B of the same non-zero length.
We are allowed to swap elements A[i] and B[i].  Note that both elements are in the same index position in their 
respective sequences.
At the end of some number of swaps, A and B are both strictly increasing.  (A sequence is strictly increasing if and 
only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)
Given A and B, return the minimum number of swaps to make both sequences strictly increasing.  It is guaranteed that 
the given input always makes it possible.
Example:
Input: A = [1,3,5,4], B = [1,2,3,7]
Output: 1
Explanation: 
Swap A[3] and B[3].  Then the sequences are:
A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
which are both strictly increasing.
Note:
A, B are arrays with the same length, and that length will be in the range [1, 1000].
A[i], B[i] are integer values in the range [0, 2000].
 */
public class _801_MinimumSwapsToMakeSequencesIncreasing {
    @Test
    public void given() {
        Assert.assertEquals(1, minSwap(
                new int[]{1, 3, 5, 4},
                new int[]{1, 2, 3, 7}
        ));
    }

    @Test
    public void testMultipleSwap() {
        Assert.assertEquals(2, minSwap(
                new int[]{5, 30, 15, 50},
                new int[]{20, 10, 40, 20}
        ));
    }

    @Test
    public void testThatPreventiveSwapIsMoreUsefull() {
        Assert.assertEquals(1, minSwap(
                new int[]{0, 4, 4, 5, 9},
                new int[]{0, 1, 6, 8, 10}
        ));
    }

    @Test
    public void failedUnit() {
        Assert.assertEquals(2, minSwap(
                new int[]{3, 5, 6, 9, 14, 15, 15, 18, 17, 20},
                new int[]{3, 4, 5, 8, 10, 14, 17, 16, 19, 19}
        ));
    }

    public int minSwap(int[] A, int[] B) {
        return countSwaps(A, B, 1);
    }

    private int countSwaps(int[] A, int[] B, int i) {
        if (i == A.length)
            return 0;

        if (A[i] <= A[i - 1] || B[i] <= B[i - 1]) {
            //must Swap
            int[] copiedA = Arrays.copyOf(A, A.length);
            int[] copiedB = Arrays.copyOf(B, B.length);
            swap(copiedA, copiedB, i);
            return 1 + countSwaps(copiedA, copiedB, i + 1);
        } else {
            if (A[i] > B[i - 1] && B[i] > A[i - 1]) {
                //swap is possible
                int[] copiedA = Arrays.copyOf(A, A.length);
                int[] copiedB = Arrays.copyOf(B, B.length);
                swap(copiedA, copiedB, i);
                int countSwaps = countSwaps(copiedA, copiedB, i + 1);
                return Math.min(countSwaps(A, B, i + 1), 1 + countSwaps);
            }
            return countSwaps(A, B, i + 1);
        }
    }

    private void swap(int[] A, int[] B, int i) {
        int tmp = A[i];
        A[i] = B[i];
        B[i] = tmp;
    }
}
