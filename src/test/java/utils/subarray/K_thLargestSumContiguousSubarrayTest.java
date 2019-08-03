package utils.subarray;

import org.junit.Assert;
import org.junit.Test;

public class K_thLargestSumContiguousSubarrayTest {

    @Test
    public void baseTest() {
        Assert.assertEquals(14, K_thLargestSumContiguousSubarray.find(new int[]{20, -5, -1}, 3));
    }

    @Test
    public void simpleTest() {
        Assert.assertEquals(-10, K_thLargestSumContiguousSubarray.find(new int[]{10, -10, 20, -40}, 6));
    }
}