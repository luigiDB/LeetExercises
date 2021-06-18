package leetfree;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class _973_KClosestPointsToOrigin {

    @Test
    public void a() {
        int[][] result = kClosest(new int[][]{
                {3, 3},
                {5, -1},
                {-2, 4}
        }, 2);

        assertEquals(2, result.length);
        Set<int[]> expected = Set.of(new int[]{3, 3}, new int[]{-2, 4});
        for (int[] point: result) {
            assertTrue(setContains(expected, point));
        }
    }

    private boolean setContains(Set<int[]> set, int[] point) {
        for (int[] i: set) {
            if(Arrays.equals(i, point))
                return true;
        }
        return false;
    }

    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                Comparator.comparingDouble(this::dist).reversed()
        );

        for (int[] point: points) {
            pq.offer(point);
            while(pq.size() > K)
                pq.poll();
        }

        int[][] res = new int[pq.size()][];
        for (int i = 0; i < res.length; i++) {
            res[i] = pq.poll();
        }
        return res;
    }

    private double dist(int[] A) {
        return Math.pow(Math.abs(A[0]), 2) + Math.pow(Math.abs(A[1]), 2);
    }
}
