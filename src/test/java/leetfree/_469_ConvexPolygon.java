package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class _469_ConvexPolygon {
    /**
     * Given a list of points that form a polygon when joined sequentially, find if this polygon is convex
     * (Convex polygon definition).
     * <p>
     * Note:
     * <p>
     * There are at least 3 and at most 10,000 points.
     * Coordinates are in the range -10,000 to 10,000.
     * You may assume the polygon formed by given points is always a simple polygon
     * (Simple polygon definition). In other words, we ensure that exactly two edges intersect at each vertex,
     * and that edges otherwise don't intersect each other.
     * Example 1:
     * <p>
     * [[0,0],[0,1],[1,1],[1,0]]
     * <p>
     * Answer: True
     * Example 2:
     * <p>
     * [[0,0],[0,10],[10,10],[10,0],[5,5]]
     * <p>
     * Answer: False
     */

    @Test
    public void trueCase() {
        Assert.assertTrue(isConvex(Arrays.asList(new Integer[]{0,0},
                new Integer[]{0,1},
                new Integer[]{1,1},
                new Integer[]{1,0})));
    }

    @Test
    public void falseCase() {
        Assert.assertFalse(isConvex(Arrays.asList(new Integer[]{0,0},
                new Integer[]{0,10},
                new Integer[]{10,10},
                new Integer[]{10,0},
                new Integer[]{5,5}
        )));
    }


    private static boolean isConvex(List<Integer[]> points) {
        int n = points.size();
        int pre = 0;
        int cur = 0;
        for (int i = 0; i < n; i++) {
            int dx1 = points.get((i+1)%n)[0]- points.get(i%n)[0];
            int dx2 = points.get((i+2)%n)[0] - points.get(i%n)[0];
            int dy1 = points.get((i+1)%n)[1] - points.get(i%n)[1];
            int dy2 = points.get((i+2)%n)[1] - points.get(i%n)[1];
            cur = dx1 * dy2 - dx2 * dy1;
            if (cur != 0) {
                if (cur * pre < 0)
                    return false;
                else
                    pre = cur;
            }
        }
        return true;
    }
}
