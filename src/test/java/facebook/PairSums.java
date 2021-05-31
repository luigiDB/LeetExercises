package facebook;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Given a list of n integers arr[0..(n-1)], determine the number of different pairs of elements within it which sum
 * to k.
 * If an integer appears in the list multiple times, each copy is considered to be different; that is, two pairs are
 * considered different if one pair includes at least one array index which the other doesn't, even if they include
 * the same values.
 */
public class PairSums {

    @Test
    public void a() {
        assertEquals(2, numberOfWays(new int[]{1,2,3,4,3}, 6));
    }

    int numberOfWays(int[] arr, int k) {
        // Write your code here
        Map<Integer, Integer> map = new HashMap<>();
        int counter = 0;
        for (int i : arr) {
            if (map.containsKey(k - i))
                counter += map.get(k - i);
            map.computeIfAbsent(i, x -> 0);
            map.compute(i, (key, v) -> v + 1);
        }
        return counter;
    }
}
