package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.hamcrest.Matchers.containsInAnyOrder;

/*
In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then
one square in an orthogonal direction.
Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.
Example 1:
Input: x = 2, y = 1
Output: 1
Explanation: [0, 0] → [2, 1]
Example 2:
Input: x = 5, y = 5
Output: 4
Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
Constraints:
|x| + |y| <= 300
 */
public class __1197_MinimumKnightMoves {

    @Test
    public void testKnightMoves() {
        Assert.assertThat(nextSteps(new int[]{2, 2, 0}), containsInAnyOrder(
                new int[]{1, 0, 1},
                new int[]{3, 0, 1},
                new int[]{4, 1, 1},
                new int[]{4, 3, 1},
                new int[]{3, 4, 1},
                new int[]{1, 4, 1},
                new int[]{0, 3, 1},
                new int[]{0, 1, 1}
        ));
    }

    @Test
    public void given1() {
        Assert.assertEquals(1, minKnightMoves(2, 1));
    }

    @Test
    public void given2() {
        Assert.assertEquals(4, minKnightMoves(5, 5));
    }

    public int minKnightMoves(int x, int y) {
        Queue<int[]> bfsQueue = new LinkedList<>();
        bfsQueue.add(new int[]{0, 0, 0});

        while (!bfsQueue.isEmpty()) {
            int[] poll = bfsQueue.poll();
            if (poll[0] == x && poll[1] == y)
                return poll[2];
            bfsQueue.addAll(nextSteps(poll));
        }

        return 0;
    }

    private List<int[]> nextSteps(int[] start) {
        List<int[]> res = new LinkedList<>();
        int[] x = {-1, 1, 2, 2, 1, -1, -2, -2};
        int[] y = {-2, -2, -1, 1, 2, 2, 1, -1};
        for (int i = 0; i < x.length; i++) {
            res.add(new int[]{start[0] + x[i], start[1] + y[i], start[2] + 1});
        }
        return res;
    }
}
