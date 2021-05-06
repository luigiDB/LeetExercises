package leetfree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
Your car starts at position 0 and speed +1 on an infinite number line.  (Your car can go into negative positions.)
Your car drives automatically according to a sequence of instructions A (accelerate) and R (reverse).
When you get an instruction "A", your car does the following: position += speed, speed *= 2.
When you get an instruction "R", your car does the following: if your speed is positive then speed = -1 , otherwise speed = 1.  (Your position stays the same.)
For example, after commands "AAR", your car goes to positions 0->1->3->3, and your speed goes to 1->2->4->-1.
Now for some target position, say the length of the shortest sequence of instructions to get there.
Example 1:
Input:
target = 3
Output: 2
Explanation:
The shortest instruction sequence is "AA".
Your position goes from 0->1->3.
Example 2:
Input:
target = 6
Output: 5
Explanation:
The shortest instruction sequence is "AAARA".
Your position goes from 0->1->3->7->7->6.
Note:
1 <= target <= 10000.
 */
public class __818_RaceCar {
    /**
     * Naive approach: iteratively try every possible combination; at each step try add an A and an R
     * Realistic approach:
     * 1_   decide a direction so start with a R in case target is negative
     * 2_   add A until P(i) < target < P(i+1)
     * at that point there are two possible routes
     * a_  from P(i) add RR and restart until point 2
     * b_  from P(i+1) add RR and restart from step 2 but with opposite direction (aka until P(i+1) < target < P(i))
     * eg to demonstrate the approach with target 8
     */
     /*     0   A   A   A   A
            0   1   3   7   15
            1   2   4   8   16
                                R   A   A   A           start with an R from 15 4 steps
                                15  14  12  8
                                -1  -2  -4  -8
                            R   R   A                   start with RR from 7 3 steps
                            7   7   8
                            -1  1   2
            So we have AAAARAAA if we came back from 15 or AAARRA if we reset the progression at 7
     */

    /**
     * Notice the pruning conditions. Here is the reasoning -
     *
     * For 'A', we will never want to go beyond 2 x target
     * For 'R', we won't go beyond 2 x target either
     * According to the constraints, target >= 1. Hence, we never consider negative numbers for the queue.
     * Time complexity not sure, but maybe O(target * log(target)). Would appreciate if someone could confirm. Thanks.
     */
    class Solution {
        public int racecar(int target) {
            if (target == 0 || target == 1) return target;
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{0, 1, 0});
            Set<String> seen = new HashSet<>();
            seen.add(getRep(0, 1));
            while (!queue.isEmpty()) {
                int[] curr = queue.remove();
                if (curr[0] == target) return curr[2];
                // A
                int pos = curr[0];
                int speed = curr[1];
                if (pos + speed > 0 && pos + speed < 2 * target) {
                    pos = curr[0] + curr[1];
                    speed *= 2;
                    if (seen.add(getRep(pos, speed))) {
                        queue.add(new int[]{pos, speed, curr[2] + 1});
                    }
                }
                // R
                pos = curr[0];
                if (pos > 0 && pos < 2 * target) {
                    speed = curr[1] > 0 ? -1 : 1;
                    if (seen.add(getRep(pos, speed))) {
                        queue.add(new int[]{pos, speed, curr[2] + 1});
                    }
                }
            }
            return -1;
        }

        private String getRep(int pos, int speed) {
            return new StringBuilder().append(pos).append("-").append(speed).toString();
        }
    }
}
