package dynamicProgramming;

/**
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of
 * painting each house with a certain color is different. You have to paint all the houses such that no two adjacent
 * houses have the same color.
 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example,
 * costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color
 * green, and so on... Find the minimum cost to paint all houses.
 * Note:
 * All costs are positive integers.
 * Example:
 * Input: [[17,2,17],[16,16,5],[14,3,19]]
 * Output: 10
 * Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
 * Minimum cost: 2 + 5 + 3 = 10.
 */
public class _256_PaintHouse {

    /**
     * The idea is to evaluate all possible costs at each step following each route. The solution is simply the minimum
     * element in the last house
     *
     * @param costs array of costs
     * @return minimum costs
     */
    public int minimumCost(int[][] costs) {
        int minRed = 0;
        int minBlue = 0;
        int minGreen = 0;
        for (int i = 0; i < costs.length; i++) {
            int red = Math.min(minBlue, minGreen) + costs[i][0];
            int blue = Math.min(minRed, minGreen) + costs[i][1];
            int green = Math.min(minRed, minBlue) + costs[i][2];
            minRed = red;
            minBlue = blue;
            minGreen = green;
        }
        return Math.min(Math.min(minRed, minBlue), minGreen);
    }
}
