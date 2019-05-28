package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class _280_WiggleSort {
    /*Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

    For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].*/

    @Test
    public void example() {
        int[] input = new int[]{3, 5, 2, 1, 6, 4};
        int[] sorted = wiggleSort(input);
        Assert.assertTrue(wiggleProperty(sorted));
    }

    /*
      <   >   <   >   <
    3   5   2   1   6   4
    3   5   1   6   4   2
    3   5   1   6   2   4
    3   5   1   6   2   4
    */
    private boolean wiggleProperty(int[] sorted) {
        for (int i = 0; i < sorted.length - 1; i++) {
            if (i % 2 == 0) {
                if (sorted[i] > sorted[i + 1]) {
                    return false;
                }
            } else {
                if (sorted[i] < sorted[i + 1])
                    return false;
            }
        }
        return true;
    }

    @Test
    public void verifyWigglePropertyFunction() {
        Assert.assertTrue(wiggleProperty(new int[]{1, 6, 2, 5, 3, 4}));
        Assert.assertFalse(wiggleProperty(new int[]{1, 6, 10, 5, 3, 4}));
    }

    private void switchElem(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private int[] wiggleSort(int[] input) {
        int[] result = Arrays.copyOf(input, input.length);

        boolean flipPresent = true;
        while (flipPresent) {
            flipPresent = false;
            for (int i = 0; i < result.length-1; i++) {
                if (i % 2 == 0) {
                    if (result[i] > result[i + 1]) {
                        flipPresent = true;
                        switchElem(result, i, i + 1);
                    }
                } else {
                    if (result[i] < result[i + 1]) {
                        flipPresent = true;
                        switchElem(result, i, i + 1);
                    }
                }
            }
        }

        return result;
    }

}
