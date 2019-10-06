package utils.dataStructures.stack;

import org.junit.Assert;
import org.junit.Test;

public class _739_DailyTemperaturesTest {

    @Test
    public void given() {
        Assert.assertArrayEquals(new int[]{1, 1, 4, 2, 1, 1, 0, 0}, _739_DailyTemperatures.waitForHotterTemperature(new int[]{73, 74, 75, 71, 69, 72, 76, 73}));
    }
}