package leetfree;

import org.junit.Assert;
import org.junit.Test;

/**
 * A peak element is an element that is greater than its neighbors.
 * <p>
 * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
 * <p>
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * <p>
 * You may imagine that nums[-1] = nums[n] = -∞.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * Example 2:
 * <p>
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 1 or 5
 * Explanation: Your function can return either index number 1 where the peak element is 2,
 * or index number 5 where the peak element is 6.
 */
public class __162_FindPeakElement {
    @Test
    public void one() {
        int[] input = new int[]{1, 2, 3, 1};
        Assert.assertEquals(2, peak(input));
    }

    @Test
    public void two() {
        int[] input = new int[]{1, 2, 1, 3, 5, 6, 4};
        int peak = peak(input);
        Assert.assertTrue(peak == 1 || peak == 5);
    }

    @Test
    public void testLimitCaseLeft() {
        int[] input = new int[]{5, 4, 3, 2, 1, 0};
        int peak = peak(input);
        Assert.assertEquals(0, peak);
    }

    @Test
    public void testLimitCaseRight() {
        int[] input = new int[]{0, 1, 2, 3, 4, 5};
        int peak = peak(input);
        Assert.assertEquals(5, peak);
    }

    private int peak(int[] input) {
        return searchPeak(input, 0, input.length - 1);
    }

    private int searchPeak(int[] array, int start, int end) {
        int middle = start + (end - start) / 2;
        if (isPeak(array, middle)) {
            return middle;
        }

        if(start != end) {
            int left = searchPeak(array, start, middle);
            if (left > -1)
                return left;
        }

        if(middle+1 <= end) {
            int right = searchPeak(array, middle + 1, end);
            if (right > -1)
                return right;
        }

        return -1;
    }

    private boolean isPeak(int[] array, int middle) {

        if (middle == 0) {
            return array[middle] > array[middle + 1];
        }

        if (middle == array.length - 1)
            return array[middle] > array[middle - 1];

        return array[middle] > array[middle - 1] && array[middle] > array[middle + 1];
    }
}
