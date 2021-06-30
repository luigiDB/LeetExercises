package leetfree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * You are given an integer array ribbons, where ribbons[i] represents the length of the ith ribbon, and an integer k.
 * You may cut any of the ribbons into any number of segments of positive integer lengths, or perform no cuts at all.
 * For example, if you have a ribbon of length 4, you can:
 * Keep the ribbon of length 4,
 * Cut it into one ribbon of length 3 and one ribbon of length 1,
 * Cut it into two ribbons of length 2,
 * Cut it into one ribbon of length 2 and two ribbons of length 1, or
 * Cut it into four ribbons of length 1.
 * Your goal is to obtain k ribbons of all the same positive integer length. You are allowed to throw away any excess
 * ribbon as a result of cutting.
 * Return the maximum possible positive integer length that you can obtain k ribbons of, or 0 if you cannot obtain k
 * ribbons of the same length.
 *
 * Constraints:
 * 1 <= ribbons.length <= 105
 * 1 <= ribbons[i] <= 105
 * 1 <= k <= 109
 */
public class _1891_CuttingRibbons {


    @Test
    public void a() {
        assertEquals(5, maxLength(new int[]{9, 7, 5}, 3));
    }

    @Test
    public void b() {
        assertEquals(4, maxLength(new int[]{7, 5, 9}, 4));
    }

    @Test
    public void c() {
        assertEquals(0, maxLength(new int[]{5, 7, 9}, 22));
    }

    public int maxLength(int[] ribbons, int k) {
        int l = 1, r = 100000;
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (!possible(ribbons, k, mid))
                r = mid - 1;
            else
                l = mid;
        }

        return possible(ribbons, k, l) ? l : 0;
    }

    private boolean possible(int[] ribbons, int k, int n) {
        int count = 0;
        for (int r : ribbons)
            count += (r / n);
        return count >= k;
    }

}
