package utils.subarray;

/**
 * https://www.geeksforgeeks.org/largest-sum-contiguous-increasing-subarray/
 */
public class LargestSumContiguousIncreasingSubarray {

    static public int  find(int[] input) {
        int maxSoFar = input[0];
        int sumSoFar = input[0];
        for (int i = 1; i < input.length; i++) {
            if(input[i] > input[i-1]) {
                sumSoFar += input[i];
            } else {
                sumSoFar = input[i];
            }
            maxSoFar = Math.max(maxSoFar, sumSoFar);
        }

        return maxSoFar;
    }
}
