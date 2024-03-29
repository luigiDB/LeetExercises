package leetfree;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
        assertEquals(11, dpSolution(new int[]{1, 4, 6, 7, 8, 20}, new int[]{2, 7, 15}));
    }

    @Test
    public void b() {
        assertEquals(17, mincostTickets(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31}, new int[]{2, 7, 15}));
        assertEquals(17, dpSolution(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31}, new int[]{2, 7, 15}));
    }

    Map<Integer, Integer> memory;

    public int mincostTickets(int[] days, int[] costs) {
        memory = new HashMap<>();
        return search(days, costs, 0);
    }

    private int search(int[] days, int[] costs, int index) {

        if (memory.containsKey(index))
            return memory.get(index);

        if (index >= days.length) {
            return 0;
        }

        int dayCost = costs[0] + search(days, costs, index + 1);

        int currentIndex = index;

        while (currentIndex < days.length && days[currentIndex] < days[index] + 7)
            currentIndex++;
        int weekCost = costs[1] + search(days, costs, currentIndex);

        while (currentIndex < days.length && days[currentIndex] < days[index] + 30)
            currentIndex++;
        int monthCost = costs[2] + search(days, costs, currentIndex);

        int minForToday = Math.min(dayCost, Math.min(weekCost, monthCost));
        memory.put(index, minForToday);
        return minForToday;
    }

    public int dpSolution(int[] days, int[] costs) {
        Set<Integer> tripDays = new HashSet<>();
        for (int i : days)
            tripDays.add(i);

        int[] dp = new int[367];

        for (int i = 1; i < 366; i++) {
            dp[i] = dp[i - 1];
            if (tripDays.contains(i)) {
                dp[i] = Math.min(
                        dp[i - 1] + costs[0],
                        Math.min(
                                dp[Math.max(0, i - 7)] + costs[1],
                                dp[Math.max(0, i - 30)] + costs[2]
                        )
                );
            }
        }
        return dp[365];
    }
}
