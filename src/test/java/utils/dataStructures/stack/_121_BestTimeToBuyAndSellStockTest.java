package utils.dataStructures.stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class _121_BestTimeToBuyAndSellStockTest {

    private _121_BestTimeToBuyAndSellStock stockBuyer;

    @Before
    public void setUp() throws Exception {
        stockBuyer = new _121_BestTimeToBuyAndSellStock();
    }

    @Test
    public void given1() {
        Assert.assertEquals(5, stockBuyer.maxGain(new int[]{7, 1, 5, 3, 6, 4}));
    }

    @Test
    public void given2() {
        Assert.assertEquals(0, stockBuyer.maxGain(new int[]{7, 6, 4, 3, 1}));
    }
}