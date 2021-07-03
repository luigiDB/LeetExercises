package leetfree;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
 */
public class _525_ContiguousArray {

    @Test
    public void a() {
        int[] input = {0, 1};
        assertEquals(2, findMaxLength(input));
    }

    @Test
    public void b() {
        int[] input = {0, 1, 0};
        assertEquals(2, findMaxLength(input));
    }

    @Test
    public void c() {
        int[] input = {0, 0, 0, 1, 0, 1, 1};
        assertEquals(6, findMaxLength(input));
    }

    // Can be optimized by keeping only the map and counting the zeros (-1) and ones (+1) and keeping the result
    // in the map
    public int findMaxLength(int[] nums) {
        int[] countZero = new int[nums.length + 1];
        int[] countOne = new int[nums.length + 1];
        //we just need to store the earliest occurrence since we are interested in the biggest window
        Map<Integer, Integer> occurrences = new HashMap<>();
        occurrences.put(0, 0);
        int maxWindow = 0;

        for (int i = 1; i < nums.length + 1; i++) {
            if (nums[i - 1] == 0) {
                countZero[i] = countZero[i - 1] + 1;
                countOne[i] = countOne[i - 1];
            } else {
                countZero[i] = countZero[i - 1];
                countOne[i] = countOne[i - 1] + 1;
            }

            int count = countZero[i] - countOne[i];
            if (!occurrences.containsKey(count))
                occurrences.put(count, i);
            maxWindow = Math.max(maxWindow, i - occurrences.get(count));
        }

        return maxWindow;
    }
}

