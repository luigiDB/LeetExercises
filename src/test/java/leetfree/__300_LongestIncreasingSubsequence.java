package leetfree;

import org.junit.Assert;
import org.junit.Test;

/*
Given an unsorted array of integers, find the length of longest increasing subsequence.
Example:
Input: [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Note:
There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?
 */
public class __300_LongestIncreasingSubsequence {
    @Test
    public void test1() {
        int[] inputArray = {
                1, 5, 3, 2, 4
        };
        Assert.assertEquals(3, lengthOfLIS(inputArray));
        Assert.assertEquals(3, dynamicProgrammingLengthOfLIS(inputArray));
    }

    @Test
    public void givenTest() {
        int[] inputArray = {
                10, 9, 2, 5, 3, 7, 101, 18
        };
        Assert.assertEquals(4, lengthOfLIS(inputArray));
        Assert.assertEquals(4, dynamicProgrammingLengthOfLIS(inputArray));
    }

    public int lengthOfLIS(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, foo(nums, 1, i));
        }
        return (max == Integer.MIN_VALUE) ? 0 : max;
    }

    private int foo(int[] nums, int lenSoFar, int currPos) {
        //This function can be easily improved with memoization if we modify it to return the number of subsequent
        // steps not the count of previous one as now
        int max = Integer.MIN_VALUE;
        for (int i = currPos + 1; i < nums.length; i++) {
            if (nums[currPos] < nums[i])
                max = Math.max(max, foo(nums, lenSoFar + 1, i));
        }
        return (max == Integer.MIN_VALUE) ? lenSoFar : max;
    }

    public int dynamicProgrammingLengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < dp.length; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxval = Math.max(maxval, dp[j]);
                }
            }
            dp[i] = maxval + 1;
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }
}
