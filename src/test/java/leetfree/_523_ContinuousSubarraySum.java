package leetfree;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Given an integer array nums and an integer k, return true if nums has a continuous subarray of size at least two
 * whose elements sum up to a multiple of k, or false otherwise.
 * An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
 */
public class _523_ContinuousSubarraySum {

    @Test
    public void a() {
        assertTrue(checkSubarraySum(new int[]{23, 2, 4, 6, 7}, 6));
        assertTrue(checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 6));
    }

    @Test
    public void b() {
        assertFalse(checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 13));
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>() {{
            put(0, -1);
        }};
        int runningSum = 0;
        for (int i = 0; i < nums.length; i++) {
            runningSum = (runningSum + nums[i]) % k;
            Integer prev = map.get(runningSum);
            if (prev != null)
                if (i - prev > 1)
                    return true;
                else
                    map.put(runningSum, i);
        }
        return false;
    }
}
