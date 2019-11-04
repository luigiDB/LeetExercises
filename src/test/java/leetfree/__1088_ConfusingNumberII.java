package leetfree;

import java.util.Arrays;
import java.util.List;

/*
We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they
become 0, 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid.
A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.
(Note that the rotated number can be greater than the original number.)
Given a positive integer N, return the number of confusing numbers between 1 and N inclusive.
Example 1:
Input: 20
Output: 6
Explanation:
The confusing numbers are [6,9,10,16,18,19].
6 converts to 9.
9 converts to 6.
10 converts to 01 which is just 1.
16 converts to 91.
18 converts to 81.
19 converts to 61.
Example 2:
Input: 100
Output: 19
Explanation:
The confusing numbers are [6,9,10,16,18,19,60,61,66,68,80,81,86,89,90,91,98,99,100].
Note:
1 <= N <= 10^9
 */
public class __1088_ConfusingNumberII {
    /**
     * Multiple solutions:
     * 1_ iterate all the numbers and check if are strobogrammatic
     * 3_ build all the numbers with 0,1,6,8,9 with a simple recursion and count the valid ones
     * 2_ given all the strobogrammatic numbers of 1 and 2 digits evaluate all the others by extending the second
     * precending with 2 strobogrammatic digits until we meet a number > N
     */

    //Example for the approach 2
    public void foo(String base, List<String> res, String N) {
        if (base.compareTo(N) > 0) return;
        if (isValid(base)) res.add(base);

        for (char c : Arrays.asList('0', '1', '6', '8', '9')) {
            foo(base+c, res, N);
        }
    }

    private boolean isValid(String base) {
        // To easiy check start from the limit and exit if a couple is not respected
        return false;
    }
}
