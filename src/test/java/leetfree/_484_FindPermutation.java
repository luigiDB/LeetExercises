package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/*
By now, you are given a secret signature consisting of character 'D' and 'I'. 'D' represents a decreasing
relationship between two numbers, 'I' represents an increasing relationship between two numbers. And our secret
signature was constructed by a special integer array, which contains uniquely all the different number from 1 to
n (n is the length of the secret signature plus 1). For example, the secret signature "DI" can be constructed by
array [2,1,3] or [3,1,2], but won't be constructed by array [3,2,4] or [2,1,3,4], which are both illegal
constructing special string that can't represent the "DI" secret signature.
On the other hand, now your job is to find the lexicographically smallest permutation of [1, 2, ... n] could refer to
the given secret signature in the input.
Example 1:
Input: "I"
Output: [1,2]
Explanation: [1,2] is the only legal initial spectial string can construct secret signature "I", where the number 1
and 2 construct an increasing relationship.
Example 2:
Input: "DI"
Output: [2,1,3]
Explanation: Both [2,1,3] and [3,1,2] can construct the secret signature "DI",
but since we want to find the one with the smallest lexicographical permutation, you need to output [2,1,3]
Note:
The input string will only contain the character 'D' and 'I'.
The length of input string is a positive integer and will not exceed 10,000
 */
public class _484_FindPermutation {
    @Test
    public void tests() {
        Assert.assertArrayEquals(new int[]{1, 2}, codePermutation("I"));
        Assert.assertArrayEquals(new int[]{2, 1, 3}, codePermutation("DI"));
        Assert.assertArrayEquals(new int[]{1, 2, 5, 4, 3, 6, 7}, codePermutation("IIDDII"));
    }

    /**
     * The smallest lexicographically sequence is the one that at each step chose always the smallest possible number.
     * We can easily notice that for each:
     * I    The best choice for the previous number is always the smallest available number
     * D    The best choice for the previous number is the Jst number with J is the number of consecutive D + 1. And in
     * general we can observe that for subsequent D the best sequence is the reversed sequence of the smallest
     * numbers available.
     * The above can be easily obtained with the use of a stack.
     */
    private int[] codePermutation(String permutationCode) {
        int[] res = new int[permutationCode.length() + 1];
        Stack<Integer> stack = new Stack<>();

        int positionOnResult = 0;
        for (int i = 1; i <= permutationCode.length(); i++) {
            if (permutationCode.charAt(i - 1) == 'I') {
                stack.push(i);
                while (!stack.isEmpty()) {
                    res[positionOnResult++] = stack.pop();
                }
            } else {
                stack.push(i);
            }
        }
        stack.push(permutationCode.length() + 1);
        while (!stack.isEmpty()) {
            res[positionOnResult++] = stack.pop();
        }
        return res;
    }
}
