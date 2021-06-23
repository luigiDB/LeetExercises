package leetfree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Find all strobogrammatic numbers that are of length = n.
Example:
Input:  n = 2
Output: ["11","69","88","96"]
 */
public class _247_StrobogrammaticNumberII {
    /**
     * three possible ways:
     * 1_ brute force: generate all the possible strings of 0,1,8,6,9 of size n and filter out the non strobogrammatic
     * 2_ knowing that for n(1) = {0,1,8} & n(2) = {00,11,88, 69, 96} ech subsequent n(x) can be build adding the
     * couples {0,0},{1,1},{8,8},{6,9},{9,6} left and right of each element in n(x-2). Important in the last n(x) we
     * must subtract all the strings that are starting with 0 since it's not a number
     * 3_ Can we build them iteratively starting from the most external numbers using the couples defined before
     * avoiding the {0,0} couple in the first iteration and if n is odd only add 0,1 or 8 in the middle position.
     */

    class IterativeSolution {

        public List<String> findStrobogrammatic(int n) {
            return helper(n, n);
        }

        List<String> helper(int n, int m) {
            if (n == 0)
                return new ArrayList<String>(Arrays.asList(""));
            if (n == 1)
                return new ArrayList<String>(Arrays.asList("0", "1", "8"));

            List<String> list = helper(n - 2, m);

            List<String> res = new ArrayList<String>();

            for (String s : list) {
                if (n != m) res.add("0" + s + "0");

                res.add("1" + s + "1");
                res.add("6" + s + "9");
                res.add("8" + s + "8");
                res.add("9" + s + "6");
            }

            return res;
        }


    }
}
