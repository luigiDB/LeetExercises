package facebook;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Suppose we have a list of N numbers, and repeat the following operation until we're left with only a single number:
 * Choose any two numbers and replace them with their sum. Moreover, we associate a penalty with each operation equal to
 * the value of the new number, and call the penalty for the entire list as the sum of the penalties of each operation.
 * For example, given the list [1, 2, 3, 4, 5], we could choose 2 and 3 for the first operation, which would transform
 * the list into [1, 5, 4, 5] and incur a penalty of 5. The goal in this problem is to find the worst possible penalty
 * for a given input.
 */
public class SlowSums {

    @Test
    public void a() {
        assertEquals(26, getTotalTime(new int[]{4, 2, 1, 3}));
    }

    int getTotalTime(int[] arr) {
        // Write your code here
        Arrays.sort(arr);

        int ans = arr[arr.length-1] + arr[arr.length-2];
        int penalty = ans;
        for(int i = arr.length-3 ; i>=0 ; i--) {
            ans += arr[i];
            penalty += ans;
        }
        return penalty;
    }
}
