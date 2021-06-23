package leetfree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * You have planned some train traveling one year in advance. The days of the year in which you will travel are given
 * as an integer array days. Each day is an integer from 1 to 365.
 * Train tickets are sold in three different ways:
 * a 1-day pass is sold for costs[0] dollars,
 * a 7-day pass is sold for costs[1] dollars, and
 * a 30-day pass is sold for costs[2] dollars.
 * The passes allow that many days of consecutive travel.
 * For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
 * Return the minimum number of dollars you need to travel every day in the given list of days.
 */
public class _983_MinimumCostForTickets {

    @Test
    public void a() {
        assertEquals(11, mincostTickets(new int[]{1, 4, 6, 7, 8, 20}, new int[]{2, 7, 15}));
    }

    @Test
    public void b() {
        assertEquals(17, mincostTickets(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31}, new int[]{2, 7, 15}));
    }

    int minCost = Integer.MAX_VALUE;

    public int mincostTickets(int[] days, int[] costs) {
        search(days, costs, 0, 0);
        return minCost;
    }

    private void search(int[] days, int[] costs, int index, int costSoFar) {

        if (index >= days.length) {
            minCost = Math.min(minCost, costSoFar);
            return;
        }

        search(days, costs, index + 1, costSoFar + costs[0]);

        int currentIndex = index;

        while (currentIndex < days.length && days[currentIndex] < days[index] + 7)
            currentIndex++;
        search(days, costs, currentIndex, costSoFar + costs[1]);

        while (currentIndex < days.length && days[currentIndex] < days[index] + 30)
            currentIndex++;
        search(days, costs, currentIndex, costSoFar + costs[2]);
    }
}
