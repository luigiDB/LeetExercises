package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.
If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple
such minimum-length windows, return the one with the left-most starting index.
Example 1:
Input:
S = "abcdebdde", T = "bde"
Output: "bcde"
Explanation:
"bcde" is the answer because it occurs before "bdde" which has the same length.
"deb" is not a smaller window because the elements of T in the window must occur in order.
Note:
All the strings in the input will only contain lowercase letters.
The length of S will be in the range [1, 20000].
The length of T will be in the range [1, 100].
*/
public class _727_MinimumWindowSubsequence {
    /**
     * the naive solution is to search for the first char and iterate the rest of the array to see if the rest of T is
     * present.
     * Better solution iterate the array and build a table where for each letter in T and each i in S it's possible to
     * find the next[i][letter]
     */
    /**
     * TODO
     */

    @Test
    public void test1() {
        Assert.assertEquals("bcde", minimumWindowSubsequence("abcdebdde", "bde"));
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
        /*
        The next matrix can be constructed with:
        int[][] matrix = new int[T.length()][S.length()];
        init last matrix[for each value][S.length()] = + inf
        for (int i = S.length() -2; i >= 0 ; i--) {
            for(int j = 0; j <= T.length() ; j++) {
                if(S[i+1] == T[j])
                    matrix[j][i] = i+1;
                else
                    matrix[j][i] = matrix[j][i+1];
            }
        }

        At this point given all the possible starting point find the shortest substring is easy since we only need to
        follow the precedending matrix for each possible start.
        eg if matrix[0][3] the start the next step is matrix[1][ matrix[0][3] ]  and so on until the last row of the matrix
         */
        return null;
    }

    /**
     * Leet code provided solution pretty similar approach but with a larger matrix
     */
    class Solution {
        public String minWindow(String S, String T) {
            int N = S.length();
            int[] last = new int[26];
            int[][] nxt = new int[N][26];
            Arrays.fill(last, -1);

            for (int i = N - 1; i >= 0; --i) {
                last[S.charAt(i) - 'a'] = i;
                for (int k = 0; k < 26; ++k) {
                    nxt[i][k] = last[k];
                }
            }

            List<int[]> windows = new ArrayList();
            for (int i = 0; i < N; ++i) {
                if (S.charAt(i) == T.charAt(0))
                    windows.add(new int[]{i, i});
            }
            for (int j = 1; j < T.length(); ++j) {
                int letterIndex = T.charAt(j) - 'a';
                for (int[] window : windows) {
                    if (window[1] < N - 1 && nxt[window[1] + 1][letterIndex] >= 0) {
                        window[1] = nxt[window[1] + 1][letterIndex];
                    } else {
                        window[0] = window[1] = -1;
                        break;
                    }
                }
            }

            int[] ans = {-1, S.length()};
            for (int[] window : windows) {
                if (window[0] == -1) break;
                if (window[1] - window[0] < ans[1] - ans[0]) {
                    ans = window;
                }

            }
            return ans[0] >= 0 ? S.substring(ans[0], ans[1] + 1) : "";
        }
    }
}
