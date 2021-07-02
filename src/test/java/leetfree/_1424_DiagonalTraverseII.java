package leetfree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;

/**
 * Given a list of lists of integers, nums, return all elements of nums in diagonal order as shown in the below images.
 */
public class _1424_DiagonalTraverseII {

    @Test
    public void a() {
        List<List<Integer>> input = List.of(
                List.of(1, 2, 3),
                List.of(4, 5, 6),
                List.of(7, 8, 9)
        );
        assertArrayEquals(new int[]{1, 4, 2, 7, 5, 3, 8, 6, 9}, findDiagonalOrder(input));
    }

    @Test
    public void b() {
        List<List<Integer>> input = List.of(
                List.of(1, 2, 3, 4, 5),
                List.of(6, 7),
                List.of(8),
                List.of(9, 10, 11),
                List.of(12, 13, 14, 15, 16)
        );
        assertArrayEquals(new int[]{1, 6, 2, 8, 7, 3, 9, 4, 12, 10, 5, 13, 11, 14, 15, 16}, findDiagonalOrder(input));
    }

    @Test
    public void c() {
        List<List<Integer>> input = List.of(
                List.of(1, 2, 3),
                List.of(4),
                List.of(5, 6, 7),
                List.of(8),
                List.of(9, 10, 11)
        );
        assertArrayEquals(new int[]{1, 4, 2, 5, 3, 8, 6, 9, 7, 10, 11}, findDiagonalOrder(input));
    }

    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int countElements = 0;
        for (int i = 0; i < nums.size(); i++)
            countElements += nums.get(i).size();

        int[] visit = new int[countElements];
        int visitIndex = 0;

        for (int i = 0; i < nums.size(); i++) {
            for (int row = i, col = 0; row >= 0; row--, col++) {
                if (nums.get(row).size() > col)
                    visit[visitIndex++] = nums.get(row).get(col);
            }
        }
        for (int i = 1; i < nums.get(nums.size() - 1).size(); i++) {
            for (int row = nums.size() - 1, col = i; row >= 0; row--, col++) {
                if (nums.get(row).size() > col)
                    visit[visitIndex++] = nums.get(row).get(col);
            }
        }

        return visit;
    }

    class Solution {
        /**
         * This solution is based on the fact that the values on the same antidiagonal have the same "row + column" value.
         * By simply iterating the matrix from the last row we can build each antidiagonal in the correct sequence and
         * reconstructing the sequence of diagonals is easy since "row+column" is in order starting from 0.
         */
        public int[] findDiagonalOrder(List<List<Integer>> nums) {
            int n = 0, i = 0, maxKey = 0;
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int r = nums.size() - 1; r >= 0; --r) { // The values from the bottom rows are the starting values of the diagonals.
                for (int c = 0; c < nums.get(r).size(); ++c) {
                    map.putIfAbsent(r + c, new ArrayList<>());
                    map.get(r + c).add(nums.get(r).get(c));
                    maxKey = Math.max(maxKey, r + c);
                    n++;
                }
            }
            int[] ans = new int[n];
            for (int key = 0; key <= maxKey; ++key) {
                List<Integer> value = map.get(key);
                if (value == null) continue;
                for (int v : value) ans[i++] = v;
            }
            return ans;
        }
    }
}
