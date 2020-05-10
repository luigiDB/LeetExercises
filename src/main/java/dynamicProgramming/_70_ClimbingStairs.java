package dynamicProgramming;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * Note: Given n will be a positive integer.
 * Example 1:
 * Input: 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 * Input: 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 */
public class _70_ClimbingStairs {
    /**
     * The recursion is easy to see after writing some example down each N can be created with a number of
     * combinations equals to sum of the previous 2 N. The code can simply evaluate all starting from 3.
     * @param n the stair we want to reach
     * @return the possible way to reach the n-th stair
     */
    int iterativeCount(int n) {
        switch (n) {
            case 1:
                return 1;
            case 2:
                return 2;
        }
        int n_minus_two = 1;
        int n_minus_one = 2;
        int steps = 0;
        for (int i = 3; i <= n; i++) {
            steps = n_minus_one + n_minus_two;
            n_minus_two = n_minus_one;
            n_minus_one = steps;
        }
        return steps;
    }

    public int recursiveCount(int n) {
        return recursiveStep(n);
    }

    private int recursiveStep(int i) {
        switch (i) {
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                return recursiveStep(i - 1) + recursiveStep(i - 2);
        }
    }

}
