package utils.subarray;

import java.util.PriorityQueue;

public class K_thLargestSumContiguousSubarray {

    static public int find(int[] input, int k) {
        PriorityQueue<Integer> sums = new PriorityQueue<>();

        int[] partialSum = new int[input.length + 1];
        for (int i = 1; i < input.length + 1; i++) {
            partialSum[i] = partialSum[i - 1] + input[i - 1];
        }

        for (int i = 0; i < partialSum.length; i++) {
            for (int j = i + 1; j < partialSum.length; j++) {
                if(sums.size()<k)
                    sums.offer(partialSum[j] - partialSum[i]);
                else
                    if(sums.peek()<partialSum[j] - partialSum[i]) {
                        sums.poll();
                        sums.offer(partialSum[j] - partialSum[i]);
                    }
            }
        }

        return sums.poll();
    }
}
