package leetfree;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero
 * elements.
 * Note that you must do this in-place without making a copy of the array.
 */
public class _283_MoveZeroes {

    @Test
    public void a() {
        int[] arr = {0, 1, 0, 3, 12};
        moveZeroes(arr);
        assertArrayEquals(new int[]{1, 3, 12, 0, 0}, arr);
    }

    @Test
    public void b() {
        int[] arr = {0};
        moveZeroes(arr);
        assertArrayEquals(new int[]{0}, arr);
    }

    public void moveZeroes(int[] nums) {
        int lastZero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[lastZero] = nums[i];
                lastZero++;
            }
        }
        for (int i = lastZero; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
