package leetfree;

import org.junit.Assert;
import org.junit.Test;

/**
 * Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.
 *
 * If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple
 * such minimum-length windows, return the one with the left-most starting index.
 *
 * Example 1:
 * Input:
 * S = "abcdebdde", T = "bde"
 * Output: "bcde"
 * Explanation:
 * "bcde" is the answer because it occurs before "bdde" which has the same length.
 * "deb" is not a smaller window because the elements of T in the window must occur in order.
 * Note:
 *
 * All the strings in the input will only contain lowercase letters.
 * The length of S will be in the range [1, 20000].
 * The length of T will be in the range [1, 100].
 */
public class _727_MinimumWindowSubsequence {
    /**
     * the naive solution is to search for the first char and iterate the rest of the srray to see if the rest of T is
     * present.
     * Better solution iterate the array and build a table where for each letter in T and each i in S it's possible to
     * find the next[i][letter]
     */
    /**
     * TODO
     */

    @Test
    public void test1() {
        Assert.assertEquals("bcde", minimumWindowSubsequence("abcdebdde","bde"));
        /**
         * in this case the table should be like
         *
         *      a   b   c   d   e   b   d   d   e
         *      0   1   2   3   4   5   6   7   8   <-- index
         *  b   1   1   5   5   5   5   -1  -1  -1
         *  d   3   3   3   3   6   6   6   7   -1
         *  e   4   4   4   4   4   8   8   8   8
         *  ^
         *  |
         *  letter
         *  The above structure can be build in linear time starting from the end.
         *  Since we are interested only in the min string we need to follow all valid path from first line to last
         *  line. In this case are valid paths:
         *      1 -> 3 -> 4
         *      5 -> 6 -> 8
         */
    }

    private String minimumWindowSubsequence(String abcdebdde, String bde) {
        return null;
    }
}
