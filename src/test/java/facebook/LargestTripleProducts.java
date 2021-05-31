package facebook;

import org.junit.Assert;
import org.junit.Test;

import java.util.PriorityQueue;

/**
 * You're given a list of n integers arr[0..(n-1)]. You must compute a list output[0..(n-1)] such that, for each
 * index i (between 0 and n-1, inclusive), output[i] is equal to the product of the three largest elements out of
 * arr[0..i] (or equal to -1 if i < 2, as arr[0..i] then includes fewer than three elements).
 * Note that the three largest elements used to form any product may have the same values as one another, but they
 * must be at different indices in arr.
 */
public class LargestTripleProducts {

    @Test
    public void a() {
        int[] arr_1 = {1, 2, 3, 4, 5};
        int[] expected_1 = {-1, -1, 6, 24, 60};
        int[] output_1 = findMaxProduct(arr_1);
        Assert.assertArrayEquals(expected_1, output_1);
    }

    int[] findMaxProduct(int[] arr) {
        // Write your code here
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int[] res = new int[arr.length];
        res[0] = -1;
        res[1] = -1; //Add checks for length
        res[2] = arr[0]*arr[1]*arr[2];
        pq.offer(arr[0]);
        pq.offer(arr[1]);
        pq.offer(arr[2]);

        for(int i = 3; i<arr.length; i++) {
            pq.offer(arr[i]);
            pq.poll();
            res[i] = pq.stream().reduce(1, (a, b) -> a*b);
        }
        return res;
    }
}
