package leetfree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * You are given an integer num. You can swap two digits at most once to get the maximum valued number.
 * Return the maximum valued number you can get.
 */
public class _670_MaximumSwap {

    @Test
    public void a() {
        assertEquals(7236, maximumSwap(2736));
    }

    @Test
    public void b() {
        assertEquals(87236, maximumSwap(82736));
    }

    public int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray();

        int[] lastIndex = new int[10];
        for (int i = 0; i < digits.length; i++) {
            lastIndex[digits[i] - '0'] = i;
        }

        for (int i = 0; i < digits.length; i++) {
            for (int k = 9; k > digits[i] - '0'; k--) {
                if (lastIndex[k] > i) {
                    char tmp = digits[i];
                    digits[i] = digits[lastIndex[k]];
                    digits[lastIndex[k]] = tmp;
                    return Integer.parseInt(new String(digits));
                }
            }
        }

        return num;
    }
}
