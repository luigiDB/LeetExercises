package leetfree;

import java.util.*;

/*
Given a string of numbers and operators, return all possible results from computing all the different possible ways to
group numbers and operators. The valid operators are +, - and *.
Example 1:
Input: "2-1-1"
Output: [0, 2]
Explanation:
((2-1)-1) = 0
(2-(1-1)) = 2
Example 2:
Input: "2*3-4*5"
Output: [-34, -14, -10, -10, 10]
Explanation:
(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
 */
public class __241_DifferentWaysToAddParentheses {
    /**
     * My solution:
     * legend: D -> digit, O -> operator
     * recursively try all the possible arrangements. Every time we encounter a D O we have two choices:
     * 1_   D O ->  D O recursiveFoo()
     * 2_   D O ->  recursiveFoo()
     * at the end of the recursion we obtain all the possible arrangement
     */
    /**
     * Second personal solution:
     * At each iteration we can evaluate all the possible picks.
     * eg if we start from 2*3-4*5
     * at the first step we can evaluate all the possible couples obtaining:
     *  6-4*5, 2*-1*5, 2*3-20
     *  the process can be iterated to obtain all the possible arrangementss:
     *  from 6-4*5 -> 2*5 & 6-20 => 10 &-14
     */
    /**
     * Other solution uses divide et impera to split the problem at each operator
     */
    class Solution {
        Set<Character> operatorSet;
        Map<String, List<Integer>> memo;

        public List<Integer> diffWaysToCompute(String input) {
            if (input == null) return null;
            memo = new HashMap();
            operatorSet = new HashSet();
            operatorSet.add('*');
            operatorSet.add('+');
            operatorSet.add('-');

            return compute(input);
        }

        private boolean isOperator(char c) {
            return operatorSet.contains(c);
        }

        private List<Integer> compute(String exp) {
            if (memo.containsKey(exp)) return memo.get(exp);

            List<Integer> ans = new ArrayList();
            if (!(exp.contains("*") || exp.contains("+") || exp.contains("-"))) {
                ans.add(Integer.parseInt(exp));
                memo.put(exp, ans);
                return ans;
            }

            for (int i = 0; i < exp.length(); ++i) {
                if (isOperator(exp.charAt(i))) {
                    List<Integer> leftValList = compute(exp.substring(0, i));
                    char op = exp.charAt(i);
                    List<Integer> rtValList = compute(exp.substring(i + 1, exp.length()));

                    for (int leftVal : leftValList) {
                        for (int rtVal : rtValList) {
                            if (op == '*') ans.add(leftVal * rtVal);
                            if (op == '+') ans.add(leftVal + rtVal);
                            if (op == '-') ans.add(leftVal - rtVal);
                        }
                    }
                }
            }
            memo.put(exp, ans);
            return ans;
        }
    }
}
