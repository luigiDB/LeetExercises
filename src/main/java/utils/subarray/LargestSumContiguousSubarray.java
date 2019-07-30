package utils.subarray;

/**
 * https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
 * KADANE ALG
 */
public class LargestSumContiguousSubarray {

    static public int maximumSumSubarray(int[] array) {
        int max = 0;
        int counter = 0;

        for (int i = 0; i < array.length; i++) {
            if (counter + array[i] < 0) {
                counter = 0;
            } else {
                counter += array[i];
                max = Math.max(max, counter);
            }
        }

        return max;
    }
}
