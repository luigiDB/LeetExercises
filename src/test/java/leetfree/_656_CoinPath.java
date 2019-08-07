package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

/**
 * Given an array A (index starts at 1) consisting of N integers: A1, A2, ..., AN and an integer B. The integer B
 * denotes that from any place (suppose the index is i) in the array A, you can jump to any one of the place in the
 * array A indexed i+1, i+2, …, i+B if this place can be jumped to. Also, if you step on the index i, you have to pay
 * Ai coins. If Ai is -1, it means you can’t jump to the place indexed i in the array.
 * <p>
 * Now, you start from the place indexed 1 in the array A, and your aim is to reach the place indexed N using the
 * minimum coins. You need to return the path of indexes (starting from 1 to N) in the array you should take to get to
 * the place indexed N using minimum coins.
 * <p>
 * If there are multiple paths with the same cost, return the lexicographically smallest such path.
 * <p>
 * If it's not possible to reach the place indexed N then you need to return an empty array.
 * <p>
 * Example 1:
 * Input: [1,2,4,-1,2], 2
 * Output: [1,3,5]
 * Example 2:
 * Input: [1,2,4,-1,2], 1
 * Output: []
 * Note:
 * Path Pa1, Pa2, ..., Pan is lexicographically smaller than Pb1, Pb2, ..., Pbm, if and only if at the first i where
 * Pai and Pbi differ, Pai < Pbi; when no such i exists, then n < m.
 * A1 >= 0. A2, ..., AN (if exist) will in the range of [-1, 100].
 * Length of A is in the range of [1, 1000].
 * B is in the range of [1, 100].
 */
public class _656_CoinPath {

    @Test
    public void simplePossibleTest() {
        Assert.assertArrayEquals(new int[]{1, 3, 5}, coinpath(new int[]{1, 2, 4, -1, 2}, 2));
    }

    @Test
    public void simpleImpossibleTest() {
        Assert.assertArrayEquals(new int[]{}, coinpath(new int[]{1, 2, 4, -1, 2}, 1));
    }

    private int[] coinpath(int[] A, int B) {
        int len = A.length;
        int[] minSoFar = new int[len];
        Arrays.fill(minSoFar, -1);
        int[] parent = new int[len];
        Arrays.fill(parent, -1);

        minSoFar[0] = A[0];
        for (int i = 1; i < len; i++) {
            if (A[i] == -1)
                continue;
            int min = Integer.MAX_VALUE;
            int minPos = Integer.MAX_VALUE;
            for (int j = i - 1; j >= i - B; j--) {
                if (j < 0)
                    continue;
                if (A[j] != -1) {
                    int tempMin = Math.min(min, A[j]);
                    if (tempMin <= min) {
                        minPos = j;
                        min = tempMin;
                    }
                }
            }
            if (minPos == Integer.MAX_VALUE)
                return new int[]{};
            minSoFar[i] = minSoFar[minPos] + A[i];
            parent[i] = minPos;
        }

        Stack<Integer> visitOrder = new Stack<>();
        visitOrder.push(len - 1);
        do {
            int lastVisited = visitOrder.peek();
            visitOrder.push(parent[lastVisited]);
        } while (visitOrder.peek() != -1);
        visitOrder.pop();   //drop -1

        int[] returnArray = new int[visitOrder.size()];
        while (!visitOrder.isEmpty()) {
            returnArray[returnArray.length - visitOrder.size()] = visitOrder.pop() + 1;
        }
        return returnArray;
    }
}
