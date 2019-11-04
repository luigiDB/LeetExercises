package leetfree;

import org.junit.Assert;
import org.junit.Test;

/*
Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
Formally the function should:
Return true if there exists i, j, k
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.
Example 1:
Input: [1,2,3,4,5]
Output: true
Example 2:
Input: [5,4,3,2,1]
Output: false
 */
public class __334_IncreasingTripletSubsequence {

    @Test
    public void positiveTests() {
        Assert.assertTrue(increasingTriplet(new int[]{1, 2, 3, 4, 5}));
        Assert.assertTrue(increasingTriplet(new int[]{1, 5, 3, 2, 4}));
        Assert.assertTrue(increasingTriplet(new int[]{5, 1, 6, 7}));
        Assert.assertTrue(increasingTriplet(new int[]{1, 4, 2, 5, 3}));
        Assert.assertTrue(increasingTriplet(new int[]{4, 5, 2, 7}));
        Assert.assertTrue(increasingTriplet(new int[]{1, 4, 2, 3}));
    }

    @Test
    public void negativeTests() {
        Assert.assertFalse(increasingTriplet(new int[]{5, 4, 3, 2, 1}));
        Assert.assertFalse(increasingTriplet(new int[]{2, 1, 5, 0, 3}));
        Assert.assertFalse(increasingTriplet(new int[]{2, 4, -2, -3}));
    }

    public boolean increasingTriplet(int[] nums) {
        int lower = Integer.MAX_VALUE, higher = lower;
        for (int i : nums) {
            if (i > higher)
                return true;
            else if (i <= lower)
                lower = i;
            else
                higher = i;
        }
        return false;
    }
}

