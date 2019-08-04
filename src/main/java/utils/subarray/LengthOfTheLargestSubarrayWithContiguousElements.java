package utils.subarray;

import org.junit.Test;

/**
 * https://www.geeksforgeeks.org/length-largest-subarray-contiguous-elements-set-1/
 */
public class LengthOfTheLargestSubarrayWithContiguousElements {

    static public int find(int[] input) {
        /**
         * this can be solved using:
         * 1_ order the array and after that counting the contiguous elements in O(n logn) + O(n)
         * 2_ reading the array and merge contiguous zone leveraging on disjoint sets
         */
        int max = input[0];
        for (int i = 0; i < input.length; i++) {
            max = Math.max(max, input[i]);
        }

        int[] disjointSet = new int[max + 1];
        for (int i = 0; i < disjointSet.length; i++) {
            disjointSet[i] = i;
        }

        for (int i = 0; i < input.length; i++) {
            int num = input[i];
            if (num != 0) {
                if (disjointSet[num - 1] != num - 1) {
                    disjointSet[num] = num - 1;
                } else {
                    disjointSet[num] = 0;
                }
            }
            if (num != max)
                if (disjointSet[num + 1] != num + 1) {
                    disjointSet[num + 1] = num;
                }
        }

        int maxLenSoFar = 0;
        boolean counting = false;
        int counter = 0;
        for (int i = max; i >= 0; i--) {
            if (disjointSet[i] != i) {
                if (!counting) {
                    counting = true;
                    counter = 1;
                } else {
                    counter++;
                    maxLenSoFar = Math.max(maxLenSoFar, counter);
                }
            } else {
                counting = false;
            }
        }
        maxLenSoFar = Math.max(maxLenSoFar, counter);
        return maxLenSoFar;
    }
}
