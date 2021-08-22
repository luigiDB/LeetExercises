package facebook;

import org.junit.Assert;
import org.junit.Test;

/**
 * We keep track of the revenue Facebook makes every day, and we want to know on what days Facebook hits certain
 * revenue milestones. Given an array of the revenue on each day, and an array of milestones Facebook wants to reach,
 * return an array containing the days on which Facebook reached every milestone.
 */
public class RevenueMilestones {

    @Test
    public void a() {
        int[] revenues_1 = {100, 200, 300, 400, 500};
        int[] milestones_1 = {300, 800, 1000, 1400};
        int[] expected_1 = {2, 4, 4, 5};
        int[] output_1 = getMilestoneDays(revenues_1, milestones_1);
        Assert.assertArrayEquals(expected_1, output_1);
    }

    @Test
    public void b() {
        int[] revenues_1 = {100, 200, 300, 400, 500};
        int[] milestones_1 = {300, 800, 1000, 1400};
        int[] expected_1 = {2, 4, 4, 5};
        int[] output_1 = getMilestoneDays(revenues_1, milestones_1);
        Assert.assertArrayEquals(expected_1, output_1);
    }

    @Test
    public void c() {
        int[] revenues_2 = {700, 800, 600, 400, 600, 700};
        int[] milestones_2 = {3100, 2200, 800, 2100, 1000};
        int[] expected_2 = {5, 4, 2, 3, 2};
        int[] output_2 = getMilestoneDays(revenues_2, milestones_2);
        Assert.assertArrayEquals(expected_2, output_2);
    }

    int[] getMilestoneDays(int[] revenues, int[] milestones) {
        // Write your code here
        int[] cumulative = new int[revenues.length];
        cumulative[0] = revenues[0];
        for(int i = 1; i < revenues.length; i++) {
            cumulative[i] = cumulative[i-1] + revenues[i];
        }

        int[] res = new int[milestones.length];
        for(int i = 0; i < milestones.length; i++) {

            int left = 0, right = cumulative.length;
            while(left <= right) {
                int mid = (left + right) / 2;

                if(cumulative[mid] < milestones[i]) {
                    left = mid + 1;
                } else {
                    right = mid -1;
                }
            }
            res[i] = left+1;
        }
        return res;
    }
}
