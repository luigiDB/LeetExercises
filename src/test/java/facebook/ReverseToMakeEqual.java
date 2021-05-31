package facebook;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Given two arrays A and B of length N, determine if there is a way to make A equal to B by reversing any subarrays
 * from array B any number of times.
 */
public class ReverseToMakeEqual {
    @Test
    public void a() {
        assertTrue(areTheyEqual(new int[]{1,2,3,4}, new int[]{1,4,3,2}));
        assertFalse(areTheyEqual(new int[]{1,2,3,4}, new int[]{1,4,3,3}));
    }

    boolean areTheyEqual(int[] array_a, int[] array_b) {
        // Write your code here
        Map<Integer, Integer> a = new HashMap<>();
        for(int i = 0; i<array_a.length; i++ ) {
            a.put(array_a[i], a.getOrDefault(array_a[i], 0) + 1);
        }

        Map<Integer, Integer> b = new HashMap<>();
        for(int i = 0; i<array_b.length; i++ ) {
            b.put(array_b[i], b.getOrDefault(array_b[i], 0) + 1);
        }

        for(int key: a.keySet()) {
            int counter_a = a.get(key);
            if(counter_a != b.getOrDefault(key, 0))
                return false;
        }

        return true;
    }
}
