package leetfree;

import java.util.ArrayList;

/*
You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.
Example 1:
Input: [4, 1, 8, 7]
Output: True
Explanation: (8-4) * (7-1) = 24
Example 2:
Input: [1, 2, 1, 2]
Output: False
Note:
The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.
 */
public class __679_24Game {

    /**
     * The number of possible combinations are limited possibilities thus we can test the all the possibilites.
     * We can recursively try all the combinations by executing one operation at the time.
     * At each step we keep a couple and for each possible operation we recursively apply the operation on the
     * remaining elements and the previous result.
     */

    /**
     * Probably can also be resolved in polish notation considering that all the possible combinations are in the form
     * of
     * A B x C D x x
     * and
     * A B x C x D x
     * where ABCD are the numbers and x any operator.
     * the evaluation is also fast because we can solve directly without using the classical double stack solution
     */

    public boolean judgePoint24(int[] nums) {
        ArrayList<Double> A = new ArrayList<>();
        for (int v : nums) A.add((double) v);
        return solve(A);
    }

    private boolean solve(ArrayList<Double> nums) {
        if (nums.size() == 0) return false;
        if (nums.size() == 1) return Math.abs(nums.get(0) - 24) < 1e-6;

        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.size(); j++) {
                if (i != j) {
                    ArrayList<Double> nums2 = new ArrayList<Double>();
                    for (int k = 0; k < nums.size(); k++)
                        if (k != i && k != j) {
                            nums2.add(nums.get(k));
                        }
                    for (int k = 0; k < 4; k++) {
                        if (k < 2 && j > i) continue;
                        if (k == 0) nums2.add(nums.get(i) + nums.get(j));
                        if (k == 1) nums2.add(nums.get(i) * nums.get(j));
                        if (k == 2) nums2.add(nums.get(i) - nums.get(j));
                        if (k == 3) {
                            if (nums.get(j) != 0) {
                                nums2.add(nums.get(i) / nums.get(j));
                            } else {
                                continue;
                            }
                        }
                        if (solve(nums2)) return true;
                        nums2.remove(nums2.size() - 1);
                    }
                }
            }
        }
        return false;
    }

}
