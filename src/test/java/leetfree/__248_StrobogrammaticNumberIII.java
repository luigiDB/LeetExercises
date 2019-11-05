package leetfree;

/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
Example:
Input: low = "50", high = "100"
Output: 3
Explanation: 69, 88, and 96 are three strobogrammatic numbers.
Note:
Because the range might be a large number, the low and high numbers are represented as string.
 */
public class __248_StrobogrammaticNumberIII {
    /**
     * Standard solution from strobogrammatic numbers
     */
    class Solution {
        private char[][] PAIRS = new char[][]{{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};

        public int strobogrammaticInRange(String low, String high) {
            int cnt = 0;
            for (int i = low.length(); i <= high.length(); ++i) {
                cnt += dfs(low, high, new char[i], 0, i - 1);
            }
            return cnt;
        }

        private int dfs(String low, String high, char[] c, int l, int r) {
            if (l > r) {
                String s = new String(c);
                if (c.length == low.length() && s.compareTo(low) < 0 ||
                        c.length == high.length() && s.compareTo(high) > 0) {
                    return 0;
                } else {
                    return 1;
                }
            }
            int cnt = 0;
            for (char[] pair : PAIRS) {
                c[l] = pair[0];
                c[r] = pair[1];
                if (c.length != 1 && c[0] == '0') {
                    continue;
                }
                if (l == r && pair[0] != pair[1]) {
                    continue;
                }
                cnt += dfs(low, high, c, l + 1, r - 1);
            }
            return cnt;
        }
    }
}
