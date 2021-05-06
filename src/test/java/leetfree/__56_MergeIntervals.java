package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * <p>
 * Example 1:
 * <p>
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 * <p>
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */
public class __56_MergeIntervals {
    //TODO:
    // Can be resolved as the job scheduler with complexity O(N logN) + O(N)
    // or without reordering by keeping a list of the intervals ordered for the start or finish time and inserting
    // the new element with binary search (remember that when an interval is enlarged we need to check if the next
    // intervals are to be coalesced in the first one). Complexity O(N logN)  [N for the input and logN for the
    // insertion with binary search]


    @Test
    public void given() {
        int[][] input = new int[][]{
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18},
        };
        int[][] output = new int[][] {
                {1,6},
                {8,10},
                {15,18},
        };
        Assert.assertArrayEquals(output, merge(input));
    }

    @Test
    public void testCorrectUpdateOfLastIndex() {
        int[][] input = new int[][]{
                {1, 4},
                {2, 3}
        };
        int[][] output = new int[][] {
                {1,4}
        };
        Assert.assertArrayEquals(output, merge(input));
    }

    public int[][] merge(int[][] intervals) {
        if(intervals.length == 0)
            return new int[0][];

        PriorityQueue<int[]> order = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int[] interval : intervals) {
            order.offer(interval);
        }
        List<int[]> result = new LinkedList<>();

        int[] current = order.peek();
        while(!order.isEmpty()) {
            int[] poll = order.poll();
            if(poll[0] <= current[1])
                current[1] = Math.max(poll[1], current[1]);
            else {
                result.add(current);
                current = poll;
            }
        }
        result.add(current);

        return result.toArray(new int[0][]);
    }
}
