package leetfree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can
 * flip at most k 0's.
 */
public class _1004_MaxConsecutiveOnesIII {

    @Test
    public void a() {
        assertEquals(6, longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
    }

    @Test
    public void b() {
        assertEquals(10, longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3));
    }

    public int longestOnes(int[] nums, int k) {
        int maxWindow = 0;
        int left = 0;
        int zeroCounter = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 1) {
                while (zeroCounter >= k) {
                    if (nums[left++] == 0)
                        zeroCounter--;
                }
                zeroCounter++;
            }
            maxWindow = Math.max(maxWindow, i - left + 1);
        }

        return maxWindow;
    }
}
