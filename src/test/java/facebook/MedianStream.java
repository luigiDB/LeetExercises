package facebook;

import java.util.PriorityQueue;

/**
 * You're given a list of n integers arr[0..(n-1)]. You must compute a list output[0..(n-1)] such that, for each index
 * i (between 0 and n-1, inclusive), output[i] is equal to the median of the elements arr[0..i] (rounded down to the
 * nearest integer).
 * The median of a list of integers is defined as follows. If the integers were to be sorted, then:
 * If there are an odd number of integers, then the median is equal to the middle integer in the sorted order.
 * Otherwise, if there are an even number of integers, then the median is equal to the average of the two middle-most
 * integers in the sorted order.
 */
public class MedianStream {

    int[] findMedian(int[] arr) {
        // Write your code here
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        int[] res = new int[arr.length];

        res[0] = arr[0];
        res[1] = (arr[0] + arr[1]) / 2;
        minHeap.offer(Math.max(arr[0], arr[1]));
        maxHeap.offer(Math.min(arr[0], arr[1]));

        for (int i = 2; i < arr.length; i++) {
            maxHeap.offer(arr[i]);
            if (maxHeap.peek() > minHeap.peek() || maxHeap.size() > minHeap.size() + 1)
                minHeap.offer(maxHeap.poll());

            if (maxHeap.size() == minHeap.size())
                res[i] = (maxHeap.peek() + minHeap.peek()) / 2;
            else if (maxHeap.size() > minHeap.size())
                res[i] = maxHeap.peek();
            else
                res[i] = minHeap.peek();
        }
        return res;
    }
}
