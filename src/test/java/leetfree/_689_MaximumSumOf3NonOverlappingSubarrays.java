package leetfree;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Given an integer array nums and an integer k, find three non-overlapping subarrays of length k with maximum sum and
 * return them.
 * Return the result as a list of indices representing the starting position of each interval (0-indexed). If there
 * are multiple answers, return the lexicographically smallest one.
 */
public class _689_MaximumSumOf3NonOverlappingSubarrays {

    @Test
    public void a() {
        int[] input = {1, 2, 1, 2, 6, 7, 5, 1};
        assertArrayEquals(
                new int[]{0, 3, 5},
                maxSumOfThreeSubarrays(input, 2)
        );
    }

    @Test
    public void b() {
        int[] input = {1, 2, 1, 2, 1, 2, 1, 2, 1};
        assertArrayEquals(
                new int[]{0, 2, 4},
                maxSumOfThreeSubarrays(input, 2)
        );
    }

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int maxSum = 0;
        int[] startIndexes = null;

        int[] cumulativeSum = new int[nums.length + 1];
        for (int i = 1; i < cumulativeSum.length; i++) {
            cumulativeSum[i] = cumulativeSum[i - 1] + nums[i - 1];
        }

        for (int i = 0; i <= nums.length - 2 * k - k; i++) {
            System.out.println("a " + i);
            for (int j = i + k; j <= nums.length - k - k; j++) {
                System.out.println("  b " + j);
                for (int l = j + k; l <= nums.length - k; l++) {
                    System.out.println("    c " + l);
                    int currentSum = triSum(cumulativeSum, k, i, j, l);
                    if (currentSum > maxSum) {
                        startIndexes = new int[]{i, j, l};
                        maxSum = currentSum;
                    }
                }
            }
        }

        return startIndexes;
    }

    private int triSum(int[] cumulativeSum, int k, int i, int j, int l) {
        int sum = 0;
        sum += cumulativeSum[i + k] - cumulativeSum[i];
        sum += cumulativeSum[j + k] - cumulativeSum[j];
        sum += cumulativeSum[l + k] - cumulativeSum[l];
        return sum;
    }
}
