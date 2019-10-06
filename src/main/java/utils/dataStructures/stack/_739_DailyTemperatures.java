package utils.dataStructures.stack;

import java.util.Stack;

/**
 * Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you
 * would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.
 * Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range
 * [30, 100].
 */
public class _739_DailyTemperatures {

    public static int[] waitForHotterTemperature(int[] temperature) {
        int[] waiting = new int[temperature.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < temperature.length; i++) {
            while (!stack.isEmpty()
                    && temperature[stack.peek()] < temperature[i]) {
                Integer colderDay = stack.pop();
                waiting[colderDay] = i - colderDay;
            }
            stack.push(i);
        }

        return waiting;
    }
}
