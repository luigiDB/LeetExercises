package utils.subarray;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LargestSumContiguousSubarrayTest {

    @Test
    public void anExample() {
        Assert.assertEquals(7, LargestSumContiguousSubarray.maximumSumSubarray(new int[]{-2, -3, 4, -1, -2, 1, 5, -3}));
    }
}