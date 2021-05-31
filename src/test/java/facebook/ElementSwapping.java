package facebook;

import org.junit.Assert;
import org.junit.Test;

/**
 * Given a sequence of n integers arr, determine the lexicographically smallest sequence which may be obtained from it
 * after performing at most k element swaps, each involving a pair of consecutive elements in the sequence.
 * Note: A list x is lexicographically smaller than a different equal-length list y if and only if, for the earliest
 * index at which the two lists differ, x's element at that index is smaller than y's element at that index.
 */
public class ElementSwapping {

    @Test
    public void a() {
        int k_1 = 2;
        int[] arr_1 = {5, 3, 1};
        int[] expected_1 = {1, 5, 3};
        int[] output_1 = findMinArray(arr_1,k_1);
        Assert.assertArrayEquals(expected_1, output_1);

    }

    @Test
    public void b() {
        int k_2 = 3;
        int[] arr_2 = {8, 9, 11, 2, 1};
        int[] expected_2 = {2, 8, 9, 11, 1};
        int[] output_2 = findMinArray(arr_2,k_2);
        Assert.assertArrayEquals(expected_2, output_2);
    }



    int moveMinToX(int[] arr, int x, int k ) {
        int minIndex = x;

        for(int i = x; i <= k+x; i++ ) {
            if(arr[i] < arr[minIndex])
                minIndex = i;
        }

        for(int i = minIndex; i > x; i-- ) {
            swap(arr, i-1, i);
        }

        return minIndex-x;
    }

    private void swap(int[] arr, int x, int minIndex) {
        int tmp = arr[x];
        arr[x] = arr[minIndex];
        arr[minIndex] = tmp;
    }

    int[] findMinArray(int[] arr, int k) {
        // Write your code here
        int remaining = k;
        int counter = 0;
        while(remaining != 0) {
            int tmp = moveMinToX(arr, counter, remaining);
            remaining -= tmp;
        }

        return arr;
    }
}
