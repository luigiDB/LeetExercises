package dynamicProgramming;

/**
 * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
 * Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the
 * floor, and you can either start from the step with index 0, or the step with index 1.
 * Note:
 * cost will have a length in the range [2, 1000].
 * Every cost[i] will be an integer in the range [0, 999].
 */
public class _746_MinCostClimbingStairs {

    public int minCost(int[] stairs) {
        int cost = 0;

        for (int i = stairs.length; i > 1; ) {
            i = (stairs[i - 2] <= stairs[i - 1]) ? i - 2 : i - 1;
            cost += stairs[i];
        }

        return cost;
    }
}
