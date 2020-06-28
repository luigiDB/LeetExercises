package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/*
There are N network nodes, labelled 1 to N.
Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target
node, and w is the time it takes for a signal to travel from source to target.
Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is
impossible, return -1.
Example 1:
Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
Output: 2
Note:
N will be in the range [1, 100].
K will be in the range [1, N].
The length of times will be in the range [1, 6000].
All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.
 */
public class _743_NetworkDelayTime {

    @Test
    public void givenTest() {
        int[][] input = new int[][]{
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1}
        };
        Assert.assertEquals(2, networkDelayTime(input, 4, 2));
    }

    @Test
    public void testWithDifferentEdges() {
        int[][] input = new int[][]{
                {2, 1, 1},
                {2, 3, 3},
                {3, 4, 4}
        };
        Assert.assertEquals(7, networkDelayTime(input, 4, 2));
    }

    @Test
    public void testImpossibleCase() {
        int[][] input = new int[][]{
                {2, 1, 1},
                {2, 3, 1}
        };
        Assert.assertEquals(-1, networkDelayTime(input, 4, 2));
    }

    //Dijkstra implementation
    public int networkDelayTime(int[][] times, int N, int K) {

        Map<Integer, List<int[]>> fanOut = new HashMap<>();
        for (int[] edge : times) {
            fanOut.compute(edge[0], (key, value) -> {
                if (value == null) {
                    LinkedList<int[]> list = new LinkedList<>();
                    list.add(edge);
                    return list;
                } else {
                    value.add(edge);
                    return value;
                }
            });
        }

        int[] distances = new int[N + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        PriorityQueue<int[]> edgeHeap = new PriorityQueue<>(Comparator.comparingInt(x -> x[2]));

        //start
        edgeHeap.add(new int[]{K, K, 0});
        distances[K] = 0;

        while (!edgeHeap.isEmpty()) {
            int[] poll = edgeHeap.poll();
            for (int[] next : fanOut.getOrDefault(poll[1], Collections.emptyList())) {
                if (distances[next[1]] > distances[next[0]] + next[2]) {
                    distances[next[1]] = distances[next[0]] + next[2];
                    edgeHeap.add(next);
                }
            }
        }


        int maxValue = Arrays.stream(distances).skip(1).max().getAsInt();
        return maxValue == Integer.MAX_VALUE ? -1 : maxValue;
    }
}
