package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Given an array of positive integers arr (not necessarily distinct), return the lexicographically largest
 * permutation that is smaller than arr, that can be made with exactly one swap (A swap exchanges the positions of two
 * numbers arr[i] and arr[j]). If it cannot be done, then return the same array.
 */
public class _1053_PreviousPermutationWithOneSwap {

    @Test
    public void a() {
        Assert.assertArrayEquals(
                new int[]{3, 1, 2},
                prevPermOpt1(new int[]{3, 2, 1})
        );
    }

    @Test
    public void b() {
        Assert.assertArrayEquals(
                new int[]{1, 1, 5},
                prevPermOpt1(new int[]{1, 1, 5})
        );
    }

    @Test
    public void c() {
        Assert.assertArrayEquals(
                new int[]{1, 7, 4, 6, 9},
                prevPermOpt1(new int[]{1, 9, 4, 6, 7})
        );
    }

    @Test
    public void d() {
        Assert.assertArrayEquals(
                new int[]{1, 3, 1, 3},
                prevPermOpt1(new int[]{3, 1, 1, 3})
        );
    }

    /**
     * 1. Starting from right find the first increasing element
     * 2. Starting from the element found at step 1 search the greatest element lower than the found element on the right
     * 3. Swap the elements found at step 1 and 2
     */
    public int[] prevPermOpt1(int[] arr) {
        int found = -1;
        for (int i = arr.length - 1; i > 0; i--) {
            if (arr[i - 1] > arr[i]) {
                found = i - 1;
                break;
            }
        }

        if (found == -1) // there are no possible swaps
            return arr;

        int indexMaxSoFar = Integer.MIN_VALUE;
        for (int i = found + 1; i < arr.length; i++) {
            if(arr[i] < arr[found]) {
                if(arr[i] > indexMaxSoFar)
                    indexMaxSoFar = i;
            }
        }

        int[] result = Arrays.copyOf(arr, arr.length);
        result[found] = arr[indexMaxSoFar];
        result[indexMaxSoFar] = arr[found];

        return result;
    }
}
