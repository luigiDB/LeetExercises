package utils.subarray;

import org.junit.Assert;
import org.junit.Test;

public class LengthOfTheLargestSubarrayWithContiguousElementsTest {

    @Test
    public void test1() {
        Assert.assertEquals(3, LengthOfTheLargestSubarrayWithContiguousElements.find(new int[]{10, 12, 11}));
    }

    @Test
    public void test2() {
        Assert.assertEquals(4, LengthOfTheLargestSubarrayWithContiguousElements.find(new int[]{14, 12, 13, 11, 20}));
    }

    @Test
    public void test3() {
        Assert.assertEquals(5, LengthOfTheLargestSubarrayWithContiguousElements.find(new int[]{1, 56, 58, 57, 90, 92, 94, 93, 91, 45}));
    }
}