package utils.subarray;

import org.junit.Assert;
import org.junit.Test;

public class LargestSumContiguousIncreasingSubarrayTest {

    @Test
    public void test1() {
        Assert.assertEquals(12, LargestSumContiguousIncreasingSubarray.find(new int[]{2, 1, 4, 7, 3, 6}));
    }

    @Test
    public void test2() {
        Assert.assertEquals(38, LargestSumContiguousIncreasingSubarray.find(new int[]{38, 7, 8, 10, 12}));
    }

    @Test
    public void testWithNegativeNumbers() {
        Assert.assertEquals(10, LargestSumContiguousIncreasingSubarray.find(new int[]{2, -3, 6, 7, 5}));
    }
}