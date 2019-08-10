package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is
 * no limit on how many times a digit can be reused.
 * <p>
 * You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9"
 * are all invalid.
 * <p>
 * Example 1:
 * <p>
 * Input: "19:34"
 * Output: "19:39"
 * Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  It is
 * not 19:33, because this occurs 23 hours and 59 minutes later.
 * Example 2:
 * <p>
 * Input: "23:59"
 * Output: "22:22"
 * Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed that the returned
 * time is next day's time since it is smaller than the input time numerically.
 */
public class _681_NextClosestTime {

    /**
     * Two posible solutions:
     * 1_ move the clock one minute at the time and check if valid
     *  TIP
     *  roll minutes with %60 and hours with %24. After that each number can be spit MM%60 units and MM/60 tents.
     * 2_ start from the minutes determine the first number that can roll and fallback updates to the right (simply
     * pull the smallest possible cipher)
     */

    @Test
    public void test1() {
        Assert.assertEquals("19:39", nextClosestTime("19:34"));
    }

    @Test
    public void test2() {
        Assert.assertEquals("22:22", nextClosestTime("23:59"));
    }

    private String nextClosestTime(String time) {
        return null;
    }
}
