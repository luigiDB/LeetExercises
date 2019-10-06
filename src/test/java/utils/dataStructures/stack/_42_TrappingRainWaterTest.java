package utils.dataStructures.stack;

import org.junit.Assert;
import org.junit.Test;

public class _42_TrappingRainWaterTest {

    @Test
    public void given() {
        Assert.assertEquals(6, _42_TrappingRainWater.waterVolume(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    @Test
    public void simple() {
        Assert.assertEquals(3, _42_TrappingRainWater.waterVolume(new int[]{1, 0, 0, 0, 1}));
    }
}