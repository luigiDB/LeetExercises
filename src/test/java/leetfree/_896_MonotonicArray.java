package leetfree;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * An array is monotonic if it is either monotone increasing or monotone decreasing.
 * An array nums is monotone increasing if for all i <= j, nums[i] <= nums[j].  An array nums is monotone decreasing
 * if for all i <= j, nums[i] >= nums[j].
 * Return true if and only if the given array nums is monotonic.
 */
public class _896_MonotonicArray {
    @Test
    public void trueTests() {
        assertTrue(isMonotonic(new int[]{1, 2, 2, 3}));
        assertTrue(isMonotonic(new int[]{6, 5, 4, 4}));
        assertTrue(isMonotonic(new int[]{1, 2, 4, 5}));
        assertTrue(isMonotonic(new int[]{1, 1, 1}));
    }

    @Test
    public void falseTests() {
        assertFalse(isMonotonic(new int[]{1, 3, 2}));
    }

    public boolean isMonotonic(int[] nums) {
        Boolean increasing = null;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1])
                continue;

            if (increasing == null)
                increasing = nums[i] < nums[i + 1];

            if (
                    (nums[i] < nums[i + 1] & !increasing)
                            || (nums[i] > nums[i + 1] & increasing)
            )
                return false;
        }
        return true;
    }
}
