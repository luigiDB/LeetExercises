package utils.subarray;

import java.util.PriorityQueue;

public class K_thLargestSumContiguousSubarray {

    static public int find(int[] input, int k) {
        PriorityQueue<Integer> sums = new PriorityQueue<>((a, b) -> b - a);

        int[] partialSum = new int[input.length + 1];
        for (int i = 1; i < input.length + 1; i++) {
            partialSum[i] = partialSum[i - 1] + input[i - 1];
        }

        for (int i = 0; i < partialSum.length; i++) {
            for (int j = i + 1; j < partialSum.length; j++) {
                sums.offer(partialSum[j] - partialSum[i]);
            }
        }

        int res = 0;
        for (int i = 0; i < k; i++) {
            res = sums.poll();
        }
        return res;
    }
}
