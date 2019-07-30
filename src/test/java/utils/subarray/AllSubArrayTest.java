package utils.subarray;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class AllSubArrayTest {

    @Test
    public void base() {
        int[] arr = new int[]{1};
        AllSubArray allSubArray = new AllSubArray(arr);
        List<int[]> generate = allSubArray.generate();
        List<int[]> expected = Arrays.asList(new int[]{1});
    }

    @Test
    public void four() {
        int[] arr = new int[]{1, 2, 3, 4};
        AllSubArray allSubArray = new AllSubArray(arr);
        List<int[]> generate = allSubArray.generate();
        List<int[]> expected = Arrays.asList(
                new int[]{1},
                new int[]{1, 2},
                new int[]{1, 2, 3},
                new int[]{1, 2, 3, 4},
                new int[]{2},
                new int[]{2, 3},
                new int[]{2, 3, 4},
                new int[]{3},
                new int[]{3, 4},
                new int[]{4}
        );
    }
}