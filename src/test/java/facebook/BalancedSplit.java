package facebook;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Given an array of integers (which may include repeated integers), determine if there's a way to split the array into
 * two subsequences A and B such that the sum of the integers in both arrays is the same, and all of the integers in A
 * are strictly smaller than all of the integers in B.
 * Note: Strictly smaller denotes that every integer in A must be less than, and not equal to, every integer in B.
 */
public class BalancedSplit {

    @Test
    public void a() {
        assertTrue(balancedSplitExists(new int[]{2, 1, 2, 5}));
        assertTrue(balancedSplitExists(new int[]{2, 3, 3, 1, 5, 4}));
    }

    boolean balancedSplitExists(int[] arr) {
        // Write your code here
        Arrays.sort(arr);

        int[] sum = new int[arr.length];
        Map<Integer, Integer> s = new HashMap<>();
        sum[0] = arr[0];
        s.put(sum[0], 0);
        for (int i = 1; i < arr.length; i++) {
            sum[i] = (sum[i - 1] + arr[i]);
            s.put(sum[i], i);
        }

        if (s.containsKey(sum[sum.length - 1] / 2)) {
            int index = s.get(sum[sum.length - 1] / 2);
            return arr[index] < arr[index + 1];
        } else
            return false;
    }
}
