package utils.subarray;

import org.junit.Assert;
import org.junit.Test;

public class LargestSubArrayWithGCDOneTest {

    @Test
    public void validExample() {
        Assert.assertEquals(3, LargestSubArrayWithGCDOne.find(new int[]{1, 3, 5}));
    }

    @Test
    public void negativeTest() {
        Assert.assertEquals(-1, LargestSubArrayWithGCDOne.find(new int[]{2, 4, 6}));
    }

    @Test
    public void complexTest() {
        Assert.assertEquals(5, LargestSubArrayWithGCDOne.find(new int[]{3, 1, 3, 5, 7}));
    }
}