package utils.numeric;

import org.junit.Assert;
import org.junit.Test;

public class GCDTest {
    @Test
    public void zeroTest() {
        Assert.assertEquals(0, GCD.find(3, 0));
    }

    @Test
    public void equalsTest() {
        Assert.assertEquals(3, GCD.find(3, 3));
    }

    @Test
    public void normalTest() {
        Assert.assertEquals(5, GCD.find(5, 15));
    }
}