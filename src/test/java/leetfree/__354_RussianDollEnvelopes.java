package leetfree;

import java.util.Arrays;
import java.util.Comparator;

/*
You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into
another if and only if both the width and height of one envelope is greater than the width and height of the other
envelope.
What is the maximum number of envelopes can you Russian doll? (put one inside other)
Note:
Rotation is not allowed.
Example:
Input: [[5,4],[6,4],[6,7],[2,3]]
Output: 3
Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */
public class __354_RussianDollEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        // sort the widths and then longest increasing subseq problem for heights
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        Comparator comp = Comparator.comparing((int[] arr) -> arr[0]).thenComparing((int[] arr) -> arr[1], Comparator.reverseOrder());
        Arrays.sort(envelopes, comp);
        int result = 1;
        int[] dp = new int[envelopes.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < envelopes.length; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            result = Math.max(dp[i], result);
        }
        return result;
    }
}
