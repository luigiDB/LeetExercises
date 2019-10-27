package leetfree;

import java.util.LinkedList;
import java.util.Queue;

/*
Given an array nums sorted in ascending order, return true if and only if you can split it into 1 or more
subsequences such that each subsequence consists of consecutive integers and has length at least 3.
Example 1:
Input: [1,2,3,3,4,5]
Output: True
Explanation:
You can split them into two consecutive subsequences :
1, 2, 3
3, 4, 5
Example 2:
Input: [1,2,3,3,4,4,5,5]
Output: True
Explanation:
You can split them into two consecutive subsequences :
1, 2, 3, 4, 5
3, 4, 5
Example 3:
Input: [1,2,3,4,4,5]
Output: False
Constraints:
1 <= nums.length <= 10000
 */
public class __659_SplitArrayIntoConsecutiveSubsequences {
    /**
     * We can think of the problem as drawing intervals on a number line. This gives us the idea of opening and
     * closing events.
     * To illustrate this concept, say we have nums = [10, 10, 11, 11, 11, 11, 12, 12, 12, 12, 13], with no 9s and
     * no 14s. We must have two sequences start at 10, two sequences start at 11, and 3 sequences end at 12.
     * In general, when considering a chain of consecutive integers x, we must have C = count[x+1] - count[x] sequences
     * start at x+1 when C > 0, and -C sequences end at x if C < 0. Even if there are more endpoints on the intervals
     * we draw, there must be at least this many endpoints.
     * With the above example, count[11] - count[10] = 2 and count[13] - count[12] = -3 show that two sequences start
     * at 11, and three sequences end at 12.
     * Also, if for example we know some sequences must start at time 1 and 4 and some sequences end at 5 and 7, to
     * maximize the smallest length sequence, we should pair the events together in the order they occur: ie., 1
     * with 5 and 4 with 7.
     * <p>
     * The following is a real complex code that exploit the above
     */
    public boolean isPossible(int[] nums) {
        Integer prev = null;
        int prevCount = 0;
        Queue<Integer> starts = new LinkedList();
        int anchor = 0;
        for (int i = 0; i < nums.length; ++i) {
            int t = nums[i];
            if (i == nums.length - 1 || nums[i + 1] != t) {
                int count = i - anchor + 1;
                if (prev != null && t - prev != 1) {
                    while (prevCount-- > 0)
                        if (prev < starts.poll() + 2) return false;
                    prev = null;
                }

                if (prev == null || t - prev == 1) {
                    while (prevCount > count) {
                        prevCount--;
                        if (t - 1 < starts.poll() + 2)
                            return false;
                    }
                    while (prevCount++ < count)
                        starts.add(t);
                }
                prev = t;
                prevCount = count;
                anchor = i + 1;
            }
        }

        while (prevCount-- > 0)
            if (nums[nums.length - 1] < starts.poll() + 2)
                return false;
        return true;
    }
}
